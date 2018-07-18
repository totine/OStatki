package placement.model.field;

/**
 * Represents a field that is adjacent to some ship.
 */
public class BufferField implements Field {
    /**
     * Simply returns certain FieldState
     * @return
     * return parameter is of FieldState and always is BUFFER which
     * indicates space which is near ship.
     */
    @Override
    public final FieldState getState() {
        return FieldState.BUFFER;
    }

    /**
     * returns String representation of BUFFER FieldState
     * @return
     * certain character which indicates that this field is a buffer for
     * some ship
     */
    @Override
    public final String getMark() {
        return "⚓";
    }

    /**
     * returns boolean if this field is able to place any ship.
     * @return
     * returns always false as on BUFFER type field player cannot
     * place any ships.
     */
    @Override
    public final boolean isFreeToPlace() {
        return false;
    }
}
