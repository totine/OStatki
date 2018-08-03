package gui.scenes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The entrypoint point of GUI package. Provides form to name the player and connect to server.
 */
public class PlayerScene extends Application {
    private static final int SCENE_WIDTH = 800;
    private static final int SCENE_HEIGHT = 600;

    public static PlayerScene create() {
        return new PlayerScene();
    }

    @Override
    public final void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/player_scene.fxml"));
        primaryStage.setTitle("Battleships OStatki");
        root.getStylesheets().add("https://fonts.googleapis.com/css?family=Pirata+One&amp;subset=latin-ext");
        primaryStage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));
        primaryStage.show();
    }
}
