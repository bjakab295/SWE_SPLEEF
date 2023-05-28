package Spleef.View;

import Spleef.Controller.GameController;
import Spleef.MatchResult;
import Spleef.Model.Game;
import Spleef.Model.GameBoard;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.application.Platform;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The GameView class represents the visual presentation of Spleef.
 * It handles all UI interactions with the game board and knights. This is
 * specific to a GUI implemented using JavaFX.
 */
public class GameView {
    private static final int SIZE = 8;
    private final Rectangle[][] rectangles = new Rectangle[SIZE][SIZE];
    private final Label turnLabel;
    private final Scene scene;
    private final Group root;
    private final Game game;
    private final GameBoard gameBoard;
    private static final Logger logger = LogManager.getLogger(GameView.class);

    // Load the images (adjust the paths if you put the images in a subdirectory)
    private final Image blackKnightImage = new Image("black_knight.png");
    private final Image whiteKnightImage = new Image("white_knight.png");

    // Create ImageView nodes for the Knights
    private final ImageView blackKnightView = new ImageView(blackKnightImage);
    private final ImageView whiteKnightView = new ImageView(whiteKnightImage);

    /**
     * Constructs a GameView with the provided stage, root group, game, game board, and game controller.
     *
     * @param primaryStage    the primary stage of the application
     * @param root            the root group of the scene graph
     * @param game            the game being played
     * @param gameBoard       the game board of the game
     * @param gameController  the game controller handling game logic
     */
    public GameView(Stage primaryStage, Group root, Game game, GameBoard gameBoard, GameController gameController) {
        this.root = root;
        this.game = game;
        this.gameBoard = gameBoard;
        this.scene = new Scene(root, 800, 850);

        // Set the on click event for the scene
        scene.setOnMouseClicked(gameController);

        // Set the size of the knight images
        blackKnightView.setFitWidth(100);
        blackKnightView.setFitHeight(100);
        whiteKnightView.setFitWidth(100);
        whiteKnightView.setFitHeight(100);

        primaryStage.setTitle("Spleef");
        primaryStage.setScene(scene);
        primaryStage.show();

        turnLabel = new Label("White's turn");
        turnLabel.setLayoutX(350);
        turnLabel.setLayoutY(820);
        root.getChildren().add(turnLabel);

        // Initialize the board
        initializeBoard(root);

        // Update the view to reflect the initial positions of the knights
        update(game, gameBoard);
    }

    private void initializeBoard(Group root) {
        Group rectangleGroup = new Group();
        Group knightGroup = new Group();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Rectangle rectangle = new Rectangle(i * 100, j * 100, 100, 100);
                rectangle.setFill((i + j) % 2 == 0 ? Color.LIGHTGRAY : Color.DARKGRAY);
                rectangles[i][j] = rectangle;
                rectangleGroup.getChildren().add(rectangle);
            }
        }

        // Add the groups to the main group
        root.getChildren().addAll(rectangleGroup, knightGroup);

        // Add the knights to the knight group and set their initial positions
        knightGroup.getChildren().add(whiteKnightView);
        knightGroup.getChildren().add(blackKnightView);
    }

    /**
     * Updates the displayed turn information.
     *
     * @param turn  a string representation of whose turn it is
     */
    public void updateTurn(String turn) {
        // Update the turn label
        turnLabel.setText(turn);
    }

    /**
     * Updates the game view, reflecting the current state of the game and game board.
     *
     * @param game       the current game
     * @param gameBoard  the current game board
     */
    public void update(Game game, GameBoard gameBoard) {
        // Update the knights' positions
        whiteKnightView.setLayoutX(game.getWhitePlayer().getKnight().getX() * 100);
        whiteKnightView.setLayoutY(game.getWhitePlayer().getKnight().getY() * 100);
        blackKnightView.setLayoutX(game.getBlackPlayer().getKnight().getX() * 100);
        blackKnightView.setLayoutY(game.getBlackPlayer().getKnight().getY() * 100);

        // Update the visited squares
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (gameBoard.isOccupied(i, j)) {
                    rectangles[i][j].setFill(Color.RED);
                }
            }
        }
    }

    private void resetBoardVisuals() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                rectangles[i][j].setFill((i + j) % 2 == 0 ? Color.LIGHTGRAY : Color.DARKGRAY);
            }
        }
    }

    /**
     * Shows options for end game actions including starting a new game or quitting the game.
     */
    public void showEndGameOptions() {
        // Create buttons
        Button newGameButton = new Button("New Game");
        Button quitButton = new Button("Quit");

        // Set button positions
        newGameButton.setLayoutX(300);
        newGameButton.setLayoutY(500);
        quitButton.setLayoutX(400);
        quitButton.setLayoutY(500);

        // Set button actions
        newGameButton.setOnAction(event -> {
            game.reset();
            gameBoard.reset();
            root.getChildren().remove(newGameButton);
            root.getChildren().remove(quitButton);
            Label endGameLabel = (Label) root.lookup("#endGameLabel"); //CSS ID lookup
            if (endGameLabel != null) {
                root.getChildren().remove(endGameLabel);
            }

            resetBoardVisuals();
            update(game, gameBoard);
        });
        quitButton.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });

        // Add buttons to root
        root.getChildren().addAll(newGameButton, quitButton);
    }

    /**
     * This method is called when the game is over. It displays the game over message
     * along with the name of the winner, and saves the match result.
     *
     * @param winner the name of the player who won the game.
     */
    public void gameOver(String winner) {
        Label endGameLabel = new Label(winner + " wins!");
        endGameLabel.setLayoutX(350);
        endGameLabel.setLayoutY(400);
        endGameLabel.setId("endGameLabel");
        root.getChildren().add(endGameLabel);
        showEndGameOptions();

        MatchResult result = new MatchResult();
        result.setWhitePlayerName(game.getWhitePlayer().getName());
        result.setBlackPlayerName(game.getBlackPlayer().getName());
        result.setWinner(winner);


        try {
            saveMatchResult(result);
        } catch (IOException e) {
            logger.error("An error occurred while saving match results.", e);
        }
    }

    /**
     * This method saves the result of a match in a JSON file. If the file does not
     * exist, it is created. If it exists, the result is appended to the file.
     *
     * @param result the result of the match to save.
     * @throws IOException if an I/O error occurs writing to or creating the file.
     */
    private void saveMatchResult(MatchResult result) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("match_results.json");
        List<MatchResult> matchResults;

        if (!file.exists() || file.length() == 0) {
            matchResults = new ArrayList<>();
        } else {
            matchResults = new ArrayList<>(Arrays.asList(objectMapper.readValue(file,MatchResult[].class)));
        }

        matchResults.add(result);

        objectMapper.writeValue(file, matchResults);
    }


    /**
     * Gets the scene of the game view.
     *
     * @return the scene of the game view
     */
    public Scene getScene() {
        return scene;
    }
}