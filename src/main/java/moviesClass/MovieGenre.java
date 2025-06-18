package moviesClass;

/**
 * Перечисление {@code MovieGenre} — жанры фильмов, поддерживаемые в коллекции {@link Movie}.
 *
 * <p>Используется как значение поля {@code genre} в объекте {@link Movie}.</p>
 *
 * <p>Доступные жанры:</p>
 * <ul>
 *     <li>{@link #ACTION} — боевик</li>
 *     <li>{@link #DRAMA} — драма</li>
 *     <li>{@link #MUSICAL} — мюзикл</li>
 *     <li>{@link #TRAGEDY} — трагедия</li>
 *     <li>{@link #SCIENCE_FICTION} — научная фантастика</li>
 * </ul>
 *
 * @author Камиль
 */
public enum MovieGenre {
    ACTION,
    DRAMA,
    MUSICAL,
    TRAGEDY,
    SCIENCE_FICTION;
}
