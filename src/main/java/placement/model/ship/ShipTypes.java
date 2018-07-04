package placement.model.ship;

public enum ShipTypes {
    BATTLESHIP(4),
    CRUISER(3),
    DESTROYER(2),
    SUBMARINE(1);

    private int size;

    ShipTypes(int size) {
        this.size = size;
    }


    public int getSize() {
        return size;
    }
}
