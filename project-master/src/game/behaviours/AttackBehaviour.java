package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actors.Player;
import game.enums.Status;
import game.grounds.Fire;
import game.interfaces.Behaviour;
import game.items.SuperMushroom;

/**
 * A class that figures out a Attack action that will attack the player by enemy when the enemy is
 * closer to a target Actor.
 * @see edu.monash.fit2099.demo.mars.Application
 */
public class AttackBehaviour implements Behaviour {

    /**
     * the target actor
     */
    private Actor target;

    /**
     * an instance of superMushroom
     */
    private SuperMushroom superMushroom;


    /**
     * the enemy's attack behavior (they will attack the player)
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor() && destination.getActor() instanceof Player){
                target = destination.getActor();
            }
        }
        if (target==null){
            return null;
        }
        if(target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            // if the player has consumed Power Star, the player will not get hurt by enemy
            if (target.hasCapability(Status.INVINCIBLE)) {
                System.out.println(actor + " attacked " + target + " with " + actor.getWeapon().damage() + " damage.");
                target.hurt(0);

            }
            // the player's super mushroom effect will disappear if the enemy attacks him
            else if (target.hasCapability(Status.TALL)) {
                System.out.println(actor + " attacked " + target + " with " + actor.getWeapon().damage() + " damage.");
                superMushroom = new SuperMushroom();
                superMushroom.debuff(target);

            }

            // enemy attacks the player
            else if(actor.getWeapon().chanceToHit()> Utils.getRandomPercentage()) {
                System.out.println(actor + " attacked " + target + " with " + actor.getWeapon().damage() + " damage.");
                target.hurt(actor.getWeapon().damage());
                if(actor.getDisplayChar()=='B'){
                    Location location = map.locationOf(target);
                    Ground ground = location.getGround();
                    Fire fire =  new Fire(ground);
                    location.setGround(fire);
                    fire.addCapability(Status.FIRE);
                }

            }
            else{
                System.out.println(actor + " missed " + target);
            }
        }
        target=null;

        return null;
    }
}

