package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Utils;
import game.actors.Dormant;
import game.actors.Koopa;
import game.actors.Player;
import game.enums.Status;
import game.grounds.Fire;
import game.items.SuperMushroom;

/**
 * Special Action for attacking other Actors.
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Action
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Perform Attack action.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a message about every Mario's attack attempt
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();
		String msg="";
		// if actor has consumed Power Star, he can instantly kill the enemy if he didn't miss
		if (actor.hasCapability(Status.INVINCIBLE)) {
			if (Utils.getRandomPercentage()>(actor.getWeapon().chanceToHit())) {
				return actor + " misses " + target + ".";
			}

			// if the target is Koopa, it will instantly kill it and Koopa will become Dormant
			target.hurt(99999);
			if (target.getDisplayChar() == 'K') {
				Location location = map.locationOf(target);
				map.removeActor(target);
				Dormant dormant = new Dormant();
				map.addActor(dormant, location);
				msg = actor + " instantly killed " + target;
			}

			// if Dormant is killed, it will drop a Super Mushroom
			else if (target.getDisplayChar() == 'D'){
				ActionList dropActions = new ActionList();
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);
				map.removeActor(target);
				msg = "Hiding Koopa's shell is destroyed, Super Mushroom is dropped";
			}

			// else killed the target
			else {
				map.removeActor(target);
				msg = actor + " instantly killed " + target;
			}

			return msg;
		}


		// player missed hitting the target
		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		if (actor.hasCapability(Status.FIRE_BUFF)){
			Location location = map.locationOf(target);
			Ground ground = location.getGround();
			Fire fire =  new Fire(ground);
			location.setGround(fire);
			fire.addCapability(Status.FIRE);
		}

		// player attacks the enemy
		int damage = weapon.damage();
		Player player = (Player) actor;
		int turn = player.getBuffCounter();
		int totalDamage = damage + (15*turn);
		String result = "";

		if (actor.hasCapability(Status.POWERWATER)){
			if (actor.hasCapability(Status.WRENCH)){
				result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
				target.hurt(damage);
			}
			else{
				result = actor + " " + weapon.verb() + " " + target + " for " + totalDamage + " damage.";
				target.hurt(totalDamage);
			}
		}
		else{
			result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
			target.hurt(damage);
		}

		// if the enemy is dead
		if (!target.isConscious()) {

			// Koopa and Flying Koopa will become Dormant
			if (target.getDisplayChar() == 'K'||target.getDisplayChar()=='F'){
				Location location = map.locationOf(target);
				map.removeActor(target);
				Dormant dormant = new Dormant();
				map.addActor(dormant,location);
			}

			// else the enemy's inventory items will drop out
			else {
				ActionList dropActions = new ActionList();
				// drop all items
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);

				//remove actor
				map.removeActor(target);


				if(target.getDisplayChar() == 'D'){
					result += System.lineSeparator() + "Shell is destroyed, Super Mushroom is dropped";
				}
				if (target.getDisplayChar()=='B'){
					result+= System.lineSeparator() + target + " is killed, key is dropped";
				}
				else
					result += System.lineSeparator() + target + " is killed.";
			}
		}

		return result;
	}


	/**
	 * Returns a descriptive string that state the coordinates of enemy (for attack action)
	 *
	 * @param actor The actor performing the action.
	 * @return string of an attack action for attacking the enemy
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
