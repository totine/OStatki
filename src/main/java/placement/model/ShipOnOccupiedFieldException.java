package placement.model;

/**
 * Denotes that the player is trying to place his ship on a field that already contains a ship,
 * which is illegal in terms of known battleship rules.
 */
public class ShipOnOccupiedFieldException extends Exception {
    @Override
    public String getMessage() {
        return "Attempt to place ship on occupied field.";
    }
}
