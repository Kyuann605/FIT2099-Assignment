package game.weapons;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;
import game.items.MagicalItem;

/**
 * A class that represent wrench.
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see WeaponItem
 */
public class Wrench extends WeaponItem {
    /**
     * Constructor
     * @param name the name of Wrench
     * @param displayChar the display character of Wrench
     * @param damage the damage of Wrench
     * @param verb the verb of Wrench's attack
     * @param hitRate the hit rate of wrench
     */

    public Wrench(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb,hitRate);
        this.addCapability(Status.WRENCH);
    }
    public Wrench(){
        super("Wrench", 'W', 50, "hit", 80);
        this.addCapability(Status.WRENCH);
    }

}
