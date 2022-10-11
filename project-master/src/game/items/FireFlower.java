package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

/**
 * A class that represent fire flower.
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see MagicalItem
 */
public class FireFlower extends MagicalItem{
    /**
     * counter to check turns
     */
    private int counter;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public FireFlower(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);

    }

    /**
     * a zero parameter constructor
     */
    public FireFlower(){
        super("Fire Flower", 'f',true);
        setCounter(20);
    }

    /**
     * setter of counter
     * @param counter number of turns
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * fire flower buff effect
     * @param actor game's actor
     */
    public void buff(Actor actor) {
        actor.addCapability(Status.FIRE_BUFF);
    }

    /**
     * fire flower debuff effect
     * @param actor game's actor
     */
    public void debuff(Actor actor) {
        actor.removeCapability(Status.FIRE_BUFF);
    }


}
