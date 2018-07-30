package gui.printers;

import model.Coordinates;


import java.util.List;

/**
 * GUI representation of ship object
 */
public class ShipView {
    private final List<Coordinates> mastCoordinates;

    public ShipView(List<Coordinates> structure) {
        this.mastCoordinates = structure;
    }

    List<Coordinates> getPositionCoordinates() {
        return mastCoordinates;
    }


}
