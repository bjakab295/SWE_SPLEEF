package Spleef.Model;

/**
 * The Knight class represents a knight piece in Spleef.
 * It maintains the knight's current position (x, y) on the game board.
 */
public class Knight {
    private int x;
    private int y;

    /**
     * Constructs a Knight instance at the specified position.
     *
     * @param x  the initial x-coordinate of the knight on the game board
     * @param y  the initial y-coordinate of the knight on the game board
     */
    public Knight(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Moves the knight to the specified position on the game board.
     *
     * @param x  the x-coordinate of the position to move the knight to
     * @param y  the y-coordinate of the position to move the knight to
     */
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Resets the knight's position to the specified coordinates.
     *
     * @param x  the x-coordinate of the position to reset the knight to
     * @param y  the y-coordinate of the position to reset the knight to
     */
    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the knight's position on the game board.
     *
     * @return  the x-coordinate of the knight's position
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of the knight's position on the game board.
     *
     * @return  the y-coordinate of the knight's position
     */
    public int getY() {
        return this.y;
    }
}
