package robotkittenearthguardians.entity.mob;

import robotkittenearthguardians.entity.AABB;
import robotkittenearthguardians.entity.mob.ai.WaterBalloonAi;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;
import robotkittenearthguardians.level.GameMaster;
import robotkittenearthguardians.level.Level;

public class WaterBalloon extends Mob{

	private double speed = 3.2;
	private int sightRange = 300;
	WaterBalloonAi ai;

	public WaterBalloon(int x, int y) {
		health = 50.0f;
		points = 5;
		damage = 0.1f;
		sprite = Sprite.waterBalloon;
		this.x = x;
		this.y = y;
		somePosition.setXVector(x);
		somePosition.setYVector(y);
		size.setXVector(14);
		size.setYVector(14);
		mobs.add(this);
		//Creates bound box for WaterBalloon
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
		ai.ai(speed, this);
		
		//If collides with player, will damage it.
		for(int index = 0; index < player.size(); index++) {
			if(hit(player.get(index))) {
				player.get(index).hurt(damage);
				ai.onCollide(player.get(index));
			}
		}
		
		for(int index = 0; index < mobs.size(); index++) {
			if(hit(mobs.get(index)) && !mobs.get(index).equals(this)) {
				ai.onCollide(this, mobs.get(index));
			}
		}
		
		//If off stage mob will fall and lose health
		if(!(Level.isOnStage(somePosition))) {
			falling();
		} else {
			onStage = true;
			falseFall = 0;
		}
		
		//If health is 0 remove mob.
		if(health <= 0) {
			GameMaster.addScore(points);
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
