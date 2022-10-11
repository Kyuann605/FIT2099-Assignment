package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * A class that represent fountain water.
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Item
 */
public abstract class FountainWater extends Item {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public FountainWater(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * buff the player with the water either Health water or Power water
     *
     * @param actor actor that gets this buff
     */
    public abstract void waterBuff(Actor actor);

}
