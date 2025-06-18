package commands;

import moviesClass.Movie;
import workWithComm.CollectionManager;
import workWithComm.CommandManager;
import others.MovieFiller;

/**
 * Команда {@code add_if_min} — добавляет новый фильм в коллекцию,
 * только если он строго меньше текущего минимального элемента.
 *
 * <p>Сравнение происходит через реализацию {@link Comparable#compareTo(Object)} в {@link Movie}.</p>
 *
 * <p>Если коллекция пуста — фильм добавляется автоматически.</p>
 *
 * @author Камиль
 * @see CollectionManager
 * @see Movie
 */
public class AddIfMinCommand implements Command {

    /**
     * Выполняет команду: добавляет новый {@link Movie}, если он меньше минимального в коллекции.
     *
     * @param args аргументы команды (не используются)
     * @param collectionManager менеджер коллекции, где хранятся фильмы
     * @param commandManager менеджер команд (не используется здесь)
     */
    @Override
    public void run(String[] args, CollectionManager collectionManager, CommandManager commandManager) {
        Movie newMovie = MovieFiller.fillMovieManually(collectionManager);

        Movie minMovie = collectionManager.getCollection().stream()
                .min(Movie::compareTo)
                .orElse(null);

        if (minMovie == null || newMovie.compareTo(minMovie) < 0) {
            collectionManager.getCollection().add(newMovie);
            System.out.println("Фильм добавлен, так как он меньше всех существующих.");
        } else {
            System.out.println("Фильм не добавлен — он не меньше минимального.");
        }
    }

    /**
     * Возвращает имя команды.
     *
     * @return "add_if_min"
     */
    @Override
    public String getName() {
        return "add_if_min";
    }

    /**
     * Возвращает описание команды.
     *
     * @return строка описания ": добавить новый элемент в коллекцию, если он меньше минимального."
     */
    @Override
    public String getDescription() {
        return ": добавить новый элемент в коллекцию, если он меньше минимального.";
    }
}
