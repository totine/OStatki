package gui.printers;

import java.util.ArrayList;
import java.util.List;

/**
 * GUI representation of fleet object
 */
public class FleetView {
    private final List<ShipView> ships;

    public FleetView() {
        this.ships = new ArrayList<>();
    }

    public void add(ShipView shipView) {
        ships.add(shipView);
    }

    List<ShipView> getShipList() {
        return ships;
    }
}
