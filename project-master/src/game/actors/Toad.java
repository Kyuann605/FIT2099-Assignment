package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;

import game.Utils;
import game.actions.MenuLogAction;
import game.actions.TradeAction;

import java.util.ArrayList;


/**
 * Class representing the Toad aka the trader
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Actor
 */
public class Toad extends Actor {
    /**
     * List of Action
     */
    private ActionList actionsList = new ActionList();
    /**
     * the list of monologues that is going to output by Toad
     */
    private static final String[] sentences = new String[] {"You might need a wrench to smash Koopa's hard shells.",
            "You better get back to finding the Power Stars.",
            "The Princess is depending on you! You are our only hope.",
            "Being imprisoned in these walls can drive a fungus crazy :("};

    /**
     * count the number of play turns
     */
    private int count = 0;

    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Toad(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Empty Constructor.
     *
     */
    public Toad(){
        super("Toad",'O',99999);
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Do nothing
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        count++;
        // it will speak every two turns
        if (count % 2 == 0){
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            arrayList.add(1); arrayList.add(0);
            int index = Utils.getRandomIndex(arrayList);
            String chosenSentence = sentences[index];
            return new MenuLogAction(chosenSentence);
        }

        return new DoNothingAction();
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return TradeAction
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        actionsList.clear();
        actionsList.add(new TradeAction(this));
        return actionsList;
    }

    /**
     * get a sentence with the index
     *
     * @param index the index of the monologue
     * @return A monologue out of 4 monologues coded
     */
    public static String getSentence(int index) {
        return sentences[index];
    }
}
