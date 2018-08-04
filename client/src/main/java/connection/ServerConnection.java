package connection;

import connection.commands.CommandFromServer;
import connection.commands.CommandFromServerGenerator;
import gui.data.FieldBus;
import gui.data.Player;
import gui.printers.FleetView;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * this class is for connection with server from GUI of client.
 */
public class ServerConnection implements Runnable {
    private final int portNumber;
    private final String host;
    private ClientIO server;

    private static final int QUEUE_CAPACITY = 10;
    private BlockingQueue<FieldBus> myBoardChanges = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    private BlockingQueue<FieldBus> opponentBoardChanges = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    private BlockingQueue<FleetView> fleetQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    private CommandFromServerGenerator commandGenerator;
    private boolean isGameEnd;
    private Player winner;


    private Player currentPlayer;

    private ServerConnection(int portNumber, String host) {
        this.portNumber = portNumber;
        this.host = host;
        commandGenerator = new CommandFromServerGenerator(this);
    }

    public void updateCommandGenerator(InvalidationListener listener, Observable observable) {
        commandGenerator = new CommandFromServerGenerator(this, listener, observable);
    }

    /**
     * This is a fabricating method.
     *
     * @param host       ip address of the server.
     * @param portNumber number of port of the server
     * @return returns instance of ServerConnection
     */
    public static ServerConnection initializeConnection(String host, int portNumber) {
        return new ServerConnection(portNumber, host);
    }

    public void createServerConnection() {
        try {
            connect();
        } catch (IOException e) {
            handleConnectionException(e);
        }
    }

    private void connect() throws IOException {
        server = ClientIO.createClient(host, portNumber);
        new Thread(this).start();

    }
    public FieldBus getMyBoardChanges() {
        return myBoardChanges.poll();
    }

    public FieldBus getOpponentBoardChanges() throws InterruptedException {
        return opponentBoardChanges.take();
    }
    public void run() {
        while (server.hasNextLine()) {
            String message = server.getMessage();
            System.out.println(message);
            CommandFromServer currentCommand = commandGenerator.createCommandFromMessage(message);
            currentCommand.execute();

        }
    }

    private void handleConnectionException(IOException e) {
        e.printStackTrace();
    }

    public void sendMessage(String message) {

        System.out.println(message);
        server.sendMessage(message);
    }

    public void addFleetToQueue(FleetView fleetView) {

        try {
            fleetQueue.put(fleetView);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public FleetView getFleetFromQueue() throws InterruptedException {
        return fleetQueue.take();
    }

    public boolean isGameEnd() {
        return this.isGameEnd;
    }

    public void addMyBoardChangesQueue(FieldBus fieldBus) {
        myBoardChanges.add(fieldBus);
    }

    public void addOpponentBoardChangesQueue(FieldBus fieldBus) {
        opponentBoardChanges.add(fieldBus);
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setGameIsEnd() {
        isGameEnd = true;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

}
