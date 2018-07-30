package game.shooting;

import model.preparing.Player;

import java.util.ArrayList;
import java.util.List;


class PlayerSwapper {
    private final List<Player> players;
    private int currentIndex;

    PlayerSwapper() {
        players = new ArrayList<>();
        currentIndex = 0;
    }

    public void add(Player player) {
        players.add(player);
    }

    Player getCurrentPlayer() {
        return players.get(currentIndex);
    }

    void swap() {
        currentIndex = (currentIndex + 1) % players.size();
    }
}
