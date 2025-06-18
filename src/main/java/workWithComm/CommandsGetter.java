package workWithComm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * {@code CommandsGetter} — это обёртка для запуска интерактивного режима работы с пользователем.
 * Он принимает команды с консоли и передаёт их в {@link CommandManager} для обработки.
 *
 * <p>Подходит для использования в клиентской части standalone-приложений.</p>
 *
 * <p>Использует {@code Scanner} для чтения ввода из консоли.</p>
 *
 * @author Камиль
 */
public class CommandsGetter {
    private CommandManager commandManager;

    /**
     * Конструктор. Создаёт и инициализирует {@link CommandManager}.
     *
     * @throws Exception если произошла ошибка при инициализации (теоретически)
     */
    public CommandsGetter() throws Exception {
        this.commandManager = new CommandManager();
    }


    /**
     * Запускает интерактивный режим работы.
     * Бесконечный цикл, в котором пользователь может вводить команды через консоль.
     * Ввод каждой строки отправляется в {@code CommandManager#execute(String)}.
     */
    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите команду:");

            while (true) {
                System.out.print("-- ");
                String input = scanner.nextLine().trim();

                if (!input.isEmpty()) {
                    commandManager.execute(input);
                }
            }
        }
    }
}
