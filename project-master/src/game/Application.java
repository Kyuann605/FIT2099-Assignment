package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actors.*;
import game.grounds.*;
import game.items.FireFlower;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.weapons.Wrench;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	/**
	 * Main method in driver class
	 * @param args arguments from command line
	 */
	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout());

		List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);

		FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(), new Lava());
		List<String> map2 = Arrays.asList(
				"...L...L..............................L................................L...L...",
				"...............................................................................",
				"........L.L.L...................................................L..............",
				".........L.L.L..................L..............................................",
				"..........L.L.L..............L..............................L..................",
				"...............................................................................",
				"................................................LLL............................",
				"....................................L..............LL..........................",
				"...............................................................................",
				"...............L...............................................................",
				"...........................LLL.................................................",
				"....................................................L..........................",
				".................L.............................................................");



		GameMap gameMap2 = new GameMap(groundFactory2, map2);
		world.addGameMap(gameMap2);

		// ADD WARP PIPES
		// IN GAMEMAP1
		gameMap.at(40,8).setGround(new WarpPipe(gameMap2));
		gameMap.at(37,9).setGround(new WarpPipe(gameMap2));
		gameMap.at(8,9).setGround(new WarpPipe(gameMap2));


		// IN GAMEMAP2
		// set reference from map2's wrap pipe to map1 after map1 has been created
		WarpPipe warpPipe = new WarpPipe(gameMap);
		warpPipe.setSecondMap(true);
		gameMap2.at(0,0).setGround(warpPipe);

		Actor mario = new Player("Mario", 'm', 100);
		world.addPlayer(mario, gameMap.at(42, 10));


		gameMap.at(42, 11).addActor(new Toad());
		gameMap.at(42,9).addItem(new SuperMushroom());
		gameMap.at(42,8).addItem(new PowerStar());
		gameMap.at(42,7).addItem(new Wrench());
		gameMap.at(25,4).addItem(new FireFlower());
		gameMap.at(44,8).addActor(new FlyingKoopa());
		gameMap2.at(20,1).addActor(new Bowser());
		gameMap2.at(20,2).addActor(new PrincessPeach());

		gameMap.at(42,15).setGround(new HealthFountain());
		gameMap.at(42,16).setGround(new PowerFountain());

//			FIXME: the Goomba should be generated from the Tree
//			gameMap.at(35, 10).addActor(new Goomba());

		world.run();

	}
}

