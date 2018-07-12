package gui;

import placement.controller.FleetController;
import placement.model.Fleet;
import placement.model.ship.PlacedShip;


/**
 * GUI-side fleet randomizer
 */
public class FleetFromRandomGenerator implements FleetDAO {
    @Override
    public final GUIFleet getGUIFleet() {
        Fleet<PlacedShip> fleetFromRandom = FleetController.generatePlacedStandardFleet();
        GUIFleet guiFleet = new GUIFleet();
        for (PlacedShip ship : fleetFromRandom.getShipList()) {
            GUIShip guiShip = new GUIShip(ship.getMastCoordinates());
            guiFleet.add(guiShip);
        }
        return guiFleet;
    }
}