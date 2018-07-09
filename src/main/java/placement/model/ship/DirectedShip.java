package placement.model.ship;

import placement.model.Coordinates;

import java.util.*;

/**
 * Represents a ship in the context of placing ships on a board. Knows its shape and position on the board.
 */
public class DirectedShip implements Ship {
    private final int mastNumber;
    private Direction direction;


    public DirectedShip(int mastCount, Direction direction) {
        this.mastNumber = mastCount;
        this.direction = direction;
    }

    public DirectedShip(int mastNumber) {
        this(mastNumber, Direction.UNDETERMINED);
    }

    public final int getMastNumber() {
        return mastNumber;
    }


    public final boolean isPlaced() {
        return false;
    }


    /**
     * Sets an orientation of the ship - if it's heading north, south, east, west OR if it's
     *
     * @param direction - south, west, east or north
     */
    public final DirectedShip direct(Direction direction) {
        this.direction = direction;
        return this;
    }


    public List<Coordinates> getTempCoordinates(Coordinates headCoordinates) {
        List<Coordinates> mastCoordinates = new ArrayList<>();
        mastCoordinates.add(headCoordinates);
        Coordinates currentCoordinates = headCoordinates;
        while (mastCoordinates.size() < mastNumber) {
            Coordinates nextCoord = direction.nextCoordinates();
            currentCoordinates = currentCoordinates.add(nextCoord);
            mastCoordinates.add(currentCoordinates);
        }
        return mastCoordinates;
    }
}
