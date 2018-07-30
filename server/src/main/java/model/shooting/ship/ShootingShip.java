package model.shooting.ship;

import model.shooting.board.BoardObserver;
import model.shooting.board.ShootingBoard;
import model.Coordinates;
import model.shooting.field.HitResult;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShootingShip implements BoardObserver {
    private final List<Coordinates> mastCoordinates;
    private final Set<Coordinates> nonShotCoordinates;
    private boolean toDestroy;

    public ShootingShip(List<Coordinates> mastCoordinates) {
        this.mastCoordinates = mastCoordinates;
        nonShotCoordinates = new HashSet<>(mastCoordinates);
        toDestroy = true;
    }

    @Override
    public void update(ShootingBoard board, Coordinates coordinates, HitResult hitResult) {
        nonShotCoordinates.remove(coordinates);

        if (nonShotCoordinates.isEmpty() && toDestroy) {
            mastCoordinates.forEach(board::destroyMast);
            toDestroy = false;
        }
    }
}
