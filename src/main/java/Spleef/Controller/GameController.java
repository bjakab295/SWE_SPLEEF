package Spleef.Controller;

import Spleef.Model.Game;
import Spleef.Model.Player;
import Spleef.View.GameView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * GameController is a class responsible for handling the game logic of Spleef.
 * It reacts to the user interactions by processing mouse events, updating the state of the game,
 * and triggering changes in the visual presentation of the game.
 */
public class GameController implements EventHandler<MouseEvent> {
    private final Game game;
    private GameView gameView;
    private static final Logger logger = LogManager.getLogger(GameController.class);

    /**
     * Constructs a GameController instance with the specified game.
     *
     * @param game  the game this GameController will manage
     */
    public GameController(Game game) {
        this.game = game;
    }

    /**
     * Sets the GameView instance associated with this GameController.
     *
     * @param gameView  the GameView instance to be associated with this GameController
     */
    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Handles mouse click events on the game board. It calculates the clicked square's coordinates,
     * attempts to move the current player's knight, updates the game view, and checks if the game
     * has ended.
     *
     * @param event  the MouseEvent to handle
     */
    @Override
    public void handle(MouseEvent event) {
        // Calculate the clicked square's coordinates
        int x = (int) event.getX() / 100;
        int y = (int) event.getY() / 100;

        Player currentPlayer = game.getCurrentPlayer();

        // Attempt to move the knight whose turn it is
        if (currentPlayer.isMyTurn()) {
            if (game.attemptMove(currentPlayer, x, y)) {
                gameView.update(game, game.getGameBoard());
                game.changeTurns();
                gameView.updateTurn(currentPlayer.getName() + "'s turn"); // Update the turn label

                // Check if the game is over
                if (game.isGameOver(currentPlayer)) {
                    gameView.gameOver(currentPlayer.getName());
                    logger.info("The game has ended. Winner: {}", currentPlayer.getName());
                }
            }
        }
    }
}