package game.shooting.shotsuppliers;

import model.Coordinates;


import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class ScannerShotSupplier implements ShotSupplier {
    private final Scanner scanner = new Scanner(System.in, String.valueOf(StandardCharsets.UTF_8));

    public Coordinates getCoordinatesToShot() {
        return Coordinates.create(scanner.nextInt(), scanner.nextInt());
    }

}
