package placement.model;

public class OccupiedField implements Field {
    @Override
    public FieldState getState() {
        return FieldState.OCCUPIED;
    }

    @Override
    public String getMark() {
        return "⛴";
    }
}
