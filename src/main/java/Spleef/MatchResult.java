package Spleef;

/**
 * This class represents the result of a match in the game.
 * It contains the names of the white and black players, and the winner's name.
 */
public class MatchResult {
    private String whitePlayerName;
    private String blackPlayerName;
    private String winner;

    /**
     * No-arg constructor for creating a MatchResult object without initial values.
     * The setters must be used to set the values after creating the object.
     */
    public MatchResult() {}

    /**
     * Constructor for creating a MatchResult object with initial values.
     *
     * @param whitePlayerName  The name of the white player
     * @param blackPlayerName  The name of the black player
     * @param winner           The name of the player who won the match
     */
    public MatchResult(String whitePlayerName, String blackPlayerName, String winner) {
        this.whitePlayerName = whitePlayerName;
        this.blackPlayerName = blackPlayerName;
        this.winner = winner;
    }

    /**
     * Returns the name of the player playing white.
     *
     * @return the name of the white player.
     */
    public String getWhitePlayerName() {
        return whitePlayerName;
    }

    /**
     * Sets the name of the player playing white.
     *
     * @param whitePlayerName the name of the white player.
     */
    public void setWhitePlayerName(String whitePlayerName) {
        this.whitePlayerName = whitePlayerName;
    }

    /**
     * Returns the name of the player playing black.
     *
     * @return the name of the black player.
     */
    public String getBlackPlayerName() {
        return blackPlayerName;
    }

    /**
     * Sets the name of the player playing black.
     *
     * @param blackPlayerName the name of the black player.
     */
    public void setBlackPlayerName(String blackPlayerName) {
        this.blackPlayerName = blackPlayerName;
    }

    /**
     * Returns the name of the player who won the match.
     *
     * @return the name of the winner.
     */
    public String getWinner() {
        return winner;
    }

    /**
     * Sets the name of the player who won the match.
     *
     * @param winner the name of the winner.
     */
    public void setWinner(String winner) {
        this.winner = winner;
    }

}
