public class Main {
    private static final int SERVER_PORT = 7777;
    public static void main(String[] args) {
        Server server = Server.createServer(SERVER_PORT);
        server.start();
    }
}
