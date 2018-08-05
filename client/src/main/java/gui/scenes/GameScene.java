package gui.scenes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Scene in application which consists of actual game.
 */
public class GameScene extends Application {
    private static final int SCENE_WIDTH = 800;
    private static final int SCENE_HEIGHT = 600;

    public static GameScene create() {
        return new GameScene();
    }
    private GameScene() {
    }

    @Override
    public final void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/game_scene.fxml"));
        primaryStage.setTitle("Battleships OStatki");
        primaryStage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));
        primaryStage.show();
    }
}
