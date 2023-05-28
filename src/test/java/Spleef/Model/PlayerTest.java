package Spleef.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;
    private Knight knight;

    @BeforeEach
    public void setUp() {
        this.knight = new Knight(0,0);
        this.player = new Player(knight, true, "Player");
    }

    @Test
    public void testConstructor() {
        assertNotNull(player, "Player object should not be null");
    }

    @Test
    public void testGetName() {
        assertEquals("Player", player.getName(), "Player name should match the one set in constructor");
    }

    @Test
    public void testGetKnight() {
        assertEquals(knight, player.getKnight(), "Knight should match the one set in constructor");
    }

    @Test
    public void testIsMyTurn() {
        assertTrue(player.isMyTurn(), "Should return true as set in the constructor");
    }

    @Test
    public void testSetMyTurn() {
        player.setMyTurn(false);
        assertFalse(player.isMyTurn(), "Should return false as set in the test");
    }
}
