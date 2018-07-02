package placement.model;

import org.testng.Assert;
import org.testng.annotations.Test;


public class BoardTests {
    @Test
    public void givenNewlyCreatedBoard_WhenCheck_ItIsEmpty() {
        Board board = new Board();
        Assert.assertTrue(board.isEmpty());
    }

    @Test
    public void givenNewlyCreatedBoard_WhenCheckSize_ItIs10x10() {
        Board board = new Board();
        Assert.assertEquals(board.rows(), 10);
        Assert.assertEquals(board.cols(), 10);
    }

    @Test
    public void givenNewlyCreatedBoard_WhenShipIsPlacedCorrectly_ItIsNotEmpty() {
        Board board = new Board();
        Ship ship = new Ship(4, Direction.SOUTH);

        board.placeShip(ship, 8, 3);

        Assert.assertFalse(board.isEmpty());
    }

    @Test
    public void givenNewlyCreatedBoard_WhenShipIsPlacedIncorrectly_ItIsEmpty() {
        Board board = new Board();
        Ship incorrectShip = new Ship(3, Direction.NORTH);

        board.placeShip(incorrectShip, 5, 15);

        Assert.assertTrue(board.isEmpty());
    }

    @Test
    public void givenBoard_WhenShipWithOneMastIsPlaced_ThenFieldIsMarkedAsOccupied() {
        Board board = new Board();
        Ship ship = new Ship(1, Direction.SOUTH);

        board.placeShip(ship, 6, 6);

        Assert.assertTrue(board.isFieldOccupied(6, 6));
        Assert.assertFalse(board.isFieldOccupied(3, 7));
    }

    @Test
    public void givenEmptyBoard_WhenShipWithLengthIsPlaced_ThenAllFieldAreMarkedAsOccupied() {
        Board board = new Board();
        Ship ship = new Ship(3, Direction.EAST);

        board.placeShip(ship, 5, 5);

        Assert.assertTrue(board.isFieldOccupied(5, 5));
        Assert.assertTrue(board.isFieldOccupied(6, 5));
        Assert.assertTrue(board.isFieldOccupied(7, 5));

        Assert.assertFalse(board.isFieldOccupied(8, 5));
        Assert.assertFalse(board.isFieldOccupied(4, 5));
    }

    @Test
    public void givenEmptyBoard_WhenShipIsPlaced_ThenSurroundingFieldsAreBuffers() {
        Board board = new Board();
        Ship ship = new Ship(1, Direction.NORTH);
        board.placeShip(ship, 3, 3);

        // (3, 3) is not a buffer, but a ship
        Assert.assertFalse(board.isFieldBuffer(3, 3));

        // 8 surrounding fields are supposed to be buffers now
        Assert.assertTrue(board.isFieldBuffer(2, 3));
        Assert.assertTrue(board.isFieldBuffer(4, 3));
        Assert.assertTrue(board.isFieldBuffer(2, 2));
        Assert.assertTrue(board.isFieldBuffer(3, 2));
        Assert.assertTrue(board.isFieldBuffer(4, 2));
        Assert.assertTrue(board.isFieldBuffer(2, 4));
        Assert.assertTrue(board.isFieldBuffer(3, 4));
        Assert.assertTrue(board.isFieldBuffer(4, 4));

        // just a quick check that some other field is not a buffer (nor occupied)
        Assert.assertFalse(board.isFieldBuffer(5, 5));
        Assert.assertFalse(board.isFieldBuffer(2, 1));
    }

    @Test
    public void givenEmptyBoard_WhenShipIsPlacedInCorner_ThenSurroundingFieldsAreBuffers() {
        Board board = new Board();
        Ship ship = new Ship(1, Direction.NORTH);
        board.placeShip(ship, 0, 0);

        // (3, 3) is not a buffer, but a ship
        Assert.assertFalse(board.isFieldBuffer(0, 0));

        // 8 surrounding fields are supposed to be buffers now
        Assert.assertTrue(board.isFieldBuffer(0, 1));
        Assert.assertTrue(board.isFieldBuffer(1, 0));
        Assert.assertTrue(board.isFieldBuffer(1, 1));


        // just a quick check that some other field is not a buffer (nor occupied)
        Assert.assertFalse(board.isFieldBuffer(2, 2));
        Assert.assertFalse(board.isFieldBuffer(2, 1));
    }

