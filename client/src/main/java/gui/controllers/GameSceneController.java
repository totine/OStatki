package gui.controllers;


import connection.ServerConnection;
import gui.data.Player;
import gui.instance.ClientAppRunner;
import gui.printers.FieldPrinter;
import gui.printers.FleetView;
import gui.printers.ShipPrinter;
import gui.scenes.PlacementScene;
import gui.utility.ChangesObserver;
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
import java.util.logging.Logger;

import static javafx.scene.control.Alert.AlertType.INFORMATION;

/**
 * this Controller has aim to connect user interface with functions of the Battleship game.
 */
public class GameSceneController {
    private ClientAppRunner appInstance;
    private FleetView fleet;
    private ServerConnection serverConnection;
    private ChangesObserver observer;
    @FXML
    private Label currentPlayerName;
    @FXML
    private Button playerReadyButton;
    @FXML
    private GridPane friendlyBoard;
    private InvalidationListener friendlyBoardEventListener = observable -> Platform.runLater(updateFriendlyBoard());
    @FXML
    private GridPane enemyBoard;
    private InvalidationListener informAboutTurnListener = observable -> Platform.runLater(checkIfYourTurn());
    private Player me;

    public void initialize() {
        appInstance = ClientAppRunner.getInstance();

        serverConnection = appInstance.getServerConnection();

        fleet = appInstance.getFleet();
        me = appInstance.getPlayer();
        String playerName = me.getName();

        currentPlayerName.setText(playerName);

        Runnable endGame = () -> {
            if (serverConnection.isGameEnd()) {
                showWinnerInformationDialog();
            }
        };

        ShipPrinter.printFleet(fleet, friendlyBoard);
        FieldPrinter.insertFields(enemyBoard, endGame);
        observer = new ChangesObserver(friendlyBoardEventListener);
        observer.addListener(informAboutTurnListener);

        serverConnection.updateCommandGenerator(observer, friendlyBoardEventListener, informAboutTurnListener);
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

    private Runnable updateFriendlyBoard() {
        return () -> {
            ShotBoardHandler.friendlyShotReaction(serverConnection, friendlyBoard);
            if (serverConnection.isGameEnd()) {
                showWinnerInformationDialog();
            }
        };
    }
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Runnable checkIfYourTurn() {
        return () -> {
            Player currentPlayer = serverConnection.getCurrentPlayer();
            logger.warning("Me player is: " + me);
            logger.warning("Current player is: " + currentPlayer);
            if (currentPlayer.equals(me)) {
                logger.warning("UDAŁO SIĘ!!!");
                enemyBoard.setDisable(false);
                showYourTurnDialog(currentPlayer);
            } else {
                enemyBoard.setDisable(true);
            }
        };
    }

    private void showYourTurnDialog(Player currentPlayer) {
        Alert alert = new Alert(INFORMATION);
        alert.setTitle("Turn of player");
        alert.setHeaderText(null);
        alert.setContentText("This is your turn " + currentPlayer);
        alert.showAndWait();
    }

    @FXML
    private void sendPlayerReadyCommand() {
        
    }

}
