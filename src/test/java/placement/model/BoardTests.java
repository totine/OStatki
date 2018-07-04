package placement.model;

import org.testng.Assert;
import org.testng.annotations.Test;


public class BoardTests {
    @Test
    public void givenNewlyCreatedBoard_WhenCheck_ItIsEmpty() {
        Board board = Board.createDefaultBoard();
        Assert.assertTrue(board.isEmpty());
    }

    @Test
    public void givenNewlyCreatedBoard_WhenCheckSize_ItIs10x10() {
        Board board = Board.createDefaultBoard();
        Assert.assertEquals(board.rows(), 10);
        Assert.assertEquals(board.cols(), 10);
    }

    @Test
    public void givenNewlyCreatedBoard_WhenShipIsPlacedCorrectly_ItIsNotEmpty() {
        Board board = Board.createDefaultBoard();
        Ship ship = new Ship(4, Direction.SOUTH);

        board.placeShip(ship, 8, 3);

        Assert.assertFalse(board.isEmpty());
    }

    @Test
    public void givenNewlyCreatedBoard_WhenShipIsPlacedIncorrectly_ItIsEmpty() {
        Board board = Board.createDefaultBoard();
        Ship incorrectShip = new Ship(3, Direction.NORTH);

        board.placeShip(incorrectShip, 5, 15);

        Assert.assertTrue(board.isEmpty());
    }

    @Test
    public void givenBoard_WhenShipWithOneMastIsPlaced_ThenFieldIsMarkedAsOccupied() {
        Board board = Board.createDefaultBoard();
        Ship ship = new Ship(1, Direction.SOUTH);

        board.placeShip(ship, 6, 6);

        Assert.assertEquals(board.checkFieldState(6, 6), FieldState.OCCUPIED);
        Assert.assertNotEquals(board.checkFieldState(3, 7), FieldState.OCCUPIED);
    }

    @Test
    public void givenEmptyBoard_WhenShipWithLengthIsPlaced_ThenAllFieldAreMarkedAsOccupied() {
        Board board = Board.createDefaultBoard();
        Ship ship = new Ship(3, Direction.EAST);

        board.placeShip(ship, 5, 5);

        Assert.assertEquals(board.checkFieldState(5, 5), FieldState.OCCUPIED);
        Assert.assertEquals(board.checkFieldState(6, 5), FieldState.OCCUPIED);
        Assert.assertEquals(board.checkFieldState(7, 5), FieldState.OCCUPIED);

        Assert.assertNotEquals(board.checkFieldState(8, 5), FieldState.OCCUPIED);
        Assert.assertNotEquals(board.checkFieldState(4, 5), FieldState.OCCUPIED);
    }

    @Test
    public void givenEmptyBoard_WhenShipIsPlaced_ThenSurroundingFieldsAreBuffers() {
        Board board = Board.createDefaultBoard();
        Ship ship = new Ship(1, Direction.NORTH);
        board.placeShip(ship, 3, 3);

        // (3, 3) is not a buffer, but a ship
        Assert.assertNotEquals(board.checkFieldState(3, 3), FieldState.BUFFER);

        // 8 surrounding fields are supposed to be buffers now
        Assert.assertEquals(board.checkFieldState(2, 3), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(4, 3), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(2, 2), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(3, 2), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(4, 2), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(2, 4), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(3, 4), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(4, 4), FieldState.BUFFER);

        // just a quick check that some other field is not a buffer (nor occupied)
        Assert.assertNotEquals(board.checkFieldState(5, 5), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(2, 1), FieldState.BUFFER);
    }

    @Test
    public void givenEmptyBoard_WhenShipIsPlacedInCorner_ThenSurroundingFieldsAreBuffers() {
        Board board = Board.createDefaultBoard();
        Ship ship = new Ship(1, Direction.NORTH);
        board.placeShip(ship, 0, 0);

        // (3, 3) is not a buffer, but a ship
        Assert.assertNotEquals(board.checkFieldState(0, 0), FieldState.BUFFER);

        // 8 surrounding fields are supposed to be buffers now
        Assert.assertEquals(board.checkFieldState(0, 1), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(1, 0), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(1, 1), FieldState.BUFFER);


        // just a quick check that some other field is not a buffer (nor occupied)
        Assert.assertNotEquals(board.checkFieldState(2, 2), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(2, 1), FieldState.BUFFER);
    }

