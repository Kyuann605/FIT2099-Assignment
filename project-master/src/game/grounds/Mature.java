package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actions.JumpAction;
import game.actors.FlyingKoopa;
import game.actors.Koopa;
import game.enums.Status;
import game.items.Coin;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing mature tree
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Ground
 */
public class Mature extends Tree {

    /**
     * Constructor for the Mature stage
     * Overrides the constructor for tree to change the display char for Mature and sets the appropriate success rate,
     *damage and name for this particular Tree stage
     */
    public Mature() {
        super('T', 70, 30);
    }


    /**
     * Called once per turn, so that Locations can experience the passage time. If that's
     * important to them. It will has 15% chances spawn koopa and will has 20% chances die and become to dirt. In every 5 turns,
     * it will grows a sprout next to it.
     *
     * @param location the location of the actor
     */
    public void tick(Location location){
        // In every turn, if the player is not standing on the Mature, it will have 15% to spawn a koopa
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

            if (Utils.getRandomPercentage() < 15 && !location.containsAnActor()) {
                if (Utils.getRandomPercentage() < 50) {
                    Koopa k = new Koopa();
                    location.addActor(k);
                }
                else{
                    FlyingKoopa f = new FlyingKoopa();
                    location.addActor(f);
                }
            }

            //In every turn, Mature has 20% to die and becomes to Dirt
            if (Utils.getRandomPercentage() < 20) {
                Dirt d = new Dirt();
                location.setGround(d);
            }

            //In every five turns, Mature can grow a new sprout in one of the surrounding dirt, randomly. If there is no available dirt, it will stop growing sprouts.
            if (counter % 5 == 0) {
                // make an empty list of all surrounding exits that have dirt in the game map
                List<Exit> matureExits = new ArrayList<Exit>();
                for (Exit exit : location.getExits()){
                    if (exit.getName() == "North" || exit.getName() == "South" || exit.getName() == "East" || exit.getName() == "North-West" || exit.getName() == "South-West" || exit.getName() == "North-East" || exit.getName() == "South-East" || exit.getName() == "West") {
                        if (exit.getDestination().getGround().getDisplayChar() == '.') {
                            matureExits.add(exit);
                        }
                    }
                }

                ArrayList<Integer> indexes = new ArrayList();
                for (int i=0;i < matureExits.size();i++){
                    indexes.add(i);
                }
                if ((matureExits.size() > 0 && indexes.size() > 0)) {
                    matureExits.get(Utils.getRandomIndex(indexes)).getDestination().setGround(new Sprout());
                }

            }
        }

    }

}
