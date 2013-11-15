package robotkittenearthguardians.entity.mob.ai;

import robotkittenearthguardians.entity.CollisionLibrary;
import robotkittenearthguardians.entity.Vector2Float;
import robotkittenearthguardians.entity.mob.Mob;

public class WaterBalloonAi extends Ai{
	
	public Vector2Float ai(double speed, Mob mob) {
		for(int index = 0; index < mobs.size(); index++) {
			if(!(mobs.get(index).equals(mob))) {
				System.out.println(" " + CollisionLibrary.testAABBAABB(mob.getAABB(), mobs.get(index).getAABB()));
			}
		}
		
		if(mob.getSeePlayer()) {
			movement = simpleAi(speed);
		} else {
			movement.x = 0;
			movement.y = 0;
		}
		return movement;
	}

}
