package placement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a ship in the context of placing ships on a board. Knows its shape and position on the board.
 */
public class Ship {

    private final int mastNumber;
    private Direction direction;
    private boolean placed;
    private List<Coordinates> directionCoordinates;


    /**
     * Returns a list of ship's absolute coordinates.
     * @return list of coordinates.
     */
    public final List<Coordinates> getDirectionCoordinates() {
        return directionCoordinates;
    }


    Ship(int mastCount, Direction direction) {
        this.mastNumber = mastCount;
        this.direction = direction;
        directionCoordinates = new ArrayList<>();
    }

    Ship(int i) {
        mastNumber = i;
        directionCoordinates = new ArrayList<>();
    }


    final int getMastNumber() {
        return mastNumber;
    }

    final Direction getDirection() {
        return direction;
    }

    public final boolean isPlaced() {
        return placed;
    }

    final void markAsPlaced() {
        this.placed = true;
    }


    /**
     * Sets an orientation of the ship - if it's heading north, south, east, west OR if it's
     * @param direction - south, west, east or north
     */
    public final void setDirection(Direction direction) {
        this.direction = direction;
    }

    final void addDirectionCoord(Coordinates coordinates) {

        directionCoordinates.add(coordinates);
    }


}
