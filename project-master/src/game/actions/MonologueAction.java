package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Utils;
import game.actors.Toad;
import game.enums.Status;
import game.weapons.Wrench;

import java.util.ArrayList;
import java.util.List;

/**
 * Output monologue by Toad
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Action
 */
public class MonologueAction extends Action{

    /**
     * Perform the outputting monologue action with conditions
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string of monologue
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // check if player has a wrench
        boolean Wrench = false;
        List<Item> inventory = actor.getInventory();
        for (Item item : inventory) {
            if (item.getDisplayChar() == 'W') {
                Wrench = true;
                break;
            }
        }

        // check if player has consumed Power Star
        boolean PowerStarEffect = actor.hasCapability(Status.INVINCIBLE);
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        String sentence;

        // return one of the sentences if player has a wrench and has consumed PowerStar
        if (Wrench && PowerStarEffect) {
            arrayList.add(2);
            arrayList.add(3);
        }
        // return one of the sentences if player has a wrench and hasn't consumed PowerStar
        else if (Wrench && !PowerStarEffect) {
            arrayList.add(1);
            arrayList.add(2);
            arrayList.add(3);
        }

        // return one of the sentences if player doesn't have a wrench and has consumed PowerStar
        else if (!Wrench && PowerStarEffect) {
            arrayList.add(0);
            arrayList.add(2);
            arrayList.add(3);
        }

        //return any sentence
        else {
            arrayList.add(0);
            arrayList.add(1);
            arrayList.add(2);
            arrayList.add(3);
        }

        // get one of the index number by using getRandomIndex and get the sentence from getSentence
        int index = Utils.getRandomIndex(arrayList);
        sentence = Toad.getSentence(index);
        return sentence;
    }

    /**
     * Returns a string where player can speak to the toad
     *
     * @param actor The actor performing the action.
     * @return actor + "speaks with Toad"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " speaks with Toad";
    }


}
