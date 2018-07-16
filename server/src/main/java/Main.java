import connection.Server;

/**
 * Entry point of client module.
 */
public class Main {
    private Main() {
    }

    private static final int SERVER_PORT = 7777;

    public static void main(String[] args) {
        Server server = Server.createServer(SERVER_PORT);
        server.start();
    }
}
