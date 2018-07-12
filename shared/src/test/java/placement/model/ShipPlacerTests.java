package placement.model;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import placement.controller.ShipPlacer;
import placement.model.board.Board;
import placement.model.field.FieldState;
import placement.model.ship.Direction;
import placement.model.ship.DirectedShip;


public class ShipPlacerTests {
    private Board board;

    @BeforeMethod
    private void createBoard() {
        board = Board.createDefaultBoard();
    }

    @AfterMethod
    private void printBoard() {
        System.out.println(board);
    }


    @Test
    public void givenNewlyCreatedBoard_WhenShipIsPlacedCorrectly_ItIsNotEmpty() {
        int mastNumber = 4;
        DirectedShip ship = new DirectedShip(mastNumber, Direction.SOUTH);
        Coordinates headCoordinates = new Coordinates(8, 3);
        ShipPlacer.tryToPlaceShip(board, ship, headCoordinates);

        Assert.assertFalse(board.isEmpty());
    }

    @Test
    public void givenNewlyCreatedBoard_WhenShipIsPlacedIncorrectly_ItIsEmpty() {
        int mastNumber = 3;
        DirectedShip incorrectShip = new DirectedShip(mastNumber, Direction.WEST);

        ShipPlacer.tryToPlaceShip(board, incorrectShip, new Coordinates(5, 15));

        Assert.assertTrue(board.isEmpty());
    }

    @Test
    public void givenBoard_WhenShipWithOneMastIsPlaced_ThenFieldIsMarkedAsOccupied() {
        int mastNumber = 1;
        DirectedShip ship = new DirectedShip(mastNumber, Direction.SOUTH);

        ShipPlacer.tryToPlaceShip(board, ship, new Coordinates(6, 6));

        Assert.assertEquals(board.getFieldState(new Coordinates(6, 6)), FieldState.OCCUPIED);
        Assert.assertNotEquals(board.getFieldState(new Coordinates(3, 7)), FieldState.OCCUPIED);
    }

    @Test
    public void givenEmptyBoard_WhenShipWithLengthIsPlaced_ThenAllFieldAreMarkedAsOccupied() {
        int mastNumber = 3;
        DirectedShip ship = new DirectedShip(mastNumber, Direction.EAST);

        ShipPlacer.tryToPlaceShip(board, ship, new Coordinates(5, 5));

        int[][] coordsThatShouldBeOccupied = {{5, 5}, {6, 5}, {7, 5}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.OCCUPIED, coordsThatShouldBeOccupied);

        Assert.assertNotEquals(board.getFieldState(new Coordinates(8, 5)), FieldState.OCCUPIED);
        Assert.assertNotEquals(board.getFieldState(new Coordinates(4, 5)), FieldState.OCCUPIED);
    }

    @Test
    public void givenEmptyBoard_WhenShipIsPlaced_ThenSurroundingFieldsAreBuffers() {
        int mastNumber = 1;

        DirectedShip ship = new DirectedShip(mastNumber, Direction.NORTH);
        ShipPlacer.tryToPlaceShip(board, ship, new Coordinates(3, 3));

        // (3, 3) is not a buffer, but a ship
        Assert.assertNotEquals(board.getFieldState(new Coordinates(3, 3)), FieldState.BUFFER);
        Assert.assertEquals(board.getFieldState(new Coordinates(3, 3)), FieldState.OCCUPIED);

        // 8 surrounding fields are supposed to be buffers now
        int[][] coordsThatShouldBeBuffer = {{2, 3}, {4, 3}, {2, 2}, {3, 2}, {4, 2}, {2, 4}, {3, 4}, {4, 4}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.BUFFER, coordsThatShouldBeBuffer);


        // just a quick check that some other field is not a buffer (nor occupied)
        int[][] coordsThatShouldBeEmpty = {{0, 0}, {5, 5}, {2, 1}, {1, 3}, {3, 1}, {5, 5}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.EMPTY, coordsThatShouldBeEmpty);

    }

    @Test
    public void givenEmptyBoard_WhenShipIsPlacedInCorner_ThenSurroundingFieldsAreBuffers() {

        DirectedShip ship = new DirectedShip(1, Direction.NORTH);
        ShipPlacer.tryToPlaceShip(board, ship, new Coordinates(0, 0));

        // (0,0) is not a buffer, but a ship
        Assert.assertEquals(board.getFieldState(new Coordinates(0, 0)), FieldState.OCCUPIED);

        // 3 surrounding fields are supposed to be buffers now
        int[][] coordsThatShouldBeBuffer = {{0, 1}, {1, 0}, {1, 1}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.BUFFER, coordsThatShouldBeBuffer);

        // just a quick check that some other field is not a buffer (nor occupied)
        int[][] coordsThatShouldBeEmpty = {{2, 0}, {2, 1}, {2, 2}, {1, 2}, {0, 2}, {5, 5}, {2, 1}, {1, 3}, {3, 1}, {5, 5}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.EMPTY, coordsThatShouldBeEmpty);
    }

