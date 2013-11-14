package robotkittenearthguardians.level;

import robotkittenearthguardians.entity.mob.WaterBalloon;

public class GameMaster {
	
	//Holds the mobs to be spawned each level
	private static int[][] levels = {
			{1},
			{2}
	};
	
	public static void loadLevel(int levelNum) {
		for(int index = 0; index < levels[levelNum].length; index++) {
			for(int monsterCount = 0; monsterCount < levels[levelNum][index]; monsterCount++) {
				switch (index) {
				case 0: @SuppressWarnings("unused")
				WaterBalloon waterballoon = new WaterBalloon(800, 400);
				}
			}
		}
	}

}
