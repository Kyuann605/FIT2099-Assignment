package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
//import game.interfaces.Tradeable;


/**
 * A class representing the wallet of the player
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Item
 */
public class Wallet extends Item {

    /**
     * the total balance of player's wallet
     */
    private int totalBalance;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up, set to false for Wallet for player class
     */
    public Wallet(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /***
     * Constructor.
     */
    public Wallet(){
        super("Wallet", '@', false);
    }

    /**
     * getter
     *
     * @return total balance in the whole game
     */
    public int getTotalBalance() {
        return totalBalance;
    }

    /**
     * setter
     *
     * @param totalBalance add whatever new coin value to the totalBalance
     */
    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }

    /**
     * add money to the total
     *
     * @param amount add money to balance
     */
    // a function to add the balance of the player's wallet
    public void addBalance(int amount) {
        this.setTotalBalance(this.getTotalBalance() + amount);
    }

    /**
     * reduce money from balance
     *
     * @param amount minus money from the total
     * @return boolean true if positive money else false
     */
    // a function to deduct the balance of the player's wallet
    public boolean deductBalance(int amount) {
        int balance = this.getTotalBalance() - amount;
        if (balance >=0) {
            this.setTotalBalance(balance);
            return true;
        }
        return false;
    }
}
