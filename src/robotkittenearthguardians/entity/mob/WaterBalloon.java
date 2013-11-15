package robotkittenearthguardians.entity.mob;

import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;

public class WaterBalloon extends Mob{

	private double speed = 2.5;
	private int sightRange = 300;

	public WaterBalloon(int x, int y) {
		health = 100.0f;
		this.x = x;
		this.y = y;
		mobs.add(this);
	}
	
	public void update() {
		seePlayer = Ai.seePlayer(x, y, sightRange);
		
		double[] absPos = Ai.simpleAi(x, y, speed);
		if(seePlayer) move((int) absPos[0], (int) absPos[1]);
	}
	
	public void render(Screen screen) {
		if(frame == 8) frame = 0;
		screen.renderPlayer(x, y, falseFall, Sprite.waterBalloon[frame], false);
		if(frameLife == 15) {
			frame++;
			frameLife = 0;
		}
		frameLife++;
	}
	
	//Figure out how to implement wandering
	public void wander() {
	}
}
