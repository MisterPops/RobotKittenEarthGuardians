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
	
	public void onCollide(Mob mob, Entity entity) {
		if(entity instanceof WaterBalloon) {
			float radius = size.getXVector() + entity.getSizeVector().getXVector();
			
			double dx = (entity.getXCoord() - mob.getXCoord());
			double dy = (entity.getYCoord() - mob.getYCoord());
			double angle = Math.atan2(dy, dx);
			double scale = 1.0f - Math.sqrt(dx * dx + dy * dy) / radius + 1;
			movement.addToXVector(-(float) (Math.cos(angle) * scale * 2));
			movement.addToYVector(-(float) (Math.sin(angle) * scale * 2));
			mob.move(-(int) (Math.cos(angle) * scale * 2), -(int) (Math.sin(angle) * scale * 2));
			System.out.println(-(float) (Math.cos(angle) * scale * 2));
			
			entity.getMovementVector().addToXVector((float) (Math.cos(angle) * scale * 2));
			entity.getMovementVector().addToYVector((float) (Math.sin(angle) * scale * 2));
			entity.move((int) (Math.cos(angle) * scale * 2), (int) (Math.sin(angle) * scale * 2));
		}
	}

}
