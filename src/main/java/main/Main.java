package main;

import java.util.Scanner;
import workWithComm.*;

/**
 * Главный класс {@code Main} — точка входа в клиентское приложение.
 *
 * <p>Загружает коллекцию из указанного JSON-файла и запускает интерактивный режим обработки команд.</p>
 *
 * <p>Команды обрабатываются через {@link CommandManager}, взаимодействующий с {@link CollectionManager}.</p>
 *
 * <p>Для запуска требуется указать путь к JSON-файлу в аргументах командной строки.</p>
 *
 * <pre>{@code
 * Пример запуска:
 *   java -cp <classpath> main.Main /path/to/collection.json
 * }</pre>
 *
 * @author Камиль
 */
public class Main {

    /**
     * Точка входа в программу.
     * <ul>
     *     <li>Загружает коллекцию из файла, указанного в аргументах командной строки</li>
     *     <li>Запускает цикл интерактивного ввода команд</li>
     *     <li>Обрабатывает каждую введённую команду через {@link CommandManager}</li>
     * </ul>
     *
     * @param args аргументы командной строки, где {@code args[0]} — путь к JSON-файлу
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Использование: java -cp <classpath> main.Main <path_to_json>");
            System.exit(1);
        }

        String filePath = args[0];
        CommandManager manager = new CommandManager();

        // Загружаем коллекцию из файла
        manager.getCollectionManager().loadCollection(filePath);

        // Интерактивный ввод команд
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите команду (help — список команд):");
        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) break;
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            manager.execute(line);
        }
        scanner.close();
    }
}
