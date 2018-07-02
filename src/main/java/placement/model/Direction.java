package placement.model;

import java.util.Random;

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


    public static Direction getRandomDirection() {
        Random random = new Random();
        int index = random.nextInt(4);
        return Direction.class.getEnumConstants()[index];
    }

    abstract Coordinates nextCoordinates(int previousX, int previousY);


}
