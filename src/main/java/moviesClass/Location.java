package moviesClass;

public class Location {
    private Long x;    // Не может быть null
    private Long y;    // Не может быть null
    private long z;
    private String name; // Не может быть null

    public Location() {}

    public Location(Long x, Long y, long z, String name) {
        setX(x);
        setY(y);
        setZ(z);
        setName(name);
    }

    // Геттеры
    public Long getX() { return x; }
    public Long getY() { return y; }
    public long getZ() { return z; }
    public String getName() { return name; }

    // Сеттеры с проверками
    public void setX(Long x) {
        if (x == null) throw new IllegalArgumentException("Координата X не может быть null");
        this.x = x;
    }

    public void setY(Long y) {
        if (y == null) throw new IllegalArgumentException("Координата Y не может быть null");
        this.y = y;
    }

    public void setZ(long z) {
        this.z = z;
    }

    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("Название локации не может быть null");
        this.name = name;
    }
}