    @Test
    public void givenEmptyBoard_WhenTwoMastShipIsPlaced_ThenSurroundingFieldsAreBuffers() {
        Board board = Board.createDefaultBoard();
        Ship ship = new Ship(2, Direction.NORTH);
        board.placeShip(ship, 3, 3);

        // (3, 3) is not a buffer, but a ship
        Assert.assertNotEquals(board.checkFieldState(3, 3), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(3, 2), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(3, 3), FieldState.OCCUPIED);
        Assert.assertEquals(board.checkFieldState(3, 2), FieldState.OCCUPIED);

        // 8 surrounding fields are supposed to be buffers now
        Assert.assertEquals(board.checkFieldState(2, 1), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(3, 1), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(4, 1), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(2, 2), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(4, 2), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(2, 3), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(4, 3), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(2, 4), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(3, 4), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(4, 4), FieldState.BUFFER);


        // just a quick check that some other field is not a buffer (nor occupied)
        Assert.assertNotEquals(board.checkFieldState(5, 5), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(7, 6), FieldState.BUFFER);
    }

    @Test
    public void givenEmptyBoard_WhenThreeMastShipIsPlacedInCorner_ThenSurroundingFieldsAreBuffers() {
        Board board = Board.createDefaultBoard();
        Ship ship = new Ship(3, Direction.SOUTH);

        board.placeShip(ship, 9, 0);

        Assert.assertEquals(board.checkFieldState(9, 0), FieldState.OCCUPIED);
        Assert.assertEquals(board.checkFieldState(9, 1), FieldState.OCCUPIED);
        Assert.assertEquals(board.checkFieldState(9, 2), FieldState.OCCUPIED);

        Assert.assertEquals(board.checkFieldState(8, 0), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(8, 1), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(8, 2), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(8, 3), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(9, 3), FieldState.BUFFER);

        Assert.assertNotEquals(board.checkFieldState(7, 1), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(7, 3), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(6, 2), FieldState.BUFFER);
    }

    @Test
    public void givenEmptyBoard_WhenPLacingTwoShipsFarApart_ThenEverythingOK() {
        Board board = Board.createDefaultBoard();
        Ship ship = new Ship(1, Direction.NORTH);
        board.placeShip(ship, 3, 3);

        // (3, 3) is not a buffer, but a ship
        Assert.assertNotEquals(board.checkFieldState(3, 3), FieldState.BUFFER);

        // 8 surrounding fields are supposed to be buffers now
        Assert.assertEquals(board.checkFieldState(2, 3), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(4, 3), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(2, 2), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(3, 2), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(4, 2), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(2, 4), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(3, 4), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(4, 4), FieldState.BUFFER);

        // just a quick check that some other field is not a buffer (nor occupied)
        Assert.assertNotEquals(board.checkFieldState(5, 5), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(2, 1), FieldState.BUFFER);


        Ship ship1 = new Ship(3, Direction.SOUTH);
        board.placeShip(ship1, 6, 5);
        System.out.println(board);
        // (6, 5) (6, 6) (6, 7) are not a buffer, but a ship
        Assert.assertNotEquals(board.checkFieldState(6, 5), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(6, 6), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(6, 7), FieldState.BUFFER);

        // 8 surrounding fields are supposed to be buffers now
        Assert.assertEquals(board.checkFieldState(5, 4), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(6, 4), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(7, 4), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(7, 5), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(7, 6), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(7, 7), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(7, 8), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(6, 8), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(5, 8), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(5, 7), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(5, 6), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(5, 5), FieldState.BUFFER);

        // just a quick check that some other field is not a buffer (nor occupied)
        Assert.assertNotEquals(board.checkFieldState(5, 9), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(8, 8), FieldState.BUFFER);
    }

    @Test
    public void givenEmptyBoard_WhenPLacingTwoShipsNextToEachOther_ThenEverythingOK() {
        Board board = Board.createDefaultBoard();
        Ship ship = new Ship(3, Direction.EAST);
        Ship ship1 = new Ship(2, Direction.SOUTH);

        board.placeShip(ship1, 3, 7);
        board.placeShip(ship, 3, 5);

        System.out.println(board);

        Assert.assertEquals(board.checkFieldState(3, 4), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(4, 4), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(5, 4), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(6, 4), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(6, 5), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(6, 6), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(5, 6), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(4, 6), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(3, 6), FieldState.BUFFER);

        Assert.assertNotEquals(board.checkFieldState(3, 7), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(3, 8), FieldState.BUFFER);

        Assert.assertNotEquals(board.checkFieldState(3, 5), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(4, 5), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(5, 5), FieldState.BUFFER);


        Assert.assertEquals(board.checkFieldState(4, 7), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(4, 8), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(3, 9), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(4, 9), FieldState.BUFFER);

    }


