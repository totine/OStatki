import connection.ConnectionAttempt;
import gui.GUIStarter;
import javafx.application.Application;

/**
 * Entry point of the application.
 */
public final class Ships {
    private Ships() {
    }


    /**
     * entry point
     *
     * @param args arguments from command line
     */
    public static void main(String[] args) {
        new Thread(ConnectionAttempt::chat).start();
        new Thread(() -> Application.launch(GUIStarter.class)).start();
    }
}
