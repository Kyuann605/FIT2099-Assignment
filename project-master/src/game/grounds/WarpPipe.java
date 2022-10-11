package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.actions.TeleportAction;
import game.actors.PiranhaPlant;
import game.enums.Status;
import game.interfaces.HighGround;
import game.interfaces.Resettable;


/**
 * A class representing the Warp Pipe that implements HighGround
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Ground
 */
public class WarpPipe extends Ground implements HighGround, Resettable {
    /**
     * A constant for success_rate of jumps to higher ground
     */
    private final int SUCCESS_RATE = 90;
    /**
     * A constant for damage when failing to jump to higher ground
     */
    private final int DAMAGE_RECEIVED = 10;
    /**
     * An instance of the ActionList class called allowableActions
     */
    private ActionList allowableActions;
    /**
     * A variable called jumped to check whether the actor has jumped or not
     */
    private boolean jumped;
    /**
     * A instance of JumpAction called lastJumpAction
     */
    private JumpAction lastJumpAction;
    /**
     * A variable called jumped to check whether the actor has jumped or not
     */
    private boolean teleported;
    /**
     * A instance of JumpAction called lastJumpAction
     */
    private TeleportAction lastTeleportAction;

    /**
     * to check if there's a piranha plant there
     */
    private boolean hasPiranha=false;

    /**
     * to check if it's map2 (lava zone)
     */
    private boolean secondMap=false;

    /**
     * the map to portal
     */
    private GameMap mapToPortal;

    /**
     * the location of previous warp pipe
     */
    private Location previousWarpPipe;


    /**
     * setter for second map
     * @param secondMap to check if it's map2 (lava zone)
     */
    public void setSecondMap(boolean secondMap) {
        this.secondMap = secondMap;
    }

    /**
     * to check if it's map2 (lava zone)
     * @return true or false
     */
    public boolean isSecondMap() {
        return secondMap;
    }

    /**
     * setter for mapToPortal
     * @param mapToPortal the map to portal
     */
    public void setMapToPortal(GameMap mapToPortal) {
        this.mapToPortal = mapToPortal;
    }

    /**
     * setter for previousWarpPipe
     * @param previousWarpPipe the location of previous warp pipe
     */
    public void setPreviousWarpPipe(Location previousWarpPipe) {
        this.previousWarpPipe = previousWarpPipe;
    }

    /**
     * Constructor
     * @param map game map
     */
    public WarpPipe(GameMap map) {
        super('C');
        this.mapToPortal=map;
        this.allowableActions=new ActionList();
        Resettable.super.registerInstance();
    }


    @Override
    /**
     * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
     * the actor cannot walk onto warp pipe
     *
     * @param actor the Actor to check
     * @return true
     */
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Called once per turn, so that Locations can experience the passage time. If that's
     * important to them. It will spawn a piranha plant once
     *
     * @param location of the actor
     */
    @Override
    public void tick(Location location) {

        if (!location.containsAnActor() && !hasPiranha) {
            //spawn piranha on top of it
            location.addActor(new PiranhaPlant());
            hasPiranha=true;
        }

        // if the Piranha has spawn, hasPiranha=true
        hasPiranha=true;

        // if the gam is reset, hasPiranha=false
        if(this.hasCapability(Status.RESET)) {
            hasPiranha = false;
            this.removeCapability(Status.RESET);
        }
        super.tick(location);
    }

    @Override
    /**
     * Returns an Action list that contains each kind of actions.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        // if the player has teleported to the another map, remove teleport action in the allowableAction list
        if(this.teleported ==true) {
            this.allowableActions.remove(lastTeleportAction);
        }

        if(location.getActor() == actor) {

            // teleport action from map1 to map2 (lava zone)
            if(previousWarpPipe==null) {
                TeleportAction tp = new TeleportAction(mapToPortal.at(0, 0), " to " + mapToPortal, location, this, " to Lavazone");
                this.allowableActions.add(tp);
                lastTeleportAction = tp;
                this.teleported = true;
            }

            // teleport action from map2 back to map1
            else {
                TeleportAction tp = new TeleportAction(previousWarpPipe, " to " + previousWarpPipe, location, this, " back to previous pipe");
                this.allowableActions.add(tp);
                lastTeleportAction = tp;
                this.teleported = true;
            }
        }
        // if the player has jumped onto the current high ground, remove jump action in the allowableAction list
        if(this.jumped ==true) {
            this.allowableActions.remove(lastJumpAction);
        }

        // if the player wants to jump onto high ground and he doesn't consume Power Star, we will add a jump action into allowable action list to let him jump
        if (actor != location.getActor() && actor.hasCapability(Status.NEED_TO_JUMP) && !actor.hasCapability(Status.INVINCIBLE)) {
            JumpAction j= JumpUpAction(location, SUCCESS_RATE, DAMAGE_RECEIVED, direction);
            this.allowableActions.add(j);
            lastJumpAction =j;
            this.jumped =true;
        }

        return allowableActions;
    }

    // player needs to jump onto wall if he wants to go there, so we need to implement JumpUpAction
    @Override
    /**
     * Constructor.
     *
     * @param location The location of the actor
     * @param SUCCESS_RATE The success rate to jump onto the wall
     * @param DAMAGE The damage taken by the player if not success
     * @param direction The direction that the player target
     */
    public JumpAction JumpUpAction(Location location, int success_rate, int damage_received, String direction) {
        return new JumpAction(location, success_rate, damage_received, direction);
    }


    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items. It will spawn one piranhaplant on it
     * if player presses the reset button and there is no piranha plant there
     *
     * @param map the map containing the Actor
     */
    @Override
    public void resetInstance(GameMap map) {
        this.addCapability(Status.RESET);
    }
}
