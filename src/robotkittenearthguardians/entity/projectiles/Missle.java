package robotkittenearthguardians.entity.projectiles;

import robotkittenearthguardians.entity.AABB;
import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.mob.Mob;
import robotkittenearthguardians.entity.mob.Player;
import robotkittenearthguardians.entity.mob.ai.MissleAi;
import robotkittenearthguardians.entity.particles.Particle;
import robotkittenearthguardians.entity.particles.SmokeTrail;
import robotkittenearthguardians.graphics.AnimateMachine;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;

public class Missle extends Projectiles{
	
	private MissleAi ai;
	private int timer = 0;
	private Mob target;
	
	public static final int FIRE_RATE = 45;

	public Missle(int x, int y) {
		super(x, y);
		damage = 30;
		speed = 6;
		sprite = Sprite.missle;
		deathParticle = Sprite.mainExplosion;
		this.x = x;
		this.y = y;
		somePosition.setXVector(x);
		somePosition.setYVector(y);
		size.setXVector(5);
		size.setYVector(5);
		boundBox = new AABB(somePosition, size);
		target = findTarget();
		ai = new MissleAi(this, target);
		animation = new AnimateMachine(sprite, x, y);
		projectiles.add(this);
		missileLaunch();
	}

	public void update() {
		somePosition.setXVector((float) this.x);
		somePosition.setYVector((float) this.y);
		boundBox.update(somePosition);
		
		ai.update(somePosition);
		ai.ai(speed, this);
		direction = ai.aiDirection(false, false);
		
		smokeTrail();
		animation.update((int) x, (int) y, direction);
		
		//Checks if missle has hit a non-player mob. If so actions are taken.
		for(int index = 0; index < mobs.size(); index++) {
			if(hit(mobs.get(index)) && !(mobs.get(index) instanceof Player)) {
				mobs.get(index).hurt(damage);
				x += vectorX;
				y += vectorY;
				blowBack();
				dieMissle();
			} else if(ai.getDistance() < 3) {
				remove();
			}
		}
	}
	
	public void render(Screen screen) {
		animation.animateProjectile(screen);
	}
	
	/**
	 * Generates smoketrails from the missle at delayed intervals.
	 */
	public void smokeTrail() {
		if(timer % 6 == 0) {
			int delayedX = this.x, delayedY = this.y;
			if(timer % 4 == 0) {
				Particle smokeTrail = new SmokeTrail(delayedX , delayedY);
				particles.add(smokeTrail);
				timer = 0;
			}
		}
		timer++;
	}
	
	/**
	 * Finds the closest Mob that is not a player from its position.
	 * @return the closest mob from its x/y position.
	 */
	private Mob findTarget() {
		Mob target = Entity.mobs.get(0);
		double shortestDist = 900000001;
		
		for(int index = 0; index < Entity.mobs.size(); index++) {
			if(!(Entity.mobs.get(index) instanceof Player)) {
				int testXCoord = Entity.mobs.get(index).getXCoord(), testYCoord = Entity.mobs.get(index).getXCoord();
				double dx = testXCoord - x, dy = testYCoord - y;
				double testDist = Math.sqrt(dx * dx + dy * dy);
				if(testDist < shortestDist) {
					shortestDist = testDist;
					target = Entity.mobs.get(index);
				} 
			}
		}
		return target;
	}
	
	/**
	 * Damages and blowbacks targets within a certain radius of the missle detonation site.
	 */
	public void blowBack() {
		final int BLOW_RADIUS = 65;
		for(int index = 0; index < mobs.size(); index++) {
			//Distance from mob to detonation point
			int mobCoordX = mobs.get(index).getXCoord(), mobCoordY = mobs.get(index).getYCoord();
			double distance = Math.sqrt(((mobCoordX - x) * (mobCoordX - x)) + ((mobCoordY - y) * (mobCoordY - y)));
			if(distance <= BLOW_RADIUS && !(mobs.get(index) instanceof Player)) {
				//int dx = mobs.get(index).getXCoord() - x, dy = mobs.get(index).getYCoord() - y;
				//mobs.get(index2).bounceBack(dx, dy);
				mobs.get(index).hurt(damage);
			}
		}
	}

}
