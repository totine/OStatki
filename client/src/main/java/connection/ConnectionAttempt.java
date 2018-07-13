package connection;

import java.io.IOException;
import java.util.Scanner;

public class ConnectionAttempt {
    public static void main(String[] args) {
        Client client;
        try {
            client = Client.createClient("localhost", 7777);
            Scanner scanner = new Scanner(System.in);
            new Thread(client::getMessage).start();
            while (scanner.hasNextLine()) {
                client.sendMessage(scanner.nextLine());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
