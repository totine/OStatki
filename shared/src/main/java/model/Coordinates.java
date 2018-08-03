package model;

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

    /**
     * this is the factory method for Coordinates.
     *
     * @param coordinateX this parameter indicates horizontal coordinate on the 2D board.
     * @param coordinateY this parameter indicates vertical coordinate on the 2D board.
     * @return method returns an instance of Coordinates which is set which fields as in
     * parameters.
     */
    public static Coordinates create(int coordinateX, int coordinateY) {
        return new Coordinates(coordinateX, coordinateY);
    }

    /**
     * this is simple getter for x coordinate.
     *
     * @return returns int type value which indicates horizontal coordinate.
     */
    public final int getX() {
        return this.x;
    }

    /**
     * this is simple getter for y coordinate.
     *
     * @return returns int type value which indicates vertical coordinate.
     */
    public final int getY() {
        return this.y;
    }

    /**
     * this method is a translation of Coordinates which transforms instance of Coordinates
     * which is used on by a coordinates parameter.
     *
     * @param coordinates is used to translate object which is used on based on fields which it consists of.
     * @return returns translated version of coordinates.
     */
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
        return String.format("{x=%d, y=%d}", x, y);
    }
}
