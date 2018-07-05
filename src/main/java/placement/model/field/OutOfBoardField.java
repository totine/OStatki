package placement.model.field;

import placement.model.ShipOutOfBoardException;

public class OutOfBoardField implements Field {

    @Override
    public FieldState getState() {
        return FieldState.OUTOFBOUND;
    }

    @Override
    public String getMark() {
        return "";
    }

    @Override
    public void checkState() throws ShipOutOfBoardException {
        throw new ShipOutOfBoardException();
    }
}
