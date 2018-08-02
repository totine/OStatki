package connection;


import connection.commands.CommandFromServer;
import connection.commands.CommandFromServerGenerator;
import gui.data.FieldBus;
import gui.data.FieldState;
import gui.printers.FleetView;
import model.Coordinates;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * this class is prototype for checking connection with server from GUI of client.
 */
public class ServerConnection  implements Runnable {
    private final int portNumber;
    private final String host;
    private ClientIO server;
    private BlockingQueue<FieldBus> myBoardChanges = new ArrayBlockingQueue<>(10);
    private BlockingQueue<FieldBus> opponentBoardChanges = new ArrayBlockingQueue<>(10);
    private BlockingQueue<FleetView> fleetQueue = new ArrayBlockingQueue<>(10);
    private CommandFromServerGenerator commandGenerator;

    private ServerConnection(int portNumber, String host) {
        this.portNumber = portNumber;
        this.host = host;
        commandGenerator = new CommandFromServerGenerator(this);
        Map<Coordinates, FieldState> fieldStateMap1 = new HashMap<>();
        fieldStateMap1.put(Coordinates.create(1,2), FieldState.DESTROYED);
        fieldStateMap1.put(Coordinates.create(8,7), FieldState.DESTROYED);
        Map<Coordinates, FieldState> fieldStateMap2 = new HashMap<>();
        fieldStateMap2.put(Coordinates.create(1,5), FieldState.DESTROYED);
        fieldStateMap2.put(Coordinates.create(5,7), FieldState.DESTROYED);

        FieldBus fieldBus1 = FieldBus.create(fieldStateMap1);
        FieldBus fieldBus2 = FieldBus.create(fieldStateMap2);

        myBoardChanges.add(fieldBus1);
        opponentBoardChanges.add(fieldBus2);
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
    public FieldBus getMyBoardChanges() throws InterruptedException {
        return myBoardChanges.take();
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

    public void addMyBoardChangesQueue(FieldBus fieldBus) {
        myBoardChanges.add(fieldBus);
    }

    public void addOpponentBoardChangesQueue(FieldBus fieldBus) {
        opponentBoardChanges.add(fieldBus);
    }
}
