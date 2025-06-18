package commands;

import workWithComm.CollectionManager;
import workWithComm.CommandManager;

/**
 * Команда {@code info} — выводит информацию о коллекции:
 * её тип, дату инициализации и количество элементов.
 *
 * <p>Данные берутся из метода {@link CollectionManager#getCollectionInfo()}.</p>
 *
 * @author Камиль
 * @see CollectionManager
 */
public class InfoCommand implements Command {

    /**
     * Выполняет команду: выводит информацию о коллекции в стандартный поток вывода.
     *
     * @param args аргументы команды (не используются)
     * @param collectionManager менеджер коллекции, содержащий всю необходимую информацию
     * @param commandManager менеджер команд (не используется)
     */
    @Override
    public void run(String[] args, CollectionManager collectionManager, CommandManager commandManager) {
        System.out.println(collectionManager.getCollectionInfo());
    }

    /**
     * Возвращает имя команды.
     *
     * @return "info"
     */
    @Override
    public String getName() {
        return "info";
    }

    /**
     * Возвращает описание команды.
     *
     * @return строка описания ": вывести информацию о коллекции (тип, дата инициализации, количество элементов)"
     */
    @Override
    public String getDescription() {
        return ": вывести информацию о коллекции (тип, дата инициализации, количество элементов)";
    }
}
