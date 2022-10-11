package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.enums.Status;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.weapons.Wrench;

/**
 * Class representing the Dormant
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Actor
 */
public class Dormant extends Actor {
    /**
     * Constructor.
     *
     */
    public Dormant() {
        super("Koopa(Dormant)", 'D', 1);
        this.addItemToInventory(new SuperMushroom());
    }

    /**
     * it will be attack by player. Thep player needs a Wrench to turn Koopa into Dormant
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.getWeapon() instanceof Wrench) {
            actions.add(new AttackAction(this, direction));

        }
        else{
            System.out.println("You need a Wrench to destroy Koopa(Dormant) shell");

        }
        return actions;
    }

    /**
     * The dormant state of koopa will do nothing every turn
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
