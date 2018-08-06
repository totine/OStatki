package gui.controllers;

import connection.ServerConnection;
import gui.data.Player;
import gui.data.ServerInfo;
import gui.instance.ClientAppRunner;
import gui.scenes.PlacementScene;
import gui.utility.Command;
import gui.utility.JSONConverter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import static gui.utility.CommandType.SEND_PLAYER;
import static javafx.scene.control.Alert.AlertType.ERROR;

public class PlayerSceneController {

    public static final int MAX_PLAYER_SUFFIX = 999;
    private static final int PORT_NUMBER = 7777;
    @FXML
    private ComboBox<ServerInfo> serverNameComboBox;
    @FXML
    private TextField playerNameField;
    @FXML
    private Button startPlacement;
    @FXML
    private AnchorPane mainPane;

    private ClientAppRunner appInstance;


    public void initialize() {
        appInstance = ClientAppRunner.getInstance();
        appInstance.addNewServer(ServerInfo.create("localhost", PORT_NUMBER));

        serverNameComboBox.setItems(appInstance.getServerInfoList());
        serverNameComboBox.getSelectionModel().select(0);
    }

    @FXML
    private void onEnterClickedStart(KeyEvent event) throws Exception {
        if (event.getCode().equals(KeyCode.ENTER)) {
            startPlacing();
        }
    }

    @FXML
    private void startPlacing() throws Exception {
        Window currentWindow = startPlacement.getScene().getWindow();
        if (tryToConnect()) {
            savePlayer();
            if (currentWindow instanceof Stage) {
                initNewScene(currentWindow);
            }
        }
    }

    private boolean tryToConnect() {
        ClientAppRunner appInstance = ClientAppRunner.getInstance();
        if (!appInstance.initializeServerConnection(getSelectedServerInfo())) {
            serverUnreachable();
            return false;
        }
        return true;
    }

    private void savePlayer() {
        Player currentPlayer = Player.create(getPlayerName(), createIDForPlayer());
        appInstance.setPlayer(currentPlayer);

        Command addPlayerCommand = Command.withType(SEND_PLAYER, currentPlayer);
        ServerConnection serverConnection = appInstance.getServerConnection();

        serverConnection.sendMessage(JSONConverter.convertToJSON(addPlayerCommand));
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

    private void serverUnreachable() {
        Alert alert = new Alert(ERROR);
        alert.setTitle("Cannot connect to the server");
        alert.setHeaderText(null);
        alert.setContentText("Selected server is unreachable for now, "
                + "try again later or choose other host and port.");
        alert.showAndWait();
    }

    private String getPlayerName() {
        String providedName = playerNameField.getText();
        if (providedName.isEmpty()) {
            return createRandomName();
        }
        return providedName;
    }

    private String createRandomName() {
        StringBuilder stringBuilder = new StringBuilder("Player");
        stringBuilder.append(getRandomNumber());
        return stringBuilder.toString();
    }

    private int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(MAX_PLAYER_SUFFIX) + 1;
    }

    private int createIDForPlayer() {
        return getRandomNumber();
    }

    @FXML
    private void addServerAddress() {
        Dialog<ButtonType> addNewServerDialog = initDialogPane();
        FXMLLoader fxmlLoader = initLoader();
        tryToAddFXML(addNewServerDialog, fxmlLoader);

        addControlButtons(addNewServerDialog);

        addNewServerIfResultPresent(addNewServerDialog, fxmlLoader);
    }

    private Dialog<ButtonType> initDialogPane() {
        Dialog<ButtonType> addNewServerDialog = new Dialog<>();
        addNewServerDialog.initOwner(mainPane.getScene().getWindow());
        return addNewServerDialog;
    }

    private FXMLLoader initLoader() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/add_new_server_dialog.fxml"));
        return fxmlLoader;
    }

    private void tryToAddFXML(Dialog<ButtonType> addNewServerDialog, FXMLLoader fxmlLoader) {
        try {
            addNewServerDialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addControlButtons(Dialog<ButtonType> addNewServerDialog) {
        addNewServerDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        addNewServerDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
    }

    private void addNewServerIfResultPresent(Dialog<ButtonType> addNewServerDialog, FXMLLoader fxmlLoader) {
        AddNewServerController controller = fxmlLoader.getController();

        Optional<ButtonType> result = addNewServerDialog.showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.OK)) {
            controller.confirmAddingNewServer();
        }
    }
}
