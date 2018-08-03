package gui.controllers;


import connection.ServerConnection;
import gui.instance.ClientAppRunner;
import gui.printers.FieldPrinter;
import gui.printers.FleetView;
import gui.printers.ShipPrinter;
import gui.scenes.PlacementScene;
import gui.utility.ShotBoardHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * this Controller has aim to connect user interface with functions of the Battleship game.
 */
public class GameSceneController {
    private ClientAppRunner appInstance;
    private FleetView fleet;
    private ServerConnection serverConnection;

    @FXML
    private Label currentPlayerName;
    @FXML
    private Button backToPlacement;
    @FXML
    private GridPane friendlyBoard;
    @FXML
    private GridPane enemyBoard;

    public void initialize() {
        appInstance = ClientAppRunner.getInstance();

        serverConnection = appInstance.getServerConnection();

        fleet = appInstance.getFleet();

        String playerName = appInstance.getPlayer().getName();
        currentPlayerName.setText(playerName);

        ShipPrinter.printFleet(fleet, friendlyBoard);
        FieldPrinter.insertFields(enemyBoard);

        //updateFriendlyBoard();
    }

    private void updateFriendlyBoard() {
        ShotBoardHandler.friendlyShotReaction(serverConnection, friendlyBoard);
    }

    @FXML
    private void returnToShipPlacement() throws Exception {
        Window currentWindow = backToPlacement.getScene().getWindow();
        if (currentWindow instanceof Stage) {
            Stage currentStage = (Stage) currentWindow;
            PlacementScene placementScene = PlacementScene.create();
            placementScene.start(currentStage);
        }
    }

}