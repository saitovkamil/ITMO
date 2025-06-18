package jsonFiles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import moviesClass.Movie;

import java.io.*;
import java.util.LinkedList;

/**
 * Утилитный класс {@code MovieJsonParser} для сериализации и десериализации коллекции {@link Movie} в JSON-файл.
 *
 * <p>Использует библиотеку Jackson с поддержкой {@code java.time} и настройками
 * для форматирования вывода.</p>
 *
 * <p>Предназначен для чтения и записи коллекции фильмов в формате JSON.
 * Все методы статичны, экземпляр класса создавать не требуется.</p>
 *
 * @author Камиль
 * @see Movie
 */
public final class MovieJsonParser {

    /**
     * Настроенный Jackson-маршаллизатор с поддержкой даты/времени и форматированного вывода.
     */
    private static final ObjectMapper mapper = createMapper();

    /**
     * Приватный конструктор — чтобы нельзя было создать экземпляр утилитного класса.
     */
    private MovieJsonParser() { }

    /**
     * Создаёт и настраивает {@link ObjectMapper} с нужными модулями и параметрами.
     *
     * @return настроенный {@code ObjectMapper}
     */
    private static ObjectMapper createMapper() {
        ObjectMapper m = new ObjectMapper();
        m.registerModule(new JavaTimeModule()); // поддержка LocalDateTime
        m.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        m.enable(SerializationFeature.INDENT_OUTPUT);
        return m;
    }

    /**
     * Десериализует коллекцию фильмов из JSON-файла.
     *
     * @param filePath путь к JSON-файлу
     * @return {@code LinkedList<Movie>} — прочитанная коллекция
     * @throws IOException если файл не найден, повреждён или произошла ошибка чтения
     */
    public static LinkedList<Movie> parseFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            return new LinkedList<>();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return mapper.readValue(reader, new TypeReference<LinkedList<Movie>>() {});
        }
    }

    /**
     * Сохраняет коллекцию фильмов в указанный JSON-файл.
     *
     * @param filePath путь к файлу
     * @param movies коллекция фильмов, которую нужно сохранить
     * @throws IOException если произошла ошибка при записи
     */
    public static void writeToFile(String filePath, LinkedList<Movie> movies) throws IOException {
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            mapper.writeValue(writer, movies);
        }
    }
}
