package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.grounds.HealthFountain;
import game.items.Bottle;
import game.items.FountainWater;


import java.util.ArrayList;

/**
 * Special Action for consuming fountain water.
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Action
 */
public class ConsumeBottleAction extends Action {
    /**
     * The actor performing the action.
     */
    private Actor actor;
    /**
     * an arraylist of fountain water
     */
    private ArrayList<FountainWater> bottleArr;

    /**
     * Constructor
     * @param actor The actor performing the action.
     * @param fountainWater an arraylist of fountain water
     */
    public ConsumeBottleAction(Actor actor, ArrayList<FountainWater>fountainWater) {
        this.actor = actor;
        this.bottleArr = fountainWater;
    }

    /**
     * Perform Consume Bottle action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a message about every Mario's consume the bottle
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String message = "";
        if (bottleArr.size() == 0){
            message = "No water to be consumed";
        }
        else
        {
            message = bottleArr.get(bottleArr.size()-1) + " has been drunk";
            bottleArr.get(bottleArr.size()-1).waterBuff(actor);
            bottleArr.remove(bottleArr.size() - 1);
        }
        return message;
    }

    /**
     * Returns a descriptive string that state the charges of the bottle
     *
     * @param actor The actor performing the action.
     * @return string of a consume action for consume the list of the health water or power water
     */
    @Override
    public String menuDescription(Actor actor) {
        ArrayList<FountainWater> alphabets = new ArrayList<>();
        for (int i=0; i<bottleArr.size();i++){
            alphabets.add(bottleArr.get(i));
        }
        return actor + " consumes Bottle " + alphabets;
    }
}
