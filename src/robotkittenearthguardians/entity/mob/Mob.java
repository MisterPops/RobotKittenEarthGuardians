package robotkittenearthguardians.entity.mob;

import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.HealthBar;
import robotkittenearthguardians.entity.particles.Explosion;
import robotkittenearthguardians.entity.particles.Particle;
import robotkittenearthguardians.entity.projectiles.MainBullet;
import robotkittenearthguardians.entity.projectiles.Missle;
import robotkittenearthguardians.entity.projectiles.Projectiles;
import robotkittenearthguardians.entity.projectiles.WaterBottleBullet;
import robotkittenearthguardians.graphics.Sprite;
import robotkittenearthguardians.level.Level;

public abstract class Mob extends Entity {
	
	//Mobs health
	protected float health;
	protected float falseFall;
	//Mobs's front, right, left, and back velocity.
	protected float fVel = 0f, bVel = 0f, rVel = 0f, lVel = 0f;
	//If the mob is moving
	protected boolean moving = false;
	//If the mob sees the player.
	protected boolean seePlayer = false;
	//If mob is taking damage
	protected boolean damaged = false;
	//Mob's healthbar object
	protected HealthBar healthBar;
	
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
	
	public void update() {
	}
	
	public void render() {
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
	public void shoot(int x, int y, double dir, int projectile) {
		if(projectile == 0) {
			Projectiles mainShot = new MainBullet(x, y, dir);
			projectiles.add(mainShot);
		} else if(projectile == 1) {
			Projectiles missle = new Missle(x, y);
			projectiles.add(missle);
		} else {
			Projectiles mainShot = new WaterBottleBullet(x, y, dir);
			projectiles.add(mainShot);
		}
	}
	
	/**
	 * Checks if mob is on stage.
	 */
	public void stageUpdates() {
		//Whether the player is on the stage or not
		if(!(Level.isOnStage(somePosition))) {
			falling();
		} else {
			onStage = true;
			falseFall = 0;
			updateShooting();
		}
	}
	
	/**
	 * Decereases mob's health and sets the mob in a fall like visual.
	 */
	protected void falling() {
		health -= 0.5f;
		onStage = false;
		falseFall += 3.5f;
		damaged = true;
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
		damaged = true;
		health -= damage;
	}
	
	/**
	 * Returns the mobs healthbar
	 * @return HealthBar object
	 */
	public HealthBar getHealthBar() {
		return healthBar;
	}
	
	public Sprite[] getSprite() {
		return sprite;
	}
	
	public void mainExplode() {
		Particle mainExplosion = new Explosion(x , y);
		particles.add(mainExplosion);
	}
	
	public void mainExplode(Sprite[] particle) {
		Particle mainExplosion = new Explosion(particle, x , y);
		particles.add(mainExplosion);
	}
	
	/**
	 * Controls mob's shooting and when the mob can shoot.
	 */
	protected void updateShooting() {
		
	}
}