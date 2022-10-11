package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.enums.Status;
import game.interfaces.HighGround;
import game.items.Coin;

/**
 * A class representing the Wall that implements HighGround
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Ground
 */
public class Wall extends Ground implements HighGround {
	/**
	 * A constant for success_rate of jumps to higher ground
	 */
	private final int SUCCESS_RATE = 80;
	/**
	 * A constant for damage when failing to jump to higher ground
	 */
	private final int DAMAGE_RECEIVED = 20;
	/**
	 * An instance of the ActionList class called allowableActions
	 */
	private ActionList allowableActions;
	/**
	 * A variable called jumped to check whether the actor has jumped or not
	 */
	private boolean jumped;
	/**
	 * A instance of JumpAction called lastJumpAction
	 */
	private JumpAction lastJumpAction;

	/**
	 * Constructor
	 */
	public Wall() {
		super('#');
		this.allowableActions=new ActionList();
	}

	// the actor cannot walk onto wall
	@Override
	/**
	 * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
	 * the actor cannot walk onto wall
	 *
	 * @param actor the Actor to check
	 * @return true
	 */
	public boolean canActorEnter(Actor actor) {
		if(actor.hasCapability(Status.INVINCIBLE)) {
			return true;
		}
		return false;
	}

	/**
	 * Called once per turn, so that Locations can experience the passage time. If that's
	 * important to them.
	 *
	 * @param location of the actor
	 */
	public void tick(Location location){
		// if player has consume power star, he can walk onto wall and the wall will become dirt and spawn coin
		if (location.containsAnActor()) {
			if(location.getActor().hasCapability(Status.INVINCIBLE)) {
				Dirt dirt = new Dirt();
				location.setGround(dirt);
				Coin c = new Coin(5);
				location.addItem(c);
			}
		}
	}
	@Override
	/**
	 * Returns an Action list that contains each kind of actions.
	 *
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return a new, empty collection of Actions
	 */
	public ActionList allowableActions(Actor actor, Location location, String direction) {
		// if the player has jumped onto the current high ground, remove jump action in the allowableAction list
		if(this.jumped ==true) {
			this.allowableActions.remove(lastJumpAction);
		}
		// if the player wants to jump onto high ground and he doesn't consume Power Star, we will add a jump action into allowable action list to let him jump
		if (actor != location.getActor() && actor.hasCapability(Status.NEED_TO_JUMP) && !actor.hasCapability(Status.INVINCIBLE)) {
			JumpAction j= JumpUpAction(location, SUCCESS_RATE, DAMAGE_RECEIVED, direction);
			this.allowableActions.add(j);
			lastJumpAction =j;
			this.jumped =true;
		}

		return allowableActions;
	}


	// player needs to jump onto wall if he wants to go there, so we need to implement JumpUpAction
	@Override
	/**
	 * Constructor.
	 *
	 * @param location The location of the actor
	 * @param SUCCESS_RATE The success rate to jump onto the wall
	 * @param DAMAGE The damage taken by the player if not success
	 * @param direction The direction that the player target
	 */
	public JumpAction JumpUpAction(Location location, int success_rate, int damage_received, String direction) {
		return new JumpAction(location, success_rate, damage_received, direction);
	}
}
