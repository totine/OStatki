package gui.printers;

import model.Coordinates;

import java.util.List;
import java.util.Objects;

/**
 * GUI representation of ship object
 */
public class ShipView {
    private final List<Coordinates> mastCoordinates;

    public ShipView(List<Coordinates> structure) {
        this.mastCoordinates = structure;
    }

    List<Coordinates> getPositionCoordinates() {
        return mastCoordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShipView)) {
            return false;
        }
        ShipView shipView = (ShipView) o;
        return Objects.equals(mastCoordinates, shipView.mastCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mastCoordinates);
    }

    @Override
    public String toString() {
        return "ShipView{"
                + "mastCoordinates="
                + mastCoordinates
                + '}';
    }
}
