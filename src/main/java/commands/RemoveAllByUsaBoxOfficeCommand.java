package commands;

import moviesClass.Movie;
import workWithComm.CollectionManager;
import workWithComm.CommandManager;

import java.util.List;
import java.util.Set;

/**
 * Команда {@code remove_all_by_usa_box_office} — удаляет из коллекции
 * все элементы, у которых значение поля {@link Movie#getUsaBoxOffice()} совпадает
 * с переданным аргументом.
 *
 * <p>Значение сравнивается строго с помощью {@link Double#compare(double, double)}.</p>
 *
 * <p>Если аргумент не передан или не является числом — выводится сообщение об ошибке.</p>
 *
 * @author Камиль
 * @see Movie
 * @see CollectionManager
 */
public class RemoveAllByUsaBoxOfficeCommand implements Command {

    /**
     * Выполняет удаление всех фильмов, у которых {@code usaBoxOffice} равно указанному значению.
     *
     * @param args массив аргументов, где {@code args[0]} — значение для сравнения
     * @param collectionManager менеджер коллекции
     * @param commandManager менеджер команд (не используется)
     */
    @Override
    public void run(String[] args, CollectionManager collectionManager, CommandManager commandManager) {
        if (args.length < 1) {
            System.out.println("Ошибка: укажите значение usaBoxOffice для удаления.");
            return;
        }

        try {
            double target = Double.parseDouble(args[0]);
            Set<Movie> list = collectionManager.getCollection();
            int removedCount = 0;
            for (Movie m : list) {
                if (Double.compare(m.getUsaBoxOffice(), target) == 0) {
                    if (collectionManager.removeById(m.getId())) {
                        removedCount++;
                    }
                }
            }
            System.out.println("Удалено элементов с usaBoxOffice = " + target + ": " + removedCount);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: usaBoxOffice должно быть числом с плавающей точкой.");
        }
    }

    /**
     * Возвращает имя команды.
     *
     * @return "remove_all_by_usa_box_office"
     */
    @Override
    public String getName() {
        return "remove_all_by_usa_box_office";
    }

    /**
     * Возвращает описание команды.
     *
     * @return строка описания ": удалить все элементы, значение поля usaBoxOffice которого равно заданному"
     */
    @Override
    public String getDescription() {
        return ": удалить все элементы, значение поля usaBoxOffice которого равно заданному";
    }
}
