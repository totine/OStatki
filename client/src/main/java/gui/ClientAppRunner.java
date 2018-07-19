package gui;

/**
 * This is the singleton class. It's purpose is to
 * provide information flow between controller instances.
 */
class ClientAppRunner {

    private FleetView fleet;

    private static ClientAppRunner ourInstance = new ClientAppRunner();

    static ClientAppRunner getInstance() {
        return ourInstance;
    }

    private ClientAppRunner() {
    }

    void setFleet(FleetView fleet) {
        this.fleet = fleet;
    }

    FleetView getFleet() {
        return fleet;
    }

}
