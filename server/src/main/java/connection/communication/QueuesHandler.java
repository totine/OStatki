package connection.communication;

import connection.command.CommandGenerator;
import connection.command.GameCommand;
import connection.serializers.JSONConverter;
import connection.utility.Command;
import connection.utility.CommandType;
import game.shooting.ShotResults;
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
    private final BlockingQueue<Player> playerQueue;
    private final BlockingQueue<Fleet<PlacedShip>> fleetFromPlayer;
    private boolean isActive;
    private static final int QUEUE_CAPACITY = 10;

    public QueuesHandler(ServerClientIO serverClientIO) {
        this.serverClientIO = serverClientIO;
        this.commandGenerator = new CommandGenerator(this);
        coordinatesToShotQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
        fleetFromPlayer = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
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
            else {
                Command command = Command.withType(CommandType.SEND_OPPONENT_CHANGES, new ShotResults());
                String s = JSONConverter.convertToJSON(command);
                sendMessage(s);
            }
        }
    }

    public void addCoordinateToQueue(Coordinates coordinates) throws InterruptedException {
        coordinatesToShotQueue.put(coordinates);
    }

    public void addToFleetQueue(Fleet<PlacedShip> fleet) throws InterruptedException {
        fleetFromPlayer.put(fleet);
    }

    public void sendMessage(String message) {
        serverClientIO.sendMessage(message);
    }

    public Player getPlayer() throws InterruptedException {
        return playerQueue.take();
    }

    public Fleet<PlacedShip> getFleetFromPlayer() throws InterruptedException {
        return fleetFromPlayer.take();
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
