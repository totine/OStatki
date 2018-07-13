import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import placement.model.Coordinates;

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
    private static final Logger logger = LogManager.getLogger(Server.class);


    private Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }


    static Server createServer(int serverPort) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return new Server(serverSocket);
    }

    void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                logger.info(clientSocket.toString() + " connected");
                MessagesFromClientHandler client = new MessagesFromClientHandler(clientSocket);
                new Thread(client).start();

            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
