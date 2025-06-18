package commands;

import moviesClass.*;
import workWithComm.CollectionManager;
import workWithComm.CommandManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Команда {@code execute_script} — исполняет команды из указанного текстового файла.
 *
 * <p>Поддерживает выполнение как простых, так и сложных команд ({@code add}, {@code add_if_min},
 * {@code add_if_max}, {@code update_id}), включая чтение аргументов из файла без интерактивного ввода.</p>
 *
 * <p>Реализована защита от рекурсивных вызовов скриптов (рекурсия блокируется при повторном вызове
 * одного и того же файла).</p>
 *
 * <p>Скрипт должен содержать по одной команде на строку, а для сложных команд — последовательно идущие строки
 * с данными объекта.</p>
 *
 * @author Камиль
 * @see CollectionManager
 * @see CommandManager
 */
public class ExecuteScriptCommand implements Command {

    /** Набор путей, чтобы отслеживать вложенные скрипты и предотвращать рекурсию */
    private static final Set<String> SCRIPT_STACK = new HashSet<>();

    /** Набор команд, требующих чтения дополнительных аргументов из скрипта */
    private static final Set<String> COMPLEX = Set.of("add", "add_if_min", "add_if_max", "update_id");

    /**
     * Возвращает имя команды.
     *
     * @return "execute_script"
     */
    @Override
    public String getName() {
        return "execute_script";
    }

    /**
     * Возвращает описание команды.
     *
     * @return ": выполнить команды из файла (теперь с поддержкой add/… без интерактива)"
     */
    @Override
    public String getDescription() {
        return ": выполнить команды из файла (теперь с поддержкой add/… без интерактива)";
    }

    /**
     * Выполняет команды, считанные из файла. Если команда требует аргументов (например, {@code add}),
     * дополнительные строки используются для создания объектов.
     *
     * @param args первый аргумент — путь к скрипту
     * @param cm менеджер коллекции
     * @param mgr менеджер команд
     */
    @Override
    public void run(String[] args, CollectionManager cm, CommandManager mgr) {
        if (args.length == 0 || args[0].isBlank()) {
            System.err.println("execute_script: укажи путь до файла");
            return;
        }
        String path = args[0];
        if (SCRIPT_STACK.contains(path)) {
            System.err.println("Рекурсия скриптов: " + path);
            return;
        }

        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            System.err.println("Файл не найден: " + path);
            return;
        }

        SCRIPT_STACK.add(path);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String raw;
            while ((raw = reader.readLine()) != null) {
                String line = raw.trim();
                if (line.isEmpty()) continue;
                System.out.println("> " + line);
                String[] parts = line.split("\\s+", 2);
                String cmd = parts[0];
                if (COMPLEX.contains(cmd)) {
                    processComplex(cmd, parts, reader, cm, mgr);
                } else {
                    mgr.execute(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения скрипта: " + e.getMessage());
        } finally {
            SCRIPT_STACK.remove(path);
        }
    }

    /**
     * Обрабатывает сложные команды, которым нужны дополнительные данные из скрипта.
     */
    private void processComplex(String cmd, String[] parts,
                                BufferedReader reader,
                                CollectionManager cm,
                                CommandManager mgr) throws IOException {
        switch (cmd) {
            case "add" -> {
                Movie m = readMovieFromScript(reader);
                if (cm.addElement(m)) {
                    System.out.println("Добавлен фильм: " + m);
                } else {
                    System.err.println("Не добавлен (ID занят): " + m.getId());
                }
            }
            case "add_if_min" -> {
                Movie m1 = readMovieFromScript(reader);
                Optional<Movie> min = cm.getCollection().stream().min(Movie::compareTo);
                if (min.isEmpty() || m1.compareTo(min.get()) < 0) {
                    cm.addElement(m1);
                    System.out.println("Добавлен по условию min: " + m1);
                } else {
                    System.out.println("Не добавлен (не меньше min)");
                }
            }
            case "add_if_max" -> {
                Movie m2 = readMovieFromScript(reader);
                Optional<Movie> max = cm.getCollection().stream().max(Movie::compareTo);
                if (max.isEmpty() || m2.compareTo(max.get()) > 0) {
                    cm.addElement(m2);
                    System.out.println("Добавлен по условию max: " + m2);
                } else {
                    System.out.println("Не добавлен (не больше max)");
                }
            }
            case "update_id" -> {
                if (parts.length < 2 || parts[1].isBlank()) {
                    System.err.println("update_id: не указан ID");
                    return;
                }
                int id;
                try {
                    id = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    System.err.println("update_id: ID должен быть числом");
                    return;
                }
                cm.removeById(id);
                Movie m3 = readMovieFromScriptWithId(reader, id);
                cm.addElement(m3);
                System.out.println("Обновлён фильм ID=" + id + ": " + m3);
            }
        }
    }

    /**
     * Читает объект {@link Movie} из скрипта, построчно.
     *
     * @param r поток чтения
     * @return созданный объект Movie
     * @throws IOException если данные неполные или некорректны
     */
    private Movie readMovieFromScript(BufferedReader r) throws IOException {
        String name = readNonEmpty(r);
        boolean hasX = readYesNo(r);
        Integer x = null;
        if (hasX) x = Integer.parseInt(readNonEmpty(r));
        Float y = Float.parseFloat(readNonEmpty(r));
        Coordinates coords = new Coordinates(x, y);
        Long oscars = Long.parseLong(readNonEmpty(r));
        int palms = Integer.parseInt(readNonEmpty(r));
        Float box = Float.parseFloat(readNonEmpty(r));
        MovieGenre genre = MovieGenre.valueOf(readNonEmpty(r));
        boolean hasOp = readYesNo(r);
        Person op = null;
        if (hasOp) {
            String personName = readNonEmpty(r);
            boolean hasPid = readYesNo(r);
            String pid = null;
            if (hasPid) pid = readNonEmpty(r);
            Color hair = Color.valueOf(readNonEmpty(r));
            Country nat = Country.valueOf(readNonEmpty(r));
            op = new Person(personName, pid, hair, nat);
        }
        return new Movie(name, coords, oscars, palms, box, genre, op);
    }

    /**
     * То же, что {@link #readMovieFromScript(BufferedReader)}, но с установкой заданного ID.
     */
    private Movie readMovieFromScriptWithId(BufferedReader r, int id) throws IOException {
        Movie m = readMovieFromScript(r);
        try {
            java.lang.reflect.Field f = Movie.class.getDeclaredField("id");
            f.setAccessible(true);
            f.set(m, id);
        } catch (Exception ignored) {
        }
        return m;
    }

    /**
     * Читает строку, пропуская пустые.
     *
     * @param r поток ввода
     * @return непустая строка
     * @throws IOException если данные отсутствуют
     */
    private String readNonEmpty(BufferedReader r) throws IOException {
        String line;
        while ((line = r.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) return line;
        }
        throw new IOException("Недостаточно данных в скрипте");
    }

    /**
     * Читает "yes" или "no" из скрипта, возвращая булево значение.
     *
     * @param r поток ввода
     * @return true, если "yes", false, если "no"
     * @throws IOException если получено что-то иное
     */
    private boolean readYesNo(BufferedReader r) throws IOException {
        String line = readNonEmpty(r);
        if (line.equalsIgnoreCase("yes")) return true;
        if (line.equalsIgnoreCase("no")) return false;
        throw new IOException("Ожидается yes или no, получено: " + line);
    }
}
