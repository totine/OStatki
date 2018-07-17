package gui;


import connection.GUIServerConnection;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * JavaFX standard application controller class
 */
public class GUIPlacementController {

    private static final int FIELD_WIDTH = 50;
    private static final int FIELD_HEIGHT = 50;
    private static final String HOST = "localhost";
    private static final int PORT = 7777;
    private static final int NUMBER_OF_THREADS = 8;
    private static final int MAX_ITERATION_COUNT = 100;
    private ExecutorService executorService;

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
        executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
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
        new Thread(() -> serverConnection.sendMessage(message)).start();
    }

    @FXML
    private void processMessagesFromServer() throws InterruptedException, ExecutionException {
        Callable<String> takeMessage = createServerCall();
        List<Future<String>> messagesFromServer = createFutureMessagesList();
        retrieveListOfMessages(takeMessage, messagesFromServer);
        addToTextArea(messagesFromServer);
        executorService.shutdown();
    }

    private Future<String> callServer(Callable<String> taskToCallServer) {
        return executorService.submit(taskToCallServer);
    }

    private void addToTextArea(List<Future<String>> listOfMessages) throws InterruptedException, ExecutionException {
        for (Future<String> futureMessage : listOfMessages) {
            String message = futureMessage.get();
            outputFromServer.appendText(message);
        }
    }

    private Callable<String> createServerCall() {
        return () -> serverConnection.getMessage();
    }

    private void retrieveListOfMessages(Callable<String> takeMessage, List<Future<String>> messagesFromServer) {
        for (int i = 0; i < MAX_ITERATION_COUNT; i++) {
            Future<String> resultOfCalling = callServer(takeMessage);
            messagesFromServer.add(resultOfCalling);
        }
    }

    private List<Future<String>> createFutureMessagesList() {
        return new ArrayList<>();
    }


}
