package connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class for connection object. When calling start(), it starts
 * to listen for client connections.
 */
public class Server {
    private static final Logger LOGGER = LogManager.getLogger(Server.class);
    private final ServerSocket serverSocket;
    private final List<MessagesToClientHandler> clientSocketList;
    private final List<MessagesFromClientHandler> fromClientHandlers;

    private Server(ServerSocket serverSocket, List<MessagesToClientHandler> list) {
        this.serverSocket = serverSocket;
        this.clientSocketList = list;
        this.fromClientHandlers = new ArrayList<>();
    }

    public static Server createServer(int serverPort) {
        Optional<ServerSocket> possibleServerSocket = Optional.empty();
        try {
            possibleServerSocket = Optional.of(new ServerSocket(serverPort));
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
                MessagesFromClientHandler messagesFromClientHandler = new MessagesFromClientHandler(clientSocket, this);
                fromClientHandlers.add(messagesFromClientHandler);
                LOGGER.info(fromClientHandlers);
                boolean autoFlush = true;
                OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
                PrintWriter printWriter = new PrintWriter(out, autoFlush);
                MessagesToClientHandler messagesToClient = new MessagesToClientHandler(clientSocket, printWriter);
                clientSocketList.add(messagesToClient);
                new Thread(messagesFromClientHandler).start();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    void sendMessageToAll(String message) {
        clientSocketList.forEach(io -> io.sendMessage(message));
    }

}
