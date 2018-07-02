package placement.model;

public class RandomBoardCreator {

    private final Fleet fleet;
    private final Board board;

    public RandomBoardCreator(Fleet fleet, Board board) {
        this.fleet = fleet;
        this.board = board;
    }

    public Fleet generateFleet() {
        for (Ship ship : fleet.shipList) {
            while (!ship.isPlaced()) {
                Direction direction = Direction.getRandomDirection();
                ship.setDirection(direction);
                Coordinates randomCoordinates = Coordinates.getRandom(board.rows(), board.cols());
                board.placeShip(ship, randomCoordinates);
            }

        }
        return fleet;
    }
}
