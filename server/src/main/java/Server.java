import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class for server object. When calling start(), it starts
 * to listen for client connections.
 */
class Server {
    private final ServerSocket serverSocket;

    private Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    static Server createServer(int serverPort) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Server(serverSocket);
    }

    void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println(clientSocket);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
