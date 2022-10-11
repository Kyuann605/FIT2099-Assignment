package game.interfaces;

import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;

/**
 * A interface for high ground.
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 */
public interface HighGround {


    /**
     * a function for jumpAction with the location, success rate, damage received and direction for each high grounds
     *
     * @param location the current Location
     * @param success_rate the success rate for completion of jump onto higher ground
     * @param damage_received the damage if player fails to jump onto higher ground
     * @param direction the direction of the Ground from the Actor
     */
    JumpAction JumpUpAction(Location location, int success_rate, int damage_received, String direction);

}

