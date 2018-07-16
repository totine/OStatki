package placement.model.field;

/**
 * Represents a notion of a field that is
 * impossible to exist. In other words a wrong field.
 */
public class OutOfBoardField implements Field {

    /**
     * Simply returns certain FieldState
     * @return
     * return parameter is of FieldState and always is OUT_OF_BOUND which
     * indicates that this space is beyond the board and doesn't belong to it.
     */
    @Override
    public final FieldState getState() {
        return FieldState.OUT_OF_BOUND;
    }

    /**
     * returns String representation of OUT_OF_BOUND FieldState
     * @return
     * empty character which indicates that this field is beyond the board.
     */
    @Override
    public final String getMark() {
        return "";
    }

    /**
     * returns boolean if this field is able to place any ship.
     * @return
     * returns always false as on OUT_OF_BOUND type field player cannot place anything.
     */
    @Override
    public final boolean isFreeToPlace() {
        return false;
    }
}
