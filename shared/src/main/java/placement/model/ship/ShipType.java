package placement.model.ship;

/**
 * Enum containing four standard ship sizes.
 */
public enum ShipType {
    SHIP_MAST4(4),
    SHIP_MAST3(3),
    SHIP_MAST2(2),
    SHIP_MAST1(1);

    private final int size;

    ShipType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
