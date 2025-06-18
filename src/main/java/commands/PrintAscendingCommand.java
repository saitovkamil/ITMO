package commands;

import moviesClass.Movie;
import workWithComm.CollectionManager;
import workWithComm.CommandManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Команда {@code print_ascending} — выводит все элементы коллекции {@link Movie}
 * в порядке возрастания, согласно их реализации {@link Comparable}.
 *
 * <p>Если коллекция пуста, выводится соответствующее сообщение.</p>
 *
 * @author Камиль
 * @see Movie
 * @see CollectionManager
 */
public class PrintAscendingCommand implements Command {

    /**
     * Выполняет команду: сортирует элементы коллекции по возрастанию и выводит их.
     *
     * @param args аргументы команды (не используются)
     * @param collectionManager менеджер коллекции, содержащей элементы {@link Movie}
     * @param commandManager менеджер команд (не используется)
     */
    @Override
    public void run(String[] args, CollectionManager collectionManager, CommandManager commandManager) {
        List<Movie> list = new ArrayList<>(collectionManager.getCollection());
        Collections.sort(list);
        if (list.isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            for (Movie movie : list) {
                System.out.println(movie);
            }
        }
    }

    /**
     * Возвращает имя команды.
     *
     * @return "print_ascending"
     */
    @Override
    public String getName() {
        return "print_ascending";
    }

    /**
     * Возвращает описание команды.
     *
     * @return строка описания ": вывести элементы коллекции в порядке возрастания"
     */
    @Override
    public String getDescription() {
        return ": вывести элементы коллекции в порядке возрастания";
    }
}
