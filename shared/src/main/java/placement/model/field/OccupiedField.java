package placement.model.field;

/**
 * Represents a field already taken by some ship.
 */
public class OccupiedField implements Field {

    /**
     * Simply returns certain FieldState
     * @return
     * return parameter is of FieldState and always is OCCUPIED which
     * indicates that this space has ship on it.
     */
    @Override
    public final FieldState getState() {
        return FieldState.OCCUPIED;
    }

    /**
     * returns String representation of OCCUPIED FieldState
     * @return
     * empty character which indicates that this field is occupied by a ship.
     */
    @Override
    public final String getMark() {
        return "⛴";
    }

    /**
     * returns boolean if this field is able to place any ship.
     * @return
     * returns always false as on OCCUPIED type field player cannot place more ships.
     */
    @Override
    public final boolean isFreeToPlace() {
        return false;
    }
}
