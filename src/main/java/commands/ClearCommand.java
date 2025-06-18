package commands;

import workWithComm.CollectionManager;
import workWithComm.CommandManager;

/**
 * Команда {@code clear} — очищает всю коллекцию, удаляя из неё все элементы.
 *
 * <p>После выполнения коллекция становится пустой.</p>
 *
 * @author Камиль
 * @see CollectionManager
 */
public class ClearCommand implements Command {

    /**
     * Выполняет команду очистки коллекции.
     *
     * @param args аргументы команды (не используются)
     * @param collectionManager менеджер коллекции, содержащий все элементы
     * @param commandManager менеджер команд (не используется)
     */
    @Override
    public void run(String[] args, CollectionManager collectionManager, CommandManager commandManager) {
        collectionManager.getCollection().clear();
        System.out.println("Коллекция очищена.");
    }

    /**
     * Возвращает имя команды.
     *
     * @return "clear"
     */
    @Override
    public String getName() {
        return "clear";
    }

    /**
     * Возвращает описание команды.
     *
     * @return строка описания ": очистить коллекцию"
     */
    @Override
    public String getDescription() {
        return ": очистить коллекцию";
    }
}
