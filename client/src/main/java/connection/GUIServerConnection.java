package connection;

import java.io.IOException;

/**
 * this class is prototype for checking connection with server from GUI of client.
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
     * This is a fabricating method.
     * @param portNumber
     * number of port of the server
     * @param host
     * ip address of the server.
     * @return
     * returns instance of GUIServerConnection
     */
    public static GUIServerConnection initializeConnection(int portNumber, String host) {
        return new GUIServerConnection(portNumber, host);
    }

    public void createServerConnection() {
        try {
            connect();
        } catch (IOException e) {
            handleConnectionException(e);
        }
    }

    private void connect() throws IOException {
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
