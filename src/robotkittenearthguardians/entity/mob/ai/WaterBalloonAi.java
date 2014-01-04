package robotkittenearthguardians.entity.mob.ai;

import robotkittenearthguardians.entity.mob.Mob;

public class WaterBalloonAi extends Ai{
	
	public WaterBalloonAi(Mob mob) {
		super(mob);
		this.mob = mob;
	}

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

}
