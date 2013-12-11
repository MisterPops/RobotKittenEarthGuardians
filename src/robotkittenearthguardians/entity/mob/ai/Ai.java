
package robotkittenearthguardians.entity.mob.ai;

import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.mob.Mob;
import robotkittenearthguardians.util.Vector2F;

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
	
	/**
	 * Pushes two entities away from each other
	 * @param mainEntity the main entity calling this method
	 * @param entity the entity to unstack from
	 */
	public void unStack(Entity mainEntity, Entity entity) {
		float radius = size.getXVector() + entity.getSizeVector().getXVector();
		
		double dx = (entity.getXCoord() - mainEntity.getXCoord());
		double dy = (entity.getYCoord() - mainEntity.getYCoord());
		double angle = Math.atan2(dy, dx);
		double scale = 1.0f - Math.sqrt(dx * dx + dy * dy) / radius + 1.2;
		mainEntity.move(-(int) (Math.cos(angle) * scale * 2), -(int) (Math.sin(angle) * scale * 2));
		
		entity.move((int) (Math.cos(angle) * scale * 2), (int) (Math.sin(angle) * scale * 2));
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
		return getDistance(mobPos) < sightRange;
	}
	
	public void onCollide(Entity entity) {
	}
	
	public Vector2F getMovementVector() {
		return movement;
	}

	//***<<=== Player Position setting and getting ===>>***
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