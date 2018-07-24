package model.placement.ship;

import model.Coordinates;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a ship, that is directed but not placed in the context of placing ships on a board.
 * Knows its direction and mast number.
 */
public class DirectedShip implements Ship {
    private final int mastNumber;
    private Direction direction;

    public DirectedShip(int mastCount, Direction direction) {
        this.mastNumber = mastCount;
        this.direction = direction;
    }

    public final boolean isPlaced() {
        return false;
    }

    /**
     * Returns this ship with a updated direction
     *
     * @param direction - south, west, east or north
     */
    public final DirectedShip direct(Direction direction) {
        this.direction = direction;
        return this;
    }

    public final List<Coordinates> createTempCoordinatesList(Coordinates headCoordinates) {
        return createMastCoordinates(new ArrayList<>(), headCoordinates, mastNumber);
    }

    private List<Coordinates> createMastCoordinates(
            List<Coordinates> mastCoordinatesList, Coordinates headCoordinates, int mastNumber) {
        if (mastCoordinatesList.size() == mastNumber) {
            return mastCoordinatesList;
        }
        mastCoordinatesList.add(headCoordinates);
        return createMastCoordinates(mastCoordinatesList, headCoordinates.add(direction.nextCoordinates()), mastNumber);
    }
}
