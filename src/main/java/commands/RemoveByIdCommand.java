package commands;

import workWithComm.CollectionManager;
import workWithComm.CommandManager;

/**
 * Команда {@code remove_by_id} — удаляет элемент коллекции по его уникальному {@code ID}.
 *
 * <p>Если элемент с указанным ID не найден, выводится соответствующее сообщение.</p>
 *
 * <p>В случае некорректного ввода ID (не число) — также выводится сообщение об ошибке.</p>
 *
 * @author Камиль
 * @see CollectionManager
 */
public class RemoveByIdCommand implements Command {

    /**
     * Выполняет удаление элемента коллекции по заданному {@code ID}.
     *
     * @param args массив аргументов, где {@code args[0]} — целочисленный идентификатор элемента
     * @param collectionManager менеджер коллекции, содержащий элементы
     * @param commandManager менеджер команд (не используется)
     */
    @Override
    public void run(String[] args, CollectionManager collectionManager, CommandManager commandManager) {
        if (args.length < 1) {
            System.out.println("Ошибка: укажите ID для удаления.");
            return;
        }
        try {
            int id = Integer.parseInt(args[0]);
            boolean removed = collectionManager.removeById(id);
            if (removed) {
                System.out.println("Элемент с ID " + id + " успешно удалён.");
            } else {
                System.out.println("Элемент с ID " + id + " не найден.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть целым числом.");
        }
    }

    /**
     * Возвращает имя команды.
     *
     * @return "remove_by_id"
     */
    @Override
    public String getName() {
        return "remove_by_id";
    }

    /**
     * Возвращает описание команды.
     *
     * @return строка описания ": удалить элемент из коллекции по его id"
     */
    @Override
    public String getDescription() {
        return ": удалить элемент из коллекции по его id";
    }
}
