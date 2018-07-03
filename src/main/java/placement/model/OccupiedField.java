package placement.model;

public class OccupiedField implements Field {
    @Override
    public final FieldState getState() {
        return FieldState.OCCUPIED;
    }

    @Override
    public final String getMark() {
        return "⛴";
    }
}
