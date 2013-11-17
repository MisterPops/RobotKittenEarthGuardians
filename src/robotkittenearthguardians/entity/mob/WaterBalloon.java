package robotkittenearthguardians.entity.mob;

import robotkittenearthguardians.entity.AABB;
import robotkittenearthguardians.entity.CollisionLibrary;
import robotkittenearthguardians.entity.mob.ai.WaterBalloonAi;
import robotkittenearthguardians.entity.projectiles.Projectiles;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;
import robotkittenearthguardians.level.Level;

public class WaterBalloon extends Mob{

	private double speed = 3.2;
	private int sightRange = 300;
	WaterBalloonAi ai;

	public WaterBalloon(int x, int y) {
		health = 50.0f;
		sprite = Sprite.waterBalloon;
		this.x = x;
		this.y = y;
		somePosition.setXVector(x);
		somePosition.setYVector(y);
		size.setXVector(14);
		size.setYVector(14);
		mobs.add(this);
		boundBox = new AABB(somePosition, size);
		//Initialize mob's Ai
		ai = new WaterBalloonAi();
	}
	
	public void update() {
		//Updates mob's x/y to somePostion vector
		somePosition.setXVector(this.x);
		somePosition.setYVector(this.y);
		boundBox.update(somePosition);
		
		//Updates ai with mob's x/y vector
		ai.update(somePosition);
		
		//Checks if mob can see the player
		seePlayer = ai.seePlayer(sightRange);
		
		//Mobs movement patterns
		movement = ai.ai(speed, this);
		move((int) movement.getXVector(), (int) movement.getYVector());
		
		//If off stage mob will fall and lose health
		if(!(Level.isOnStage(somePosition))) {
			falling();
		} else {
			onStage = true;
			falseFall = 0;
			
			//Checks if mob is hit with projectile
			for(int index = 0; index < projectiles.size(); index++) {
				if(hit(projectiles.get(index))) {
					bounceBack(index);
					health -= projectiles.get(index).getDamage();
				}
			}
		}
		
		//If health is 0 remove mob.
		if(health <= 0) {
			remove();
		}
	}
	
	/**
	 * Loops through all the frames in the sprite array and sends them
	 * to the screen class accordingly.
	 */
	public void render(Screen screen) {
		if(frame == 8) frame = 0;
		screen.renderPlayer(x, y, falseFall, sprite[frame], false);
		if(frameLife == 15) {
			frame++;
			frameLife = 0;
		}
		frameLife++;
	}
	
	/**
	 * Checks if a projectile's bounding box collides with the mob's
	 * bounding box
	 * @param projectile the projectile to be compared
	 * @return true if collides, false otherwise
	 */
	public boolean hit(Projectiles projectile) {
		if(CollisionLibrary.testAABBAABB(boundBox, projectile.getAABB())) {
			projectile.isCollided();
			return true;
		}
		else return false;
	}
}
