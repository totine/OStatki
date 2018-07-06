package placement.model.field;

import placement.model.ShipOnBufferException;

/**
 * Represents a field that is adjacent to some ship.
 */
public class BufferField implements Field {
    @Override
    public final FieldState getState() {
        return FieldState.BUFFER;
    }

    @Override
    public final String getMark() {
        return "⚓";
    }

    @Override
    public final void checkState() throws ShipOnBufferException {
        throw new ShipOnBufferException();
    }
}
