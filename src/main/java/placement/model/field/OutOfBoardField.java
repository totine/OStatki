package placement.model.field;

import placement.model.ShipOutOfBoardException;

/**
 * Represents a notion of a field that is
 * impossible to exist. In other words a wrong field.
 */
public class OutOfBoardField implements Field {

    @Override
    public final FieldState getState() {
        return FieldState.OUTOFBOUND;
    }

    @Override
    public final String getMark() {
        return "";
    }

    @Override
    public final void checkState() throws ShipOutOfBoardException {
        throw new ShipOutOfBoardException();
    }
}
