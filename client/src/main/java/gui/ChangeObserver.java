package gui;

import connection.ServerConnection;
import gui.utility.ShotBoardHandler;
import javafx.scene.layout.GridPane;

public class ChangeObserver {

    private static ServerConnection serverConnection;
    private static GridPane friendlyBoard;

    private static void initFields(ServerConnection controllerServerConnection, GridPane controllerBoard) {
        serverConnection = controllerServerConnection;
        friendlyBoard = controllerBoard;
    }

    public static void checkIfChangeAvailable() {
        ShotBoardHandler.friendlyShotReaction(serverConnection, friendlyBoard);
    }
}
