package gui.instance;

import connection.ServerConnection;
import gui.data.Player;
import gui.data.ServerInfo;
import gui.printers.FleetView;

/**
 * This is the singleton class. It's purpose is to
 * provide information flow between controller instances.
 */
public class ClientAppRunner {

    private FleetView fleet;
    private ServerConnection serverConnection;
    private Player currentPlayer;

    private static ClientAppRunner ourInstance = new ClientAppRunner();

    public static ClientAppRunner getInstance() {
        return ourInstance;
    }

    private ClientAppRunner() {
    }

    public void setFleet(FleetView fleet) {
        this.fleet = fleet;
    }
    public FleetView getFleet() {
        return fleet;
    }

    public void setPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public Player getPlayer() {
        return currentPlayer;
    }

    public ServerConnection getServerConnection() {
        return serverConnection;
    }

    public void initializeServerConnection(ServerInfo serverInfo) {
        if (checkIfDisconnected()) {
            connectToServer(serverInfo);
        }
    }

    private boolean checkIfDisconnected() {
        return null == serverConnection;
    }

    private void connectToServer(ServerInfo serverInfo) {
        int port = serverInfo.getPort();
        String host = serverInfo.getHost();
        serverConnection = ServerConnection.initializeConnection(host, port);
        serverConnection.createServerConnection();
    }
}