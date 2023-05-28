package Spleef;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import Spleef.Model.Game;
import Spleef.Model.Knight;
import Spleef.View.GameView;
import Spleef.Controller.GameController;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Main class is the entry point of Spleef. It extends the JavaFX Application class.
 * This class is responsible for setting up the game,
 * initializing the model, view, and controller, and launching the game UI.
 */
public class Main extends Application {
    private static final Logger logger = LogManager.getLogger(Main.class);
    /**
     * The start method is the main entry point for all JavaFX applications.
     * It sets up the players, knights, game, controller, and view, and displays the initial game UI.
     *
     * @param primaryStage  the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        // Ask white player for their name
        TextInputDialog whiteDialog = new TextInputDialog("White Player");
        whiteDialog.setTitle("White Player");
        whiteDialog.setHeaderText("Enter your name:");
        Optional<String> whiteResult = whiteDialog.showAndWait();
        String whitePlayerName = whiteResult.orElse("White Player");

        // Ask black player for their name
        TextInputDialog blackDialog = new TextInputDialog("Black Player");
        blackDialog.setTitle("Black Player");
        blackDialog.setHeaderText("Enter your name:");
        Optional<String> blackResult = blackDialog.showAndWait();
        String blackPlayerName = blackResult.orElse("Black Player");


        // Create the knights
        Knight whiteKnight = new Knight(0, 0);
        Knight blackKnight = new Knight(7, 7);

        // Create the game model
        Game game = new Game(whiteKnight, blackKnight, whitePlayerName, blackPlayerName);

        // Create the game controller (we'll bind it to the view later)
        GameController gameController = new GameController(game);

        // Create root of the scene graph
        Group root = new Group();

        // Create the game view
        GameView gameView = new GameView(primaryStage, root, game, game.getGameBoard(), gameController);
        gameController.setGameView(gameView);  // Bind the view to the controller

        // Set the scene and show the stage
        primaryStage.setScene(gameView.getScene());
        logger.info("A new game has started between {} (White) and {} (Black)", whitePlayerName, blackPlayerName);
        primaryStage.show();
    }

    /**
     * This comment is only here to avoid a warning.
     *
     * @param args args
     */
    public static void main(String[] args) {
        launch(args);
    }
}