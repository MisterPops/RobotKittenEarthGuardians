package robotkittenearthguardians.entity.projectiles;

import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.mob.Mob;

public abstract class Projectiles extends Entity{
	
	protected double angle;
	protected double xOrgin, yOrgin;
	protected double x, y;
	protected double vectorX, vectorY;
	protected int range;
	protected int speed;
	protected Mob owner;
	
	
	public Projectiles(int x, int y, double dir) {
		xOrgin = x + 20;
		yOrgin = y + 14;
		this.x = xOrgin;
		this.y = yOrgin;
		angle = dir;
	}
	
	public void update() {
	}
	
	public void render() {
	}
	
	protected void move() {
	}
	
	/**
	 * Returns the projectile's damage output
	 * @return float of projectile's damage
	 */
	public float getDamage() {
		return damage;
	}
	
	/**
	 * Returns the speed of the projectile's
	 * @return int projectile's speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Returns the y vector direction of the projectile
	 * @return x vector direction
	 */
	public double getVectorX() {
		return this.vectorX;
	}
	
	/**
	 * Returns the x vector direction of the projectile
	 * @return x vector direction
	 */
	public double getVectorY() {
		return this.vectorY;
	}
}
