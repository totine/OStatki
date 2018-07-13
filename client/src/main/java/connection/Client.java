package connection;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    private final Socket socket;
    PrintWriter out;
    Scanner in;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriterUTF8 = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        this.out = new PrintWriter(outputStreamWriterUTF8, true);
        this.in = new Scanner(socket.getInputStream(), String.valueOf(StandardCharsets.UTF_8));
    }


    public static Client createClient(String host, int port) throws IOException {
        Socket socket = null;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Client(socket);
    }

    void sendMessage(String message) {
        out.println(message);
    }

    void getMessage() {
        while (in.hasNextLine()) {
            System.out.println(in.nextLine());
        }
    }
}
