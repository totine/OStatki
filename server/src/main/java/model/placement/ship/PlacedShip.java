package model.placement.ship;

import model.Coordinates;
import java.util.List;

/**
 * Represents a placed ship in the context of placing ships on a board. Knows its absolute mast coordinates and id.
 */
public final class PlacedShip implements Ship {
    private final List<Coordinates> mastCoordinates;

    public PlacedShip(List<Coordinates> mastCoordinates) {
        this.mastCoordinates = mastCoordinates;
    }

    private PlacedShip(DirectedShip directedShip, Coordinates headCoordinates) {
        mastCoordinates = directedShip.createTempCoordinatesList(headCoordinates);
    }

    public static PlacedShip createFromDirectedShip(DirectedShip directedShip, Coordinates headCoordinates) {
        return new PlacedShip(directedShip, headCoordinates);
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
