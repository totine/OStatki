package gui;

import connection.GUIServerConnection;

/**
 * This is the singleton class. It's purpose is to
 * provide information flow between controller instances.
 */
class ClientAppRunner {

    private FleetView fleet;
    private GUIServerConnection serverConnection;
    private static final String HOST = "localhost";
    private static final int PORT = 7777;

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

    void initializeServerConnection() {
        if (checkIfDisconnected()) {
            connectToServer();
        }
    }

    private boolean checkIfDisconnected() {
        return null == serverConnection;
    }

    private void connectToServer() {
        serverConnection = GUIServerConnection.initializeConnection(PORT, HOST);
        serverConnection.createServerConnection();
    }

    GUIServerConnection getServerConnection() {
        return serverConnection;
    }

}
