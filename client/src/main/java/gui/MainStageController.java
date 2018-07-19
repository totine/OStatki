package gui;


import connection.GUIServerConnection;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.Window;
import placement.model.Coordinates;

/**
 * JavaFX standard application controller class
 */
public class MainStageController {

    private static final int FIELD_WIDTH = 50;
    private static final int FIELD_HEIGHT = 50;
    private static final String HOST = "localhost";
    private static final int PORT = 7777;
    private static final String NEW_LINE = "\n";

    private FleetView fleet;
    private GUIServerConnection serverConnection;
    private ClientAppRunner appInstance;

    @FXML
    private GridPane guiBoard;
    @FXML
    private Button startButton;
    @FXML
    private TextField connectionTextBox;
    @FXML
    private TextArea outputFromServer;


    public void initialize() {
        appInstance = ClientAppRunner.getInstance();
        startButton.setDisable(true);
        serverConnection = GUIServerConnection.initializeConnection(PORT, HOST);
        serverConnection.createServer();
    }


    @FXML
    private void placeRandom() {
        guiBoard.getChildren().removeIf(node -> node instanceof Shape);
        FleetDAO fleetDAO = new RandomFleet();
        fleet = fleetDAO.getGUIFleet();
        for (ShipView ship : fleet.getShipList()) {
            printShip(ship);
        }
        startButton.setDisable(false);
    }

    private Rectangle createMastRepresentation() {
        Rectangle mast = new Rectangle();
        mast.setHeight(FIELD_HEIGHT);
        mast.setWidth(FIELD_WIDTH);

        return mast;
    }

    private void printShip(ShipView ship) {
        for (Coordinates coordinates : ship.getPositionCoordinates()) {
            Shape next = createMastRepresentation();
            guiBoard.add(next, coordinates.getX(), coordinates.getY());
        }
    }

    @FXML
    private void startTheGame() throws Exception {
        Window currentWindow = startButton.getScene().getWindow();
        if (currentWindow instanceof Stage) {
            appInstance.setFleet(fleet);
            Stage currentStage = (Stage) currentWindow;
            GameStage gameStage = GameStage.createGameStage();
            gameStage.start(currentStage);
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


}
