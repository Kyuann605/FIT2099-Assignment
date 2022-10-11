package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.FireFlower;
import game.items.PowerStar;
import game.items.SuperMushroom;

/**
 * Special Action for consuming magical items.
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Action
 */
public class ConsumeAction extends Action {
    /**
     * an item
     */
    private Item item;

    /**
     * Constructor.
     *
     * @param item an item
     */
    public ConsumeAction(Item item){
        this.item = item;
    }

    /**
     * Perform Consume action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a message about every Mario's consume attempt
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String message = "No magical item to be consumed";

        // it will buff the player if the player consumes and it will be from player's inventory
        if (this.item instanceof SuperMushroom superMushroom) {
            superMushroom.buff(actor);
            actor.removeItemFromInventory(item);
            message = "Mario consumed Super Mushroom";
        }
        // it will buff the player if the player consumes and it will be from player's inventory
        if (this.item instanceof PowerStar powerStar) {
            powerStar.buff(actor);
            actor.removeItemFromInventory(item);
            message = "Mario is INVINCIBLE! 10 more turns remaining";

        }
        // it will buff the player if the player consumes and it will be from player's inventory
        if (this.item instanceof FireFlower fireFlower) {
            fireFlower.buff(actor);
            actor.removeItemFromInventory(item);
            message = "SUPER MARIO (FIRE MODE)! 20 more turns remaining";

        }

        return message;
    }

    /**
     * Returns a descriptive string that state the coordinates of enemy (for consume action)
     *
     * @param actor The actor performing the action.
     * @return string of a consume action for consume the magical item
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + this.item.toString();
    }
}
