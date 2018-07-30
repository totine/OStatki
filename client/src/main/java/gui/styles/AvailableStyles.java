package gui.styles;

public enum AvailableStyles {
    WATER_STYLE("water-button"),
    DAMAGED_STYLE("damaged-button"),
    DESTROYED_STYLE("destroyed-button");

    private final String value;

    AvailableStyles(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
