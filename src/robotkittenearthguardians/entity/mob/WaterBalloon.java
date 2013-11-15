package robotkittenearthguardians.entity.mob;

import robotkittenearthguardians.entity.AABB;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;
import robotkittenearthguardians.level.Level;

public class WaterBalloon extends Mob{

	private double speed = 2.5;
	private int sightRange = 300;
	Ai ai;

	public WaterBalloon(int x, int y) {
		health = 50.0f;
		sprite = Sprite.waterBalloon;
		this.x = x;
		this.y = y;
		mobs.add(this);
		//Mobs bounding box
		boundBox = new AABB(32, 32);
		//Initialize mob's Ai
		ai = new Ai();
	}
	
	public void update() {
		//Updates mob's x/y to somePostion vector
		somePosition.x = this.x;
		somePosition.y = this.y;
		//Updates bounding box with mob's x/y vector
		boundBox.update(somePosition);
		//Updates ai with mob's x/y vector
		ai.update(somePosition);
		
		//Checks if mob can see the player
		seePlayer = ai.seePlayer(sightRange);
		//Basic movement ai
		movement = ai.simpleAi(speed);
		
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
