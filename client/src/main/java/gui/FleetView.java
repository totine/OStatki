package gui;

import java.util.ArrayList;
import java.util.List;

/**
 * GUI representation of fleet object
 */
class FleetView {
    private final List<ShipView> ships;

    FleetView() {
        this.ships = new ArrayList<>();
    }

    void add(ShipView shipView) {
        ships.add(shipView);
    }

    List<ShipView> getShipList() {
        return ships;
    }
}
