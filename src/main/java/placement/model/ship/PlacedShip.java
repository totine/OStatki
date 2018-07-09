package placement.model.ship;

import placement.model.Coordinates;

import java.util.List;

/**
 * Represents a placed ship in the context of placing ships on a board. Knows its absolute mast coordinates and id.
 */
public final class PlacedShip implements Ship {
    private final List<Coordinates> mastCoordinates;

    public PlacedShip(List<Coordinates> mastCoordinates) {
        this.mastCoordinates = mastCoordinates;
    }

    public PlacedShip(DirectedShip directedShip, Coordinates headCoordinates) {
        mastCoordinates = directedShip.getTempCoordinates(headCoordinates);
    }

    @Override
    public boolean isPlaced() {
        return true;
    }

    public List<Coordinates> getMastCoordinates() {
        return mastCoordinates;
    }

    @Override
    public DirectedShip direct(Direction direction) {
        return new DirectedShip(mastCoordinates.size(), direction);
    }

}


