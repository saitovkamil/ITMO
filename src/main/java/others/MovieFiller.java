package others;

import moviesClass.*;
import workWithComm.CollectionManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Утилитный класс {@code MovieFiller} — предназначен для интерактивного создания объектов {@link Movie}
 * и, при необходимости, связанных с ним объектов: {@link Coordinates}, {@link Person}, {@link Color}, {@link Country}, {@link MovieGenre}.
 *
 * <p>Класс используется на клиентской стороне в командах {@code add}, {@code add_if_min}, {@code add_if_max},
 * а также при {@code update_id}.</p>
 *
 * <p>Поля запрашиваются у пользователя через консоль с валидацией на этапе ввода.</p>
 *
 * @author Камиль
 */
public class MovieFiller {

    /** Встроенный {@link Scanner} для чтения пользовательского ввода из консоли. */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Интерактивно собирает объект {@link Movie}, включая координаты и, опционально, данные об операторе.
     *
     * @return готовый объект {@link Movie}
     */
    public static Movie fillMovieManually(CollectionManager collectionManager) {
        System.out.println("=== Ввод данных о фильме ===");

        String name = input("Название фильма: ",
                s -> !s.isBlank() ? s : throwEx("Название не может быть пустым"));

        boolean hasX = input("Добавить координату X? (yes/no): ",
                s -> {
                    if (s.equalsIgnoreCase("yes")) return true;
                    if (s.equalsIgnoreCase("no")) return false;
                    throw new IllegalArgumentException("ответь yes или no");
                });

        Integer x = null;
        if (hasX) {
            x = input("  Введите Coord X (int): ", Integer::parseInt);
        }

        Float y = input("Coord Y (float): ",
                s -> {
                    if (s.isBlank()) throw new IllegalArgumentException("Y обязателен");
                    return Float.parseFloat(s);
                });
        Coordinates coords = new Coordinates(x, y);

        Long oscarsCount = input("Количество Оскаров (>0): ",
                s -> {
                    long v = Long.parseLong(s);
                    if (v <= 0) throw new IllegalArgumentException("должно быть >0");
                    return v;
                });

        int goldenPalmCount = input("Золотые пальмы (>0): ",
                s -> {
                    int v = Integer.parseInt(s);
                    if (v <= 0) throw new IllegalArgumentException("должно быть >0");
                    return v;
                });

        Float usaBoxOffice = input("BoxOffice (>0): ",
                s -> {
                    float v = Float.parseFloat(s);
                    if (v <= 0) throw new IllegalArgumentException("должно быть >0");
                    return v;
                });

        MovieGenre genre = input("Жанр " + Arrays.toString(MovieGenre.values()) + ": ",
                MovieGenre::valueOf);

        System.out.println("=== Данные об операторе ===");
        boolean hasOperator = input("Добавить оператора? (yes/no): ",
                s -> {
                    if (s.equalsIgnoreCase("yes")) return true;
                    if (s.equalsIgnoreCase("no")) return false;
                    throw new IllegalArgumentException("ответь yes или no");
                });

        Person operator = null;
        if (hasOperator) {
            String personName = input("  Имя оператора: ",
                    s -> !s.isBlank() ? s : throwEx("не может быть пустым"));

            String passportID = null;
            while (true) {
                String answer = input("  PassportID (≤43 символов) [yes/no]: ",
                        s -> {
                            if (s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("no")) return s.toLowerCase();
                            throw new IllegalArgumentException("ответь yes или no");
                        });

                if (answer.equals("no")) break;

                while (true) {
                    System.out.print("Введите PassportID: ");
                    String p = scanner.nextLine().trim();

                    if (p.isBlank()) {
                        System.out.println("Ошибка: не может быть пустым");
                        continue;
                    }

                    if (p.length() > 43) {
                        System.out.println("Ошибка: макс 43 символа");
                        continue;
                    }

                    if (collectionManager.checkPassword(p)) {
                        System.out.println("Ошибка: Passport_id должно быть уникальным");
                        continue;
                    }

                    passportID = p;
                    break;
                }

                break;
            }




            Color hairColor = input("  HairColor " + Arrays.toString(Color.values()) + ": ",
                    Color::valueOf);

            Country nationality = input("  Nationality " + Arrays.toString(Country.values()) + ": ",
                    Country::valueOf);

            operator = new Person(personName, passportID, hairColor, nationality);
        }

        return new Movie(name, coords, oscarsCount, goldenPalmCount, usaBoxOffice, genre, operator);
    }

    /**
     * Обобщённый метод чтения пользовательского ввода с обработкой ошибок и валидацией.
     *
     * @param prompt сообщение для пользователя
     * @param parser функция разбора строки в нужный тип
     * @param <T> возвращаемый тип
     * @return результат парсинга
     */
    private static <T> T input(String prompt, Function<String, T> parser) {
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
     * Генерирует исключение с сообщением (используется в лямбдах).
     *
     * @param msg сообщение ошибки
     * @return никогда не возвращает значение, всегда выбрасывает исключение
     * @param <T> тип возвращаемого значения
     */
    private static <T> T throwEx(String msg) {
        throw new IllegalArgumentException(msg);
    }

}
