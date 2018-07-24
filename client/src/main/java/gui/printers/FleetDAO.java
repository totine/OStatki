package gui.printers;

/**
 * Layer of abstraction over fleet generation.
 * In fact functional interface.
 */
public interface FleetDAO {
    FleetView getGUIFleet();
}
