package moviesClass;

/**
 * Перечисление {@code Country} — набор возможных значений гражданства оператора фильма ({@link Person}).
 *
 * <p>Используется в поле {@code nationality} класса {@link Person}.</p>
 *
 * <p>Доступные страны:</p>
 * <ul>
 *     <li>{@link #INDIA}</li>
 *     <li>{@link #VATICAN}</li>
 *     <li>{@link #THAILAND}</li>
 *     <li>{@link #SOUTH_KOREA}</li>
 * </ul>
 *
 * @author Камиль
 */
public enum Country {
    INDIA, VATICAN, THAILAND, SOUTH_KOREA;
}
