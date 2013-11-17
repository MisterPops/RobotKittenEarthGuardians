package robotkittenearthguardians.entity.mob;

import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.Vector2F;
import robotkittenearthguardians.entity.projectiles.MainBullet;
import robotkittenearthguardians.entity.projectiles.Projectiles;
import robotkittenearthguardians.graphics.Sprite;

public abstract class Mob extends Entity {
	
	//Mobs sprite array
	Sprite[] sprite;
	//Mobs health
	protected float health;
	protected float falseFall;
	//Mobs's front, right, left, and back velocity.
	protected float fVel = 0f, bVel = 0f, rVel = 0f, lVel = 0f;
	//If the mob is moving
	protected boolean moving = false;
	//If the mob sees the player.
	protected boolean seePlayer = false;
	//Vector to hold mobs movement speed
	protected Vector2F movement = new Vector2F();
	
	/**
	 * Moves the coordinate of the mob on the X/Y plane
	 * @param xa Left and right on plane
	 * @param ya Up and down on plane
	 */
	public void move(int xa, int ya) {
		//-1, 0, or 1
		x += xa;
		y += ya;
	}
	
	/**
	 * Creates a new projectile object with the x/y, dir, and mouse x/y and adds it to
	 * the projectile arrayList
	 * @param x x pos projectile starts from
	 * @param y y pos projectile starts from
	 * @param dir dir in radians
	 * @param mouseX x pos where mouse is pointed
	 * @param mouseY y pos where mouse is pointed
	 */
	public void shoot(int x, int y, double dir, double mouseX, double mouseY) {
		Projectiles mainShot = new MainBullet(x, y, dir, mouseX, mouseY);
		projectiles.add(mainShot);
	}
	
	public void update() {
	}
	
	public void render() {
	}
	
	/**
	 * Returns if a mob is colliding with something.
	 * @return true if mob is colliding with something, false otherwise;
	 */
	private boolean collision() {
		return collided;
	}
	
	/**
	 * Decereases mob's health and sets the mob in a fall like visual.
	 */
	protected void falling() {
		health -= 0.5f;
		onStage = false;
		falseFall += 3.5f;
	}
	
	/**
	 * Returns if the mob sees the palyer
	 * @return true of the mob sees the player, false otherwise.
	 */
	public boolean getSeePlayer() {
		return seePlayer;
	}
	
	/**
	 * Bounces back the mob in the direction the projectile came from
	 * @param projectile the projectile that will be transfering it's force
	 */
	public void bounceBack(Projectiles projectile) {
		move((int) projectile.getVectorX(), (int) projectile.getVectorY());
	}
	
	/**
	 * Takes damage amount from the health of the mob.
	 * @param damage the amount of health you want to remove from the mob
	 */
	public void hurt(float damage) {
		health -= damage / 2;
	}
}
