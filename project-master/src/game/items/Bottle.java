package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import game.enums.Status;
import game.grounds.HealthFountain;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represent bottle.
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Item
 */
public class Bottle extends Item {

    /**
     * an array list of bottle that can fill fountain water
     */
    private ArrayList <FountainWater> bottle = new ArrayList<>();

    /***
     * Constructor.
     *
     */
    public Bottle(){
        super("Bottle", 'b', false);
        this.addCapability(Status.BOTTLE);
    }

    /**
     * Get the bottle that player currently holding
     *
     * @return bottle
     */
    public ArrayList<FountainWater> getBottle() {
        return bottle;
    }

    /**
     * add the fountain(Health or Power) to the bottle
     *
     * @param water
     */
    public void addWater(FountainWater water) {
        this.bottle.add(water);
    }

    /**
     * Bottle cannot be dropped by the actor
     *
     * @param actor
     * @return null
     */
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }


}
