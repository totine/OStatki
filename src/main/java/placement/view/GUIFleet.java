package placement.view;

import java.util.ArrayList;
import java.util.List;

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
