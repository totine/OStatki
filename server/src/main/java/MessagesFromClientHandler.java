import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * encapsulates client's side in and out streams
 */
class MessagesFromClientHandler implements Runnable {
    private Scanner in;

    MessagesFromClientHandler(Socket clientSocket, Server server) throws IOException {
        this.in = new Scanner(clientSocket.getInputStream(), String.valueOf(StandardCharsets.UTF_8));
    }

    @Override
    public void run() {
        while (in.hasNextLine()) {
            System.out.println(in.nextLine());
        }
    }
}

