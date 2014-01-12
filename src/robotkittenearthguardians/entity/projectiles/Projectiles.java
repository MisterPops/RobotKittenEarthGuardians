package robotkittenearthguardians.entity.projectiles;

import robotkittenearthguardians.audio.AudioLibrary;
import robotkittenearthguardians.audio.AudioPlayer;
import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.particles.Explosion;
import robotkittenearthguardians.entity.particles.MainBulletParticle;
import robotkittenearthguardians.entity.particles.Particle;

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
	
	protected void move() {
	}
	
	/**
	 * Moves the coordinate of the projectile on the X/Y plane
	 * @param xa Left and right on plane
	 * @param ya Up and down on plane
	 */
	public void move(int xa, int ya) {
		//-1, 0, or 1
		x += xa;
		y += ya;
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
	
	protected void dieMechBullet() {
		int scatter = 0;
		while(scatter != 180) {
			@SuppressWarnings("unused")
			Projectiles mechScatterBullet1 = new WaterBottleBullet(x, y, scatter * (Math.PI/180) * 10);
			
			double inverse = -1 * (scatter * (Math.PI/180));
			if(inverse != 0) {
				@SuppressWarnings("unused")
				Projectiles mechScatterBullet2 = new WaterBottleBullet(x, y, inverse * 10);
			}
			scatter += 10;
		}
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
