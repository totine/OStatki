package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The entry point of GUI package. Sets up a window, loads components.
 */
public class GameStage extends Application {
    private static final int SCENE_WIDTH = 1200;
    private static final int SCENE_HEIGHT = 900;
    private GameStageController gameStageController;
    @Override
    public final void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = FXMLLoader.load(getClass().getResource("/game_stage.fxml"));
        loader.setController(gameStageController);
        Parent root = loader.getRoot();
        primaryStage.setTitle("Battleships OStatki");
        primaryStage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
    }

    private GameStage(GameStageController gameStageController) {
        this.gameStageController = gameStageController;
    }

    public static GameStage createGameStage(GameStageController gameStageController) {
        return new GameStage(gameStageController);
    }

    /**
     * chat method - actual entry point of the application
     * @param args - console arguments used by FX
     */
    public static void main(String[] args) {
        launch(args);
    }
}
