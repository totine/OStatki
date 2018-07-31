package entrypoint;

import connection.Server;

/**
 * Entry point of server module.
 */
class ServerRunner {
    private ServerRunner() {
    }

    private static final int SERVER_PORT = 7777;

    public static void main(String[] args) {
        Server server = Server.createServer(SERVER_PORT);
        server.start();
    }
}
