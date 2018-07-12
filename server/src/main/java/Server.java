import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private final ServerSocket serverSocket;

    private Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static Server createServer(int serverPort)  {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Server(serverSocket);
    }

    public void start() {
        while (true) {
            try {
                serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
