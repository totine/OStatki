package placement.model;

/**
 * Denotes that the player is trying to place a ship on a field next to already present ship,
 * which is illegal in terms of known battleship rules.
 */
public class ShipOnBufferException extends Exception {
    @Override
    public String getMessage() {
        return "Attempt to place ship next to other ship.";
    }
}
