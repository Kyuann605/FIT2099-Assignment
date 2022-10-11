package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.WarpPipe;

/**
 * Special Action for teleporting.
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see MoveActorAction
 */
public class TeleportAction extends MoveActorAction {

    /**
     * the current location
     */
    private Location currentLocation;

    /**
     * the warp pipe
     */
    private WarpPipe warpPipe;

    /**
     * a description for menu description
     */
    private String description;

    /**
     * constructor
     * @param moveToLocation the location that we want to teleport to
     * @param direction the direction of the location that we want to teleport to
     * @param currentLocation the player's current location
     * @param warpPipe the warp pipe
     * @param description  a description for menu description
     */
    public TeleportAction(Location moveToLocation, String direction, Location currentLocation, WarpPipe warpPipe, String description) {
        super(moveToLocation, direction);
        this.currentLocation = currentLocation;
        this.warpPipe = warpPipe;
        this.description=description;
    }

    /**
     * Perform Teleport action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a message about every Mario's jump attempt
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //teleport actor
        if(map.isAnActorAt(moveToLocation)) {
            // instantly kill piranha plant at the warp pipe
            map.removeActor(moveToLocation.getActor());
            map.moveActor(actor, moveToLocation);
        }
        else {
            map.moveActor(actor, moveToLocation);
        }

        //set destination warp pipe's location to be
        if(!warpPipe.isSecondMap()) {
            WarpPipe dest = (WarpPipe) moveToLocation.getGround();
            dest.setPreviousWarpPipe(currentLocation);
        }

        return "Teleporting fiuuuuuuu.... Successfully Teleported";
    }

    /**
     * Returns a descriptive string that state teleport direction
     *
     * @param actor The actor performing the action.
     * @return string after actor jumping onto a target
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Teleport" + description;
    }
}
