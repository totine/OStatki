package gui;

/**
 * Layer of abstraction over fleet generation.
 * De facto functional interface.
 */
public interface FleetDAO {
    GUIFleet getGUIFleet();
}
