package placement.model;

import java.util.Random;

/**
 * Represents a direction at which a ship is heading after being placed on a 2-D board.
 * Can be used to obtain unitary directional coordinates which can be used to calculate
 * other coordinates with regard to it.
 */
public enum Direction {
    NORTH {
        @Override
        Coordinates nextCoordinates() {
            return new Coordinates(0, -1);
        }
    },
    EAST {
        @Override
        Coordinates nextCoordinates() {
            return new Coordinates(1, 0);
        }
    },
    SOUTH {
        @Override
        Coordinates nextCoordinates() {
            return new Coordinates(0, 1);
        }
    },
    WEST {
        @Override
        Coordinates nextCoordinates() {
            return new Coordinates(-1, 0);
        }
    };


    public static Direction getRandomDirection() {
        Random random = new Random();
        Direction[] directions = Direction.class.getEnumConstants();
        int index = random.nextInt(directions.length);
        return directions[index];
    }

    abstract Coordinates nextCoordinates();
}
