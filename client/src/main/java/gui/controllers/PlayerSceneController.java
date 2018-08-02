package gui.controllers;

import connection.ServerConnection;
import gui.data.Player;
import gui.data.ServerInfo;
import gui.instance.ClientAppRunner;
import gui.scenes.PlacementScene;
import gui.utility.Command;
import gui.utility.JSONConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import static gui.utility.CommandType.SEND_PLAYER;

public class PlayerSceneController {

    private static final int PORT_NUMBER = 7777;
    @FXML
    private ComboBox<ServerInfo> serverNameComboBox;
    @FXML
    private TextField playerNameField;
    @FXML
    private Button startPlacement;
    private ClientAppRunner appInstance;

    public void initialize() {
        appInstance = ClientAppRunner.getInstance();

        ObservableList<ServerInfo> listOfServers = FXCollections
                .observableArrayList();
        listOfServers.add(ServerInfo.create("10.30.1.170", PORT_NUMBER));
        listOfServers.add(ServerInfo.create("localhost", PORT_NUMBER));

        serverNameComboBox.setItems(listOfServers);
        serverNameComboBox.getSelectionModel().select(0);
    }

    @FXML
    private void startPlacing() throws Exception {
        Window currentWindow = startPlacement.getScene().getWindow();
        tryToConnect();
        savePlayer();
        if (currentWindow instanceof Stage) {
            initNewScene(currentWindow);
        }
    }



    private void initNewScene(Window currentWindow) throws Exception {
        if (currentWindow instanceof Stage) {
            Stage currentStage = (Stage) currentWindow;
            PlacementScene placementScene = PlacementScene.create();
            placementScene.start(currentStage);
        }
    }

    private ServerInfo getSelectedServerInfo() {
        return serverNameComboBox.getSelectionModel().getSelectedItem();
    }

    private void savePlayer() {
        Player currentPlayer = Player.create(playerNameField.getText());
        appInstance.setPlayer(currentPlayer);

        Command addPlayerCommand = Command.withType(SEND_PLAYER, currentPlayer);
        ServerConnection serverConnection = appInstance.getServerConnection();


        serverConnection.sendMessage(JSONConverter.convertToJSON(addPlayerCommand));
    }

    private void tryToConnect() {
        ClientAppRunner appInstance = ClientAppRunner.getInstance();
        appInstance.initializeServerConnection(getSelectedServerInfo());
    }
}
