package game.shooting.matchers;

import game.shooting.shotsuppliers.ShotSupplier;
import model.Coordinates;
import model.preparing.Player;


import java.util.HashMap;
import java.util.Map;

public class PlayerToShotSupplierMatcher {
    private final Map<Player, ShotSupplier> matcher;

    private PlayerToShotSupplierMatcher(Map<Player, ShotSupplier> matcher) {
        this.matcher = matcher;
    }

    public static PlayerToShotSupplierMatcher create(Player playerA, ShotSupplier shotSupplierA,
                                                     Player playerB, ShotSupplier shotSupplierB) {
        Map<Player, ShotSupplier> matcher = matchPlayerWithShotSupplier(playerA, shotSupplierA, playerB, shotSupplierB);
        return new PlayerToShotSupplierMatcher(matcher);
    }

    private static Map<Player, ShotSupplier> matchPlayerWithShotSupplier(Player playerA, ShotSupplier shotSupplierA,
                                                                         Player playerB, ShotSupplier shotSupplierB) {
        Map<Player, ShotSupplier> matcher = new HashMap<>();
        matcher.put(playerA, shotSupplierA);
        matcher.put(playerB, shotSupplierB);
        return matcher;
    }

    public Coordinates getCoordinatesToShot(Player currentPlayer) {
        ShotSupplier shotSupplier = matcher.get(currentPlayer);
        return shotSupplier.getCoordinatesToShot();
    }
}
