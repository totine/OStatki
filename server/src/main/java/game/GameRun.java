package game;

import connection.communication.QueuesHandler;
import game.shooting.GameShootingPart;
import game.shooting.Judge;
import game.shooting.PlayerSwapper;
import game.shooting.matchers.PlayerBoardMatcher;
import game.shooting.matchers.PlayerToShotSupplierMatcher;
import game.shooting.observers.ShotResultSender;
import game.shooting.shotsuppliers.FromGuiShotSupplier;
import game.shooting.shotsuppliers.ShotSupplier;
import model.placement.fleet.Fleet;
import model.placement.ship.PlacedShip;
import model.preparing.Player;
import model.shooting.board.ShootingBoard;

public class GameRun implements Runnable {
    private final QueuesHandler queuesHandlerA;
    private final QueuesHandler queuesHandlerB;

    private GameRun(QueuesHandler queuesHandlerA, QueuesHandler queuesHandlerB) {
        this.queuesHandlerA = queuesHandlerA;
        this.queuesHandlerB = queuesHandlerB;
    }

    public static GameRun create(QueuesHandler queuesHandlerA, QueuesHandler queuesHandlerB) {
        return new GameRun(queuesHandlerA, queuesHandlerB);
    }

    @Override
    public void run() {
        System.out.println("gamerun run method started");
        Player playerA = getPlayerFromQueue(queuesHandlerA);
        System.out.println("took player A");
        Player playerB = getPlayerFromQueue(queuesHandlerB);
        System.out.println("took playerB");

        while (!(queuesHandlerA.isReady() && queuesHandlerB.isReady())) {
            System.out.println("waiting for both players to be ready");
        } // to change

        System.out.println("both are ready, processing further...");

        PlayerSwapper playerSwapper = createPlayerSwapper(playerA, playerB);
        Fleet<PlacedShip> fleetA = getPlacedShipFleetFromQueue(queuesHandlerA);

        Fleet<PlacedShip> fleetB = getPlacedShipFleetFromQueue(queuesHandlerB);
        PlayerBoardMatcher playerBoardMatcher = createPlayerBoardMatcher(playerA, playerB, fleetA, fleetB);
        PlayerToShotSupplierMatcher playerToShotSupplierMatcher = createPlayerToShotSupplierMatcher(playerA, playerB);
        Judge judge = new Judge(playerSwapper);
        GameShootingPart game = GameShootingPart.create(judge, playerBoardMatcher, playerToShotSupplierMatcher);
        addObserversToGame(playerA, playerB, playerBoardMatcher, game);
        game.start();

    }

    private Player getPlayerFromQueue(QueuesHandler queuesHandler) {
        Player player = null;
        try {
            player = queuesHandler.getPlayer();
            System.out.println("PLAYER GOTOWY");
            System.out.println(player);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return player;
    }

    private PlayerSwapper createPlayerSwapper(Player playerA, Player playerB) {
        PlayerSwapper playerSwapper = new PlayerSwapper();
        playerSwapper.add(playerA);
        playerSwapper.add(playerB);
        return playerSwapper;
    }

    private Fleet<PlacedShip> getPlacedShipFleetFromQueue(QueuesHandler queuesHandler) {
        Fleet<PlacedShip> fleet = null;
        try {
            fleet = queuesHandler.getFleetFromPlayer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("FLOTA GOTOWA");
        return fleet;
    }

    private PlayerToShotSupplierMatcher createPlayerToShotSupplierMatcher(Player playerA, Player playerB) {
        ShotSupplier shotSupplierA = new FromGuiShotSupplier(queuesHandlerA);
        ShotSupplier shotSupplierB = new FromGuiShotSupplier(queuesHandlerB);

        return PlayerToShotSupplierMatcher.create(playerA, shotSupplierA, playerB, shotSupplierB);
    }

    private PlayerBoardMatcher createPlayerBoardMatcher(Player playerA, Player playerB,
                                                        Fleet<PlacedShip> fleetA, Fleet<PlacedShip> fleetB) {
        ShootingBoard shootingBoardA = ShootingBoard.fromFleet(fleetA);
        ShootingBoard shootingBoardB = ShootingBoard.fromFleet(fleetB);

        return PlayerBoardMatcher.create(playerA, shootingBoardA, playerB, shootingBoardB);
    }

    private void addObserversToGame(Player playerA, Player playerB,
                                    PlayerBoardMatcher playerBoardMatcher, GameShootingPart game) {
        ShotResultSender shotResultSenderA = new ShotResultSender(queuesHandlerA, playerA, playerBoardMatcher);
        ShotResultSender shotResultSenderB = new ShotResultSender(queuesHandlerB, playerB, playerBoardMatcher);
        game.addObserver(new GamePrinter());
        game.addObserver(shotResultSenderA);
        game.addObserver(shotResultSenderB);
    }
}
