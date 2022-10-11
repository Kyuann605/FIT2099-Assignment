package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.Utils;

import java.util.HashMap;

/**
 * Perform Jump action by player
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Action
 */
public class JumpAction extends Action {
    /**
     * location of actor
     */
    private Location location;
    /**
     * actor jumping success rate
     */
    private final int SUCCESS_RATE;
    /**
     * actor damage received if fail
     */
    private final int DAMAGE_RECEIVED;
    /**
     * direction of actor jumping
     */
    private final String DIRECTION;

    /**
     * Constructor.
     *
     * @param location The location of the actor
     * @param SUCCESS_RATE The success rate to jump onto the wall
     * @param DAMAGE The damage taken by the player if not success
     * @param direction The direction that the player target
     */
    public JumpAction(Location location, int SUCCESS_RATE, int DAMAGE, String direction) {
        this.location = location;
        this.SUCCESS_RATE = SUCCESS_RATE;
        this.DAMAGE_RECEIVED = DAMAGE;
        this.DIRECTION = direction;
    }

    /**
     * Perform Jump action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a message about every Mario's jump attempt
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Get the high ground type and the coordinate of the high ground
        String groundType = location.getGround().getClass().getSimpleName();
        int indexX = location.x();
        int indexY = location.y();

        String result = "";

        // If player doesn't consume Super Mushroom, we need to get the success rate of the high ground and see whether the player can jump up to the high ground or not (using random.nextInt(101)
        if(!actor.hasCapability(Status.TALL)){
            if(Utils.getRandomPercentage()<SUCCESS_RATE){
                // If the player success to jump up to high ground, move the actor the high ground and return a message
                map.moveActor(actor,this.location);
                result = "Mario has successfully jumped onto the high ground:) " + groundType + " Current Location: (" + indexX + "," + indexY + ")";

            }
            // Else, the player gets hurt and return a message
            else {
                actor.hurt(DAMAGE_RECEIVED);
                result = "Aiya! Mario failed and fell down :( Damage Received:" + DAMAGE_RECEIVED ;
            }

        }
        // If the player has consumed Super Mushroom, just move the actor to the high ground and return a message
        else if (actor.hasCapability(Status.TALL)){
            map.moveActor(actor,this.location);
            result =  "Mario has successfully jumped onto the high ground :) "  + groundType + " Current Location: (" + indexX + "," + indexY + ")";

        }
        return result;
    }

    /**
     * Returns a descriptive string that state the coordinates of those high grounds for jump action
     *
     * @param actor The actor performing the action.
     * @return string after actor jumping onto a target
     */
    @Override
    public String menuDescription(Actor actor) {
        String groundType = location.getGround().getClass().getSimpleName();
        int indexX = location.x();
        int indexY = location.y();

        return actor + " jumps to " + this.DIRECTION +" " + groundType + "(" + indexX + "," + indexY + ")";
    }

}


