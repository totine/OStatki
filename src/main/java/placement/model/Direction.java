package placement.model;

public enum Direction {
    NORTH {
        @Override
        Coordinates nextCoordinates(int previousX, int previousY) {
            return new Coordinates(previousX, previousY - 1);
        }
    },
    EAST {
        @Override
        Coordinates nextCoordinates(int previousX, int previousY) {
            return new Coordinates(previousX + 1, previousY);
        }
    },
    SOUTH {
        @Override
        Coordinates nextCoordinates(int previousX, int previousY) {
            return new Coordinates(previousX, previousY + 1);
        }
    },
    WEST {
        @Override
        Coordinates nextCoordinates(int previousX, int previousY) {
            return new Coordinates(previousX - 1, previousY);
        }
    };


    abstract Coordinates nextCoordinates(int previousX, int previousY);


}
