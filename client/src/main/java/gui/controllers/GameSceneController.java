package gui.controllers;


import connection.ServerConnection;
import gui.data.Player;
import gui.instance.ClientAppRunner;
import gui.printers.FieldPrinter;
import gui.printers.FleetView;
import gui.printers.ShipPrinter;
import gui.utility.ChangesObserver;
import gui.utility.Command;
import gui.utility.JSONConverter;
import gui.utility.ShotBoardHandler;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Optional;

import static gui.utility.CommandType.IM_READY;
import static javafx.scene.control.Alert.AlertType.INFORMATION;

/**
 * this Controller has aim to connect user interface with functions of the Battleship game.
 */
public class GameSceneController {
    private static final String YOUR_TURN_INFO = "Twoja tura";
    private static final String ENEMY_TURN_INFO = "Tura przeciwnika";

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
    @FXML
    private GridPane enemyBoard;
    private Player me;
    @FXML
    private Label whichTurnInfo;

    private InvalidationListener friendlyBoardEventListener = observable -> Platform.runLater(updateFriendlyBoard());
    private InvalidationListener informAboutRoundListener = observable -> Platform.runLater(checkIfYourTurn());
    private InvalidationListener gameEndListener = observable -> Platform.runLater(checkIfGameHasEnded());

    public void initialize() {
        appInstance = ClientAppRunner.getInstance();

        serverConnection = appInstance.getServerConnection();

        fleet = appInstance.getFleet();
        me = appInstance.getPlayer();
        String playerName = me.getName();

        currentPlayerName.setText(playerName);

        ShipPrinter.printFleet(fleet, friendlyBoard);
        FieldPrinter.insertFields(enemyBoard);
        observer = new ChangesObserver(friendlyBoardEventListener);
        observer.addListener(informAboutRoundListener);
        observer.addListener(gameEndListener);

        serverConnection.updateCommandGenerator(observer, friendlyBoardEventListener,
                informAboutRoundListener, gameEndListener);
        enemyBoard.setDisable(true);
    }

    private Runnable checkIfGameHasEnded() {
        return  () -> {
            if (serverConnection.isGameEnd()) {
                showWinnerInformationDialog();
            }
        };
    }

    private void showWinnerInformationDialog() {
        Alert alert = new Alert(INFORMATION);
        alert.setTitle("We have a winner!");
        alert.setHeaderText(null);
        alert.setContentText("The winner is: " + processPlayerName(serverConnection.getWinner()));
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

    private Runnable checkIfYourTurn() {
        return () -> {
            String currentPlayerName = processPlayerName(serverConnection.getCurrentPlayer());
            String myName = me.getName();
            if (currentPlayerName.equals(myName)) {
                enemyBoard.setDisable(false);
                showWhichTurnIsIt(YOUR_TURN_INFO);
            } else {
                enemyBoard.setDisable(true);
                showWhichTurnIsIt(ENEMY_TURN_INFO);
            }
        };
    }

    private static String processPlayerName(Player currentPlayer) {
        String beforeChanges = currentPlayer.getName();

        int firstLetterOfName = beforeChanges.indexOf(":") + 2;
        int lastLetterOfName = beforeChanges.lastIndexOf("\"");

        return beforeChanges.substring(firstLetterOfName, lastLetterOfName);
    }

    private void showWhichTurnIsIt(String whichTurn) {
        whichTurnInfo.setText(whichTurn);
    }

    @FXML
    private void sendPlayerReadyCommand() {
        Command clientReady = Command.withType(IM_READY, me);
        serverConnection.sendMessage(JSONConverter.convertToJSON(clientReady));
        playerReadyButton.setDisable(true);
    }

}
