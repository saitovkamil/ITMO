package moviesClass;

/**
 * Класс {@code Coordinates} — представляет координаты фильма.
 * Используется как поле в объекте {@link Movie}.
 *
 * <p>Координата {@code x} может быть {@code null}, а {@code y} — обязательно должна быть задана.</p>
 *
 * @author Камиль
 */
public class Coordinates {
    /** Координата X (может быть null) */
    private Integer x;

    /** Координата Y (не может быть null) */
    private Float y;

    /**
     * Пустой конструктор для Jackson.
     */
    public Coordinates() {}

    /**
     * Конструктор с параметрами.
     *
     * @param x координата X (может быть null)
     * @param y координата Y (не может быть null)
     * @throws IllegalArgumentException если {@code y} равна {@code null}
     */
    public Coordinates(Integer x, Float y) {
        setX(x);
        setY(y);
    }

    /**
     * Возвращает координату X.
     *
     * @return значение X (может быть null)
     */
    public Integer getX() {
        return x;
    }

    /**
     * Возвращает координату Y.
     *
     * @return значение Y (никогда не null)
     */
    public Float getY() {
        return y;
    }

    /**
     * Устанавливает координату X.
     *
     * @param x новое значение X (может быть null)
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * Устанавливает координату Y.
     *
     * @param y новое значение Y (не может быть null)
     * @throws IllegalArgumentException если {@code y} равна {@code null}
     */
    public void setY(Float y) {
        if (y == null) throw new IllegalArgumentException("y не может быть null");
        this.y = y;
    }

    /**
     * Возвращает строковое представление координат в виде {@code (x,y)}.
     *
     * @return строка координат
     */
    @Override
    public String toString() {
        return String.format("(%s,%.2f)", x, y);
    }
}
