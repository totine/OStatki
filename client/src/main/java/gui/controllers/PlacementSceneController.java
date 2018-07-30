package gui.controllers;

import gui.instance.ClientAppRunner;
import gui.printers.FleetView;
import gui.receivers.RandomFleet;
import gui.printers.ShipPrinter;
import gui.scenes.GameScene;
import gui.scenes.PlayerScene;
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

    private static final String ASK_FOR_FLEET = "ASK_FOR_FLEET";
    private static final String CLIENT_READY = "CLIENT_READY";

    private FleetView fleet;
    private ClientAppRunner appInstance;

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

    private void disableStartWhenNoFleet() {
        boolean noFleetInitialized = fleet == null;
        startButton.setDisable(noFleetInitialized);
    }

}
