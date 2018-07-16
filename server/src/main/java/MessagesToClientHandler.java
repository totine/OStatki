import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * encapsulates client's side in and out streams
 */
class MessagesToClientHandler {
    private PrintWriter out;

    MessagesToClientHandler(Socket clientSocket) throws IOException {
        OutputStream outputStream = clientSocket.getOutputStream();
        OutputStreamWriter outputStreamWriterUTF8 = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        this.out = new PrintWriter(outputStreamWriterUTF8, true);
    }

    void sendMessage(String message) {
        out.println(message);
    }
}

