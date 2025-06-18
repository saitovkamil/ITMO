package commands;

import java.io.IOException;

import workWithComm.CollectionManager;
import workWithComm.CommandManager;

/**
 * Интерфейс {@code Command} описывает контракт для всех команд, которые могут
 * быть выполнены в рамках приложения.
 *
 * <p>Каждая команда обязана иметь имя, описание и метод исполнения.
 * Реализации интерфейса должны определять, как конкретно команда взаимодействует
 * с {@link CollectionManager} и {@link CommandManager}.</p>
 *
 * @author Камиль
 */
public interface Command {

    /**
     * Запускает выполнение команды.
     *
     * @param args аргументы, переданные команде
     * @param collectionManager менеджер коллекции, с которым работает команда
     * @param commandManager менеджер команд, предоставляющий доступ к другим командам
     * @throws IOException если в процессе выполнения возникает ошибка ввода-вывода
     */
    void run(String[] args, CollectionManager collectionManager, CommandManager commandManager) throws IOException;

    /**
     * Возвращает имя команды (ключевое слово, по которому она вызывается).
     *
     * @return строка с именем команды
     */
    String getName();

    /**
     * Возвращает краткое описание команды.
     *
     * @return строка с описанием действия команды
     */
    String getDescription();
}
