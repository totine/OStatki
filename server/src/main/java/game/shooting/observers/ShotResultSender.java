package game.shooting.observers;

import connection.communication.QueuesHandler;
import game.shooting.ShotResults;
import game.shooting.matchers.PlayerBoardMatcher;
import model.preparing.Player;
import model.shooting.board.ShootingBoard;

import java.util.Arrays;

public class ShotResultSender implements GameObserver {
    private final QueuesHandler communicationRun;
    private final Player player;
    private final PlayerBoardMatcher boards;

    public ShotResultSender(QueuesHandler communicationRun, Player player, PlayerBoardMatcher boards) {
        this.communicationRun = communicationRun;
        this.player = player;
        this.boards = boards;
    }

    private String boardsToSend() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMy Board\n\n");
        sb.append(boards.getOwnBoard(player).toString());
        sb.append("\n\nOpponent Board\n\n");
        sb.append(boards.getOpponentBoard(player).toStringOpponent());
        return sb.toString();
    }

    @Override
    public void update(ShotResults changes, ShootingBoard board, Player currentPlayer) {
        String[] toSend = (boardsToSend() + "\n\n"
                + currentPlayer.getName() + ": " + changes.toString() + "\nEND").split("\n");
        Arrays.stream(toSend).forEach(communicationRun::sendMessage);
    }

    @Override
    public void updateEndGame(Player winner) {
        communicationRun.sendMessage("\nGame is over\n");
        communicationRun.sendMessage("Winner is: " + winner.getName() + "\nEND");
    }
}
