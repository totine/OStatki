package placement.view;

import placement.controller.FleetController;
import placement.model.Fleet;
import placement.model.ship.Ship;

public class FleetFromRandomGenerator implements FleetDAO {
    @Override
    public GUIFleet getFleet() {
        Fleet fleetFromRandom = FleetController.generatePlacedStandardFleet();
        GUIFleet guiFleet = new GUIFleet();
        for (Ship ship : fleetFromRandom.getShipList()) {
            GUIShip guiShip = new GUIShip(ship.getId(), ship.getPositionCoordinates());
            guiFleet.add(guiShip);
        }
        return guiFleet;
    }
}
