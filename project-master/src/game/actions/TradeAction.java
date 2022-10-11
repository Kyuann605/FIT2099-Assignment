package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.actors.Toad;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.Wallet;
import game.weapons.Wrench;

import java.util.Scanner;

/**
 * Trade action performed by Toad
 *
 * @author Ting Yi Xuan, Goh Kai Yuan, Jonathan Koh
 * @version 1.0.0
 * @see Action
 */
public class TradeAction extends Action {
    /**
     * cost of Wrench
     */
    private final int WRENCH_COST = 200;
    /**
     * cost of Super Mushroom
     */
    private final int SUPERMUSHROOM_COST = 400;
    /**
     * cost of PowerStar
     */
    private final int POWERSTAR_COST = 600;
    /**
     * MONEYYYYYYY!!!
     */
    private Wallet points;
    /**
     * selection to select from menu
     */
    private String selection;
    /**
     * trader Toad
     */
    protected Toad toad;

    /**
     * Constructor
     *
     * @param toad the trader of this application
     */
    public TradeAction(Toad toad)
    {
        this.toad = toad;
    }

    /**
     * Perform the Trade Action with Toad.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        points = player.getWallet();
        do {
            selection = selectMenuItem();
            switch (selection) {
                case "a" -> {
                    if (!(points.getTotalBalance() - POWERSTAR_COST < 0)) {
                        System.out.println("Mario bought PowerStar");
                        points.deductBalance(POWERSTAR_COST);
                        player.addItemToInventory(new PowerStar("PowerStar", '*', true));
                    } else {
                        System.out.println("You don't have enough coins!");
                    }
                }


                case "b" -> {
                    if (!(points.getTotalBalance() - SUPERMUSHROOM_COST < 0)) {
                        System.out.println("Mario bought SuperMushroom");
                        points.deductBalance(SUPERMUSHROOM_COST);
                        player.addItemToInventory(new SuperMushroom("SuperMushroom", '^', true));
                    } else {
                        System.out.println("You don't have enough coins!");
                    }
                }

                case "c" -> {
                    if (!(points.getTotalBalance() - WRENCH_COST < 0)) {
                        System.out.println("Mario bought Wrench");
                        points.deductBalance(WRENCH_COST);
                        player.addItemToInventory(new Wrench("Wrench", 'w', 30, "bonk", 50));
                    } else
                        System.out.println("You don't have enough coins!");
                }

                case "d" -> {
                    MonologueAction m = new MonologueAction();
                    String sentence = m.execute(actor, map);
                    System.out.println(sentence);
                }
                case "e" -> {
                    break;
                }
                default -> {
                    System.out.println("Not an option");
                }
            }
        } while (!selection.equals("e"));
        return "Mario bought nothing";
    }


    private String selectMenuItem() {
        Boolean turn = true;
        Scanner scanner = new Scanner(System.in);
        while (turn) {
            System.out.println("Available Coin: $" + points.getTotalBalance());
            System.out.println("a. Mario buys Power Star ($" + POWERSTAR_COST + ")");
            System.out.println("b. Mario buys Super Mushroom ($" + SUPERMUSHROOM_COST + ")");
            System.out.println("c. Mario buys Wrench ($" + WRENCH_COST + ")");
            System.out.println("d. Mario speaks with Toad");
            System.out.println("e. Exit");
            turn = false;
            selection = scanner.nextLine();
        }
        return selection;
    }
    /**
     * Returns a descriptive string that say player talk with Toad
     *
     * @param actor The actor performing the action.
     * @return actor + "talks with Toad"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks with Toad";
    }
}
