package robotkittenearthguardians.entity.mob.ai;

import robotkittenearthguardians.entity.mob.Mob;
import robotkittenearthguardians.entity.projectiles.Projectiles;

public class MissleAi extends Ai{

	public MissleAi(Projectiles projectile, Mob target) {
		super(projectile, target);
		this.projectile = projectile;
		this.target = target;
	}

	public void ai(double speed, Projectiles projectile) {
		movement.setVector(hunt(speed));
		projectile.move((int) movement.getXVector(), (int) movement.getYVector()); 
	}
}
