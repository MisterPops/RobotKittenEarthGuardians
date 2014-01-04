package robotkittenearthguardians.entity.mob.ai;

import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.mob.Mob;
import robotkittenearthguardians.entity.mob.Player;

public class WaterBottlePackAi extends Ai{
	
	public WaterBottlePackAi(Mob mob) {
		super(mob);
		this.mob = mob;
	}

	public void ai(double speed, Mob mob) {
		for(int index = 0; index < mobs.size(); index++) {
				movement.setVector(wander(speed));
		}
		mob.move((int) movement.getXVector(), (int) movement.getYVector()); 
		
		int randomizer = random.nextInt(6);
		if(timer > 90 && randomizer != 5) {
			fire();
			timer = 0;
		}
		timer++;
	}
	
	public void onCollide(Entity mainEntity, Entity entity) {
		if(!(entity instanceof Player)) {
			unStack(mainEntity, entity);
		} else if(entity instanceof Player) {
			
		}
	}

}
