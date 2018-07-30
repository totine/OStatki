package connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger LOGGER = LogManager.getLogger(MessagesFromClientHandler.class);


    MessagesFromClientHandler(Socket clientSocket, Server server) throws IOException {
        this.in = new Scanner(clientSocket.getInputStream(), String.valueOf(StandardCharsets.UTF_8));
        this.server = server;
    }

    @Override
    public void run() {
        ServiceManager serviceManager = new ServiceManager(server);
        while (in.hasNextLine()) {
            String message = in.nextLine();
            LOGGER.info(message);
            GameCommand currentCommand = serviceManager.getCommand(message);
            currentCommand.execute(message);
        }
    }

}

