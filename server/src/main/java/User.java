import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * encapsulates client's side in and out streams
 */
class User {
    private PrintWriter out;
    private Scanner in;

    User(Socket clientSocket) throws IOException {
        OutputStream outputStream = clientSocket.getOutputStream();
        OutputStreamWriter outputStreamWriterUTF8 = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        this.out = new PrintWriter(outputStreamWriterUTF8, true);
        this.in = new Scanner(clientSocket.getInputStream(), String.valueOf(StandardCharsets.UTF_8));
    }

    String getMessage() {

        return in.nextLine();
    }

    void sendMessage(String message) {
        out.println(message);
    }
}
