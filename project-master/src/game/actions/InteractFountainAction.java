package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.items.Bottle;
import game.items.FountainWater;

/**
 * Special Action for fill the fountain water.
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Action
 */
public class InteractFountainAction extends Action {
    /**
     * Bottle
     */
    private Bottle bottle;
    /**
     * The actor performing the action.
     */
    private Actor actor;
    /**
     * Fountain water
     */
    private FountainWater fountainWater;

    /**
     * Constructor
     * @param actor The actor performing the action.
     * @param fountainWater an arraylist of fountain water
     */
    public InteractFountainAction(Actor actor, FountainWater fountainWater) {
        this.fountainWater = fountainWater;
        this.actor = actor;
    }

    /**
     * Perform the action to interact between Mario and the fountains.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a message about every Mario's interaction with the fountains
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (int i = 0; i < actor.getInventory().size(); i++) {
            if (actor.getInventory().get(i).hasCapability(Status.BOTTLE)) {
                bottle = (Bottle) actor.getInventory().get(i);
                bottle.addWater(fountainWater);
            }
        }
        return fountainWater + " filled";
    }

    /**
     * Returns a descriptive string that state when Mario refills his bottle
     *
     * @param actor The actor performing the action.
     * @return string of a refill action for health water or power water
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills " + this.fountainWater;
    }
}
