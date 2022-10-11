package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;


/**
 * A class that represents lava.
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Ground
 */
public class Lava extends Ground {
    /**
     * Constructor.
     */
    public Lava() {
        super('L');
    }

    /**
     * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
     *
     * @param actor the Actor to check
     * @return true
     */
    @Override
    public boolean canActorEnter(Actor actor) {

        if (actor.hasCapability(Status.FLOOR_LAVA)) {
            return true;
        }
        return false;
    }

    /**
     * Called once per turn, so that Locations can experience the passage time. If that's
     * important to them.if actor has capability FLOOR_LAVA, he can enter but it will reduce 15hp every turn if he stands on lava
     *
     * @param location of the actor
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()){
            Actor player = location.getActor();
            player.hurt(15);
        }
    }
}
