package game.shooting;

import model.Coordinates;
import model.shooting.field.states.FieldStateName;

import java.util.HashMap;
import java.util.Map;

public class ShotResults {
    private final Map<Coordinates, FieldStateName> results;

    public ShotResults() {
        results = new HashMap<>();
    }

    public void put(Coordinates coordinates, FieldStateName stateName) {
        results.put(coordinates, stateName);
    }

    @Override
    public String toString() {
        return "ShotResults{"
                + "results=" + results
                + '}';
    }
}
