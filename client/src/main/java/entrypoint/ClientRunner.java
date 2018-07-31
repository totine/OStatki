package entrypoint;

import gui.scenes.PlayerScene;
import javafx.application.Application;

/**
 * Entry point of the application.
 */
final class ClientRunner {
    private ClientRunner() {
    }

    /**
     * entrypoint point
     *
     * @param args arguments from command line
     */
    public static void main(String[] args) {
        new Thread(() -> Application.launch(PlayerScene.class)).start();
    }
}
