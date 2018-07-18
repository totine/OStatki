package gui;


/**
 * this Controller has aim to connect user interface with functions of the Battleship game.
 */
public class GameStageController {
    private FleetView fleet;

    private GameStageController(FleetView fleet) {
        this.fleet = fleet;
    }

    public static GameStageController createController(FleetView fleet) {
        return new GameStageController(fleet);
    }

    public void initialize() {
        System.out.println(fleet);
    }
}
