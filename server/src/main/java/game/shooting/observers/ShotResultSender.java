package game.shooting.observers;

import connection.communication.QueuesHandler;
import connection.serializers.JSONConverter;
import game.shooting.ShotResults;
import game.shooting.matchers.PlayerBoardMatcher;
import model.preparing.Player;
import model.shooting.board.ShootingBoard;

public class ShotResultSender implements GameObserver {
    private final QueuesHandler queuesHandler;
    private final Player player;
    private final PlayerBoardMatcher boards;

    public ShotResultSender(QueuesHandler communicationRun, Player player, PlayerBoardMatcher boards) {
        this.queuesHandler = communicationRun;
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
        String changesJSON = JSONConverter.convertToJSON(changes.getMap());
        String currentPlayerJSON = JSONConverter.convertToJSON(currentPlayer);
        String toSend = "{\"player\": " + changesJSON + ", \"changes\": " + currentPlayerJSON + "}";
        queuesHandler.sendMessage(changesJSON);
    }

    @Override
    public void updateEndGame(Player winner) {
        queuesHandler.sendMessage("\nGame is over\n");
        queuesHandler.sendMessage("Winner is: " + winner.getName() + "\nEND");
    }
}
