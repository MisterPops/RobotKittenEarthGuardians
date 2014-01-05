
package robotkittenearthguardians.entity.mob.ai;

import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.mob.Mob;
import robotkittenearthguardians.entity.mob.Player;
import robotkittenearthguardians.entity.projectiles.Projectiles;
import robotkittenearthguardians.input.Mouse;
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
	//Used for projectiles to target mobs
	protected Mob target;
	//Distance X/Y from host to target
	private double dx, dy;
	protected Vector2F movement = new Vector2F();
	protected Vector2F mobPos = new Vector2F();
	double randomCoordX = 1000 / 2;
	double randomCoordY = 500 / 2;
	protected double randomDistance = 0;
	protected int timer = 0;
	protected boolean collided = false;
	protected Mob mob;
	protected Projectiles projectile;
	
	public Ai(Mob mob) {
		this.mob = mob;
	}
	
	public Ai(Projectiles projectile, Mob target) {
		this.projectile = projectile;
		this.target = target;
	}
	
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
		dx = playerX - mobPos.getXVector(); dy = playerY - mobPos.getYVector();
		double distance = Math.sqrt(dx * dx + dy * dy);
		double multiplier = speed / distance;
		movement.setXVector((float) (dx * multiplier));
		movement.setYVector((float) (dy * multiplier));
		return movement;
	}
	
	/**
	 * Similar to simple Ai but hunts a specified target that is not the player.
	 * @param speed Entitie's speed
	 * @return movement Vector2Float
	 */
	public Vector2F hunt(double speed) {
		dx = target.getXCoord() - mobPos.getXVector(); dy = target.getYCoord() - mobPos.getYVector();
		double distance = Math.sqrt(dx * dx + dy * dy);
		double multiplier = speed / distance;
		movement.setXVector((float) (dx * multiplier));
		movement.setYVector((float) (dy * multiplier));
		return movement;
	}
	
	/**
	 * Used for random wandering around the arena.
	 * @param speed speed the mob will travel.
	 * @return Vector2F of x and y movement for Ai.
	 */
	public Vector2F wander(double speed) {
		//Make a point to wander too
		double stageDiameterX = 1520, stageDiameterY = 822;
		double stageCenterX = stageDiameterX / 2 + 50, stageCenterY = stageDiameterY / 2 + 25;
		
		if(randomDistance < 150) {
			randomCoordX = random.nextInt((int) (stageDiameterX - 45)) + 45;
			randomCoordY = random.nextInt((int) ((stageCenterY / stageCenterX) * 
					(Math.sqrt(stageDiameterX * randomCoordX - (randomCoordX * randomCoordX)) + stageCenterX) - 25)) + 25;
		}
		
		//get Distance between the points
		double randomDx = randomCoordX - mobPos.getXVector(), randomDy = randomCoordY - mobPos.getYVector();
		randomDistance = Math.sqrt(randomDx * randomDx + randomDy * randomDy);
		double multiplier = speed / randomDistance;
		movement.setXVector((float) (randomDx * multiplier));
		movement.setYVector((float) (randomDy * multiplier));
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
		double scale = 1.3f - Math.sqrt(dx * dx + dy * dy) / radius + 1.0;
		mainEntity.move(-(int) (Math.cos(angle) * scale * 2.3), -(int) (Math.sin(angle) * scale * 2.3));
		
		entity.move((int) (Math.cos(angle) * scale * 2.3), (int) (Math.sin(angle) * scale * 2.3));
	}
	
	/**
	 * Method to prompt mob to fire weapon.
	 */
	public void fire() {
		double dx = playerX - mobPos.getXVector(), dy = playerY - mobPos.getYVector();
		double angle = Math.atan2(dy, dx);
		mob.shoot((int) mobPos.getXVector(), (int) mobPos.getYVector(), angle, -1);
	}
	
	/**
	 * Actions taken when mobs collide.
	 * @param mainEntity The main mob.
	 * @param entity The mob that the main mob is colliding with.
	 */
	public void onCollide(Entity mainEntity, Entity entity) {
		if(!(entity instanceof Player)) {
			unStack(mainEntity, entity);
			collided = true;
		} else if(entity instanceof Player) {
			
		}
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
	
	/**
	 * Direction the Ai is moving in. This direction method is for isometric mobs.
	 * @return returns an int depending on Ai direction.
	 */
	public int aiDirection(boolean isometric) {
		if(isometric) {
			//For isometric direction.
			if(movement.getXVector() <= 0 && movement.getYVector() >= 0) {
				return 1;
			} else if(movement.getXVector() > 0 && movement.getYVector() > 0) {
				return 7;
			} else if(movement.getXVector() > 0 && movement.getYVector() < 0) {
				return 8;
			} else {
				return 0;
			}
		} else {
			//For full 360 direction.
			//remember Y is flipped on the grid. Big error on my part.		
			double degree = Math.atan2(dy, dx) * (180/Math.PI);
			
			if(degree < -70 && degree >  -130) {
				return 0;
			} else if(degree <= -130 && degree >=  -160) {
				return 1;
			} else if(degree < -160 || degree >  160) {
				return 2;
			} else if(degree <= 160 && degree >= 110 ) {
				return 3;
			} else if(degree < 110 && degree >  50) {
				return 4;
			} else if(degree < 20 && degree >  -20) {
				return 6;
			} else if(degree <= -20 && degree >= -70) {
				return 7;
			} else if(degree <= 70 && degree >= 20) {
				return 5;
			} else {
				return 4;
			}
		}
	}
}