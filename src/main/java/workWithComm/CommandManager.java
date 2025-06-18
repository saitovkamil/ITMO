package workWithComm;

import commands.*;
import java.util.*;

/**
 * {@code CommandManager} управляет всеми доступными командами приложения,
 * их регистрацией, выполнением и историей использования.
 *
 * <p>Каждая команда реализует интерфейс {@link Command} и может быть вызвана
 * через метод {@link #execute(String)} с аргументами в виде строки.</p>
 *
 * <p>Также менеджер обеспечивает доступ к {@link CollectionManager},
 * хранящему коллекцию объектов {@code Movie}.</p>
 *
 * @author Камиль
 */
public class CommandManager {
    private final CollectionManager collectionManager;
    private final Map<String, Command> commands;
    private final Queue<String> commandHistory;
    private static final int HISTORY_SIZE = 12;

    /**
     * Создаёт новый {@code CommandManager}, инициализируя коллекцию и регистрируя команды.
     */
    public CommandManager() {
        this.collectionManager = new CollectionManager();
        this.commands = new LinkedHashMap<>();
        this.commandHistory = new LinkedList<>();
        initializeCommands();
    }

    /**
     * Регистрирует все поддерживаемые команды в порядке логических групп:
     * информационные, модификационные, анализирующие и специальные.
     */
    private void initializeCommands() {
        registerCommand(new HelpCommand());
        registerCommand(new InfoCommand());
        registerCommand(new ShowCommand());
        registerCommand(new AddElementCommand());
        registerCommand(new UpdateIdCommand());
        registerCommand(new RemoveByIdCommand());
        registerCommand(new ClearCommand());
        registerCommand(new SaveCommand());
        registerCommand(new AddIfMinCommand());
        registerCommand(new AddIfMaxCommand());
        registerCommand(new RemoveGreaterCommand());
        registerCommand(new RemoveAllByUsaBoxOfficeCommand());
        registerCommand(new ExecuteScriptCommand());
        registerCommand(new ExitCommand());
        registerCommand(new CountGTCCommand());
        registerCommand(new PrintAscendingCommand());
        registerCommand(new PrintFDCCommand());
        registerCommand(new HistoryCommand());
    }

    /**
     * Регистрирует отдельную команду в менеджере.
     *
     * @param command объект команды
     */
    private void registerCommand(Command command) {
        commands.put(command.getName(), command);
    }

    /**
     * Выполняет команду по входной строке, разбивая её на имя и аргументы.
     *
     * @param input строка вида "команда аргументы"
     */
    public void execute(String input) {
        if (input == null || input.trim().isEmpty()) return;

        String[] tokens = input.trim().split("\\s+");
        String commandName = tokens[0];
        String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);

        Command command = commands.get(commandName);
        if (command == null) {
            System.out.printf("Команда '%s' не существует. Введите 'help' для списка команд.%n", commandName);
            return;
        }

        try {
            command.run(args, collectionManager, this);
            addToHistory(command.getName());
        } catch (Exception e) {
            System.out.printf("Ошибка выполнения команды '%s': %s%n", commandName, e.getMessage());
        }
    }

    /**
     * Добавляет команду в историю последних использованных.
     *
     * @param command имя команды
     */
    private void addToHistory(String command) {
        if (commandHistory.size() >= HISTORY_SIZE) {
            commandHistory.poll();
        }
        commandHistory.offer(command);
    }

    /**
     * @return копия очереди с историей команд
     */
    public Queue<String> getCommandHistory() {
        return new LinkedList<>(commandHistory);
    }

    /**
     * @return неизменяемая карта зарегистрированных команд
     */
    public Map<String, Command> getCommands() {
        return Collections.unmodifiableMap(commands);
    }

    /**
     * @return менеджер коллекции, с которым работают команды
     */
    public CollectionManager getCollectionManager() {
        return collectionManager;
    }
}
