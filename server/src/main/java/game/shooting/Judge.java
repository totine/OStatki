package game.shooting;

import model.Coordinates;
import model.preparing.Player;
import model.shooting.board.BoardObserver;
import model.shooting.board.ShootingBoard;
import model.shooting.field.HitResult;

public class Judge implements BoardObserver {
    private final PlayerSwapper players;
    private boolean isGameEnd;

    public Judge(PlayerSwapper playerSwapper) {
        this.players = playerSwapper;
    }

    @Override
    public void update(ShootingBoard board, Coordinates coordinates, HitResult result) {
        isGameEnd = board.areAllMastsDestroyed();
        if (result.equals(HitResult.MISS)) {
            players.swap();
        }

    }

    Player getCurrentPlayer() {
        return players.getCurrentPlayer();
    }

    boolean isGameEnd() {
        return isGameEnd;
    }


}
