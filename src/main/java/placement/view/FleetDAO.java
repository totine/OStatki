package placement.view;

/**
 * Layer of abstraction over fleet generation.
 * De facto functional interface.
 */
public interface FleetDAO {
    GUIFleet getFleet();
}
