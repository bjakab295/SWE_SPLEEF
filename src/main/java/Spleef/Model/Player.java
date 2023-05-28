package Spleef.Model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Player class represents a player in Spleef.
 * It maintains the player's name, knight piece, and the current turn status.
 */
public class Player {
    private final Knight knight;
    private boolean myTurn;
    private final String name;
    private static final Logger logger = LogManager.getLogger(Player.class);

    /**
     * Constructs a Player instance with the given knight, turn status and name.
     *
     * @param knight  the knight associated with the player
     * @param myTurn  the current turn status of the player
     * @param name    the name of the player
     */
    public Player(Knight knight, boolean myTurn, String name) {
        this.knight = knight;
        this.myTurn = myTurn;
        this.name = name;
    }

    /**
     * Checks if it is the current player's turn.
     *
     * @return  the turn status of the player
     */
    public boolean isMyTurn() {
        return myTurn;
    }

    /**
     * Sets the turn status for the player.
     *
     * @param myTurn  the turn status to set for the player
     */
    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    /**
     * Returns the knight associated with the player.
     *
     * @return  the knight associated with the player
     */
    public Knight getKnight() {
        return knight;
    }

    /**
     * Returns the name of the player.
     *
     * @return  the name of the player
     */
    public String getName() {
        return this.name;
    }
}
