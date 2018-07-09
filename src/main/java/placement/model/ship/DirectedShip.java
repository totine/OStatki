package placement.model.ship;

import placement.model.Coordinates;

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

    public final int getMastNumber() {
        return mastNumber;
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


    public final List<Coordinates> getTempCoordinates(Coordinates headCoordinates) {
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
