package Spleef.Model;

/**
 * The GameBoard class represents the game board of Spleef.
 * It contains an 8x8 grid of squares, each of which can be visited or not.
 * The board also has two knights, one for each player.
 */
public class GameBoard {
    private static final int SIZE = 8;
    private final boolean[][] visitedSquares;
    private final Knight whiteKnight;
    private final Knight blackKnight;

    /**
     * Constructs a GameBoard with specified knights.
     * Initializes all squares as unvisited.
     *
     * @param whiteKnight the white knight of the game
     * @param blackKnight the black knight of the game
     */
    public GameBoard(Knight whiteKnight, Knight blackKnight) {
        this.whiteKnight = whiteKnight;
        this.blackKnight = blackKnight;
        this.visitedSquares = new boolean[SIZE][SIZE];
    }

    /**
     * Checks if a proposed move is valid based on the rules of knight's movement in chess.
     *
     * @param currentX the current x-coordinate of the knight
     * @param currentY the current y-coordinate of the knight
     * @param newX the proposed new x-coordinate of the knight
     * @param newY the proposed new y-coordinate of the knight
     * @return true if the move is valid, false otherwise
     */
    public boolean isValidMove(int currentX, int currentY, int newX, int newY) {
        int xDiff = Math.abs(newX - currentX);
        int yDiff = Math.abs(newY - currentY);
        return (xDiff == 1 && yDiff == 2) || (xDiff == 2 && yDiff == 1);
    }

    /**
     * Checks if a particular square on the game board is occupied.
     *
     * @param x the x-coordinate of the square
     * @param y the y-coordinate of the square
     * @return true if the square is occupied by either knight or has been visited, false otherwise
     */
    public boolean isOccupied(int x, int y) {
        return (whiteKnight.getX() == x && whiteKnight.getY() == y)
                || (blackKnight.getX() == x && blackKnight.getY() == y)
                || visitedSquares[x][y];
    }

    /**
     * Marks a square as visited.
     *
     * @param x the x-coordinate of the square
     * @param y the y-coordinate of the square
     */
    public void visitSquare(int x, int y) {
        visitedSquares[x][y] = true;
    }

    /**
     * Resets the game board.
     * All squares are set as unvisited, except for the starting squares of the knights.
     */
    public void reset() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                visitedSquares[i][j] = false;
            }
        }
        // Set initial positions as occupied.
        visitedSquares[0][0] = true;
        visitedSquares[7][7] = true;
    }
}