package commands;

import workWithComm.CollectionManager;
import workWithComm.CommandManager;

/**
 * Команда {@code exit} — завершает выполнение программы без сохранения текущего состояния коллекции.
 *
 * <p>Вызывает {@link System#exit(int)} с кодом 0.</p>
 *
 * <strong>Важно:</strong> эта команда завершает процесс немедленно и не сохраняет коллекцию.
 * Рекомендуется использовать только в клиентской части.
 *
 * @author Камиль
 */
public class ExitCommand implements Command {

    /**
     * Выполняет завершение программы без сохранения.
     *
     * @param args аргументы команды (не используются)
     * @param collectionManager менеджер коллекции (не используется)
     * @param commandManager менеджер команд (не используется)
     */
    @Override
    public void run(String[] args, CollectionManager collectionManager, CommandManager commandManager) {
        System.out.println("Завершение работы без сохранения...");
        System.exit(0);
    }

    /**
     * Возвращает имя команды.
     *
     * @return "exit"
     */
    @Override
    public String getName() {
        return "exit";
    }

    /**
     * Возвращает описание команды.
     *
     * @return строка описания ": завершить программу (без сохранения)"
     */
    @Override
    public String getDescription() {
        return ": завершить программу (без сохранения)";
    }
}
