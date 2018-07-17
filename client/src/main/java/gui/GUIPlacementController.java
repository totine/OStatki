package gui;


import connection.GUIServerConnection;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.Window;
import placement.model.Coordinates;

/**
 * JavaFX standard application controller class
 */
public class GUIPlacementController {

    private static final int FIELD_WIDTH = 50;
    private static final int FIELD_HEIGHT = 50;
    private static final String HOST = "localhost";
    private static final int PORT = 7777;
    private static final String NEW_LINE = "\n";

    private GUIServerConnection serverConnection;

    @FXML
    private GridPane guiBoard;
    @FXML
    private Button startButton;
    @FXML
    private TextField connectionTextBox;
    @FXML
    private TextArea outputFromServer;


    public void initialize() {
        startButton.setDisable(true);
        serverConnection = GUIServerConnection.initializeConnection(PORT, HOST);
        serverConnection.createServer();
    }


    @FXML
    private void placeRandom() {
        guiBoard.getChildren().removeIf(node -> node instanceof Shape);
        FleetDAO fleetDAO = new FleetFromRandomGenerator();
        GUIFleet fleet = fleetDAO.getGUIFleet();
        for (GUIShip ship : fleet.getShipList()) {
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

    private void printShip(GUIShip ship) {

        for (Coordinates coord : ship.getPositionCoordinates()) {
            Shape next = createMastRepresentation();
            guiBoard.add(next, coord.getX(), coord.getY());
        }


    }

    @FXML
    private void startTheGame() throws Exception {
        Window currentWindow = startButton.getScene().getWindow();
        if (currentWindow instanceof Stage) {
            Stage currentStage = (Stage) currentWindow;
            GUIGameScreen guiGameScreen = new GUIGameScreen();
            guiGameScreen.start(currentStage);
        }
    }

    @FXML
    private void sendMessageToServer() {
        String message = connectionTextBox.getText();
        serverConnection.sendMessage(message);
        connectionTextBox.clear();
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
