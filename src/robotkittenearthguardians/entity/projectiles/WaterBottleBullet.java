package robotkittenearthguardians.entity.projectiles;

import robotkittenearthguardians.entity.AABB;
import robotkittenearthguardians.entity.mob.Player;
import robotkittenearthguardians.graphics.AnimateMachine;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;

public class WaterBottleBullet extends Projectiles{
	
public static final int FIRE_RATE = 18;
	
	public WaterBottleBullet(int x, int y, double dir) {
		super(x, y, dir);
		damage = 10;
		range = random.nextInt(30) + 1000;
		speed = 5;
		sprite = Sprite.enemyBullet;
		deathParticle = Sprite.bulletImpact;
		vectorX = speed * Math.cos(angle);
		vectorY = speed * Math.sin(angle);
		size.setXVector(5);
		size.setYVector(5);
		boundBox = new AABB(somePosition, size);
		animation = new AnimateMachine(sprite, x, y);
		projectiles.add(this);
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
			if(hit(mobs.get(index)) && mobs.get(index) instanceof Player) {
				mobs.get(index).hurt(damage);
				mobs.get(index).bounceBack(this);
				x += vectorX;
				y += vectorY;
				bulletHit.play();
				die();
			}
		}
		
		animation.update((int) x, (int) y, direction);
	}
	
	/**
	 * Renders the MainBullet sending it's origin x/y position
	 * and its sprite to the screen
	 */
	public void render(Screen screen) {
		animation.animateProjectile(screen);
	}
}