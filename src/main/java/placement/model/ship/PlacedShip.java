package placement.model.ship;

import placement.model.Coordinates;

import java.util.List;
import java.util.UUID;

/**
 * Represents a placed ship in the context of placing ships on a board. Knows its absolute mast coordinates and id.
 */
public final class PlacedShip implements Ship {
    private final String id;
    private final List<Coordinates> mastCoordinates;

    public PlacedShip(List<Coordinates> mastCoordinates) {
        id = UUID.randomUUID().toString();
        this.mastCoordinates = mastCoordinates;
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
        return null;
    }

    public String getId() {
        return id;
    }
}


