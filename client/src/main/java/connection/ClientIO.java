package connection;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Wrapper for client socket input and output
 */
class ClientIO {
    private final PrintWriter out;
    private final Scanner in;

    private ClientIO(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        boolean autoFlush = true;
        this.out = new PrintWriter(outputStreamWriter, autoFlush);
        this.in = new Scanner(socket.getInputStream(), String.valueOf(StandardCharsets.UTF_8));
    }

    static ClientIO createClient(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        return new ClientIO(socket);
    }

    void sendMessage(String message) {
        out.println(message);
    }

    String getMessage() {
        if (!in.hasNextLine()) {
            out.println("Domyślna");
        }
        return in.nextLine();
    }
}
