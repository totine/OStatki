package gui;


/**
 * this Controller has aim to connect user interface with functions of the Battleship game.
 */
public class GameStageController {
    private ClientAppRunner appInstance;

    public void initialize() {
        appInstance = ClientAppRunner.getInstance();
    }

}
