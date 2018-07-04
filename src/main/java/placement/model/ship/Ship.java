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


    /**
     * Returns a list of ship's absolute coordinates.
     *
     * @return list of coordinates.
     */
    public final List<Coordinates> getPositionCoordinates() {
        return positionCoordinates;
    }


    public Ship(int mastCount, Direction direction) {
        this.id = UUID.randomUUID().toString();
        this.mastNumber = mastCount;
        this.direction = direction;
        positionCoordinates = new ArrayList<>();
    }

    public Ship(int i) {
        mastNumber = i;
        positionCoordinates = new ArrayList<>();
        id = UUID.randomUUID().toString();
        ;
    }


    public final int getMastNumber() {
        return mastNumber;
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

    public final void addDirectionCoord(Coordinates coordinates) {

        positionCoordinates.add(coordinates);
    }


    public Coordinates nextCoordinates() {
        return direction.nextCoordinates();
    }

    public String getId() {
        return id;
    }
}
