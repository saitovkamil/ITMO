package commands;

import workWithComm.CollectionManager;
import workWithComm.CommandManager;

/**
 * Команда {@code save} — сохраняет текущую коллекцию в файл.
 *
 * <p>Вызов метода {@link CollectionManager#saveCollection()} выполняет сериализацию
 * или иной способ сохранения в зависимости от реализации менеджера коллекции.</p>
 *
 * <p>Команда предназначена для серверной части и обычно вызывается вручную или при завершении работы сервера.</p>
 *
 * @author Камиль
 * @see CollectionManager
 */
public class SaveCommand implements Command {

    /**
     * Возвращает имя команды.
     *
     * @return "save"
     */
    @Override
    public String getName() {
        return "save";
    }

    /**
     * Возвращает описание команды.
     *
     * @return строка описания ": сохранить коллекцию в файл"
     */
    @Override
    public String getDescription() {
        return ": сохранить коллекцию в файл";
    }

    /**
     * Выполняет сохранение коллекции через {@link CollectionManager#saveCollection()}.
     * В случае ошибки — выводит сообщение в стандартный поток ошибок.
     *
     * @param args аргументы команды (не используются)
     * @param cm менеджер коллекции, реализующий логику сохранения
     * @param cmdMgr менеджер команд (не используется)
     */
    @Override
    public void run(String[] args, CollectionManager cm, CommandManager cmdMgr) {
        try {
            cm.saveCollection();
            System.out.println("Коллекция успешно сохранена.");
        } catch (Exception e) {
            System.err.println("Не удалось сохранить коллекцию: " + e.getMessage());
        }
    }
}
