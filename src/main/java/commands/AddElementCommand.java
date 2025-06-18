package commands;

import moviesClass.Movie;
import others.MovieFiller;
import workWithComm.CollectionManager;
import workWithComm.CommandManager;

/**
 * Команда {@code add}, которая добавляет новый элемент (фильм) в коллекцию.
 *
 * <p>Если ID нового фильма уже существует в коллекции, он будет автоматически увеличен,
 * пока не станет уникальным.</p>
 *
 * <p>Фильм заполняется вручную с помощью {@link MovieFiller#fillMovieManually(CollectionManager)}.</p>
 *
 * @see CollectionManager
 * @see Command
 */
public class AddElementCommand implements Command {

    /**
     * Возвращает имя команды.
     *
     * @return "add" — имя команды
     */
    @Override
    public String getName() {
        return "add";
    }

    /**
     * Возвращает краткое описание команды.
     *
     * @return строка описания ": добавить новый фильм"
     */
    @Override
    public String getDescription() {
        return ": добавить новый фильм";
    }

    /**
     * Запускает выполнение команды.
     * Добавляет новый {@link Movie} в коллекцию.
     * Если фильм с таким ID уже есть, ID увеличивается до тех пор, пока не станет уникальным.
     *
     * @param args аргументы команды (не используются)
     * @param cm менеджер коллекции, в которую добавляется фильм
     * @param mgr менеджер команд (не используется в данной команде)
     */
    @Override
    public void run(String[] args, CollectionManager cm, CommandManager mgr) {
        Movie movie = MovieFiller.fillMovieManually(cm);
        while (!cm.addElement(movie)) {
            movie.setId(movie.getId() + 1);
        }
        System.out.println("Фильм успешно добавлен: " + movie);
    }
}
