package robotkittenearthguardians.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.mob.WaterBalloon;
import robotkittenearthguardians.entity.mob.WaterBottlePack;
import robotkittenearthguardians.entity.mob.WaterGunMech;
import robotkittenearthguardians.util.Vector2I;

public class GameMaster {
	
	private static int level = 0;
	private static long score = 0;
	//private static int levelRestPeriod = 10;
	private static String levelName;
	
	private static int deltaTimer = 0;
	private static int levelTimer = -2;
	private static int levelArrayIndex = -1;
	private static int monsterIndex;
	private static int numOfMonsters = 0;
	private static Vector2I spawnLocation = new Vector2I();
	private static Random random = new Random();
	
	private static List<LevelBuilder> currentLevel;
	
	/**
	 * Spawns monsters to the level. And moves on to the next level when all the monsters in
	 * the previous level are killed.
	 */
	public static void update() {
		if(Entity.getMobSize() < 2 && levelTimer == -2 && numOfMonsters == 0) {
			level++;
			currentLevel = new ArrayList<LevelBuilder>();
			levels(level);
			//levelTimer = levelRestPeriod;
			levelArrayIndex = currentLevel.size();
			setSpawnLocation();
		}
		
		spawn();
	}
	
	private static void spawn() {
		if(numOfMonsters > 0) {
			switch (monsterIndex) {
			case 0: @SuppressWarnings("unused")
			WaterBalloon waterballoon = new WaterBalloon(spawnLocation.getXVector() + random.nextInt(10),
					spawnLocation.getYVector() + random.nextInt(10));
			break;
			
			case 1: @SuppressWarnings("unused")
			WaterBottlePack waterBottlePack = new WaterBottlePack(spawnLocation.getXVector() + random.nextInt(10),
					spawnLocation.getYVector() + random.nextInt(10));
			break;
			
			case 2: @SuppressWarnings("unused")
			WaterGunMech waterGunMech = new WaterGunMech(spawnLocation.getXVector() + random.nextInt(10),
					spawnLocation.getYVector() + random.nextInt(10));
			}
			numOfMonsters--;
		} else if(levelTimer > 0) {
			if(deltaTimer > 60) {
				deltaTimer = 0;
				levelTimer--;
			} else {
				deltaTimer++;
			}
		} else if(levelArrayIndex > 0) {
			levelTimer = currentLevel.get(currentLevel.size() - levelArrayIndex).getTimer();
			monsterIndex = currentLevel.get(currentLevel.size() - levelArrayIndex).getMob2Spawn();
			numOfMonsters = currentLevel.get(currentLevel.size() - levelArrayIndex).getNumOfMobs();
			setSpawnLocation();
			levelArrayIndex--;
		}
	}
	
	public static void addScore(int points) {
		score += points;
	}
	
	public static long getScore() {
		return score;
	}
	
	public static String getLevelName() {
		return levelName;
	}
	
	private static void setSpawnLocation() {
		int rand = random.nextInt(8);
		switch (rand) {
		case 0:
			spawnLocation.setVector(110, 425);
			break;
		case 1:
			spawnLocation.setVector(345, 185);
			break;
		case 2:
			spawnLocation.setVector(755, 85);
			break;
		case 3:
			spawnLocation.setVector(1150, 140);
			break;
		case 4:
			spawnLocation.setVector(1465, 425);
			break;
		case 5:
			spawnLocation.setVector(1306, 650);
			break;
		case 6:
			spawnLocation.setVector(800, 800);
			break;
		case 7:
			spawnLocation.setVector(395, 720);
			break;
		
		}
	}

	/**
	 * In charge of level name, level spawning, and spawn timer information.
	 * Fills the currentLevel arrayList with this information
	 * @param level the current level
	 */
	public static void levels(int level) {
		switch(level) {
		case 1: 
			GameMaster.levelName = "LEVEL 1";
			currentLevel.add(new LevelBuilder(2, 1, 5)); currentLevel.add(new LevelBuilder(0, 5, 5));
			currentLevel.add(new LevelBuilder(0, 5, -2));
			break;
		case 2:
			GameMaster.levelName = "LEVEL 2";
			currentLevel.add(new LevelBuilder(2, 2, -2));
			break;
		case 3:
			GameMaster.levelName = "LEVEL 3";
			currentLevel.add(new LevelBuilder(0, 200, -2));
		}
	}
}
