package Spleef.Controller;

import Spleef.Model.Game;
import Spleef.Model.Knight;
import Spleef.View.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameControllerTest {
    private GameController controller;
    private Game game;
    private GameView gameView;

    @BeforeEach
    public void setUp() {
        Knight whiteKnight = new Knight(0, 0);
        Knight blackKnight = new Knight(7, 7);
        String whitePlayerName = "White";
        String blackPlayerName = "Black";
        game = new Game(whiteKnight, blackKnight, whitePlayerName, blackPlayerName);

        controller = new GameController(game);
    }

    @Test
    public void testConstructor() {
        assertNotNull(controller, "GameController object should not be null");
    }

    @Test
    public void testHandle() {
        //Mockito?
    }
}
