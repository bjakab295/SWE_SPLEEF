package Spleef.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game game;
    private Knight whiteKnight;
    private Knight blackKnight;

    @BeforeEach
    public void setUp() {
        whiteKnight = new Knight(0, 0);
        blackKnight = new Knight(7, 7);
        String whitePlayerName = "White";
        String blackPlayerName = "Black";
        game = new Game(whiteKnight, blackKnight, whitePlayerName, blackPlayerName);
    }

    @Test
    public void testConstructor() {
        assertNotNull(game, "Game object should not be null");
    }

    @Test
    public void testAttemptMove() {
        // Test a valid move
        assertTrue(game.attemptMove(game.getWhitePlayer(), 2, 1));
        // Test an invalid move
        assertFalse(game.attemptMove(game.getBlackPlayer(), 3, 3));
        // Test a move to an occupied square
        assertFalse(game.attemptMove(game.getWhitePlayer(), 7, 7));
        assertFalse(game.attemptMove(game.getWhitePlayer(), 0,0));
    }

    @Test
    public void testChangeTurns() {
        assertTrue(game.getWhitePlayer().isMyTurn());
        assertFalse(game.getBlackPlayer().isMyTurn());
        game.changeTurns();
        assertFalse(game.getWhitePlayer().isMyTurn());
        assertTrue(game.getBlackPlayer().isMyTurn());
    }

    @Test
    public void testIsGameOver() {
        // Game should not be over at start
        assertFalse(game.isGameOver(game.getWhitePlayer()));
        assertFalse(game.isGameOver(game.getBlackPlayer()));
    }

    @Test
    public void testReset() {
        // Make a move and then reset the game
        game.attemptMove(game.getWhitePlayer(), 2, 1);
        game.reset();
        // Knights should be back at their starting squares
        assertEquals(0, game.getWhitePlayer().getKnight().getX());
        assertEquals(0, game.getWhitePlayer().getKnight().getY());
        assertEquals(7, game.getBlackPlayer().getKnight().getX());
        assertEquals(7, game.getBlackPlayer().getKnight().getY());
        // White player should have the turn
        assertTrue(game.getWhitePlayer().isMyTurn());
        assertFalse(game.getBlackPlayer().isMyTurn());
    }

    @Test
    public void testGetWhitePlayer() {
        assertEquals(whiteKnight, game.getWhitePlayer().getKnight(), "WhiteKnight should match the one set in constructor");
    }

    @Test
    public void testGetBlackPlayer() {
        assertEquals(blackKnight, game.getBlackPlayer().getKnight(), "BlackKnight should match the one set in constructor");
    }

    @Test
    public void testGetCurrentPlayerIfWhite() {
        game.getWhitePlayer().setMyTurn(true);
        game.getBlackPlayer().setMyTurn(false);

        Player result = game.getCurrentPlayer();

        assertEquals(game.getWhitePlayer(), result);
    }

    @Test
    public void testGetCurrentPlayerIfBlack() {
        game.getWhitePlayer().setMyTurn(false);
        game.getBlackPlayer().setMyTurn(true);

        Player result = game.getCurrentPlayer();

        assertEquals(game.getBlackPlayer(), result);
    }
}
