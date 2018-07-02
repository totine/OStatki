package placement.model;

import java.util.Objects;
import java.util.Random;

public class Coordinates {
    int x;
    int y;
    public Coordinates(int coordinateX, int coordinateY) {
        this.x = coordinateX;
        this.y = coordinateY;
    }

    public static Coordinates getRandom(int rows, int cols) {
        Random random = new Random();
        int x = random.nextInt(rows);
        int y = random.nextInt(cols);


        return new Coordinates(x, y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
