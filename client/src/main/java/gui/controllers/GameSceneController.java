package gui.controllers;


import connection.ServerConnection;
import gui.instance.ClientAppRunner;
import gui.printers.FieldPrinter;
import gui.printers.FleetView;
import gui.printers.ShipPrinter;
import gui.scenes.PlacementScene;
import gui.utility.FriendlyChangesObserver;
import gui.utility.ShotBoardHandler;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.INFORMATION;

/**
 * this Controller has aim to connect user interface with functions of the Battleship game.
 */
public class GameSceneController {
    private ClientAppRunner appInstance;
    private FleetView fleet;
    private ServerConnection serverConnection;
    private FriendlyChangesObserver friendlyChangesObserver;
    @FXML
    private Label currentPlayerName;
    @FXML
    private Button backToPlacement;
    @FXML
    private GridPane friendlyBoard;
    private InvalidationListener listener = observable -> Platform.runLater(updateFriendlyBoard());
    @FXML
    private GridPane enemyBoard;


    public void initialize() {
        appInstance = ClientAppRunner.getInstance();

        serverConnection = appInstance.getServerConnection();

        fleet = appInstance.getFleet();

        String playerName = appInstance.getPlayer().getName();
        currentPlayerName.setText(playerName);

        Runnable endGame = () -> {
            if (serverConnection.isGameEnd()) {
                showWinnerInformationDialog();
            }
        };

        ShipPrinter.printFleet(fleet, friendlyBoard);
        FieldPrinter.insertFields(enemyBoard, endGame);
        friendlyChangesObserver = new FriendlyChangesObserver(listener);

        serverConnection.updateCommandGenerator(listener, friendlyChangesObserver);
    }

    private Runnable updateFriendlyBoard() {
        return () -> {
            ShotBoardHandler.friendlyShotReaction(serverConnection, friendlyBoard);
            if (serverConnection.isGameEnd()) {
                showWinnerInformationDialog();
            }
        };
    }

    private void showWinnerInformationDialog() {
        Alert alert = new Alert(INFORMATION);
        alert.setTitle("We have a winner!");
        alert.setHeaderText(null);
        alert.setContentText("The winner is: " + serverConnection.getWinner());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            System.exit(0);
        }
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
