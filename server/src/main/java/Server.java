import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for server object. When calling start(), it starts
 * to listen for client connections.
 */
class Server {
    private final ServerSocket serverSocket;
    private static final Logger LOGGER = LogManager.getLogger(Server.class);
    private List<MessagesToClientHandler> clientSocketList;

    private Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.clientSocketList = new ArrayList<>();
    }

    static Server createServer(int serverPort) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return new Server(serverSocket);
    }

    void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                LOGGER.info(clientSocket.toString() + " connected");
                MessagesFromClientHandler clientIn = new MessagesFromClientHandler(clientSocket, this);
                MessagesToClientHandler clientOut = new MessagesToClientHandler(clientSocket);
                clientSocketList.add(clientOut);
                new Thread(clientIn).start();

            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    public void sendMessageToAll(String message) {
        clientSocketList.forEach(io -> io.sendMessage(message));
    }
}
