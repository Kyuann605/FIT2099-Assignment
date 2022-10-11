package game.items;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actors.Player;
import game.enums.Status;

/**
 * A class that represent power star.
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see MagicalItem
 */
public class PowerStar extends MagicalItem {
    /**
     * counter to check turns
     */
    private int counter;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public PowerStar(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);

    }

    /**
     * a zero parameter constructor
     */
    public PowerStar(){
        super("Power Star", '*',true);
        setCounter(10);
    }

    /**
     * setter of counter
     * @param counter number of turns
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * power star buff effect
     * @param actor game's actor
     */
    public void buff(Actor actor) {
        actor.addCapability(Status.INVINCIBLE);
        actor.heal(200);
    }

    /**
     * power star debuff effect
     * @param actor game's actor
     */
    public void debuff(Actor actor) {
        actor.removeCapability(Status.INVINCIBLE);
    }

    /**
     * to count the power star's remaining turn (on map)
     * @param location the location of the power star
     */
    public void tick(Location location){
        counter--;
        if (this.counter==0){
            location.removeItem(this);
            System.out.println("Power Star is removed from map");
        }
        else{
            System.out.println(counter + " more turns until Power Star is removed from map");
        }

    }

    /**
     * to count the power star's remaining turn (in player's inventory)
     * @param location  the location of power star
     * @param actor           The actor carrying this Item.
     */
    public void tick (Location location, Actor actor){
        counter--;
        if (this.counter==0){
            actor.removeItemFromInventory(this);
            System.out.println("Power Star is removed from inventory");
        }
        else{
            System.out.println(counter + " more turns until Power Star is removed from inventory");
        }
    }


}
