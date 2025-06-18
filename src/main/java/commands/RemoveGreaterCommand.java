package commands;

import moviesClass.Movie;
import workWithComm.CollectionManager;
import workWithComm.CommandManager;
import others.MovieFiller;

import java.util.ArrayList;
import java.util.List;

/**
 * Команда {@code remove_greater} — удаляет из коллекции все элементы {@link Movie},
 * которые больше заданного пользователем эталонного объекта.
 *
 * <p>Сравнение происходит через метод {@link Comparable#compareTo(Object)}, реализованный в {@link Movie}.</p>
 *
 * <p>Эталонный объект запрашивается вручную через {@link MovieFiller#fillMovieManually()}.</p>
 *
 * @author Камиль
 * @see Movie
 * @see CollectionManager
 */
public class RemoveGreaterCommand implements Command {

    /**
     * Возвращает имя команды.
     *
     * @return "remove_greater"
     */
    @Override
    public String getName() {
        return "remove_greater";
    }

    /**
     * Возвращает описание команды.
     *
     * @return строка описания ": удалить из коллекции все элементы, превышающие заданный"
     */
    @Override
    public String getDescription() {
        return ": удалить из коллекции все элементы, превышающие заданный";
    }

    /**
     * Выполняет удаление всех элементов коллекции, которые превышают указанный эталонный {@link Movie}.
     *
     * @param args аргументы команды (не используются)
     * @param collectionManager менеджер коллекции, в которой происходит удаление
     * @param commandManager менеджер команд (не используется)
     */
    @Override
    public void run(String[] args, CollectionManager collectionManager, CommandManager commandManager) {
        // Сначала получаем фильм-эталон для сравнения
        System.out.println("Введите данные фильма для сравнения (эталон):");
        Movie reference = MovieFiller.fillMovieManually(collectionManager   );

        // Делаем копию коллекции, чтобы безопасно удалять из оригинала
        List<Movie> snapshot = new ArrayList<>(collectionManager.getCollection());
        int removedCount = 0;

        for (Movie m : snapshot) {
            if (m.compareTo(reference) > 0) {
                if (collectionManager.removeById(m.getId())) {
                    removedCount++;
                }
            }
        }

        System.out.println("Удалено элементов, превышающих эталон: " + removedCount);
    }
}
