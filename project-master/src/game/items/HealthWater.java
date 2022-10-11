package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actors.Player;

/**
 * A class that represent health water.
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see FountainWater
 */
public class HealthWater extends FountainWater {
    /***
     * Constructor.
     */
    public HealthWater() {
        super("Health Water", '[', false);
    }

    /**
     * Heal the player with 50 hp
     *
     * @param actor actor that gets this buff
     */
    @Override
    public void waterBuff(Actor actor)
    {
        Player player = (Player) actor;
        player.heal(50);
    }


}
