package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;
import game.enums.Status;

/**
 * Reset the actors and grounds back to initial state
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Action
 */
public class ResetAction extends Action {

    /**
     * Action that resets of the game.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return "You have reset the game"
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run(map);
        actor.removeCapability(Status.RESET_BUTTON);

        return "You have reset the game.";
    }

    /**
     * Returns a descriptive string that states that you can reset the game
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game.";
    }

    /**
     * Returns the key used in the menu to trigger this reset Action.
     *
     * @return The key we use for this Action in the menu, or null to have it assigned for you.
     */
    @Override
    public String hotkey() {
        return "r";
    }
}