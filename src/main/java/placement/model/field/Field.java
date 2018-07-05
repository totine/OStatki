package placement.model.field;

import placement.model.ShipOnBufferException;
import placement.model.ShipOnOccupiedFieldException;
import placement.model.ShipOutOfBoardException;

/**
 * represents a field in the context of placing ships on the board.
 */
public interface Field {
    FieldState getState();

    String getMark();

    void checkState() throws ShipOutOfBoardException, ShipOnBufferException, ShipOnOccupiedFieldException;
}
