package robotkittenearthguardians.entity.mob.ai;

import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.mob.Mob;
import robotkittenearthguardians.entity.mob.WaterBalloon;

public class WaterBalloonAi extends Ai{
	
	public void ai(double speed, Mob mob) {
		for(int index = 0; index < mobs.size(); index++) {
			if(mob.getSeePlayer()) {
				movement.setVector(simpleAi(speed));
			} else {
				movement.setXVector(0);
				movement.setYVector(0);
			}
		}
		mob.move((int) movement.getXVector(), (int) movement.getYVector()); 
	}
	
	public void onCollide(Entity mainEntity, Entity entity) {
		if(entity instanceof WaterBalloon) {
			unStack(mainEntity, entity);
		}
	}

}
