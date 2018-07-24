package model.shooting.ship;

import model.shooting.board.BoardObserver;
import model.shooting.board.ShootingBoard;
import model.Coordinates;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShootingShip implements BoardObserver {
    private final List<Coordinates> mastCoordinates;
    private final Set<Coordinates> nonShotCoordinates;

    public ShootingShip(List<Coordinates> mastCoordinates) {
        this.mastCoordinates = mastCoordinates;
        nonShotCoordinates = new HashSet<>(mastCoordinates);
    }

    @Override
    public void update(ShootingBoard board, Coordinates coordinates) {
        nonShotCoordinates.remove(coordinates);

        if (nonShotCoordinates.isEmpty()) {
            mastCoordinates.forEach(board::destroyMast);
        }
    }
}
