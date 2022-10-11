package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Utils;
import game.actions.AttackAction;
import game.actions.MenuLogAction;
import game.actions.UnlockAction;
import game.behaviours.FollowBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.items.Key;
import game.weapons.Wrench;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the Princess Peach
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Actor
 */
public class PrincessPeach extends Actor {
    /**
     * a hash map with integer key for priority and Behaviour value of the actor
     */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * the list of monologues that is going to output by Princess Peach
     */
    private static final String[] sentences = new String[]
            {
                    "Dear Mario, I'll be waiting for you...",
                    "Never gonna give you up!",
                    "Release me, or I will kick you!"
            };

    /**
     * count the number of play turns
     */
    private int counter = 0;

    /**
     * constructor
     */
    public PrincessPeach() {
        super("Princess Peach", 'P', 99999);
        this.addCapability(Status.LOCKED);
    }

    /**
     * Princess Peach will be locked till it got unlocked by player, if unlocked, Princess Peach will follow Mario
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (this.hasCapability(Status.LOCKED)) {
            actions.add(new UnlockAction(otherActor, this));
        }
        else if (!this.hasCapability(Status.LOCKED)){
            FollowBehaviour followBehaviour = new FollowBehaviour(otherActor);
            this.behaviours.put(0, followBehaviour);
        }
        return actions;
    }

    /**
     * Select and return an action to perform on the current turn. Monologue will be output every 2 turns. If Princess Peach is unlocked,
     * FollowBehaviour will be added so she will follow Mario
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
            arrayList.add(1); arrayList.add(2); arrayList.add(0);
            int index = Utils.getRandomIndex(arrayList);
            String chosenSentence = sentences[index];
            return new MenuLogAction(chosenSentence);
        }
        if (!this.hasCapability(Status.LOCKED)) {
            for (game.interfaces.Behaviour Behaviour : behaviours.values()) {
                Action action = Behaviour.getAction(this, map);

                if (action != null) {
                    return action;
                }
            }
        }
        return new DoNothingAction();
    }


}
