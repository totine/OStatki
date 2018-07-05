package placement.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * The entry point of GUI package. Sets up a window, loads components.
 */
public class GUIStarter extends Application {
    private static final int SCENE_WIDTH = 1200;
    private static final int SCENE_HEIGHT = 900;
    @Override
    public final void start(Stage primaryStage) throws Exception {
        URL resource = getClass().getResource("/ship_placement.fxml");
        Parent root = FXMLLoader.load(resource);
        primaryStage.setTitle("Battleships OStatki");
        primaryStage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();


    }

    /**
     * main method - actual entry point of the application
     * @param args - console arguments used by FX
     */
    public static void main(String[] args) {
        launch(args);
    }
}

