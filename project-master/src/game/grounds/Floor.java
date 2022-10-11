package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Status;

/**
 * A class that represents the floor inside a building.
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Ground
 */
public class Floor extends Ground {
	public Floor() {
		super('_');
	}

	/**
	 * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
	 *
	 * @param actor the Actor to check
	 * @return true
	 */
	// koopa and goomba cannot enter into the floor, but player can
	public boolean canActorEnter(Actor actor) {
		if (actor.hasCapability(Status.FLOOR_LAVA)) {
			return true;
		}
		return false;
	}
}
