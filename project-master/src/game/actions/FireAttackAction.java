package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;

/**
 * Fire Action for attacking other Actors.
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Action
 */
public class FireAttackAction extends AttackAction {
    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction
     */
    public FireAttackAction(Actor target, String direction) {
        super(target, direction);
    }

    /**
     * Returns a descriptive string that state the direction of the enemy to attack with fire
     *
     * @param actor The actor performing the action.
     * @return string after actor jumping onto a target
     */
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with fire!";
    }

}
