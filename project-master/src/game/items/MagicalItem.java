package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * A class that reprsent magical item.
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Item
 */
public abstract class MagicalItem extends Item {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public MagicalItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * the buff effect of magical item
     * @param actor game's actor
     */
    public abstract void buff(Actor actor);

    /**
     * the debuff effect of magical item
     * @param actor game's actor
     */
    public abstract void debuff(Actor actor);
}
