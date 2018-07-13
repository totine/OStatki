package gui;

import connection.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

/**
 * The entry point of GUI package. Sets up a window, loads components.
 */
public class GUIStarter extends Application {
    private static final int SCENE_WIDTH = 1200;
    private static final int SCENE_HEIGHT = 900;
    @Override
    public final void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ship_placement.fxml"));
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
        Client client;
        try {
            client = Client.createClient("localhost", 7777);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                client.sendMessage(scanner.nextLine());
            }
            client.sendMessage("message");
            String message = client.getMessage();
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }


        launch(args);
    }
}
