package game;

import game.items.MagicalItem;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class that gives random numbers.
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 */
public class Utils {
    /**
     * genrate random
     */
    private static Random rand= new Random();

    /**
     * randomize percentage
     *
     * @return random percentage
     */
    public static int getRandomPercentage() {
        int high = 100;
        return rand.nextInt(high);
    }

    /**
     * randomize index
     *
     * @param list list of integer
     * @return the index within the list size
     */
    public static int getRandomIndex(ArrayList<Integer> list) {
        int index = rand.nextInt(list.size());
        return list.get(index);
    }
}
