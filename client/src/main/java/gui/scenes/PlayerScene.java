package gui.scenes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The entry point of GUI package. Provides form to name the player and connect to server.
 */
public class PlayerScene extends Application {
    private static final int SCENE_WIDTH = 1200;
    private static final int SCENE_HEIGHT = 900;

    public static PlayerScene create() {
        return new PlayerScene();
    }

    @Override
    public final void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/player_scene.fxml"));
        primaryStage.setTitle("Battleships OStatki");
        primaryStage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
    }
}
