
package robotkittenearthguardians.entity.mob.ai;

import robotkittenearthguardians.entity.Vector2Float;
import robotkittenearthguardians.entity.mob.Mob;

/**
 * A utility class to guide NPC's and implement their AI.
 * @author Brandon
 *
 */
public abstract class Ai extends Mob{
	//Player's x and y position
	protected static int playerX;
	protected static int playerY;
	protected Vector2Float movement = new Vector2Float();
	protected Vector2Float mobPos = new Vector2Float();
	
	/**
	 * Updates mob's position to the mobPos vector in the Ai class
	 * @param mobPosition vector of mob's x and y position
	 */
	public void update(Vector2Float mobPosition) {
		mobPos.x = mobPosition.x;
		mobPos.y = mobPosition.y;
	}
	
	/**
	 * Really basic ai that moves the mob simply from point A to B
	 * @param x mobs x pos
	 * @param y mobs y pos
	 * @param speed mobs speed
	 * @return movement Vector2Float
	 */
	public Vector2Float simpleAi(double speed) {
		double dx = playerX - mobPos.x, dy = playerY - mobPos.y;
		double distance = Math.sqrt(dx * dx + dy * dy);
		double multiplier = speed / distance;
		movement.x = (float) (dx * multiplier);
		movement.y = (float) (dy * multiplier);
		return movement;
	}
	
	public Vector2Float unstack(double speed, Mob mob) {
		double dx = mob.getXCoord() - mobPos.x + 0.1f, dy = mob.getYCoord() - mobPos.y + 0.1f;
		double distance = Math.sqrt(dx * dx + dy * dy);
		double multiplier = speed / distance;
		movement.x = (float) (dx * multiplier) * -1;
		movement.y = (float) (dy * multiplier) * -1;
		return movement;
	}
	
	/**
	 * Returns the distance from the mob to the player
	 * @param x mobs x pos
	 * @param y mobs y pos
	 * @return double of distance from mob to player
	 */
	public double getDistance(Vector2Float mobPosition) {
		double dx = playerX - mobPos.x, dy = playerY - mobPos.y;
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
	public boolean seePlayer(int sightRange) {
		if(getDistance(mobPos) < sightRange) {
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