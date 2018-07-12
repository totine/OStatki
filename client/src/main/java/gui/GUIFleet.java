package gui;

import java.util.ArrayList;
import java.util.List;

/**
 * GUI representation of fleet object
 */
class GUIFleet {
    private final List<GUIShip> ships;

    GUIFleet() {
        this.ships = new ArrayList<>();
    }

    void add(GUIShip guiShip) {
        ships.add(guiShip);
    }

    List<GUIShip> getShipList() {
        return ships;
    }
}
