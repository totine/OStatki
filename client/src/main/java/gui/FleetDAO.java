package gui;

/**
 * Layer of abstraction over fleet generation.
 * In fact functional interface.
 */
interface FleetDAO {
    FleetView getGUIFleet();
}
