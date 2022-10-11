package game.items;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.actions.AddWalletMoneyAction;
import game.actors.Player;
import game.enums.Status;

/**
 * A class that represent super mushroom.
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see MagicalItem
 */
public class SuperMushroom extends MagicalItem {

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public SuperMushroom(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    public SuperMushroom() {
        super("Super Mushroom", '^', true);
    }


    /**
     * super mushroom's buff effect
     *
     * @param actor game's actor
     */
    public void buff(Actor actor) {
        actor.addCapability(Status.TALL);
        actor.increaseMaxHp(50);
        actor.getDisplayChar();

    }

    /**
     * super mushroom's debuff effect
     *
     * @param actor game's actor
     */
    public void debuff(Actor actor) {
        actor.removeCapability(Status.TALL);
    }

}

