package gui.entry;

import gui.scenes.PlayerScene;
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
        new Thread(() -> Application.launch(PlayerScene.class)).start();
    }
}