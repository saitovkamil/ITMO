package commands;

import moviesClass.Movie;
import workWithComm.CollectionManager;
import workWithComm.CommandManager;

import java.util.List;
import java.util.Set;

/**
 * Команда {@code show} — выводит все элементы коллекции в строковом представлении.
 *
 * <p>Для каждого элемента вызывается {@link Movie#toString()}.</p>
 *
 * <p>Если коллекция пуста — выводится соответствующее сообщение.</p>
 *
 * @author Камиль
 * @see Movie
 * @see CollectionManager
 */
public class ShowCommand implements Command {

    /**
     * Выполняет команду: выводит все элементы коллекции в консоль.
     *
     * @param args аргументы команды (не используются)
     * @param collectionManager менеджер коллекции, содержащий фильмы
     * @param commandManager менеджер команд (не используется)
     */
    @Override
    public void run(String[] args, CollectionManager collectionManager, CommandManager commandManager) {
        Set<Movie> collection = collectionManager.getCollection();
        if (collection.isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            for (Movie movie : collection) {
                System.out.println(movie);
            }
        }
    }

    /**
     * Возвращает имя команды.
     *
     * @return "show"
     */
    @Override
    public String getName() {
        return "show";
    }

    /**
     * Возвращает описание команды.
     *
     * @return строка описания ": вывести все элементы коллекции в строковом представлении."
     */
    @Override
    public String getDescription() {
        return ": вывести все элементы коллекции в строковом представлении.";
    }
}
