package game.shooting.shotsuppliers;

import model.Coordinates;

import java.util.Random;


public class RandomShotSupplier implements ShotSupplier {
    private static final int BOUND_X = 10;
    private static final int BOUND_Y = 10;
    private final Random random = new Random();

    public Coordinates getCoordinatesToShot() {
        return Coordinates.create(
                random.nextInt(BOUND_X),
                random.nextInt(BOUND_Y));
    }
}
