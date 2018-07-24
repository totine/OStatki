package gui;


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
