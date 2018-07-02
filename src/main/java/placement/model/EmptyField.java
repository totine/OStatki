package placement.model;

public class EmptyField implements Field {
    @Override
    public FieldState getState() {
        return FieldState.EMPTY;
    }
}
