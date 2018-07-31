package model.shooting.board;

import game.shooting.ShotResults;
import model.shooting.field.HitResult;
import model.shooting.ship.ShootingShip;
import model.shooting.field.Field;
import model.shooting.field.states.FieldStateName;
import model.Coordinates;
import model.placement.fleet.Fleet;
import model.placement.ship.PlacedShip;

import java.util.List;
import java.util.ArrayList;

public class ShootingBoard implements ObservableBoard {
    private static final int COLUMNS_NUMBER = 10;
    private static final int ROWS_NUMBER = 10;
    private final ShootingBoardFields boardFields;
    private final List<BoardObserver> observers;
    private ShotResults changes;


    private ShootingBoard(ShootingBoardFields boardFields) {
        this.boardFields = boardFields;
        this.observers = new ArrayList<>();
        this.changes = new ShotResults();
    }

    public static ShootingBoard createEmpty(int rows, int cols) {
        return new ShootingBoard(new ShootingBoardFields(rows, cols));
    }

    public static ShootingBoard fromFleet(Fleet<PlacedShip> placedShipFleet) {
        ShootingBoardFields boardFields = new ShootingBoardFields(ROWS_NUMBER, COLUMNS_NUMBER);
        placedShipFleet.getShipList().
                forEach(ship -> ship.getMastCoordinates().
                        forEach(boardFields::addFloatingMastField));
        ShootingBoard shootingBoard = new ShootingBoard(boardFields);
        shootingBoard.addObserversFromFleet(placedShipFleet);
        return shootingBoard;
    }

    private void addObserversFromFleet(Fleet<PlacedShip> placedShipFleet) {
        placedShipFleet.getShipList().forEach(ship -> this.addObserver(new ShootingShip(ship.getMastCoordinates())));
    }

    public HitResult hit(Coordinates coordinates) {
        Field field = boardFields.get(coordinates);
        HitResult hitResult = field.hit();
        changes.put(coordinates, getStateName(coordinates));
        notifyObservers(coordinates, hitResult);
        return hitResult;
    }

    public void destroyMast(Coordinates coordinatesToDestroy) {
        Field field = boardFields.get(coordinatesToDestroy);
        field.markDestroyed();
        boardFields.getAllNeighboursField(coordinatesToDestroy).forEach(coordinates -> {
            boardFields.get(coordinates).hit();
            changes.put(coordinates, boardFields.get(coordinates).getStateName());
        });

    }

    public FieldStateName getStateName(Coordinates coordinates) {
        return boardFields.get(coordinates).getStateName();
    }

    public ShotResults getChanges() {
        ShotResults toSend = changes;
        changes = new ShotResults();
        return toSend;
    }

    @Override
    public void addObserver(BoardObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(Coordinates coordinates, HitResult hitResult) {
        observers.forEach(observer -> observer.update(this, coordinates, hitResult));
    }

    public boolean areAllMastsDestroyed() {
        return boardFields.hasNoFloatingMasts();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = -1; i < boardFields.getRows(); i++) {
            for (int j = -1; j < boardFields.getCols(); j++) {
                if (i == -1 && j != -1) {
                    sb.append(j);
                    sb.append("\t");
                } else if (j == -1 && i != -1) {
                    sb.append(i);
                    sb.append("\t");
                } else {
                    Coordinates coordinates = Coordinates.create(j, i);
                    Field field = boardFields.get(coordinates);
                    sb.append(field.getMark());
                    sb.append("\t");
                }
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }

    public String toStringOpponent() {
        return toString().replaceAll("\\[]", ".");
    }
}
