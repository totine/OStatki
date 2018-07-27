package gui.controllers;


import connection.ServerConnection;
import gui.instance.ClientAppRunner;
import gui.printers.FleetView;
import gui.receivers.RandomFleet;
import gui.printers.ShipPrinter;
import gui.scenes.GameScene;
import gui.scenes.PlayerScene;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * JavaFX standard application controller class
 */
public class PlacementSceneController {

    private static final String NEW_LINE = "\n";
    private static final String ASK_FOR_FLEET = "ASK_FOR_FLEET";
    private static final String CLIENT_READY = "CLIENT_READY";

    private FleetView fleet;
    private ServerConnection serverConnection;
    private ClientAppRunner appInstance;

    @FXML
    private GridPane printingBoard;
    @FXML
    private Button startButton;
    @FXML
    private TextField connectionTextBox;
    @FXML
    private TextArea outputFromServer;
    @FXML
    private Button backToNameChoosing;
    @FXML
    private Text playerNameText;


    public void initialize() {
        appInstance = ClientAppRunner.getInstance();
        String playerName = appInstance.getPlayer().getName();
        playerNameText.setText(playerName);
        serverConnection = appInstance.getServerConnection();
        fleet = appInstance.getFleet();
        disableStartWhenNoFleet();
        ShipPrinter.printFleet(fleet, printingBoard);
    }


    @FXML
    private void placeRandom() {
        appInstance.getServerConnection().sendMessage(ASK_FOR_FLEET);

        String message = appInstance.getServerConnection().getMessage();

        printingBoard.getChildren().removeIf(node -> node instanceof Shape);
        RandomFleet generatedFleet = new RandomFleet();
        fleet = generatedFleet.getGUIFleet(message);
        ShipPrinter.printFleet(fleet, printingBoard);
        startButton.setDisable(false);
    }

    @FXML
    private void startTheGame() throws Exception {
        Window currentWindow = startButton.getScene().getWindow();
        if (currentWindow instanceof Stage) {
            appInstance.setFleet(fleet);
            appInstance.getServerConnection().sendMessage(CLIENT_READY);
            Stage currentStage = (Stage) currentWindow;
            GameScene gameScene = GameScene.create();
            gameScene.start(currentStage);
        }
    }

    @FXML
    private void backToNameChoosing() throws Exception {
        Window currentWindow = backToNameChoosing.getScene().getWindow();
        if (currentWindow instanceof Stage) {
            Stage currentStage = (Stage) currentWindow;
            PlayerScene playerScene = PlayerScene.create();
            playerScene.start(currentStage);
        }
    }

    @FXML
    private void submitMessageEnter(KeyEvent pressedKey) {
        boolean enterIsPressed = pressedKey.getCode().equals(KeyCode.ENTER);
        if (enterIsPressed) {
            sendMessageToServer();
        }
    }

    @FXML
    private void submitMessageClick() {
        sendMessageToServer();
    }

    private void sendMessageToServer() {
        String message = connectionTextBox.getText();
        serverConnection.sendMessage(message);
        connectionTextBox.clear();
        connectionTextBox.requestFocus();
    }

    @FXML
    private void processMessagesFromServer() {
        Task<String> messageTask = createMessageTask();
        new Thread(messageTask).start();
    }

    private Task<String> createMessageTask() {
        return new Task<String>() {
            @Override
            protected String call() {
                String serverMessage = serverConnection.getMessage();
                Platform.runLater(addToTextArea(serverMessage));
                return serverMessage;
            }
        };
    }

    private Runnable addToTextArea(String message) {
        return () -> outputFromServer.appendText(message + NEW_LINE);
    }

    private void disableStartWhenNoFleet() {
        boolean noFleetInitialized = fleet == null;
        startButton.setDisable(noFleetInitialized);
    }

}
