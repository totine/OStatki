package gui.controllers;

import connection.ServerConnection;
import gui.instance.ClientAppRunner;
import gui.printers.FleetView;
import gui.printers.ShipPrinter;
import gui.scenes.GameScene;
import gui.scenes.PlayerScene;
import gui.utility.Command;
import gui.utility.CommandType;
import gui.utility.JSONConverter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * JavaFX standard application controller class
 */
public class PlacementSceneController {

    private FleetView fleet;
    private ClientAppRunner appInstance;
    private ServerConnection serverConnection;

    @FXML
    private GridPane printingBoard;
    @FXML
    private Button startButton;
    @FXML
    private Button backToNameChoosing;
    @FXML
    private Text playerNameText;

    public void initialize() {
        appInstance = ClientAppRunner.getInstance();

        String playerName = appInstance.getPlayer().getName();
        playerNameText.setText(playerName);

        fleet = appInstance.getFleet();

        serverConnection = appInstance.getServerConnection();

        disableStartWhenNoFleet();

        ShipPrinter.printFleet(fleet, printingBoard);
    }

    @FXML
    private void placeRandom() {
        serverConnection.sendMessage(prepareAskForFleetCommand());
        getFleetFromServer();
    }

    private void getFleetFromServer() {

        printingBoard.getChildren().removeIf(node -> node instanceof Shape);

        try {
            fleet = serverConnection.getFleetFromQueue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(fleet.getShipList());
        ShipPrinter.printFleet(fleet, printingBoard);
        startButton.setDisable(false);
    }

    private String prepareAskForFleetCommand() {
        Command askForFleet = Command.empty(CommandType.ASK_FOR_FLEET);
        return JSONConverter.convertToJSON(askForFleet);
    }

    @FXML
    private void startTheGame() throws Exception {
        Window currentWindow = startButton.getScene().getWindow();
        if (currentWindow instanceof Stage) {
            appInstance.setFleet(fleet);
            Command sendFleet = Command.withType(CommandType.SEND_FLEET, FleetView.class);
            serverConnection.sendMessage(JSONConverter.convertToJSON(sendFleet));
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

    private void disableStartWhenNoFleet() {
        boolean noFleetInitialized = fleet == null;
        startButton.setDisable(noFleetInitialized);
    }

}
