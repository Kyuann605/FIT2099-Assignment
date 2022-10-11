package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.InteractFountainAction;
import game.enums.Status;
import game.items.Bottle;
import game.items.HealthWater;
import game.items.PowerWater;

import java.util.ArrayList;

/**
 * A class that represents power fountain.
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Ground
 */
public class PowerFountain extends Ground {

    /**
     * Constructor
     */
    public PowerFountain() {
        super('A');
    }

    /**
     * Allow the actor to pass through Health Fountain
     *
     * @param actor the Actor to check
     * @return true
     */
    public boolean canActorEnter(Actor actor) {
        return true;
    }


    /**
     * Returns an Action list that contains each kind of actions. Allow the player to interact with the health fountain
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (location.containsAnActor()) {
            actionList.add(new InteractFountainAction(actor, new PowerWater()));
        }
        return actionList;
    }
}
