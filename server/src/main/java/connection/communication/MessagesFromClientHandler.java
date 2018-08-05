package connection.communication;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * encapsulates client's side in and out streams
 */
class MessagesFromClientHandler {
    private Scanner in;

    MessagesFromClientHandler(Socket clientSocket) throws IOException {
        this.in = new Scanner(clientSocket.getInputStream(), String.valueOf(StandardCharsets.UTF_8));
    }

    boolean hasMessage() {
        return in.hasNextLine();
    }

    String getMessage() {
        return in.nextLine();
    }
}

