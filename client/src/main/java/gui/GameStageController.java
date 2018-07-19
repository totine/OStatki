package gui;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * this Controller has aim to connect user interface with functions of the Battleship game.
 */
public class GameStageController {
    private ClientAppRunner appInstance;
    private FleetView fleet;

    @FXML
    private Button backToPlacement;
    @FXML
    private GridPane friendlyBoard;
    @FXML
    private GridPane enemyBoard;

    public void initialize() {
        appInstance = ClientAppRunner.getInstance();
        fleet = appInstance.getFleet();
        ShipPrinter.placeShips(fleet, friendlyBoard);
        FieldPrinter.insertFields(enemyBoard);
    }

    @FXML
    private void returnToShipPlacement() throws Exception{
        Window currentWindow = backToPlacement.getScene().getWindow();
        if (currentWindow instanceof Stage) {
            Stage currentStage = (Stage) currentWindow;
            MainStage mainStage = MainStage.createMainStage();
            mainStage.start(currentStage);
        }
    }

    private void fillEnemyBoard() {

    }

}
