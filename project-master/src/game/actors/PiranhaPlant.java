package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Utils;
import game.actions.AttackAction;
import game.actions.FireAttackAction;
import game.actions.MenuLogAction;
import game.actions.MonologueAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the Piranha Plant that implements Resettable
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Actor
 */
public class PiranhaPlant extends Actor implements Resettable {
    /**
     * a hash map with integer key for priority and Behaviour value of the actor
     */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * the list of monologues that is going to output by all Piranha Plant
     */
    private static final String[] sentences = new String[]
            {
                    "Slsstssthshs~! (Never gonna say goodbye~)",
                    "Ohmnom nom nom nom."
            };

    /**
     * count the number of play turns
     */
    private int counter = 0;

    /**
     * Constructor.
     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150);
        this.behaviours.put(0, new AttackBehaviour());
        Resettable.super.registerInstance();
    }
    /**
     * get the Weapon of Piranha Plant with creating a new instance of Intrinsic Weapon
     */
    public IntrinsicWeapon getWeapon() {
        return new IntrinsicWeapon(90, "chomps");
    }

    /**
     * it will be attack by player, It also will attack the player. Once Piranha Plant get attacked or it attacks the player,
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            if (otherActor.hasCapability(Status.FIRE_BUFF)){
                actions.add(new FireAttackAction(this, direction));
            }
            else{
                actions.add(new AttackAction(this, direction));
            }

        }
        return actions;
    }


    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        counter++;
        // it will speak every two turns
        if (counter % 2 == 0){
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            arrayList.add(1); arrayList.add(0);
            int index = Utils.getRandomIndex(arrayList);
            String chosenSentence = sentences[index];
            return new MenuLogAction(chosenSentence);
        }

        for (Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);

            if (action != null){
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * the existing piranha plant's hp will increase 50
     *
     * @param map the map containing the Actor
     */
    @Override
    public void resetInstance(GameMap map) {
        if (this.isConscious())
            this.increaseMaxHp(50);
    }


}
