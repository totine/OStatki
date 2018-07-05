package placement.model.ship;

import placement.model.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a ship in the context of placing ships on a board. Knows its shape and position on the board.
 */
public class Ship {
    private final int mastNumber;
    private final String id;
    private Direction direction;
    private boolean placed;
    private List<Coordinates> positionCoordinates;



    public Ship(int mastCount, Direction direction) {
        this.id = UUID.randomUUID().toString();
        this.mastNumber = mastCount;
        this.direction = direction;
        positionCoordinates = new ArrayList<>();
    }

    public Ship(int mastNumber) {
        this(mastNumber, Direction.UNDETERMINED);
    }


    public String getId() {
        return id;
    }

    public final int getMastNumber() {
        return mastNumber;
    }


    /**
     * Returns a list of ship's absolute coordinates.
     *
     * @return list of coordinates.
     */
    public final List<Coordinates> getPositionCoordinates() {
        return positionCoordinates;
    }

    public final boolean isPlaced() {
        return placed;
    }

    public final void markAsPlaced() {
        this.placed = true;
    }


    /**
     * Sets an orientation of the ship - if it's heading north, south, east, west OR if it's
     *
     * @param direction - south, west, east or north
     */
    public final void setDirection(Direction direction) {
        this.direction = direction;
    }

    public final void addPositionCoord(Coordinates coordinates) {
        positionCoordinates.add(coordinates);
    }

    public Coordinates nextCoordinates() {
        return direction.nextCoordinates();
    }
}
