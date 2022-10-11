
package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actions.JumpAction;
import game.enums.Status;
import game.items.Coin;
import game.items.FireFlower;

/**
 * A class representing Sapling
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Ground
 */
public class Sapling extends Tree{


    /**
     * Constructor for Sapling stage
     * Overrides the constructor for tree to change the display char for Sapling and sets the appropriate success rate,
     * damage and name for this particular Tree stage
     */
    public Sapling() {
        super('t', 80, 20);
    }


    /**
     * Called once per turn, so that Locations can experience the passage time. If that's
     * important to them. It will have 10% chances spawn coins and will grow to Mature in every 10 turns.
     *
     * @param location location of the actor
     */
    public void tick(Location location){
        // In every turn, It has a 10% chance to produce/spawn a coin ($20) on its location at every turn.
        counter++;
        if (this.hasCapability(Status.RESET)){
            Dirt dirt = new Dirt();
            location.setGround(dirt);
            this.removeCapability(Status.RESET);

        }
        if (location.containsAnActor()) {
            if (location.getActor().hasCapability(Status.INVINCIBLE)) {
                Dirt dirt = new Dirt();
                location.setGround(dirt);
                Coin c = new Coin(5);
                location.addItem(c);
            }
        }
        // let the trees stop spawning one turn when we reset the game
        else if (this.hasCapability(Status.STOP_SPAWN)){
            this.removeCapability(Status.STOP_SPAWN);
        }
        else{
            if (Utils.getRandomPercentage() < 10) {
                Coin c = new Coin(20);
                location.addItem(c);
            }


            // Sapling takes another 10 turns to grow into a Mature
            if (counter == 10) {
                Mature m = new Mature();
                location.setGround(m);
                // spawn fire flower
                if(Utils.getRandomPercentage() < 50){
                    FireFlower f = new FireFlower();
                    location.addItem(f);
                }
            }
        }

    }




}
