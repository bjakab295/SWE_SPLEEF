package Spleef.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {

    private GameBoard gameBoard;

    @BeforeEach
    public void setUp() {
        Knight whiteKnight = new Knight(0, 0);
        Knight blackKnight = new Knight(7, 7);
        gameBoard = new GameBoard(whiteKnight, blackKnight);
    }

    @Test
    public void testConstructor() {
        assertNotNull(gameBoard, "GameBoard object should not be null");
    }

    @Test
    public void testIsValidMove() {
        assertTrue(gameBoard.isValidMove(0, 0, 2, 1));
        assertFalse(gameBoard.isValidMove(0, 0, 3, 3));
    }

    @Test
    public void testIsOccupied() {
        assertTrue(gameBoard.isOccupied(0, 0));
        assertTrue(gameBoard.isOccupied(7, 7));
        assertFalse(gameBoard.isOccupied(1, 1));
        gameBoard.visitSquare(1, 1);
        assertTrue(gameBoard.isOccupied(1, 1));
    }

    @Test
    public void testReset() {
        gameBoard.visitSquare(1, 1);
        gameBoard.reset();
        assertFalse(gameBoard.isOccupied(1, 1));
        assertTrue(gameBoard.isOccupied(0, 0));
        assertTrue(gameBoard.isOccupied(7, 7));
    }
}
