package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actions.JumpAction;
import game.enums.Status;
import game.interfaces.HighGround;
import game.interfaces.Resettable;
import game.items.Coin;

/**
 * An abstract class representing the Tree that implements Resettable and HighGround
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Ground
 */
public abstract class Tree extends Ground implements Resettable, HighGround {
    /**
     * Protected Attributes
     */

    /**
     * A constant for success_rate of jumps to higher ground
     */
    protected final int SUCCESS_RATE;
    /**
     * A constant for damage when failing to jump to higher ground
     */
    protected final int DAMAGE_RECEIVED;
    /**
     * A variable called jumped to check whether the actor has jumped or not
     */
    protected boolean jumped;
    /**
     * A instance of JumpAction called lastJumpAction
     */
    protected JumpAction lastJumpAction;
    /**
     * A variable called counter which keeps track of turns being played
     */
    protected int counter;
    /**
     * An instance of the ActionList class called allowableActions
     */
    protected ActionList allowableActions;

    /**
     * Constructor.
     *
     * @param displayChar  character to display for this type of terrain
     * @param success_rate the success rate for completion of jump onto higher ground
     * @param damage_received the damage if player fails to jump onto higher ground
     */
    public Tree(char displayChar, int success_rate, int damage_received) {
        super(displayChar);
        this.DAMAGE_RECEIVED = damage_received;
        this.SUCCESS_RATE = success_rate;
        this.counter = 0;
        Resettable.super.registerInstance();
    }

    @Override
    /**
     * Called once per turn, so that Locations can experience the passage time. If that's
     * important to them. if player press restart button, every tree has 50% chances to become dirt
     *
     * @param location of the actor
     */
    public void tick(Location location) {
        if (this.hasCapability(Status.RESET)){
            Dirt dirt = new Dirt();
            location.setGround(dirt);
        }


        }




    // the actor cannot walk onto tree
    @Override
    /**
     * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
     *
     * @param actor the Actor to check
     * @return true
     */
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(Status.INVINCIBLE)){
            return true;
        }
        return false;
    }

    //
    @Override
    /**
     * Returns an Action list. Returns an Action list that contains each kind of actions.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        allowableActions = new ActionList();
        // if the player has jumped onto the current high ground, remove jump action in the allowableAction list
        if(this.jumped == true) {
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


    @Override
    /**
     * player needs to jump onto Sapling if he wants to go there, so we need to implement JumpUpAction
     *
     * @param location The location of the actor
     * @param SUCCESS_RATE The success rate to jump onto the wall
     * @param DAMAGE The damage taken by the player if not success
     * @param direction The direction that the player target
     */
    public JumpAction JumpUpAction(Location location, int success_rate, int damage_received, String direction) {
        return new JumpAction(location, success_rate, damage_received, direction);
    }

    @Override
    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items. Clear 50% of tree in
     * the gameMap if player presses the reset button.
     *
     * @param map the map containing the Actor
     */
    public void resetInstance(GameMap map) {
        if (Utils.getRandomPercentage() < 50){
            this.addCapability(Status.RESET);
        }
        else {
            this.addCapability(Status.STOP_SPAWN);
        }
    }

}

