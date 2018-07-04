package placement.view;

import placement.model.Coordinates;

import java.util.List;

class GUIShip {
    private String id;
    private List<Coordinates> structure;

    GUIShip(String id, List<Coordinates> structure) {
        this.id = id;
        this.structure = structure;
    }

    List<Coordinates> getPositionCoordinates() {
        return structure;
    }
}
