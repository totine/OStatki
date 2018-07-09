package placement.view;

import placement.model.Coordinates;

import java.util.List;

/**
 * GUI representation of ship object
 */
class GUIShip {
    private List<Coordinates> structure;

    GUIShip(List<Coordinates> structure) {
        this.structure = structure;
    }

    List<Coordinates> getPositionCoordinates() {
        return structure;
    }


}
