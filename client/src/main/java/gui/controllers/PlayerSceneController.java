package gui.controllers;

import gui.data.Player;
import gui.data.ServerInfo;
import gui.instance.ClientAppRunner;
import gui.scenes.PlacementScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PlayerSceneController {

    @FXML
    private ComboBox<ServerInfo> serverNameComboBox;
    @FXML
    private TextField playerNameField;
    @FXML
    private Button startPlacement;

    private ClientAppRunner appInstance;
    private ObservableList<ServerInfo> listOfServers;
    private static final int PORT_NUMBER = 7777;

    public void initialize() {
        appInstance = ClientAppRunner.getInstance();
        listOfServers = FXCollections.observableArrayList(ServerInfo.create("localhost", PORT_NUMBER));
        serverNameComboBox.setItems(listOfServers);
        serverNameComboBox.getSelectionModel().select(0);
    }

    @FXML
    private void startPlacing() throws Exception {
        Window currentWindow = startPlacement.getScene().getWindow();
        savePlayer();
        tryToConnect();
        if (currentWindow instanceof Stage) {
            initNewScene(currentWindow);
        }
    }

    private void tryToConnect() {
        appInstance = ClientAppRunner.getInstance();
        appInstance.initializeServerConnection(getSelectedServerInfo());
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
    }
}
