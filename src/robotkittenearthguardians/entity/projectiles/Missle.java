package robotkittenearthguardians.entity.projectiles;

import robotkittenearthguardians.entity.AABB;
import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.mob.Mob;
import robotkittenearthguardians.entity.mob.Player;
import robotkittenearthguardians.entity.mob.ai.MissleAi;
import robotkittenearthguardians.graphics.AnimateMachine;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;

public class Missle extends Projectiles{
	
	private MissleAi ai;
	private Mob target;

	public Missle(int x, int y) {
		super(x, y);
		damage = 10;
		speed = 3;
		sprite = Sprite.missle;
		deathParticle = Sprite.mainExplosion;
		size.setXVector(5);
		size.setYVector(5);
		boundBox = new AABB(somePosition, size);
		target = findTarget();
		ai = new MissleAi(this, target);
		animation = new AnimateMachine(sprite, x, y);
	}

	public void update() {
		somePosition.setXVector((float) this.x);
		somePosition.setYVector((float) this.y);
		boundBox.update(somePosition);
		
		ai.update(somePosition);
		ai.ai(speed, this);
		direction = ai.aiDirection(false);
		
		animation.update((int) x, (int) y, direction);
	}
	
	public void render(Screen screen) {
		animation.animateProjectile(screen);
	}
	
	/**
	 * Finds the closest Mob that is not a player from its position.
	 * @return the closest mob from its x/y position.
	 */
	private Mob findTarget() {
		Mob target = Entity.mobs.get(0);
		double shortestDist = Math.sqrt((9001 * 9001 - x * x) + (9001 * 9001 - y * y));
		
		for(int index = 0; index < Entity.mobs.size(); index++) {
			if(!(Entity.mobs.get(index) instanceof Player)) {
				int testXCoord = Entity.mobs.get(index).getXCoord(), testYCoord = Entity.mobs.get(index).getXCoord();
				double testDist = Math.sqrt((testXCoord * testXCoord - x * x) + (testYCoord * testYCoord - y * y));
				if(testDist < shortestDist) {
					shortestDist = testDist;
					target = Entity.mobs.get(index);
				}
			}
		}
		return target;
	}

}
