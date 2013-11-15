package robotkittenearthguardians.entity.projectiles;

import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.graphics.Sprite;

public abstract class Projectiles extends Entity{
	
	protected double angle;
	protected Sprite sprite;
	protected double xOrgin, yOrgin;
	protected double x, y;
	protected double vectorX, vectorY;
	protected int range;
	protected int damage;
	protected int speed;
	
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
	
	public int getSpeed() {
		return speed;
	}
	
	protected void move() {
	}
	
	/**
	 * Gets the sprite for a projectile objecty
	 * @return projectile objects sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}
}
