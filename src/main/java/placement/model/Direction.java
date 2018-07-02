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
    },
    BROKEN {
        @Override
        Coordinates nextCoordinates(int previousX, int previousY) {
            int[] coord = {-1, 0, 1};
            int[] coord2 = {-1, 1};
            Random random = new Random();
            int newX = coord[random.nextInt(3)];
            int newY = newX != 0 ? 0 : coord2[random.nextInt(2)];
            return new Coordinates(previousX + newX, previousY + newY);
        }
    };


    public static Direction getRandomDirection() {
        Random random = new Random();
        Direction[] directions = Direction.class.getEnumConstants();
        int index = random.nextInt(directions.length);
        return directions[index];
    }

    abstract Coordinates nextCoordinates(int previousX, int previousY);


}
