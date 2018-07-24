import controller.FleetController;
import model.Coordinates;
import model.placement.fleet.Fleet;
import model.placement.ship.PlacedShip;
import model.shooting.Judge;
import model.shooting.board.ShootingBoard;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Game {
    private ShootingBoard board;
    private Judge judge;

    private Game(Judge judge, ShootingBoard board) {
        this.judge = judge;
        this.board = board;
    }

    private void start() {
        Scanner in = new Scanner(System.in, String.valueOf(StandardCharsets.UTF_8));
        while (!judge.isEnd()) {
            System.out.println(board);
            scannerLoop(in);
            int x = in.nextInt();
            scannerLoop(in);
            int y = in.nextInt();
            board.hit(Coordinates.create(x, y));
        }
    }

    private void scannerLoop(Scanner in) {
        while (!in.hasNextInt()) {
            in.next();
        }
    }

    public static void main(String[] args) {
        Judge judge = new Judge();
        Fleet<PlacedShip> fleet = FleetController.generatePlacedStandardFleet();
        ShootingBoard board = ShootingBoard.fromFleet(fleet);
        board.addObserver(judge);
        Game game = new Game(judge, board);
        game.start();
    }
}
