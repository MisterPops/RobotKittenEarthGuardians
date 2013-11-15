package robotkittenearthguardians.entity.mob;

import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;
import robotkittenearthguardians.level.Level;

public class WaterBalloon extends Mob{

	private double speed = 2.5;
	private int sightRange = 300;

	public WaterBalloon(int x, int y) {
		health = 50.0f;
		attSpan = 100;
		sprite = Sprite.waterBalloon;
		this.x = x;
		this.y = y;
		mobs.add(this);
	}
	
	public void update() {
		double[] absPos;
		seePlayer = Ai.seePlayer(x, y, sightRange);
		
		absPos = Ai.simpleAi(x, y, speed);
		if(seePlayer) move((int) absPos[0], (int) absPos[1]);
		
		//If off stage mob will fall and lose health
		System.out.println(+ health);
		if(!(Level.isOnStage(x, y))) {
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
