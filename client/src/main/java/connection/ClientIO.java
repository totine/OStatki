package connection;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Wrapper for client socket input and output
 */
public class ClientIO implements Runnable {
    private final PrintWriter out;
    private final Scanner in;
    private static final int QUEUE_CAPACITY = 10;
    private BlockingQueue<String> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    private ClientIO(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        boolean autoFlush = true;
        this.out = new PrintWriter(outputStreamWriter, autoFlush);
        this.in = new Scanner(socket.getInputStream(), String.valueOf(StandardCharsets.UTF_8));
    }

    public static ClientIO createClient(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        return new ClientIO(socket);
    }

    void sendMessage(String message) {
        out.println(message);
    }

    String getMessage() throws InterruptedException {
        return queue.take();
    }
    public void run() {
        while (in.hasNextLine()) {
            try {
                queue.put(in.nextLine());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
