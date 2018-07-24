package model.placement.field;

/**
 * Represents a field that is not yet taken by any ship and it's not adjacent to any of them.
 * Basically means "water".
 */
public class EmptyField implements Field {
    /**
     * @return return parameter is of FieldState and always is EMPTY which
     * indicates that this space can accept ship.
     */
    @Override
    public final FieldState getState() {
        return FieldState.EMPTY;
    }

    /**
     * returns String representation of EMPTY FieldState
     *
     * @return certain character which indicates that this field is empty
     */
    @Override
    public final String getMark() {
        return "~";
    }

    /**
     * @return always true as on EMPTY type field player always can
     * place a ship.
     */
    @Override
    public final boolean isFreeToPlace() {
        return true;
    }
}