    @Test
    public void givenEmptyBoard_WhenPlacingThreeShipsOnTheBoardEdge_ThenEverythingOK() {
        Board board = Board.createDefaultBoard();
        Ship ship = new Ship(3, Direction.EAST);
        Ship ship1 = new Ship(2, Direction.SOUTH);
        Ship ship2 = new Ship(4, Direction.NORTH);

        board.placeShip(ship, 3, 9);
        board.placeShip(ship1, 0, 7);
        board.placeShip(ship2, 9, 9);

        System.out.println(board);


        Assert.assertNotEquals(board.checkFieldState(3, 9), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(4, 9), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(5, 9), FieldState.BUFFER);

        Assert.assertEquals(board.checkFieldState(3, 9), FieldState.OCCUPIED);
        Assert.assertEquals(board.checkFieldState(4, 9), FieldState.OCCUPIED);
        Assert.assertEquals(board.checkFieldState(5, 9), FieldState.OCCUPIED);


        Assert.assertNotEquals(board.checkFieldState(0, 7), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(0, 8), FieldState.BUFFER);

        Assert.assertEquals(board.checkFieldState(0, 7), FieldState.OCCUPIED);
        Assert.assertEquals(board.checkFieldState(0, 8), FieldState.OCCUPIED);

        Assert.assertNotEquals(board.checkFieldState(9, 6), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(9, 7), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(9, 8), FieldState.BUFFER);
        Assert.assertNotEquals(board.checkFieldState(9, 9), FieldState.BUFFER);

        Assert.assertEquals(board.checkFieldState(9, 6), FieldState.OCCUPIED);
        Assert.assertEquals(board.checkFieldState(9, 7), FieldState.OCCUPIED);
        Assert.assertEquals(board.checkFieldState(9, 8), FieldState.OCCUPIED);
        Assert.assertEquals(board.checkFieldState(9, 9), FieldState.OCCUPIED);

    }

    @Test
    public void board_shipPlacedOutOfBoard_ShipIsNotPlaced() {
        Board board = Board.createDefaultBoard();
        Ship ship = new Ship(3, Direction.EAST);
        board.placeShip(ship, 9, 0);
        System.out.println(board);

        Assert.assertNotEquals(board.checkFieldState(9, 0), FieldState.OCCUPIED);
    }

    @Test
    public void board_shipPlacedOnOtherShip_ShipIsNotPlaced() {
        Board board = Board.createDefaultBoard();
        Ship ship = new Ship(3, Direction.EAST);
        Ship ship2 = new Ship(3, Direction.SOUTH);
        board.placeShip(ship, 1, 1);
        board.placeShip(ship2, 1, 1);
        System.out.println(board);

        Assert.assertNotEquals(board.checkFieldState(1, 2), FieldState.OCCUPIED);
        Assert.assertNotEquals(board.checkFieldState(1, 3), FieldState.OCCUPIED);

        Assert.assertEquals(board.checkFieldState(1, 1), FieldState.OCCUPIED);
        Assert.assertEquals(board.checkFieldState(2, 1), FieldState.OCCUPIED);
        Assert.assertEquals(board.checkFieldState(2, 1), FieldState.OCCUPIED);
    }

    @Test
    public void board_shipPlacedOnBuffer_ShipIsNotPlaced() {
        Board board = Board.createDefaultBoard();
        Ship ship = new Ship(3, Direction.EAST);
        Ship ship2 = new Ship(3, Direction.SOUTH);
        board.placeShip(ship, 1, 1);
        board.placeShip(ship2, 1, 2);
        System.out.println(board);

        Assert.assertNotEquals(board.checkFieldState(1, 2), FieldState.OCCUPIED);
        Assert.assertNotEquals(board.checkFieldState(1, 3), FieldState.OCCUPIED);
        Assert.assertNotEquals(board.checkFieldState(1, 4), FieldState.OCCUPIED);


        Assert.assertEquals(board.checkFieldState(1, 2), FieldState.BUFFER);
        Assert.assertEquals(board.checkFieldState(1, 1), FieldState.OCCUPIED);
        Assert.assertEquals(board.checkFieldState(2, 1), FieldState.OCCUPIED);
        Assert.assertEquals(board.checkFieldState(2, 1), FieldState.OCCUPIED);
    }

}
