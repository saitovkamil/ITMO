package commands;

import moviesClass.Movie;
import workWithComm.CollectionManager;
import workWithComm.CommandManager;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Команда {@code print_field_descending_usa_box_office} —
 * выводит значения поля {@link Movie#getUsaBoxOffice()} всех элементов коллекции
 * в порядке убывания.
 *
 * <p>Если коллекция пуста, выводится соответствующее сообщение.</p>
 *
 * @author Камиль
 * @see Movie
 * @see CollectionManager
 */
public class PrintFDCCommand implements Command {

    /**
     * Выполняет команду: извлекает значения поля {@code usaBoxOffice}, сортирует их по убыванию и выводит.
     *
     * @param args аргументы команды (не используются)
     * @param collectionManager менеджер коллекции, содержащий объекты {@link Movie}
     * @param commandManager менеджер команд (не используется)
     */
    @Override
    public void run(String[] args, CollectionManager collectionManager, CommandManager commandManager) {
        List<Float> list = collectionManager.getCollection().stream()
                .map(Movie::getUsaBoxOffice)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            System.out.println("Поля usaBoxOffice в порядке убывания:");
            list.forEach(amount -> System.out.printf("%.2f%n", amount));
        }
    }

    /**
     * Возвращает имя команды.
     *
     * @return "print_field_descending_usa_box_office"
     */
    @Override
    public String getName() {
        return "print_field_descending_usa_box_office";
    }

    /**
     * Возвращает описание команды.
     *
     * @return строка описания ": вывести значения поля usaBoxOffice всех элементов в порядке убывания"
     */
    @Override
    public String getDescription() {
        return ": вывести значения поля usaBoxOffice всех элементов в порядке убывания";
    }
}
