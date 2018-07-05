package placement.model.field;

import placement.model.ShipOnBufferException;
import placement.model.ShipOutOfBoardException;

/**
 * Represents a field that is not yet taken by any ship and it's not adjacent to any of them.
 * Basically means "water".
 */
public class EmptyField implements Field {
    @Override
    public final FieldState getState() {
        return FieldState.EMPTY;
    }

    @Override
    public final String getMark() {
        return "~";
    }

    @Override
    public void checkState() throws ShipOutOfBoardException, ShipOnBufferException {

    }
}
