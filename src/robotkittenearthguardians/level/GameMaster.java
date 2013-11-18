package robotkittenearthguardians.level;

import java.util.Random;

import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.mob.WaterBalloon;

public class GameMaster {
	
	private static Random random = new Random();
	private static int index = 0;
	private static int monsterIndex = 0;
	private static int monsterCount = 0;
	
	//Holds the mobs to be spawned each level
	private static int[][] levels = {
			{2},
			{100}
	};
	
	/**
	 * Spawns monsters to the level. And moves on to the next level when all the monsters in
	 * the previous level are killed.
	 */
	public static void update() {
		if(monsterCount < levels[index][monsterIndex]) {
			switch (monsterIndex) {
			case 0: @SuppressWarnings("unused")
			WaterBalloon waterballoon = new WaterBalloon(500 + random.nextInt(10), 400 + random.nextInt(10));
			}
			monsterCount++;
		} else if(monsterIndex > levels[index].length) {
			monsterIndex++;
		} else if(Entity.getMobSize() < 1) {
			index++;
			monsterCount = 0;
			monsterIndex = 0;
		}
	}

}
