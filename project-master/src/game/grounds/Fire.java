package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;


/**
 * A class that represents fire.
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Ground
 */
public class Fire extends Ground {
    /**
     * count the number of turns that the fire can last
     */
    private int counter;

    /**
     * ground type
     */
    private Ground ground;

    /**
     * Constructor.
     * @param ground ground type
     */
    public Fire(Ground ground) {
        super('v');
        this.ground = ground;
    }

    /**
     * Called once per turn. set that location into fire and if there is actor on it, it will get hurt by 20
     * the fire last 3 rounds
     *
     * @param location of the actor
     */
    @Override
    public void tick(Location location) {
        if (this.counter<3) {
            if (location.containsAnActor()) {
                Actor actor = location.getActor();
                actor.hurt(20);
                System.out.println(actor + " is burned by fire, decrease 20 hit points");
            }
        }
        else {
            this.removeCapability(Status.FIRE);
            location.setGround(ground);
            System.out.println("Fire gone");
        }
        counter++;
    }

}
