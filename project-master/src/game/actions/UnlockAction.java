package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.items.Key;

/**
 * Special Action for unlocking actor.
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Action
 */
public class UnlockAction extends Action {
    /**
     * The actor performing the action.
     */
    private Actor actor;
    /**
     * the target to unlock
     */
    private Actor target;

    /**
     * Constructor
     * @param actor The actor performing the action.
     * @param target the target to unlock
     */
    public UnlockAction(Actor actor, Actor target){
        this.actor = actor;
        this.target = target;
    }

    /**
     * Perform the action to unlock the Princess Peach with a key
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a message about the status of the Princess Peach
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String message = target + " is locked, you need to get the key to save her.";
        for (int i=0; i<this.actor.getInventory().size();i++) {
            Item item = this.actor.getInventory().get(i);
            if(item instanceof Key) {
                this.actor.removeItemFromInventory(item);
                target.removeCapability(Status.LOCKED);
                message = target + " is saved!!";
            }
        }
        return message;
    }

    /**
     * Returns a descriptive string that state when Mario unlocks Princess Peach
     *
     * @param actor The actor performing the action.
     * @return string when Mario unlocks Princess Peach
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor +" unlocks " + target;
    }
}
