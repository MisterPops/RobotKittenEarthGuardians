package robotkittenearthguardians.entity.projectiles;

import robotkittenearthguardians.entity.AABB;
import robotkittenearthguardians.entity.mob.Player;
import robotkittenearthguardians.graphics.AnimateMachine;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;
import robotkittenearthguardians.level.Level;

public class WaterGunMechBullet extends Projectiles{

	private int scatter = 0;
	private boolean dead = false;
	
	public WaterGunMechBullet(int x, int y, double dir) {
		super(x, y, dir);
		damage = 7;
		range = random.nextInt(30) + 500;
		speed = 6;
		sprite = Sprite.watergunMechBullet;
		deathParticle = Sprite.bulletImpact;
		vectorX = speed * Math.cos(angle);
		vectorY = speed * Math.sin(angle);
		size.setXVector(7);
		size.setYVector(7);
		boundBox = new AABB(somePosition, size);
		animation = new AnimateMachine(sprite, x, y);
		projectiles.add(this);
	}
	
	/**
	 * Updates the MainBullet clearing the dead MainBullet's
	 * and moving the live MainBullets along their path.
	 */
	public void update() {
		somePosition.setXVector((float) this.x);
		somePosition.setYVector((float) this.y);
		boundBox.update(somePosition);
		
		if(!dead) {
			for(int index = 0; index < mobs.size(); index++) {
				if(hit(mobs.get(index)) && mobs.get(index) instanceof Player) {
					mobs.get(index).hurt(damage);
					mobs.get(index).bounceBack(this);
					dead = dieMechBullet();
				}
			}
			move();
		} else dieMechBullet();
		
		animation.update((int) x, (int) y, direction);
	}
	
	/**
	 * Renders the MainBullet sending it's origin x/y position
	 * and its sprite to the screen
	 */
	public void render(Screen screen) {
		if(!dead) animation.animateProjectile(screen);
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
			dead = dieMechBullet();
		} else if(!(Level.isOnStage(somePosition))) {
			dead = dieMechBullet();
		}
	}
	
	protected boolean dieMechBullet() {
		if(scatter != 180) {
			@SuppressWarnings("unused")
			Projectiles mechScatterBullet1 = new WaterBottleBullet(x, y, scatter * (Math.PI/180) * 10);
			
			//double inverse = -1 * (scatter * (Math.PI/180));
			//if(inverse != 0) {
				//@SuppressWarnings("unused")
				//Projectiles mechScatterBullet2 = new WaterBottleBullet(x, y, inverse * 10);
			//}
			scatter += 15;
			return true;
		} else {
			remove();
			return true;
		}
	}
}