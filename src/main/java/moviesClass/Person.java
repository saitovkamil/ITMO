package moviesClass;

/**
 * Класс {@code Person} представляет собой описание оператора фильма.
 *
 * <p>Содержит имя, паспортный идентификатор, цвет волос и национальность.</p>
 *
 * <ul>
 *     <li>{@code name} — не может быть {@code null} или пустым</li>
 *     <li>{@code passportID} — может быть {@code null}, но не длиннее 43 символов</li>
 *     <li>{@code hairColor} и {@code nationality} — обязательные поля</li>
 * </ul>
 *
 * <p>Используется внутри класса {@link Movie} как поле {@code operator}.</p>
 *
 * @author Камиль
 * @see Movie
 * @see Color
 * @see Country
 */
public class Person {
    private String name;
    private String passportID;
    private Color hairColor;
    private Country nationality;

    /**
     * Пустой конструктор для Jackson.
     */
    public Person() {}

    /**
     * Конструктор с параметрами.
     *
     * @param name имя оператора (не null, не пустое)
     * @param passportID паспортный ID (может быть null, макс. 43 символа)
     * @param hairColor цвет волос (не null)
     * @param nationality гражданство (не null)
     */
    public Person(String name, String passportID, Color hairColor, Country nationality) {
        setName(name);
        setPassportID(passportID);
        setHairColor(hairColor);
        setNationality(nationality);
    }

    public String getName() { return name; }

    public String getPassportID() { return passportID; }

    public Color getHairColor() { return hairColor; }

    public Country getNationality() { return nationality; }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name не может быть пустым");
        }
        this.name = name;
    }

    public void setPassportID(String passportID) {
        if (passportID != null && passportID.length() > 43) {
            throw new IllegalArgumentException("passportID ≤ 43 chars");
        }
        this.passportID = passportID;
    }

    public void setHairColor(Color hairColor) {
        if (hairColor == null) {
            throw new IllegalArgumentException("hairColor не может быть null");
        }
        this.hairColor = hairColor;
    }

    public void setNationality(Country nationality) {
        if (nationality == null) {
            throw new IllegalArgumentException("nationality не может быть null");
        }
        this.nationality = nationality;
    }

    /**
     * Возвращает строковое представление оператора.
     *
     * @return строка с данными о человеке
     */
    @Override
    public String toString() {
        return String.format("Person[name=%s,id=%s,color=%s,country=%s]",
                name, passportID, hairColor, nationality);
    }
}
