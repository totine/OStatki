package placement.model;

import java.util.Objects;

/**
 * Represents 2-D coordinates on the board. The top-left coordinate of the board will be (0, 0).
 * x is a horizontal coordinate
 * y is a vertical coordinate
 * Implements adding of two coordinates, e.g. (4, 4) + (2, -1) = (6, 3)
 */
public class Coordinates {
    private final int x;
    private final int y;

    private Coordinates(int coordinateX, int coordinateY) {
        this.x = coordinateX;
        this.y = coordinateY;
    }

    public static Coordinates createCoordinates(int coordinateX, int coordinateY) {
        return new Coordinates(coordinateX, coordinateY);
    }

    public final int getX() {
        return this.x;
    }

    public final int getY() {
        return this.y;
    }

    public final Coordinates add(Coordinates coordinates) {
        return new Coordinates(x + coordinates.x, y + coordinates.y);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinates that = (Coordinates) o;
        return x == that.x
                && y == that.y;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public final String toString() {
        return "Coordinates{"
                + "x=" + x
                + ", y=" + y
                + '}';
    }
}
