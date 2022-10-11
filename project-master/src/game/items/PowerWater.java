package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Player;
import game.enums.Status;

/**
 * A class that represent power water.
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see FountainWater
 */
public class PowerWater extends FountainWater {
    /***
     * Constructor.
     */
    public PowerWater() {
        super("Power Water", ']', false);
    }

    /**
     * Power water that set the counter for player to keep track how much power water has been drank
     *
     * @param actor actor that gets this buff
     */
    @Override
    public void waterBuff(Actor actor) {
        Player player = (Player) actor;
        player.setBuffCounter(1);
        player.addCapability(Status.POWERWATER);
    }
}
