package placement.model;

import java.util.Random;

public enum Direction {
    NORTH {
        @Override
        Coordinates nextCoordinates() {
            return new Coordinates(0,  -1);
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
    },
    BROKEN {
        @Override
        Coordinates nextCoordinates() {
            int[] coord = {-1, 0, 1};
            int[] coord2 = {-1, 1};
            Random random = new Random();
            int newX = coord[random.nextInt(3)];
            int newY = newX != 0 ? 0 : coord2[random.nextInt(2)];
            return new Coordinates(newX, newY);
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
