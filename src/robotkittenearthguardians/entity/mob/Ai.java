
package robotkittenearthguardians.entity.mob;

public class Ai {
	private static int playerX;
	private static int playerY;
	
	/**
	 * Really basic ai that moves the mob simply from point A to B
	 * @param x mobs x pos
	 * @param y mobs y pos
	 * @param speed mobs speed
	 * @return double[] of x and y movement for mob
	 */
	public static double[] simpleAi(int x, int y, double speed) {
		double dx = playerX - x, dy = playerY - y;
		double distance = getDistance(x, y);
		double multiplier = speed / distance;
		double xa = dx * multiplier, ya = dy * multiplier;
		double[] absPos = {xa, ya};
		return absPos;
	}
	
	/**
	 * Returns the distance from the mob to the player
	 * @param x mobs x pos
	 * @param y mobs y pos
	 * @return double of distance from mob to player
	 */
	public static double getDistance(int x, int y) {
		double dx = playerX - x, dy = playerY - y;
		double distance = Math.sqrt(dx * dx + dy * dy);
		return distance;
	}
	
	/**
	 * If mob is within siteRange will return a true value.
	 * Otherwise will return a false value.
	 * @param x Mob's x position
	 * @param y Mob's y position
	 * @param sightRange Mob's eyesight range
	 * @return boolean: true, if mob can see player else false
	 */
	public static boolean seePlayer(int x, int y, int sightRange) {
		if(getDistance(x, y) < sightRange) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sets the players current x position
	 * @param xPos players X position
	 */
	public static void setPlayerX(int xPos) {
		playerX = xPos;
	}
	
	/**
	 * Sets the player's current y position
	 * @param yPos players y position
	 */
	public static void setPlayerY(int yPos) {
		playerY = yPos;
	}
	
	/**
	 * Returns the player's x position to the caller.
	 * @return players x position
	 */
	public static int getPlayerX() {
		return playerX;
	}
	
	/**
	 * returns the player's y position to the caller.
	 * @return the player's y position
	 */
	public static int getPlayerY() {
		return playerY;
	}
}