    @Test
    public void givenEmptyBoard_WhenTwoMastShipIsPlaced_ThenSurroundingFieldsAreBuffers() {
        Board board = new Board();
        Ship ship = new Ship(2, Direction.NORTH);
        board.placeShip(ship, 3, 3);

        // (3, 3) is not a buffer, but a ship
        Assert.assertFalse(board.isFieldBuffer(3, 3));
        Assert.assertFalse(board.isFieldBuffer(3, 2));
        Assert.assertTrue(board.isFieldOccupied(3, 3));
        Assert.assertTrue(board.isFieldOccupied(3, 2));

        // 8 surrounding fields are supposed to be buffers now
        Assert.assertTrue(board.isFieldBuffer(2, 1));
        Assert.assertTrue(board.isFieldBuffer(3, 1));
        Assert.assertTrue(board.isFieldBuffer(4, 1));
        Assert.assertTrue(board.isFieldBuffer(2, 2));
        Assert.assertTrue(board.isFieldBuffer(4, 2));
        Assert.assertTrue(board.isFieldBuffer(2, 3));
        Assert.assertTrue(board.isFieldBuffer(4, 3));
        Assert.assertTrue(board.isFieldBuffer(2, 4));
        Assert.assertTrue(board.isFieldBuffer(3, 4));
        Assert.assertTrue(board.isFieldBuffer(4, 4));


        // just a quick check that some other field is not a buffer (nor occupied)
        Assert.assertFalse(board.isFieldBuffer(5, 5));
        Assert.assertFalse(board.isFieldBuffer(7, 6));
    }

    @Test
    public void givenEmptyBoard_WhenThreeMastShipIsPlacedInCorner_ThenSurroundingFieldsAreBuffers() {
        Board board = new Board();
        Ship ship = new Ship(3, Direction.SOUTH);

        board.placeShip(ship, 9, 0);

        Assert.assertTrue(board.isFieldOccupied(9, 0));
        Assert.assertTrue(board.isFieldOccupied(9, 1));
        Assert.assertTrue(board.isFieldOccupied(9, 2));

        Assert.assertTrue(board.isFieldBuffer(8, 0));
        Assert.assertTrue(board.isFieldBuffer(8, 1));
        Assert.assertTrue(board.isFieldBuffer(8, 2));
        Assert.assertTrue(board.isFieldBuffer(8, 3));
        Assert.assertTrue(board.isFieldBuffer(9, 3));

        Assert.assertFalse(board.isFieldBuffer(7, 1));
        Assert.assertFalse(board.isFieldBuffer(7, 3));
        Assert.assertFalse(board.isFieldBuffer(6, 2));
    }

    @Test
    public void givenEmptyBoard_WhenPLacingTwoShipsFarApart_ThenEverythingOK() {
        Board board = new Board();
        Ship ship = new Ship(1, Direction.NORTH);
        board.placeShip(ship, 3, 3);

        // (3, 3) is not a buffer, but a ship
        Assert.assertFalse(board.isFieldBuffer(3, 3));

        // 8 surrounding fields are supposed to be buffers now
        Assert.assertTrue(board.isFieldBuffer(2, 3));
        Assert.assertTrue(board.isFieldBuffer(4, 3));
        Assert.assertTrue(board.isFieldBuffer(2, 2));
        Assert.assertTrue(board.isFieldBuffer(3, 2));
        Assert.assertTrue(board.isFieldBuffer(4, 2));
        Assert.assertTrue(board.isFieldBuffer(2, 4));
        Assert.assertTrue(board.isFieldBuffer(3, 4));
        Assert.assertTrue(board.isFieldBuffer(4, 4));

        // just a quick check that some other field is not a buffer (nor occupied)
        Assert.assertFalse(board.isFieldBuffer(5, 5));
        Assert.assertFalse(board.isFieldBuffer(2, 1));


        Ship ship1 = new Ship(3, Direction.SOUTH);
        board.placeShip(ship1, 6, 5);
        System.out.println(board);
        // (6, 5) (6, 6) (6, 7) are not a buffer, but a ship
        Assert.assertFalse(board.isFieldBuffer(6, 5));
        Assert.assertFalse(board.isFieldBuffer(6, 6));
        Assert.assertFalse(board.isFieldBuffer(6, 7));

        // 8 surrounding fields are supposed to be buffers now
        Assert.assertTrue(board.isFieldBuffer(5, 4));
        Assert.assertTrue(board.isFieldBuffer(6, 4));
        Assert.assertTrue(board.isFieldBuffer(7, 4));
        Assert.assertTrue(board.isFieldBuffer(7, 5));
        Assert.assertTrue(board.isFieldBuffer(7, 6));
        Assert.assertTrue(board.isFieldBuffer(7, 7));
        Assert.assertTrue(board.isFieldBuffer(7, 8));
        Assert.assertTrue(board.isFieldBuffer(6, 8));
        Assert.assertTrue(board.isFieldBuffer(5, 8));
        Assert.assertTrue(board.isFieldBuffer(5, 7));
        Assert.assertTrue(board.isFieldBuffer(5, 6));
        Assert.assertTrue(board.isFieldBuffer(5, 5));

        // just a quick check that some other field is not a buffer (nor occupied)
        Assert.assertFalse(board.isFieldBuffer(5, 9));
        Assert.assertFalse(board.isFieldBuffer(8, 8));
    }

