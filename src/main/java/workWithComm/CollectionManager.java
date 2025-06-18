package workWithComm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import moviesClass.Movie;
import moviesClass.Person;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Менеджер коллекции фильмов.
 */
public class CollectionManager {
    private final Set<Movie> collection = new LinkedHashSet<>();
    private final ObjectMapper mapper;
    private String filePath;
    private final LocalDateTime initializationDate;

    public CollectionManager() {
        this.initializationDate = LocalDateTime.now();
        mapper = new ObjectMapper()
                .findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
    }

    /**
     * Загружает коллекцию из JSON-файла (через Scanner) и обрабатывает дубликаты ID.
     */
    public void loadCollection(String path) {
        this.filePath = path;
        File f = new File(path);
        if (!f.exists()) {
            System.out.println("Файл не найден, стартуем с пустой коллекцией.");
            return;
        }

        StringBuilder json = new StringBuilder();
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                json.append(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: файл не найден при чтении: " + e.getMessage());
            return;
        }

        List<Movie> rawList;
        try {
            rawList = mapper.readValue(
                    json.toString(),
                    new TypeReference<List<Movie>>() {}
            );
        } catch (IOException e) {
            System.err.println("Ошибка парсинга JSON: " + e.getMessage());
            return;
        }

        collection.clear();
        int maxIdInJson = rawList.stream()
                .map(Movie::getId)
                .max(Integer::compareTo)
                .orElse(0);
        Movie.setNextId(maxIdInJson + 1);

        Set<Integer> seen = new HashSet<>();
        for (Movie m : rawList) {
            int id = m.getId();
            if (seen.contains(id)) {
                int newId = Movie.getNextId();
                m.setId(newId);
                Movie.setNextId(newId + 1);
                System.out.println("В JSON дубликат ID=" + id +
                        ", переназначен на " + newId);
                seen.add(newId);
            } else {
                seen.add(id);
            }
            collection.add(m);
        }

        System.out.println("Коллекция загружена из " + path +
                ", элементов: " + collection.size());
    }

    /**
     * Сохраняет коллекцию в JSON-файл (через BufferedWriter).
     */
    public void saveCollection() {
        String path = this.filePath;
        File file = new File(this.filePath);
        if (path == null || path.isBlank()) {
            System.err.println("Путь к файлу не указан. Используйте: save <path>");
            return;
        }
        this.filePath = path;
        if (file.exists() && !file.canWrite()) {
            System.err.println("Ошибка: нет прав на запись в файл " + path);
            return;
        }

        String json;
        try {
            json = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(collection);
        } catch (IOException e) {
            System.err.println("Ошибка сериализации в JSON: " + e.getMessage());
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(json);
            System.out.println("Коллекция сохранена в " + path);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения коллекции: " + e.getMessage());
        }
    }

    /**
     * Добавляет фильм, если он не null, его ID уникален и passportID оператора уникален.
     * Возвращает true, если добавление произошло; false в случае конфликта.
     */
    /**
     * Добавляет фильм, если он не null, его ID уникален и passportID оператора уникален.
     * Возвращает true, если фильм добавлен, false — в противном случае (и печатает ошибку ровно один раз).
     */
    public boolean addElement(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Фильм не может быть null");
        }


        if (collection.stream().anyMatch(m -> m.getId().equals(movie.getId()))) {

            return false;
        }
        return collection.add(movie);
    }

    public boolean checkPassword(String newPassport) {
        if (newPassport != null) {
            boolean exists = collection.stream()
                    .map(Movie::getOperator)
                    .filter(p -> p != null)
                    .map(Person::getPassportID)
                    .filter(pid -> pid != null)
                    .anyMatch(existingPid -> existingPid.equals(newPassport));
            if (exists) {
                return true;
            }
        }
        return false;
    }

    /** Удаляет фильм по ID. */
    public boolean removeById(int id) {
        return collection.removeIf(m -> m.getId() == id);
    }

    /** Очищает всю коллекцию. */
    public void clear() {
        collection.clear();
    }

    /** Ищет фильм по ID. */
    public Movie getById(int id) {
        return collection.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /** Возвращает инфо о коллекции: тип, время инициализации, размер. */
    public String getCollectionInfo() {
        return String.format(
                "Тип коллекции: %s%nДата инициализации: %s%nКоличество элементов: %d",
                collection.getClass().getSimpleName(),
                initializationDate,
                collection.size()
        );
    }

    /** Размер коллекции. */
    public int size() {
        return collection.size();
    }

    /** Доступ к множеству фильмов. */
    public Set<Movie> getCollection() {
        return collection;
    }

    /** Время создания менеджера (для инфо). */
    public LocalDateTime getInitializationDate() {
        return initializationDate;
    }
}
