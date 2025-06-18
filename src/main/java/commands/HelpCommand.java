package commands;

import workWithComm.CollectionManager;
import workWithComm.CommandManager;

import java.util.*;

/**
 * Команда {@code help} — выводит список всех доступных команд и их описания.
 *
 * <p>Команды и описания берутся из {@link CommandManager#getCommands()}, каждая команда выводится
 * в формате {@code <имя команды> : <описание>}.</p>
 *
 * <p>Команда полезна для получения краткой справки по возможностям приложения.</p>
 *
 * @author Камиль
 * @see CommandManager
 */
public class HelpCommand implements Command {

    /**
     * Выполняет команду — выводит на экран имена и описания всех зарегистрированных команд.
     *
     * @param args аргументы команды (не используются)
     * @param collectionManager менеджер коллекции (не используется)
     * @param commandManager менеджер команд, откуда берутся все команды
     */
    @Override
    public void run(String[] args, CollectionManager collectionManager, CommandManager commandManager) {
        Map<String, Command> commands = commandManager.getCommands();
        System.out.println("Доступные команды:");
        for (Command cmd : commands.values()) {
            System.out.printf("%-30s : %s%n", cmd.getName(), cmd.getDescription());
        }
    }

    /**
     * Возвращает имя команды.
     *
     * @return "help"
     */
    @Override
    public String getName() {
        return "help";
    }

    /**
     * Возвращает описание команды.
     *
     * @return строка описания ": вывести справку по доступным командам"
     */
    @Override
    public String getDescription() {
        return ": вывести справку по доступным командам";
    }
}
