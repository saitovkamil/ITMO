package others;

/**
 * Класс {@code UniqueID} — утилитный генератор уникальных идентификаторов.
 *
 * <p>Используется для генерации ID объектов, когда {@link moviesClass.Movie} или другие сущности
 * требуют уникального значения ID в рамках текущего сеанса работы.</p>
 *
 * <p>Генерация ID — потокобезопасна, благодаря ключевому слову {@code synchronized}.</p>
 *
 * @author Камиль
 */
public class UniqueID {
    /** Счётчик текущего ID */
    private static int id;

    /**
     * Генерирует новый уникальный ID.
     *
     * @return следующий уникальный идентификатор
     */
    public static synchronized int generateId() {
        id++;
        return id;
    }

    /**
     * Возвращает текущее значение счётчика ID (без инкремента).
     *
     * @return последний сгенерированный ID
     */
    public static int getIdCounter() {
        return id;
    }

    /**
     * Устанавливает начальное значение счётчика ID.
     * Может использоваться при загрузке коллекции из файла.
     *
     * @param id новое стартовое значение счётчика
     */
    public static void setIdCounter(int id) {
        UniqueID.id = id;
    }
}
