package commands;

import workWithComm.CollectionManager;
import workWithComm.CommandManager;

/**
 * Команда {@code history} — выводит список последних выполненных команд.
 *
 * <p>История команд хранится в {@link CommandManager} и выводится построчно.</p>
 *
 * <p>Полезна для анализа пользовательской активности или дебага.</p>
 *
 * @author Камиль
 * @see CommandManager#getCommandHistory()
 */
public class HistoryCommand implements Command {

    /**
     * Возвращает имя команды.
     *
     * @return "history"
     */
    @Override
    public String getName() {
        return "history";
    }

    /**
     * Возвращает описание команды.
     *
     * @return строка описания ": показать последние команды"
     */
    @Override
    public String getDescription() {
        return ": показать последние команды";
    }

    /**
     * Выполняет команду: выводит сохранённую историю последних команд.
     *
     * @param args аргументы команды (не используются)
     * @param cm менеджер коллекции (не используется)
     * @param mgr менеджер команд, содержащий историю
     */
    @Override
    public void run(String[] args, CollectionManager cm, CommandManager mgr) {
        mgr.getCommandHistory().forEach(System.out::println);
    }
}
