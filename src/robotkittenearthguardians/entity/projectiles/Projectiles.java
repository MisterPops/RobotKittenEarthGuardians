package robotkittenearthguardians.entity.projectiles;

import robotkittenearthguardians.audio.AudioLibrary;
import robotkittenearthguardians.audio.AudioPlayer;
import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.particles.Explosion;
import robotkittenearthguardians.entity.particles.MainBulletParticle;
import robotkittenearthguardians.entity.particles.Particle;
import robotkittenearthguardians.level.Level;

public abstract class Projectiles extends Entity{
	
	protected double angle;
	protected int xOrgin, yOrgin;
	protected double vectorX, vectorY;
	protected int range;
	protected int speed;
	protected AudioPlayer shootSound = new AudioPlayer("/audio/mainBlaster.wav"), 
		bulletHit = new AudioPlayer("/audio/bulletHit.wav"), 
		shotgunSound = new AudioPlayer("/audio/shotgun.wav");
	
	
	public Projectiles(int x, int y, double dir) {
		xOrgin = x + 20;
		yOrgin = y + 14;
		this.x = xOrgin;
		this.y = yOrgin;
		angle = dir;
	}
	
	public Projectiles(int x, int y) {
		xOrgin = x + 20;
		yOrgin = y + 14;
		this.x = xOrgin;
		this.y = yOrgin;
	}
	
	public void update() {
	}
	
	public void render() {
	}
	
	/**
	 * Moves the bullet along the vector path.
	 * Triggers remove boolean to true and death particle
	 * when the bullet is more than it's given range.
	 */
	protected void move() {
		x += vectorX;
		y += vectorY;
		
		if(Distance() > range && Level.isOnStage(somePosition)) {
			die();
		} else if(!(Level.isOnStage(somePosition))) {
			if(Distance() > range + 300) {
				remove();
			}
		}
	}
	
	/**
	 * Returns the distance of the projectile from the origin to the point it is currently at.
	 * @return double distance 
	 */
	protected double Distance() {
		double distance;
		distance = Math.abs(Math.sqrt((xOrgin - x) * (xOrgin - x) + (yOrgin - y) * (yOrgin - y))); 
		return distance;
	}
	
	protected void die() {
		Particle mainShotParticle = new MainBulletParticle(deathParticle, (int)x , (int)y);
		particles.add(mainShotParticle);
		remove();
	}
	
	protected void dieMissle() {
		Particle mainExplosion = new Explosion(deathParticle, (int)x , (int)y);
		particles.add(mainExplosion);
		remove();
	}
	
	protected static void missileLaunch() {
		AudioLibrary.missileLaunch.play();
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