    @Test
    public void givenEmptyBoard_WhenTwoMastShipIsPlaced_ThenSurroundingFieldsAreBuffers() {

        DirectedShip ship = new DirectedShip(2, Direction.NORTH);
        ShipPlacer.tryToPlaceShip(board, ship, new Coordinates(3, 3));

        // (3, 3),(3,2) is not a buffer, but a ship
        //ship is placed
        int[][] coordsThatShouldBeOccupied = {{3, 3}, {3, 2}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.OCCUPIED, coordsThatShouldBeOccupied);

        // 10 surrounding fields are supposed to be buffers now
        int[][] coordsThatShouldBeBuffer = {{2, 1,}, {3, 1}, {4, 1}, {2, 3}, {4, 3}, {2, 2}, {4, 2}, {2, 4}, {3, 4}, {4, 4}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.BUFFER, coordsThatShouldBeBuffer);


        // just a quick check that some other field is not a buffer (nor occupied)
        int[][] coordsThatShouldBeEmpty = {{2, 0}, {1, 2}, {0, 2}, {5, 5}, {1, 3}, {5, 5}, {7, 6}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.EMPTY, coordsThatShouldBeEmpty);

    }

    @Test
    public void givenEmptyBoard_WhenThreeMastShipIsPlacedInCorner_ThenSurroundingFieldsAreBuffers() {

        DirectedShip ship = new DirectedShip(3, Direction.SOUTH);

        ShipPlacer.tryToPlaceShip(board, ship, new Coordinates(9, 0));

        //ship is placed
        int[][] coordsThatShouldBeOccupied = {{9, 0}, {9, 1}, {9, 2}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.OCCUPIED, coordsThatShouldBeOccupied);

        //ship is surrounded by buffer
        int[][] coordsThatShouldBeBuffer = {{8, 0}, {8, 1}, {8, 2}, {8, 3}, {9, 3}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.BUFFER, coordsThatShouldBeBuffer);

    }

    @Test
    public void givenEmptyBoard_WhenPLacingTwoShipsFarApart_ThenEverythingOK() {

        DirectedShip ship1 = new DirectedShip(1, Direction.NORTH);
        ShipPlacer.tryToPlaceShip(board, ship1, new Coordinates(3, 3));
        DirectedShip ship2 = new DirectedShip(3, Direction.SOUTH);
        ShipPlacer.tryToPlaceShip(board, ship2, new Coordinates(6, 5));

        // (3, 3) is not a buffer, but a ship
        Assert.assertEquals(board.getFieldState(new Coordinates(3, 3)), FieldState.OCCUPIED);

        // 8 surrounding fields are supposed to be buffers now
        int[][] coordsThatShouldBeBufferForShip1 = {{2, 3}, {4, 3}, {2, 2}, {3, 2}, {4, 2}, {2, 4}, {3, 4}, {4, 4}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.BUFFER, coordsThatShouldBeBufferForShip1);


        // (6, 5) (6, 6) (6, 7) are not a buffer, but a ship
        int[][] coordsThatShouldBeOccupiedForShip2 = {{6, 5}, {6, 6}, {6, 7}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.OCCUPIED, coordsThatShouldBeOccupiedForShip2);

        // 12 surrounding fields are supposed to be buffers now
        int[][] coordsThatShouldBeBufferForShip2 = {
                {5, 4}, {5, 5}, {5, 6}, {5, 7}, {5, 8},
                {6, 4}, {6, 8},
                {7, 4}, {7, 5}, {7, 6}, {7, 7}, {7, 8}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.BUFFER, coordsThatShouldBeBufferForShip2);


    }

    @Test
    public void givenEmptyBoard_WhenPLacingTwoShipsNextToEachOther_ThenEverythingOK() {

        DirectedShip ship1 = new DirectedShip(3, Direction.EAST);
        DirectedShip ship2 = new DirectedShip(2, Direction.SOUTH);

        ShipPlacer.tryToPlaceShip(board, ship2, new Coordinates(3, 7));
        ShipPlacer.tryToPlaceShip(board, ship1, new Coordinates(3, 5));

        //surrounding fields are buffer
        int[][] coordsThatShouldBeBuffer = {
                {2, 4}, {2, 5}, {2, 6}, {2, 7}, {2, 8}, {2, 9},
                {3, 4}, {3, 6}, {3, 9},
                {4, 4}, {4, 6}, {4, 7}, {4, 8}, {4, 9},
                {5, 4}, {5, 6},
                {6, 4}, {6, 5}, {6, 6}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.BUFFER, coordsThatShouldBeBuffer);

        //ship1 is placed
        int[][] coordsThatShouldBeOccupiedForShip1 = {{3, 5}, {4, 5}, {5, 5}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.OCCUPIED, coordsThatShouldBeOccupiedForShip1);


        //ship2 is placed
        int[][] coordsThatShouldBeOccupiedForShip2 = {{3, 7}, {3, 8}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.OCCUPIED, coordsThatShouldBeOccupiedForShip2);

    }


