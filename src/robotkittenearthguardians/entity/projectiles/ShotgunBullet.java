package robotkittenearthguardians.entity.projectiles;

import robotkittenearthguardians.entity.AABB;
import robotkittenearthguardians.entity.mob.Player;
import robotkittenearthguardians.graphics.AnimateMachine;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;

public class ShotgunBullet extends Projectiles{
	
	public static final int FIRE_RATE = 150;

	public ShotgunBullet(int x, int y, double dir) {
		super(x, y, dir);
		damage = 12;
		range = 300;
		speed = 15;
		sprite = Sprite.shotgunBullet;
		deathParticle = Sprite.bulletImpact;
		vectorX = speed * Math.cos(angle);
		vectorY = speed * Math.sin(angle);
		size.setXVector(7);
		size.setYVector(7);
		boundBox = new AABB(somePosition, size);
		animation = new AnimateMachine(sprite, x, y);
		projectiles.add(this);
		shotgunSound.volume(-15f);
		shotgunSound.play();
	}

	/**
	 * Updates the shotgun bullet clearing the dead shotgun bullet
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
				x += vectorX;
				y += vectorY;
				bulletHit.play();
				die();
			}
		}
		
		animation.update((int) x, (int) y, direction);
	}
	
	/**
	 * Renders the shotgun bullet sending it's origin x/y position
	 * and its sprite to the screen
	 */
	public void render(Screen screen) {
		animation.animateProjectile(screen);
	}
}
