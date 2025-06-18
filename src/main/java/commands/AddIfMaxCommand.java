package commands;

import others.MovieFiller;
import workWithComm.CollectionManager;
import workWithComm.CommandManager;
import moviesClass.Movie;
import java.util.Comparator;
import java.util.Optional;

import static java.util.Comparator.*;

/**
 * Команда {@code add_if_max} — добавляет новый фильм в коллекцию, только если он
 * строго больше текущего максимального элемента.
 *
 * <p>Сравнение происходит с использованием метода {@link Comparable#compareTo(Object)} —
 * предполагается, что класс {@link Movie} реализует интерфейс {@code Comparable}.</p>
 *
 * <p>Если коллекция пуста, фильм добавляется без сравнения.</p>
 *
 * @see CollectionManager
 * @see Movie
 */
public class AddIfMaxCommand implements Command {
    /**
     * Возвращает имя команды.
     *
     * @return "add_if_max" — имя команды
     */
    @Override
    public String getName() {
        return "add_if_max";
    }

    /**
     * Возвращает описание команды.
     *
     * @return строка описания ": добавить новый фильм, если он больше максимального"
     */
    @Override
    public String getDescription() {
        return ": добавить новый фильм, если он больше максимального";
    }

    /**
     * Выполняет команду добавления нового фильма, если он превышает
     * по сравнению максимальный элемент коллекции.
     *
     * @param args аргументы команды (не используются)
     * @param cm менеджер коллекции, содержащий текущие фильмы
     * @param mgr менеджер команд (не используется в этой команде)
     */
    @Override
    public void run(String[] args, CollectionManager cm, CommandManager mgr) {
        // Собираем новый фильм через MovieFiller (ID и creationDate генерируются автоматически)
        Movie newMovie = MovieFiller.fillMovieManually(cm);

        // Берём максимальный фильм из коллекции по compareTo
        Optional<Movie> maxOpt = cm.getCollection().stream()
                .max(Movie::compareTo);

        if (maxOpt.isEmpty() || newMovie.compareTo(maxOpt.get()) > 0) {
            cm.addElement(newMovie);
            System.out.println("Фильм добавлен, так как он больше всех существующих.");
        } else {
            System.out.println("Фильм не добавлен — он не больше максимального.");
        }
    }
}
