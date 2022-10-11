package game.interfaces;

import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;

/**
 * A interface for reset.
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 */
public interface Resettable {
    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items.
     * @param map the map of the game
     */
    void resetInstance(GameMap map);

    /**
     * a default interface method that register current instance to the Singleton manager.
     * It allows corresponding class uses to be affected by global reset
     */
    default void registerInstance(){
        ResetManager.getInstance().appendResetInstance(this);
    }
}
