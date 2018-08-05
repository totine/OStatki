package gui.instance;

import connection.ServerConnection;
import gui.data.Player;
import gui.data.ServerInfo;
import gui.printers.FleetView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the singleton class. It's purpose is to
 * provide information flow between controller instances.
 */
public class ClientAppRunner {

    private static ClientAppRunner ourInstance = new ClientAppRunner();
    private FleetView fleet;
    private ServerConnection serverConnection;
    private Player currentPlayer;
    private ObservableList<ServerInfo> serverInfoList = FXCollections.observableArrayList();

    private ClientAppRunner() {
    }

    public static ClientAppRunner getInstance() {
        return ourInstance;
    }

    public ObservableList<ServerInfo> getServerInfoList() {
        return serverInfoList;
    }

    public FleetView getFleet() {
        return fleet;
    }

    public void setFleet(FleetView fleet) {
        this.fleet = fleet;
    }

    public Player getPlayer() {
        return currentPlayer;
    }

    public void setPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ServerConnection getServerConnection() {
        return serverConnection;
    }

    public boolean initializeServerConnection(ServerInfo serverInfo) {
        try {
            if (checkIfDisconnected()) {
                connectToServer(serverInfo);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean checkIfDisconnected() {
        return null == serverConnection;
    }

    private void connectToServer(ServerInfo serverInfo) throws Exception {
        int port = serverInfo.getPort();
        String host = serverInfo.getHost();
        serverConnection = ServerConnection.initializeConnection(host, port);
        serverConnection.createServerConnection();

    }

    public void addNewServer(ServerInfo serverInfo) {
        serverInfoList.add(serverInfo);
    }
}
