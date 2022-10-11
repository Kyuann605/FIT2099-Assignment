package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.items.Coin;
import game.items.Wallet;


/**
 * Add money (coin) and put it into wallet system
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see PickUpItemAction
 */
public class AddWalletMoneyAction extends PickUpItemAction {

    /**
     * Coin
     */
    Coin coin;

    /**
     * Constructor.
     *
     * @param item the item to pick up
     */
    public AddWalletMoneyAction(Item item) {
        // Since item is a private attribute in PickUpItemAction, it isn't inherited, thus we'll have to create a new attribute for Coin.
        super(item);
        this.coin = (Coin) item;
    }

    /**
     * Perform the action to add coin into wallet
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Actor gets coins into wallet
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Get the location of the player and remove the coins on the location, add the coin to player's wallet
        map.locationOf(actor).removeItem(coin);
        Wallet w = ((Player) actor).getWallet();
        w.addBalance(coin.getCoinValue());
        return actor + " adds the " + coin + " ($20) to its wallet.";
    }
}
