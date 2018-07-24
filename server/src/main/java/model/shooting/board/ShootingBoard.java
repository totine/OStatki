package model.shooting.board;

import model.shooting.field.HitResult;
import model.shooting.ship.ShootingShip;
import model.shooting.field.Field;
import model.shooting.field.states.FieldStateName;
import model.Coordinates;
import model.placement.fleet.Fleet;
import model.placement.ship.PlacedShip;

import java.util.ArrayList;
import java.util.List;

public class ShootingBoard implements ObservableBoard {
    private static final int COLUMNS_NUMBER = 10;
    private static final int ROWS_NUMBER = 10;
    private ShootingBoardFields boardFields;
    private List<BoardObserver> observers;


    private ShootingBoard(ShootingBoardFields boardFields) {
        this.boardFields = boardFields;
        this.observers = new ArrayList<>();
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
        notifyObservers(coordinates);
        return hitResult;
    }

    public void destroyMast(Coordinates coord) {
        Field field = boardFields.get(coord);
        field.markDestroyed();
        boardFields.getAllNeighboursCoordinates(coord).forEach(this::hit);
    }

    public FieldStateName getStateName(Coordinates coordinates) {
        return boardFields.get(coordinates).getStateName();
    }

    @Override
    public void addObserver(BoardObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(Coordinates coordinates) {
        observers.forEach(observer -> observer.update(this, coordinates));
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
}
