package placement.model;

/**
 * Denotes that the player is trying to put his ship onto nonexistent field.
 */
public class ShipOutOfBoardException extends Exception {
    @Override
    public String getMessage() {
        return "Attempt to place ship out of the board.";
    }
}
