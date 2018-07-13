package connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Class for check if connection is OK by implementing simple chat. It will be deleted
 */
public class ConnectionAttempt {
    private static final int PORT_NUMBER = 7777;

    private ConnectionAttempt() {
    }

    public static void chat() {
        ClientIO client;
        try {
            String ip = "localhost";
            client = ClientIO.createClient(ip, PORT_NUMBER);
            Scanner scanner = new Scanner(System.in, String.valueOf(StandardCharsets.UTF_8));
            new Thread(client::getMessage).start();
            while (scanner.hasNextLine()) {
                client.sendMessage(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
