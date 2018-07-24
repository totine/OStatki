package model.placement.ship;

import model.Coordinates;

/**
 * Represents a direction at which a ship is heading after being placed on a 2-D board.
 * Can be used to obtain unitary directional coordinates which can be used to calculate
 * other coordinates with regard to it.
 */
public enum Direction {
    NORTH {
        @Override
        public Coordinates nextCoordinates() {
            return Coordinates.create(0, -1);
        }
    },
    EAST {
        @Override
        public Coordinates nextCoordinates() {
            return Coordinates.create(1, 0);
        }
    },
    SOUTH {
        @Override
        public Coordinates nextCoordinates() {
            return Coordinates.create(0, 1);
        }
    },
    WEST {
        @Override
        public Coordinates nextCoordinates() {
            return Coordinates.create(-1, 0);
        }
    };

    public abstract Coordinates nextCoordinates();
}
