package model.shooting.board;

import model.Coordinates;
import model.shooting.field.HitResult;

public interface BoardObserver {

    void update(ShootingBoard board, Coordinates coordinates, HitResult hitResult);
}
