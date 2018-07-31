package connection.communication;

import connection.command.CommandGenerator;
import connection.command.GameCommand;
import model.Coordinates;
import model.placement.fleet.Fleet;
import model.placement.ship.PlacedShip;
import model.preparing.Player;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueuesHandler implements Runnable {
    private final ServerClientIO serverClientIO;
    private final CommandGenerator commandGenerator;
    private final BlockingQueue<Coordinates> coordinatesToShotQueue;
    private final BlockingQueue<Fleet<PlacedShip>> fleetQueue;
    private final BlockingQueue<Player> playerQueue;
    private boolean isActive;
    private static final int QUEUE_CAPACITY = 10;

    public QueuesHandler(ServerClientIO serverClientIO) {
        this.serverClientIO = serverClientIO;
        this.commandGenerator = new CommandGenerator(this);
        coordinatesToShotQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
        fleetQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
        playerQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
        isActive = true;
    }

    @Override
    public void run() {
        while (serverClientIO.hasMessage()) {
            String message = serverClientIO.getMessage();
            System.out.println(message);
            if (isActive) {
                GameCommand currentCommand = commandGenerator.createCommandFromMessage(message);
                currentCommand.execute();
            }
        }
    }

    public void addCoordinateToQueue(Coordinates coordinates) throws InterruptedException {
        coordinatesToShotQueue.put(coordinates);
    }

    public void addFleetToQueue(Fleet<PlacedShip> fleet) throws InterruptedException {
        fleetQueue.put(fleet);
    }

    public void sendMessage(String message) {
        serverClientIO.sendMessage(message);
    }

    public Player getPlayer() throws InterruptedException {
        return playerQueue.take();
    }

    public Fleet<PlacedShip> getFleet() throws InterruptedException {
        return fleetQueue.take();
    }

    public Coordinates getCoordinates() throws InterruptedException {
        return coordinatesToShotQueue.take();
    }

    public void addPlayerToQueue(Player player) throws InterruptedException {
        playerQueue.put(player);
    }

    public void activate() {
        isActive = true;
    }

    public void deactivate() {
        isActive = false;
    }
}