    @Test
    public void givenEmptyBoard_WhenPLacingTwoShipsNextToEachOther_ThenEverythingOK() {
        Board board = new Board();
        Ship ship = new Ship(3, Direction.EAST);
        Ship ship1 = new Ship(2, Direction.SOUTH);

        board.placeShip(ship1, 3, 7);
        board.placeShip(ship, 3, 5);

        System.out.println(board);

        Assert.assertTrue(board.isFieldBuffer(3, 4));
        Assert.assertTrue(board.isFieldBuffer(4, 4));
        Assert.assertTrue(board.isFieldBuffer(5, 4));
        Assert.assertTrue(board.isFieldBuffer(6, 4));
        Assert.assertTrue(board.isFieldBuffer(6, 5));
        Assert.assertTrue(board.isFieldBuffer(6, 6));
        Assert.assertTrue(board.isFieldBuffer(5, 6));
        Assert.assertTrue(board.isFieldBuffer(4, 6));
        Assert.assertTrue(board.isFieldBuffer(3, 6));

        Assert.assertFalse(board.isFieldBuffer(3, 7));
        Assert.assertFalse(board.isFieldBuffer(3, 8));

        Assert.assertFalse(board.isFieldBuffer(3, 5));
        Assert.assertFalse(board.isFieldBuffer(4, 5));
        Assert.assertFalse(board.isFieldBuffer(5, 5));


        Assert.assertTrue(board.isFieldBuffer(4, 7));
        Assert.assertTrue(board.isFieldBuffer(4, 8));
        Assert.assertTrue(board.isFieldBuffer(3, 9));
        Assert.assertTrue(board.isFieldBuffer(4, 9));

    }


    @Test
    public void givenEmptyBoard_WhenPlacingThreeShipsOnTheBoardEdge_ThenEverythingOK() {
        Board board = new Board();
        Ship ship = new Ship(3, Direction.EAST);
        Ship ship1 = new Ship(2, Direction.SOUTH);
        Ship ship2 = new Ship(4, Direction.NORTH);

        board.placeShip(ship, 3, 9);
        board.placeShip(ship1, 0, 7);
        board.placeShip(ship2, 9, 9);

        System.out.println(board);


        Assert.assertFalse(board.isFieldBuffer(3, 9));
        Assert.assertFalse(board.isFieldBuffer(4, 9));
        Assert.assertFalse(board.isFieldBuffer(5, 9));

        Assert.assertTrue(board.isFieldOccupied(3, 9));
        Assert.assertTrue(board.isFieldOccupied(4, 9));
        Assert.assertTrue(board.isFieldOccupied(5, 9));

        Assert.assertFalse(board.isFieldBuffer(0, 7));
        Assert.assertFalse(board.isFieldBuffer(0, 8));

        Assert.assertTrue(board.isFieldOccupied(0, 7));
        Assert.assertTrue(board.isFieldOccupied(0, 8));

        Assert.assertFalse(board.isFieldBuffer(9, 6));
        Assert.assertFalse(board.isFieldBuffer(9, 7));
        Assert.assertFalse(board.isFieldBuffer(9, 8));
        Assert.assertFalse(board.isFieldBuffer(9, 9));

        Assert.assertTrue(board.isFieldOccupied(9, 6));
        Assert.assertTrue(board.isFieldOccupied(9, 7));
        Assert.assertTrue(board.isFieldOccupied(9, 8));
        Assert.assertTrue(board.isFieldOccupied(9, 9));

    }

    @Test
    public void board_shipPlacedOutOfBoard_ShipIsNotPlaced() {
        Board board = new Board();
        Ship ship = new Ship(3, Direction.EAST);
        board.placeShip(ship, 9, 0);
        System.out.println(board);

        Assert.assertFalse(board.isFieldOccupied(9, 0));
    }

    @Test
    public void board_shipPlacedOnOtherShip_ShipIsNotPlaced() {
        Board board = new Board();
        Ship ship = new Ship(3, Direction.EAST);
        Ship ship2 = new Ship(3, Direction.SOUTH);
        board.placeShip(ship, 1, 1);
        board.placeShip(ship2, 1, 1);
        System.out.println(board);

        Assert.assertFalse(board.isFieldOccupied(1, 2));
        Assert.assertFalse(board.isFieldOccupied(1, 3));

        Assert.assertTrue(board.isFieldOccupied(1, 1));
        Assert.assertTrue(board.isFieldOccupied(2, 1));
        Assert.assertTrue(board.isFieldOccupied(2, 1));
    }

    @Test
    public void board_shipPlacedOnBuffer_ShipIsNotPlaced() {
        Board board = new Board();
        Ship ship = new Ship(3, Direction.EAST);
        Ship ship2 = new Ship(3, Direction.SOUTH);
        board.placeShip(ship, 1, 1);
        board.placeShip(ship2, 1, 2);
        System.out.println(board);

        Assert.assertFalse(board.isFieldOccupied(1, 2));
        Assert.assertFalse(board.isFieldOccupied(1, 3));
        Assert.assertFalse(board.isFieldOccupied(1, 4));


        Assert.assertTrue(board.isFieldBuffer(1, 2));
        Assert.assertTrue(board.isFieldOccupied(1, 1));
        Assert.assertTrue(board.isFieldOccupied(2, 1));
        Assert.assertTrue(board.isFieldOccupied(2, 1));
    }

}
