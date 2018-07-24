package model.placement.ship;

/**
 * Enum containing four standard ship sizes.
 */
public enum ShipType {
    FOUR_MAST(4),
    THREE_MAST(3),
    TWO_MAST(2),
    ONE_MAST(1);

    private final int size;

    ShipType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