    @Test
    public void givenEmptyBoard_WhenPlacingThreeShipsOnTheBoardEdge_ThenEverythingOK() {

        DirectedShip ship1 = new DirectedShip(3, Direction.EAST);
        DirectedShip ship2 = new DirectedShip(2, Direction.SOUTH);
        DirectedShip ship3 = new DirectedShip(4, Direction.NORTH);

        ShipPlacer.tryToPlaceShip(board, ship1, new Coordinates(3, 9));
        ShipPlacer.tryToPlaceShip(board, ship2, new Coordinates(0, 7));
        ShipPlacer.tryToPlaceShip(board, ship3, new Coordinates(9, 9));


        //ship1 is placed
        int[][] coordsThatShouldBeOccupiedForShip1 = {{3, 9}, {4, 9}, {5, 9}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.OCCUPIED, coordsThatShouldBeOccupiedForShip1);


        //ship2 is placed
        int[][] coordsThatShouldBeOccupiedForShip2 = {{0, 7}, {0, 8}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.OCCUPIED, coordsThatShouldBeOccupiedForShip2);


        //ship3 is placed
        int[][] coordsThatShouldBeOccupiedForShip3 = {{9, 6}, {9, 7}, {9, 8}, {9, 9}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.OCCUPIED, coordsThatShouldBeOccupiedForShip3);

    }

    @Test
    public void board_shipPlacedOutOfBoard_ShipIsNotPlaced() {

        DirectedShip ship = new DirectedShip(3, Direction.EAST);
        ShipPlacer.tryToPlaceShip(board, ship, new Coordinates(9, 0));

        Assert.assertNotEquals(board.getFieldState(new Coordinates(9, 0)), FieldState.OCCUPIED);
    }

    @Test
    public void board_shipPlacedOnOtherShip_ShipIsNotPlaced() {

        DirectedShip firstShip = new DirectedShip(3, Direction.EAST);
        DirectedShip shipPlacedOnFirstShip = new DirectedShip(3, Direction.SOUTH);
        ShipPlacer.tryToPlaceShip(board, firstShip, new Coordinates(1, 1));
        ShipPlacer.tryToPlaceShip(board, shipPlacedOnFirstShip, new Coordinates(1, 1));

        //second ship is not placed
        System.out.println(board.getFieldState(new Coordinates(1, 3)));
        Assert.assertNotEquals(board.getFieldState(new Coordinates(1, 2)), FieldState.OCCUPIED);
        Assert.assertNotEquals(board.getFieldState(new Coordinates(1, 3)), FieldState.OCCUPIED);

        //fist ship is placed
        int[][] coordsThatShouldBeOccupied = {{1, 1}, {2, 1}, {3, 1}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.OCCUPIED, coordsThatShouldBeOccupied);

    }

    @Test
    public void board_shipPlacedOnBuffer_ShipIsNotPlaced() {

        DirectedShip ship1 = new DirectedShip(3, Direction.EAST);
        DirectedShip ship2 = new DirectedShip(3, Direction.SOUTH);
        ShipPlacer.tryToPlaceShip(board, ship1, new Coordinates(1, 1));
        ShipPlacer.tryToPlaceShip(board, ship2, new Coordinates(1, 2));

        //ship1 is placed
        int[][] coordsThatShouldBeOccupiedByShip1 = {{1, 1}, {2, 1}, {3, 1}};
        multipleAssertEqualsIsExpectedFieldState(board, FieldState.OCCUPIED, coordsThatShouldBeOccupiedByShip1);

        //ship2 is not placed
        Assert.assertNotEquals(board.getFieldState(new Coordinates(1, 2)), FieldState.OCCUPIED);
        Assert.assertNotEquals(board.getFieldState(new Coordinates(1, 3)), FieldState.OCCUPIED);

    }

    private void multipleAssertEqualsIsExpectedFieldState(Board board, FieldState expectedFieldState, int[][] coordinates) {
        for (int[] coord : coordinates) {
            Assert.assertEquals(board.getFieldState(new Coordinates(coord[0], coord[1])), expectedFieldState);
        }
    }

}
