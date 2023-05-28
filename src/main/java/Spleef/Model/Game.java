package Spleef.Model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Game class represents a game of Spleef.
 * It maintains the game board and the two players, and handles game logic such as moves and turns.
 */
public class Game {
    private final GameBoard gameBoard;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private static final Logger logger = LogManager.getLogger(Game.class);

    /**
     * Constructs a Game instance with the given knights.
     *
     * @param whiteKnight  the knight for the white player
     * @param blackKnight  the knight for the black player
     * @param whitePlayerName the name of the white player
     * @param blackPlayerName the name of the black player
     */
    public Game(Knight whiteKnight, Knight blackKnight, String whitePlayerName, String blackPlayerName) {

        whitePlayer = new Player(whiteKnight, true, whitePlayerName);
        blackPlayer = new Player(blackKnight, false, blackPlayerName);

        gameBoard = new GameBoard(whiteKnight, blackKnight);

        // Visit the starting squares of the knights
        gameBoard.visitSquare(whiteKnight.getX(), whiteKnight.getY());
        gameBoard.visitSquare(blackKnight.getX(), blackKnight.getY());
    }

    /**
     * Attempts to move the given player's knight to the specified coordinates.
     *
     * @param player  the player attempting the move
     * @param x       the x-coordinate to move to
     * @param y       the y-coordinate to move to
     * @return  true if the move was successful, false otherwise
     */
    public boolean attemptMove(Player player, int x, int y) {
        Knight knight = player.getKnight();
        String playerName = getCurrentPlayer().getName();
        int knightX = knight.getX();
        int knightY = knight.getY();

        // Check if the move is a valid L-shaped move
        if ((Math.abs(knightX - x) == 2 && Math.abs(knightY - y) == 1)
                || (Math.abs(knightX - x) == 1 && Math.abs(knightY - y) == 2)) {
            // Check if the destination square is occupied
            if (!gameBoard.isOccupied(x, y)) {  // Square should be unoccupied for a valid move
                logger.info(playerName + " trying to move from " + knightX + ", " + knightY + " to " + x + ", " + y);
                knight.move(x, y);
                gameBoard.visitSquare(x, y);  // Mark the square as visited
                logger.info(playerName + " moved");
                return true;
            } else {
                logger.warn(playerName + " attempted an invalid move to an occupied square");
            }
        } else {
            logger.warn(playerName + " attempted an invalid L-shaped move");
        }

        return false;
    }

    /**
     * Switches the current turn from one player to the other.
     */
    public void changeTurns() {
        whitePlayer.setMyTurn(!whitePlayer.isMyTurn());
        blackPlayer.setMyTurn(!blackPlayer.isMyTurn());
        logger.info("Changing turns");
    }

    /**
     * Checks if the game is over for the specified player.
     *
     * @param player  the player to check
     * @return  true if the game is over for the player, false otherwise
     */
    public boolean isGameOver(Player player) {
        // The game is over if the current player cannot make a valid move.
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard.isValidMove(player.getKnight().getX(), player.getKnight().getY(), i, j)
                        && !gameBoard.isOccupied(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Resets the game to its initial state.
     */
    public void reset() {
        // Reset the knights to their initial positions
        whitePlayer.getKnight().reset(0, 0);
        blackPlayer.getKnight().reset(7, 7);

        // Reset the game board
        gameBoard.reset();

        // Reset the players' turn status
        whitePlayer.setMyTurn(true);
        blackPlayer.setMyTurn(false);
    }

    /**
     * Returns the white player.
     *
     * @return  the white player
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * Returns the black player.
     *
     * @return  the black player
     */
    public Player getBlackPlayer() {
        return blackPlayer;
    }

    /**
     * Returns the current player.
     *
     * @return  the current player
     */
    public Player getCurrentPlayer() {
        return whitePlayer.isMyTurn() ? whitePlayer : blackPlayer;
    }

    /**
     * Returns the game board.
     *
     * @return  the game board
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }
}