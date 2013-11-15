package robotkittenearthguardians.entity.projectiles;

import robotkittenearthguardians.entity.particles.MainBulletParticle;
import robotkittenearthguardians.entity.particles.Particle;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;
import robotkittenearthguardians.input.Mouse;
import robotkittenearthguardians.level.Level;

public class MainBullet extends Projectiles{
	
	public static final int FIRE_RATE = 13;
	
	public MainBullet(int x, int y, double dir, double mouseX, double mouseY) {
		super(x, y, dir);
		damage = 25;
		range = (int) Mouse.mouseDistance() + random.nextInt(50);
		//range = random.nextInt(50) + 250;
		speed = 15;
		sprite = Sprite.mainBullet;
		
		vectorX = speed * Math.cos(angle);
		vectorY = speed * Math.sin(angle);
	}
	
	/**
	 * Updates the MainBullet clearing the dead MainBullet's
	 * and moving the live MainBullets along their path.
	 */
	public void update() {
		somePosition.x = (float) this.x;
		somePosition.y = (float) this.y;
		move();
	}
	
	/**
	 * Renders the MainBullet sending it's origin x/y position
	 * and its sprite to the screen
	 */
	public void render(Screen screen) {
		screen.renderProjectile(x, y, this);
	}
	
	/**
	 * Moves the MainBullet along the vector path.
	 * Triggers remove boolean to true and death particle
	 * when the bullet is more than it's given range.
	 */
	protected void move() {
		x += vectorX;
		y += vectorY;
		
		if(Distance() > range && Level.isOnStage(somePosition)) {
			Particle mainShotParticle = new MainBulletParticle(x, y);
			particles.add(mainShotParticle);
			remove();
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
	private double Distance() {
		double distance;
		distance = Math.abs(Math.sqrt((xOrgin - x) * (xOrgin - x) + (yOrgin - y) * (yOrgin - y))); 
		return distance;
	}

	/**
	 * Returns the MainBullet's movement speed
	 */
	public int getSpeed() {
		return speed;
	}
}
