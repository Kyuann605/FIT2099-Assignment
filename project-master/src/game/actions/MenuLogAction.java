package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Utils;
import game.actors.Bowser;
import game.actors.PrincessPeach;
import game.actors.Toad;

import java.util.ArrayList;

/**
 * Monologue action for every actor in every two turns
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Action
 */
public class MenuLogAction extends Action {
    /**
     * the output sentence
     */
    private String sentence;

    /**
     * Constructor
     * @param sentence the output sentence
     */
    public MenuLogAction(String sentence){
        this.sentence = sentence;
    }

    /**
     * Perform the outputting monologue action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string of monologue
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        return actor + ": " + sentence;
    }

    /**
     * Returns a string where player can speak to the toad
     *
     * @param actor The actor performing the action.
     * @return null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }


}
