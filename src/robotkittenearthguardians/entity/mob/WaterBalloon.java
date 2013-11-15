package robotkittenearthguardians.entity.mob;

import robotkittenearthguardians.entity.AABB;
import robotkittenearthguardians.entity.mob.ai.WaterBalloonAi;
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
		somePosition.x = this.x;
		somePosition.y = this.y;
		size.x = 16;
		size.y = 16;
		mobs.add(this);
		boundBox = new AABB(somePosition, size);
		//Initialize mob's Ai
		ai = new WaterBalloonAi();
	}
	
	public void update() {
		//Updates mob's x/y to somePostion vector
		somePosition.x = this.x;
		somePosition.y = this.y;
		boundBox.update(somePosition);
		
		//Updates ai with mob's x/y vector
		ai.update(somePosition);
		
		//Checks if mob can see the player
		seePlayer = ai.seePlayer(sightRange);
		//Mobs movement patterns
		
		movement = ai.ai(speed, this);
		
		if(seePlayer) move((int) movement.x, (int) movement.y);
		
		//If off stage mob will fall and lose health
		if(!(Level.isOnStage(somePosition))) {
			falling();
		} else {
			onStage = true;
			falseFall = 0;
		}
		
		//If health is 0 remove mob.
		if(health < 0f) {
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
}
