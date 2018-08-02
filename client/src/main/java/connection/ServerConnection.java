package connection;


import java.io.IOException;

/**
 * this class is prototype for checking connection with server from GUI of client.
 */
public class ServerConnection {
    private final int portNumber;
    private final String host;
    private ClientIO server;

    private ServerConnection(int portNumber, String host) {
        this.portNumber = portNumber;
        this.host = host;
    }

    /**
     * This is a fabricating method.
     *
     * @param host       ip address of the server.
     * @param portNumber number of port of the server
     * @return returns instance of ServerConnection
     */
    public static ServerConnection initializeConnection(String host, int portNumber) {
        return new ServerConnection(portNumber, host);
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

        new Thread(server).start();


    }

    private void handleConnectionException(IOException e) {
        e.printStackTrace();
    }

    public void sendMessage(String message) {
        server.sendMessage(message);
    }

    public String getMessage() {
        String message = "DEFAULT";
        try {
            message = server.getMessage();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return message;
    }

}
