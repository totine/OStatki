import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.SynchronousQueue;

/**
 * Class for server object. When calling start(), it starts
 * to listen for client connections.
 */
class Server {
    private final ServerSocket serverSocket;
    private static final Logger logger = LogManager.getLogger(Server.class);
    private List<Socket> clientSocketList;


    private Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.clientSocketList = new ArrayList<>();
    }


    static Server createServer(int serverPort) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        Queue<Socket> sockets = new SynchronousQueue<>();
        return new Server(serverSocket);
    }

    void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                clientSocketList.add(clientSocket);
                logger.info(clientSocket.toString() + " connected");
                MessagesFromClientHandler clientIn = new MessagesFromClientHandler(clientSocket, this);
                new Thread(clientIn).start();

            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
