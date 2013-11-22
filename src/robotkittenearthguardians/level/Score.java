package robotkittenearthguardians.level;

public class Score {
	private static int score = 0;
	
	public static void addScore(int points) {
		score += points;
	}
	
	public static long getScore() {
		return score;
	}
}
