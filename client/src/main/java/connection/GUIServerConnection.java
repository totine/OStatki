package connection;

import java.io.IOException;

/**
 * something
 */
public class GUIServerConnection {
    private final int portNumber;
    private final String host;
    private ClientIO server;

    private GUIServerConnection(int portNumber, String host) {
        this.portNumber = portNumber;
        this.host = host;
    }

    /**
     * @param portNumber
     * @param host
     * @return
     */
    public static GUIServerConnection initializeConnection(int portNumber, String host) {
        return new GUIServerConnection(portNumber, host);
    }

    public void createServer() {
        try {
            createServerConnection();
        } catch (IOException e) {
            handleConnectionException(e);
        }
    }

    private void createServerConnection() throws IOException {
        server = ClientIO.createClient(host, portNumber);
    }

    private void handleConnectionException(IOException e) {
        e.printStackTrace();
    }

    public void sendMessage(String message) {
        server.sendMessage(message);
    }

    public String getMessage() {
        return server.getMessage();
    }
}
