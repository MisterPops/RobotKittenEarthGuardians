package robotkittenearthguardians.entity.mob.ai;

import robotkittenearthguardians.entity.Vector2F;
import robotkittenearthguardians.entity.mob.Mob;

public class WaterBalloonAi extends Ai{
	
	public Vector2F ai(double speed, Mob mob) {
		for(int index = 0; index < mobs.size(); index++) {
			if(mob.getSeePlayer()) {
				movement.setVector(simpleAi(speed));
			} else {
				movement.setXVector(0);
				movement.setYVector(0);
			}
			
			/*if(CollisionLibrary.testAABBAABB(mob.getAABB(), mobs.get(index).getAABB())){
				System.out.println(" " + !mobs.get(index).equals(mob));
				if(!(mobs.get(index).equals(mob))) {
					movement = unstack(speed, mobs.get(index));
				}
			}*/
		}
		return movement;
	}

}
