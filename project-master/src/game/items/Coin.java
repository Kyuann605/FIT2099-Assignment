package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AddWalletMoneyAction;
import game.enums.Status;
import game.interfaces.Resettable;


/**
 * A class that represent the Coin, implementing Resettable
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Item
 */
public class Coin extends Item implements Resettable{
    /**
     *  an integer value of coin amount value
     */
    private int coinValue;

    /**
     * Constructor
     *
     * @param coinValue the value of coin
     */
    public Coin(int coinValue) {
        super("Coin", '$', true);
        this.coinValue = coinValue;
        Resettable.super.registerInstance();
    }

    /**
     * Get the value of the coin
     *
     * @return the value of coin
     */
    public int getCoinValue() {
        return coinValue;
    }

    // Use getPickUpAction to add a new AddWalletMoneyAction
    @Override
    /**
     * Create and return an action to pick this Item up.
     * If this Item is not portable, returns null.
     *
     * @return a new PickUpItemAction if this Item is portable, null otherwise.
     */
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new AddWalletMoneyAction(this);
    }


    //if the player press the reset button, all coins will be removed
    @Override
    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * if the player press the reset button, all coins will be removed, else the coin will stay on its current location
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation) {
        //if the player press the reset button, all coins will be removed
        if (this.hasCapability(Status.RESET)) {
            currentLocation.removeItem(this);
            this.removeCapability(Status.RESET);
        }
        // else, the coin will stay on its current location
        else {
            super.tick(currentLocation);
        }
    }

    @Override
    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items.
     *
     * @param map the map containing the Actor
     */
    public void resetInstance(GameMap map) {
        this.addCapability(Status.RESET);
    }

}

