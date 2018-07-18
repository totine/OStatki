package gui;

import placement.model.Coordinates;

import java.util.List;

/**
 * GUI representation of ship object
 */
class ShipView {
    private List<Coordinates> mastCoordinates;

    ShipView(List<Coordinates> structure) {
        this.mastCoordinates = structure;
    }

    List<Coordinates> getPositionCoordinates() {
        return mastCoordinates;
    }


}
