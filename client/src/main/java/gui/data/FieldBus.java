package gui.data;

import model.Coordinates;

import java.util.Map;
import java.util.Objects;

public class FieldBus {
    private final Map<Coordinates, FieldState> coordinatesState;

    private FieldBus(Map<Coordinates, FieldState> coordinatesState) {
        this.coordinatesState = coordinatesState;
    }

    public static FieldBus create(Map<Coordinates, FieldState> coordinatesState) {
        return new FieldBus(coordinatesState);
    }

    public FieldState getCoordinateState(Coordinates coordinates) {
        return coordinatesState.get(coordinates);
    }

    @Override
    public String toString() {
        return "FieldBus{"
                + "coordinatesState="
                + coordinatesState
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FieldBus)) {
            return false;
        }
        FieldBus fieldBus = (FieldBus) o;
        return Objects.equals(coordinatesState, fieldBus.coordinatesState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinatesState);
    }
}
