package connection;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * encapsulates client's side in and out streams
 */
class MessagesFromClientHandler implements Runnable {
    private Scanner in;
    private Server server;

    MessagesFromClientHandler(Socket clientSocket, Server server) throws IOException {
        this.in = new Scanner(clientSocket.getInputStream(), String.valueOf(StandardCharsets.UTF_8));
        this.server = server;
    }

    @Override
    public void run() {
        while (in.hasNextLine()) {
            String message = in.nextLine();
            System.out.println(message);
            server.sendMessageToAll(message);
        }
    }

}

