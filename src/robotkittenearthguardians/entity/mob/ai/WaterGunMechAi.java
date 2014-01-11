package robotkittenearthguardians.entity.mob.ai;

import robotkittenearthguardians.entity.mob.Mob;

public class WaterGunMechAi extends Ai{

	public WaterGunMechAi(Mob mob) {
		super(mob);
		this.mob = mob;
	}

	public void ai(double speed, Mob mob) {
		movement.setVector(wander(speed));
		mob.move((int) movement.getXVector(), (int) movement.getYVector()); 
		
		int randomizer = random.nextInt(6);	
		if(timer > 300 && randomizer != 5) {
			fire(3);
			if(randomizer != 3 && randomizer != 1) {
				dropExplodingBottle();
			}
			timer = 0;
		}
		timer++;
	}


}
