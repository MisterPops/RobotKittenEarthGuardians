
package robotkittenearthguardians.entity.mob.ai;

import robotkittenearthguardians.entity.Vector2F;
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
	protected Vector2F movement = new Vector2F();
	protected Vector2F mobPos = new Vector2F();
	
	/**
	 * Updates mob's position to the mobPos vector in the Ai class
	 * @param mobPosition vector of mob's x and y position
	 */
	public void update(Vector2F mobPosition) {
		mobPos.setXVector(mobPosition.getXVector());
		mobPos.setYVector(mobPosition.getYVector());
	}
	
	/**
	 * Really basic ai that moves the mob simply from point A to B
	 * @param x mobs x pos
	 * @param y mobs y pos
	 * @param speed mobs speed
	 * @return movement Vector2Float
	 */
	public Vector2F simpleAi(double speed) {
		double dx = playerX - mobPos.getXVector(), dy = playerY - mobPos.getYVector();
		double distance = Math.sqrt(dx * dx + dy * dy);
		double multiplier = speed / distance;
		movement.setXVector((float) (dx * multiplier));
		movement.setYVector((float) (dy * multiplier));
		return movement;
	}
	
	public Vector2F unstack(double speed, Mob mob) {
		double dx = mob.getXCoord() - mobPos.getXVector() + 0.1f, dy = mob.getYCoord() - mobPos.getYVector() + 0.1f;
		double distance = Math.sqrt(dx * dx + dy * dy);
		double multiplier = speed / distance;
		movement.setXVector((float) (dx * multiplier) * -1);
		movement.setYVector((float) (dy * multiplier) * -1);
		return movement;
	}
	
	/**
	 * Returns the distance from the mob to the player
	 * @param x mobs x pos
	 * @param y mobs y pos
	 * @return double of distance from mob to player
	 */
	public double getDistance(Vector2F mobPosition) {
		double dx = playerX - mobPos.getXVector(), dy = playerY - mobPos.getYVector();
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