package robotkittenearthguardians.level;

import java.util.Random;

import robotkittenearthguardians.entity.mob.WaterBalloon;

public class GameMaster {
	
	private static Random random = new Random();
	
	//Holds the mobs to be spawned each level
	private static int[][] levels = {
			{50},
			{100}
	};
	
	public static void loadLevel(int levelNum) {
		for(int index = 0; index < levels[levelNum].length; index++) {
			for(int monsterCount = 0; monsterCount < levels[levelNum][index]; monsterCount++) {
				switch (index) {
				case 0: @SuppressWarnings("unused")
				WaterBalloon waterballoon = new WaterBalloon(500 + random.nextInt(10), 400 + random.nextInt(10));
				}
			}
		}
	}

}
