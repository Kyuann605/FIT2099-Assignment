package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actions.JumpAction;
import game.actors.Goomba;
import game.enums.Status;
import game.items.Coin;
import game.items.FireFlower;

/**
 * A class representing the Sprout
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Ground
 */
public class Sprout extends Tree {

    /**
     * Constructor for the Sprout Class which overrides the Tree's constructor using super
     * and sets the final success rate,damage and name for this particular stage
     */
    public Sprout() {
        super('+', 90, 10);
    }


    /**
     * Called once per turn, so that Locations can experience the passage time. If that's
     * important to them. It will have 10% chances spawn goomba and will grow to Sapling in every 10 turns.
     *
     * @param location of the actor
     */
    public void tick(Location location){
        // It has a 10% chance to spawn Goomba on its position in every turn. If any actor stands on it, it cannot spawn Goomba.
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
            if (Utils.getRandomPercentage() < 10 && (!location.containsAnActor())) {
                Goomba g = new Goomba();
                location.addActor(g);
            }


            // Sapling takes another 10 turns to grow into a Sapling
            if (counter == 10) {
                Sapling s = new Sapling();
                location.setGround(s);
                // spawn fire flower
                if(Utils.getRandomPercentage() < 50){
                    FireFlower f = new FireFlower();
                    location.addItem(f);
                }
            }
        }
    }




}

