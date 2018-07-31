package connection;

import connection.communication.QueuesHandler;
import connection.communication.ServerClientIO;
import game.GameRun;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class for connection object. When calling start(), it starts
 * to listen for client connections.
 */
public class Server {
    private final ServerSocket serverSocket;
    private static final Logger LOGGER = LogManager.getLogger(Server.class);
    private List<QueuesHandler> queuesHandlers;

    private Server(ServerSocket serverSocket, List<QueuesHandler> serverClientIOs) {
        this.serverSocket = serverSocket;
        this.queuesHandlers = serverClientIOs;
    }

    public static Server createServer(int serverPort) {
        Optional<ServerSocket> possibleServerSocket = Optional.empty();
        try {
            possibleServerSocket = Optional.of(new ServerSocket(serverPort));
            LOGGER.info("Server is working");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return returnServerOrException(possibleServerSocket);
    }

    private static Server returnServerOrException(Optional<ServerSocket> possibleServerSocket) {
        if (possibleServerSocket.isPresent()) {
            return new Server(possibleServerSocket.get(), new ArrayList<>());
        } else {
            throw new IllegalArgumentException("This port is already taken.");
        }
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                LOGGER.info(clientSocket.toString() + " connected");
                ServerClientIO serverClientIO = ServerClientIO.create(clientSocket);
                QueuesHandler communicationRun = new QueuesHandler(serverClientIO);
                queuesHandlers.add(communicationRun);
                new Thread(communicationRun).start();
                createRoom();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    private void createRoom() {
        if (queuesHandlers.size() % 2 == 0) {
            int firstPlayerIndex = queuesHandlers.size() - 2;
            int secondPlayerIndex = firstPlayerIndex + 1;
            QueuesHandler firstPlayerQueueHandler = queuesHandlers.get(firstPlayerIndex);
            QueuesHandler secondPlayerQueueHandler = queuesHandlers.get(secondPlayerIndex);
            GameRun gameRun = GameRun.create(firstPlayerQueueHandler, secondPlayerQueueHandler);
            new Thread(gameRun).start();
        }
    }

}
