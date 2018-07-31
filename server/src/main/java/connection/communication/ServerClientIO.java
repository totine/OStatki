package connection.communication;

import java.io.IOException;
import java.net.Socket;

public class ServerClientIO {
    private final MessagesFromClientHandler messagesFromClientHandler;
    private final MessagesToClientHandler messagesToClientHandler;

    private ServerClientIO(MessagesFromClientHandler messagesFromClient, MessagesToClientHandler messagesToClient) {
        this.messagesFromClientHandler = messagesFromClient;
        this.messagesToClientHandler = messagesToClient;
    }

    public static ServerClientIO create(Socket clientSocket) throws IOException {
        MessagesFromClientHandler messagesFromClient = new MessagesFromClientHandler(clientSocket);
        MessagesToClientHandler messagesToClient = new MessagesToClientHandler(clientSocket);
        return new ServerClientIO(messagesFromClient, messagesToClient);
    }

    void sendMessage(String message) {
        messagesToClientHandler.sendMessage(message);
    }

    boolean hasMessage() {
        return messagesFromClientHandler.hasMessage();
    }

    String getMessage() {
        return messagesFromClientHandler.getMessage();
    }

}
