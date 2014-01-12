package robotkittenearthguardians.entity.mob.ai;

import robotkittenearthguardians.entity.mob.Mob;

public class WaterBottlePackAi extends Ai{
	
	public WaterBottlePackAi(Mob mob) {
		super(mob);
		this.mob = mob;
	}

	public void ai(double speed, Mob mob) {
		movement.setVector(wander(speed));
		mob.move((int) movement.getXVector(), (int) movement.getYVector()); 
		
		int randomizer = random.nextInt(6);
		if(timer > 120 && randomizer != 5) {
			fire(-1);
			timer = 0;
		}
		timer++;
	}

}
