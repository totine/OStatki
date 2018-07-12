package gui;

import placement.model.Coordinates;

import java.util.List;

/**
 * GUI representation of ship object
 */
class GUIShip {
    private List<Coordinates> mastCoordinates;

    GUIShip(List<Coordinates> structure) {
        this.mastCoordinates = structure;
    }

    List<Coordinates> getPositionCoordinates() {
        return mastCoordinates;
    }


}
