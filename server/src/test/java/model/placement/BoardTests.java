package model.placement;

import model.placement.board.Board;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardTests {
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
    public void givenNewlyCreatedBoard_WhenCheck_ItIsEmpty() {
        Assert.assertTrue(board.isEmpty());
    }

    @Test
    public void givenNewlyCreatedBoard_WhenCheckSize_ItIs10x10() {
        Assert.assertEquals(board.rows(), 10);
        Assert.assertEquals(board.cols(), 10);
    }

}