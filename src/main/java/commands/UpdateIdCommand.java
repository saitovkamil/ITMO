package commands;

import moviesClass.*;
import others.MovieFiller;
import workWithComm.CollectionManager;
import workWithComm.CommandManager;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Команда {@code update_id} — обновляет поля существующего объекта {@link Movie} в коллекции по его {@code ID}.
 *
 * <p>Пользователь пошагово вводит новые значения. Поля можно оставить без изменений, просто нажав Enter.
 * При необходимости оператор фильма (объект {@link Person}) также может быть обновлён вручную.</p>
 *
 * <p>Если элемент с указанным ID не найден — выводится соответствующее сообщение.</p>
 *
 * @author Камиль
 * @see Movie
 * @see CollectionManager
 */
public class UpdateIdCommand implements Command {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Возвращает имя команды.
     *
     * @return "update_id"
     */
    @Override
    public String getName() {
        return "update_id";
    }

    /**
     * Возвращает описание команды.
     *
     * @return строка описания ": обновить фильм по ID"
     */
    @Override
    public String getDescription() {
        return ": обновить фильм по ID";
    }

    /**
     * Выполняет команду: обновляет поля объекта {@link Movie} с указанным ID.
     *
     * @param args массив аргументов, где {@code args[0]} — ID объекта для обновления
     * @param cm менеджер коллекции
     * @param mgr менеджер команд (не используется)
     */
    @Override
    public void run(String[] args, CollectionManager cm, CommandManager mgr) {
        if (args.length < 1) {
            System.out.println("update_id: укажи ID");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("ID должен быть числом");
            return;
        }

        Movie movie = cm.getById(id);
        if (movie == null) {
            System.out.println("Нет фильма с ID=" + id);
            return;
        }

        System.out.println("=== Обновление фильма ID=" + id + " ===");


        String name = readField("Название [" + movie.getName() + "]: ",
                s -> s.isBlank() ? movie.getName() : (!s.isBlank() ? s : throwEx("не может быть пустым")));
        movie.setName(name);

        Integer x = readField("Coord X [" + movie.getCoordinates().getX() + "]: ",
                s -> s.isBlank() ? movie.getCoordinates().getX() : Integer.parseInt(s));
        Float y = readField("Coord Y [" + movie.getCoordinates().getY() + "]: ",
                s -> s.isBlank() ? movie.getCoordinates().getY() : Float.parseFloat(s));
        movie.setCoordinates(new Coordinates(x, y));

        Long osc = readField("OscarsCount [" + movie.getOscarsCount() + "]: ",
                s -> s.isBlank() ? movie.getOscarsCount() : validateLongPositive(s));
        movie.setOscarsCount(osc);

        Integer palms = readField("GoldenPalmCount [" + movie.getGoldenPalmCount() + "]: ",
                s -> s.isBlank() ? movie.getGoldenPalmCount() : validateIntPositive(s));
        movie.setGoldenPalmCount(palms);

        Float box = readField("USA BoxOffice [" + movie.getUsaBoxOffice() + "]: ",
                s -> s.isBlank() ? movie.getUsaBoxOffice() : validateFloatPositive(s));
        movie.setUsaBoxOffice(box);

        MovieGenre genre = readField("Genre " + Arrays.toString(MovieGenre.values()) + " [" + movie.getGenre() + "]: ",
                s -> s.isBlank() ? movie.getGenre() : MovieGenre.valueOf(s));
        movie.setGenre(genre);

        boolean changeOp = readField("Изменить оператора? (yes/no): ",
                s -> {
                    if (s.equalsIgnoreCase("yes")) return true;
                    if (s.equalsIgnoreCase("no")) return false;
                    throw new IllegalArgumentException("ответь yes или no");
                });

        if (changeOp) {
            System.out.println("=== Ввод нового оператора ===");
            String personName = readField("Имя оператора: ",
                    s -> !s.isBlank() ? s : throwEx("не может быть пустым"));

            boolean hasPid = readField("PassportID? (yes/no): ",
                    s -> {
                        if (s.equalsIgnoreCase("yes")) return true;
                        if (s.equalsIgnoreCase("no")) return false;
                        throw new IllegalArgumentException("ответь yes или no");
                    });

            String pid = null;
            if (hasPid) {
                pid = readField("  Введите PassportID: ",
                        p -> {
                            if (p.isBlank()) throw new IllegalArgumentException("не может быть пустым");
                            if (p.length() > 43) throw new IllegalArgumentException("макс 43");
                            return p;
                        });
            }

            Color hairColor = readField("HairColor " + Arrays.toString(Color.values()) + ": ", Color::valueOf);
            Country nationality = readField("Nationality " + Arrays.toString(Country.values()) + ": ", Country::valueOf);

            movie.setOperator(new Person(personName, pid, hairColor, nationality));
        }

        System.out.println("Фильм обновлён: " + movie);
    }

    /**
     * Вспомогательный метод чтения значения с валидацией.
     */
    private <T> T readField(String prompt, Function<String, T> parser) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return parser.apply(line);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    /**
     * Генерирует исключение {@link IllegalArgumentException} с указанным сообщением.
     */
    private static <T> T throwEx(String msg) {
        throw new IllegalArgumentException(msg);
    }


    private long validateLongPositive(String s) {
        long v = Long.parseLong(s);
        if (v <= 0) throw new IllegalArgumentException(">0");
        return v;
    }

    private int validateIntPositive(String s) {
        int v = Integer.parseInt(s);
        if (v <= 0) throw new IllegalArgumentException(">0");
        return v;
    }

    private float validateFloatPositive(String s) {
        float v = Float.parseFloat(s);
        if (v <= 0) throw new IllegalArgumentException(">0");
        return v;
    }
}
