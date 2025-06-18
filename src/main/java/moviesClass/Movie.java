package moviesClass;

import java.util.Date;
import java.util.Objects;

/**
 * Класс {@code Movie} представляет собой модель фильма, хранимого в коллекции.
 *
 * <p>Каждый фильм содержит уникальный идентификатор, координаты, дату создания, награды, кассовые сборы,
 * жанр и информацию об операторе (если есть).</p>
 *
 * <p>Класс реализует {@link Comparable} по количеству {@code oscarsCount}.</p>
 *
 * @author Камиль
 * @see Coordinates
 * @see Person
 * @see MovieGenre
 */
public class Movie implements Comparable<Movie> {
    private static int nextId = 1;

    private Integer id;
    private String name;
    private Coordinates coordinates;
    private Date creationDate;
    private Long oscarsCount;
    private int goldenPalmCount;
    private Float usaBoxOffice;
    private MovieGenre genre;
    private Person operator;

    /**
     * Пустой конструктор для Jackson.
     */
    public Movie() {}

    /**
     * Конструктор с параметрами. {@code id} и {@code creationDate} генерируются автоматически.
     *
     * @param name название фильма (не null и не пустое)
     * @param coordinates координаты (не null)
     * @param oscarsCount количество оскаров (>0)
     * @param goldenPalmCount количество Золотых пальмовых ветвей (>0)
     * @param usaBoxOffice кассовые сборы в США (>0)
     * @param genre жанр фильма (не null)
     * @param operator оператор фильма (может быть null)
     */
    public Movie(String name,
                 Coordinates coordinates,
                 Long oscarsCount,
                 int goldenPalmCount,
                 Float usaBoxOffice,
                 MovieGenre genre,
                 Person operator) {
        this.id = nextId++;
        this.creationDate = new Date();
        setName(name);
        setCoordinates(coordinates);
        setOscarsCount(oscarsCount);
        setGoldenPalmCount(goldenPalmCount);
        setUsaBoxOffice(usaBoxOffice);
        setGenre(genre);
        setOperator(operator);
    }

    /**
     * Устанавливает значение счётчика ID. Используется при десериализации.
     *
     * @param next следующее значение ID
     */
    public static void setNextId(int next) {
        nextId = next;
    }

    /**
     * Возвращает текущее значение счётчика ID.
     *
     * @return следующее ID
     */
    public static int getNextId() {
        return nextId;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public Coordinates getCoordinates() { return coordinates; }
    public Date getCreationDate() { return creationDate; }
    public Long getOscarsCount() { return oscarsCount; }
    public int getGoldenPalmCount() { return goldenPalmCount; }
    public Float getUsaBoxOffice() { return usaBoxOffice; }
    public MovieGenre getGenre() { return genre; }
    public Person getOperator() { return operator; }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Название не может быть пустым");
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) throw new IllegalArgumentException("Coordinates не может быть null");
        this.coordinates = coordinates;
    }

    public void setOscarsCount(Long oscarsCount) {
        if (oscarsCount == null || oscarsCount <= 0) throw new IllegalArgumentException("oscarsCount > 0");
        this.oscarsCount = oscarsCount;
    }

    public void setGoldenPalmCount(int goldenPalmCount) {
        if (goldenPalmCount <= 0) throw new IllegalArgumentException("goldenPalmCount > 0");
        this.goldenPalmCount = goldenPalmCount;
    }

    public void setUsaBoxOffice(Float usaBoxOffice) {
        if (usaBoxOffice == null || usaBoxOffice <= 0) throw new IllegalArgumentException("usaBoxOffice > 0");
        this.usaBoxOffice = usaBoxOffice;
    }

    public void setGenre(MovieGenre genre) {
        if (genre == null) throw new IllegalArgumentException("genre не может быть null");
        this.genre = genre;
    }

    public void setOperator(Person operator) {
        this.operator = operator;
    }

    /**
     * Возвращает строковое представление объекта.
     *
     * @return строка с краткой информацией о фильме
     */
    @Override
    public String toString() {
        return String.format("Movie{id=%d, name='%s', coords=%s, date=%s, oscars=%d, palms=%d, box=%.2f, genre=%s, op=%s}",
                id, name, coordinates, creationDate, oscarsCount, goldenPalmCount, usaBoxOffice, genre, operator);
    }

    /**
     * Сравнение по количеству {@code oscarsCount}.
     *
     * @param other другой фильм для сравнения
     * @return результат сравнения
     * @throws NullPointerException если {@code other} равен null
     */
    @Override
    public int compareTo(Movie other) {
        if (other == null) throw new NullPointerException("Невозможно сравнить с null");
        return Long.compare(this.oscarsCount, other.oscarsCount);
    }

    /**
     * Сравнение по ID.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Movie)) return false;
        Movie m = (Movie) o;
        return Objects.equals(id, m.id);
    }

    /**
     * Хеш-код по ID.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
