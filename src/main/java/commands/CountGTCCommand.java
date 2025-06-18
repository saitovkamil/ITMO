package commands;

import moviesClass.Movie;
import workWithComm.CollectionManager;
import workWithComm.CommandManager;

import java.util.List;
import java.util.Set;

/**
 * Команда {@code sum_of_oscars_count} — вычисляет и выводит сумму значений поля
 * {@link Movie#getOscarsCount()} для всех объектов {@link Movie} в коллекции.
 *
 * <p>Если коллекция пуста — сумма будет равна 0.</p>
 *
 * @author Камиль
 * @see Movie
 * @see CollectionManager
 */
public class CountGTCCommand implements Command {

    /**
     * Выполняет команду: считает сумму по полю {@code oscarsCount} у всех фильмов.
     *
     * @param args аргументы команды (не используются)
     * @param collectionManager менеджер, управляющий коллекцией фильмов
     * @param commandManager менеджер команд (не используется в данной команде)
     */
    @Override
    public void run(String[] args, CollectionManager collectionManager, CommandManager commandManager) {
        Set<Movie> collection = collectionManager.getCollection();
        long sum = collection.stream()
                .mapToLong(Movie::getOscarsCount)
                .sum();
        System.out.println("Сумма OscarCount всех фильмов: " + sum);
    }

    /**
     * Возвращает имя команды.
     *
     * @return "sum_of_oscars_count"
     */
    @Override
    public String getName() {
        return "sum_of_oscars_count";
    }

    /**
     * Возвращает описание команды.
     *
     * @return строка описания ": вывести сумму значений поля oscarsCount для всех элементов коллекции"
     */
    @Override
    public String getDescription() {
        return ": вывести сумму значений поля oscarsCount для всех элементов коллекции";
    }
}
