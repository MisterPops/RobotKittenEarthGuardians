package robotkittenearthguardians.entity.mob;

import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.HealthBar;
import robotkittenearthguardians.entity.particles.MissleAmmo;
import robotkittenearthguardians.entity.projectiles.MainBullet;
import robotkittenearthguardians.entity.projectiles.Missle;
import robotkittenearthguardians.entity.projectiles.Projectiles;
import robotkittenearthguardians.entity.projectiles.ShotgunBullet;
import robotkittenearthguardians.entity.projectiles.WaterBottleBullet;
import robotkittenearthguardians.entity.projectiles.WaterGunMechBullet;
import robotkittenearthguardians.graphics.Sprite;
import robotkittenearthguardians.level.Level;

public abstract class Mob extends Entity {
	
	//Mobs health
	protected float health;
	protected float falseFall;
	//Mobs's front, right, left, and back velocity.
	protected float fVel = 0f, bVel = 0f, rVel = 0f, lVel = 0f;
	//Mobs speed
	protected double speed;
	//If the mob is moving
	protected boolean moving = false;
	//If the mob sees the player.
	protected boolean seePlayer = false;
	//If mob is taking damage
	protected boolean damaged = false;
	//Mob's healthbar object
	protected HealthBar healthBar;
	protected double blowbackX = 0, blowbackY = 0;
	
	/**
	 * Moves the coordinate of the mob on the X/Y plane
	 * @param xa Left and right on plane
	 * @param ya Up and down on plane
	 */
	public void move(double xa, double ya) {
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
			@SuppressWarnings("unused")
			Projectiles mainShot = new MainBullet(x, y, dir);
		} else if(projectile == 1) {
			@SuppressWarnings("unused")
			Projectiles missle = new Missle(x, y);
		} else if(projectile == 2) {
			shotgun(dir);
		} else if(projectile == 3) {
			@SuppressWarnings("unused")
			Projectiles watergunMechBullet = new WaterGunMechBullet(x, y, dir);
		} else {
			@SuppressWarnings("unused")
			Projectiles mainShot = new WaterBottleBullet(x, y, dir);
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
		move(projectile.getVectorX(), projectile.getVectorY());
	}
	
	public void bounceBack(double dx, double dy) {
		blowbackX += dx; blowbackY += dy;
		double distance = Math.sqrt(blowbackX * blowbackX + blowbackY * blowbackY);
		double multiplier = speed / distance;
		double movementX = blowbackX * multiplier, movementY = blowbackY * multiplier;
		blowbackX -= movementX; blowbackY -= movementY;
		move(movementX, movementY);
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
	
	/**
	 * Shoots shotgun bullets at 0 degrees, 2 at (+-)15 degrees, and 2 at (+-) 5 degrees.
	 * @param dir the direction that the bullets will be traveling.
	 */
	private void shotgun(double dir) {
		@SuppressWarnings("unused")
		Projectiles shotgunBullet = new ShotgunBullet(x, y, dir);
		@SuppressWarnings("unused")
		Projectiles shotgunBullet2 = new ShotgunBullet(x, y, dir + (15 * (Math.PI/180)));
		@SuppressWarnings("unused")
		Projectiles shotgunBullet3 = new ShotgunBullet(x, y, dir - (15 * (Math.PI/180)));
		@SuppressWarnings("unused")
		Projectiles shotgunBullet4 = new ShotgunBullet(x, y, dir + (5 * (Math.PI/180)));
		@SuppressWarnings("unused")
		Projectiles shotgunBullet5 = new ShotgunBullet(x, y, dir - (5 * (Math.PI/180)));
	}
	
	/**
	 * Drops missile ammo randomly.
	 */
	protected void dropMissleAmmo() {
		int randomNum = random.nextInt(200);
		if(randomNum > 25 && randomNum < 35) {
			@SuppressWarnings("unused")
			MissleAmmo missleAmmo = new MissleAmmo(x, y);
		}
	}
	
	/**
	 * Controls mob's shooting and when the mob can shoot.
	 */
	protected void updateShooting() {
		
	}
}