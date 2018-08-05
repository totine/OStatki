package gui.printers;

import java.util.ArrayList;
import java.util.List;

/**
 * GUI representation of fleet object
 */
public class FleetView {
    private final List<ShipView> shipList;

    public FleetView() {
        this.shipList = new ArrayList<>();
    }

    public void add(ShipView shipView) {
        shipList.add(shipView);
    }

    public List<ShipView> getShipList() {
        return shipList;
    }

    @Override
    public String toString() {
        return "Fleet{"
                + "shipList="
                + shipList.toString()
                + '}';
    }
}
