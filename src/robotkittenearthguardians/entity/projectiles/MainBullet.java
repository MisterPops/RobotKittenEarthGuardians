package robotkittenearthguardians.entity.projectiles;

import robotkittenearthguardians.entity.AABB;
import robotkittenearthguardians.entity.mob.Player;
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
		damage = 5;
		range = (int) Mouse.mouseDistance() + random.nextInt(50);
		speed = 15;
		sprite = Sprite.mainBullet;
		vectorX = speed * Math.cos(angle);
		vectorY = speed * Math.sin(angle);
		size.setXVector(5);
		size.setYVector(5);
		boundBox = new AABB(somePosition, size);
	}
	
	/**
	 * Updates the MainBullet clearing the dead MainBullet's
	 * and moving the live MainBullets along their path.
	 */
	public void update() {
		move();
		somePosition.setXVector((float) this.x);
		somePosition.setYVector((float) this.y);
		boundBox.update(somePosition);
		
		for(int index = 0; index < mobs.size(); index++) {
			if(hit(mobs.get(index)) && !(mobs.get(index) instanceof Player)) {
				mobs.get(index).hurt(damage);
				mobs.get(index).bounceBack(this);
			}
		}
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
		
		if(Distance() > range && Level.isOnStage(somePosition) || collided) {
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
