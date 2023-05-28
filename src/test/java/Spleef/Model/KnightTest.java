package Spleef.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    private Knight knight;

    @BeforeEach
    void setUp() {
        knight = new Knight(3, 3);
    }

    @Test
    public void testConstructor() {
        assertNotNull(knight, "Knight object should not be null");
    }

    @Test
    void testGetX() {
        assertEquals(3, knight.getX());
    }

    @Test
    void testGetY() {
        assertEquals(3, knight.getY());
    }

    @Test
    void testMove() {
        knight.move(5, 5);
        assertEquals(5, knight.getX());
        assertEquals(5, knight.getY());
    }

    @Test
    void testReset() {
        knight.reset(5, 5);
        assertEquals(5, knight.getX());
        assertEquals(5, knight.getY());
    }
}