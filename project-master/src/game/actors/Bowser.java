package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Utils;
import game.actions.AttackAction;
import game.actions.FireAttackAction;
import game.actions.MenuLogAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.grounds.Dirt;
import game.grounds.Sapling;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.items.Key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the Browser that implements Resettable
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Actor
 */
public class Bowser extends Actor implements Resettable {
    /**
     * implement behaviour
     */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * count the number of play turns
     */
    private int count=0;

    /**
     * location
     */
    private Location oriLocation;

    /**
     * the list of monologues that is going to output by Bowser
     */
    private static final String[] sentences = new String[]
            {
                    "What was that sound? Oh, just a fire.",
                    "Princess Peach! You are formally invited... to the creation of my new kingdom!",
                    "Never gonna let you down!",
                    "Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!"
            };

    /**
     * Constructor
     */
    public Bowser() {
        super("Bowser", 'B', 500);
        this.behaviours.put(0, new AttackBehaviour());
        this.addItemToInventory(new Key());

        Resettable.super.registerInstance();
    }

    /**
     * Bowser damage to Mario
     *
     * @return a message about the status of the Princess Peach
     */
    public IntrinsicWeapon getWeapon() {
        return new IntrinsicWeapon(80, "punch");
    }


    /**
     * it will be attack by player, It also will attack the player. Once Bowser get attacked or it attacks the player
     * it will be attacked by player with fire if player has fire buff
     * it will follow the player
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            if(otherActor.hasCapability(Status.FIRE_BUFF)){
                actions.add(new FireAttackAction(this,direction));
            }
            else{
                actions.add(new AttackAction(this,direction));
            }
            FollowBehaviour followBehaviour = new FollowBehaviour(otherActor);
            this.behaviours.put(1, followBehaviour);
        }
        return actions;
    }

    /**
     * Select and return an action to perform on the current turn. Monologue will be output every 2 turns
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.count==0){
            oriLocation = map.locationOf(this);
        }
        this.count++;
        // it will speak every two turns
        if (count % 2 == 0){
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            arrayList.add(1); arrayList.add(2); arrayList.add(0); arrayList.add(3);
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
     * It will be reset to 500hp Bowser and return to the initial location
     *
     * @param map the map containing the Actor
     */
    @Override
    public void resetInstance(GameMap map) {
        if (this.isConscious()){
            this.resetMaxHp(500);
            if (map.getActorAt(oriLocation) != this) {
                if (oriLocation.containsAnActor()){
                    map.removeActor(map.getActorAt(oriLocation));
                }
                map.moveActor(this, oriLocation);
                System.out.println("Bowser moved back to initial position");
            }
            else
                System.out.println("Bowser remains at same position");
            }
        this.behaviours.remove(1);
        }


    }

