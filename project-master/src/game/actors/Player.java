package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.ConsumeAction;
import game.actions.ConsumeBottleAction;
import game.actions.ResetAction;
import game.enums.Status;
import game.interfaces.Resettable;
import game.items.*;

import java.util.ArrayList;


/**
 * Class representing the Player that implements Resettable
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Actor
 */
public class Player extends Actor implements Resettable {
	/**
	 * menu
	 */
	private final Menu menu = new Menu();

	/**
	 * wallet that store coins
	 */
	private Wallet wallet;

	/**
	 * Power Star
	 */
	private PowerStar powerStar;

	/**
	 * Fire flower
	 */
	private FireFlower fireFlower;

	/**
	 * count for the power star's effective time
	 */
	private int counterInvincible = 10;

	/**
	 * count for the fire flower buff's effective time
	 */
	private int counterFire = 20;

	/**
	 * Bottle
	 */
	private Bottle bottle;

	/**
	 * Count for the numbers of power water that player has drunk
	 */
	private int buffCounter = 0;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.NEED_TO_JUMP);
		this.addCapability(Status.FLOOR_LAVA);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.RESET_BUTTON);
		this.wallet = new Wallet();
		this.bottle = new Bottle();
		this.resetMaxHp(100);
		Resettable.super.registerInstance();

	}

	/**
	 * Select and return an action to perform on the current turn.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return next Action for every round
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// shows the player's name, hp and current location every turn
		String name = this.toString();
		String hp = this.printHp();
		int indexX = map.locationOf(this).x();
		int indexY = map.locationOf(this).y();
		System.out.println(name + hp + " at (" + indexX + ", "  + indexY + ")");

		// shows the player's wallet balance every turn
		int walletMoney = this.getWallet().getTotalBalance();
		System.out.println("wallet: $" + walletMoney);

		// player can consume magical items whenever he likes if he has them
		for (int i=0; i<this.getInventory().size();i++) {
			Item item = this.getInventory().get(i);
				if (item.getDisplayChar() == '^' || item.getDisplayChar() == '*' || item.getDisplayChar() == 'f') {
					actions.add(new ConsumeAction(item));
				}
		}

		// if player has wrench woohoo
		for (int i=0; i<this.getInventory().size();i++) {
			Item item = this.getInventory().get(i);
			if (item.getDisplayChar() == 'W') {
				this.addCapability(Status.WRENCH);
			}
			else{
				this.removeCapability(Status.WRENCH);
			}
		}


		// if player do not have bottle in his inventory at the first round, bottle will be added into inventory
		if (!this.getInventory().contains(bottle)){
			this.addItemToInventory(bottle);
		}

		// players can consume the charges of bottle.
		ArrayList<FountainWater> bottleArr;
		for (int i = 0; i < this.getInventory().size(); i++) {
			if (this.getInventory().get(i).hasCapability(Status.BOTTLE)) {
				bottle = (Bottle) this.getInventory().get(i);
			}
		}

		bottleArr = bottle.getBottle();
		actions.add(new ConsumeBottleAction(this, bottleArr));


		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// If player has NOT YET BEEN reset, add the resetAction
		if (this.hasCapability(Status.RESET_BUTTON)) {
			actions.add(new ResetAction());
		}

		// The power star will last for 10 turns
		if (this.hasCapability(Status.INVINCIBLE)) {

			powerStar = new PowerStar();
			System.out.println("Mario is INVINCIBLE! - " + counterInvincible + " more turns remaining");
			this.counterInvincible -= 1;
			if (this.counterInvincible == 0) {
				powerStar.debuff(this);
			}
		}

		// The fire buff will last for 20 turns
		if (this.hasCapability(Status.FIRE_BUFF)) {

			fireFlower = new FireFlower();
			System.out.println("SUPER MARIO (FIRE MODE)! - " + counterFire + " more turns remaining");
			this.counterFire -= 1;
			if (this.counterFire == 0) {
				fireFlower.debuff(this);
			}
		}

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * get the actor display char
	 * @return  display character of an instance
	 */
	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * getter wallet
	 *
	 * @return  get wallet from Wallet class
	 */
	public Wallet getWallet() {
		return wallet;
	}

	/**
	 * Add an item to this Player's inventory.
	 *
	 * @param item The Item to add.
	 */
	@Override
	public void addItemToInventory(Item item) {
		super.addItemToInventory(item);
	}

	/**
	 * It will be removed if the reset button is pressed
	 *
	 * @param map the map containing the Actor
	 */
	@Override
	public void resetInstance(GameMap map) {
		//resets player straight away here instead
		//reset player status
		if (this.hasCapability(Status.INVINCIBLE)) {
			this.removeCapability(Status.INVINCIBLE);
		} else if(this.hasCapability(Status.TALL)) {
			this.removeCapability(Status.TALL);
		}
		// heal player to maximum
		this.resetMaxHp(100);
	}

	/**
	 * get how many times the power water has been drunk
	 *
	 * @return the times of power water drank
	 */
	public int getBuffCounter()
	{
		return buffCounter;
	}

	/**
	 * set the times of power water that has been drunk
	 *
	 * @param times 1 time per power water drank
	 */
	public void setBuffCounter(int times)
	{
		this.buffCounter = times + getBuffCounter();
	}
}
