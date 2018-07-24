package gui.generator;


import gui.printers.FleetDAO;
import gui.printers.FleetView;

/**
 * GUI-side fleet random generator
 */
public class RandomFleet implements FleetDAO {
    @Override
    public final FleetView getGUIFleet() {
         FleetView fleetView = new FleetView();
        return fleetView;
    }
}
