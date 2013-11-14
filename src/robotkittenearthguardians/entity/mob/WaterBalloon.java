package robotkittenearthguardians.entity.mob;

import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;
import robotkittenearthguardians.level.Level;

public class WaterBalloon extends Mob{

	private double speed = 2.5;
	private double dx, dy;
	private double distance;
	private double multiplier;

	public WaterBalloon(int x, int y) {
		health = 100.0f;
		this.x = x;
		this.y = y;
		mobs.add(this);
	}
	
	public void update() {
		seePlayer();
		
		playerX = Level.getPlayerX();
		playerY = Level.getPlayerY();
		dx = playerX - x; dy = playerY - y;
		distance = Math.sqrt(dx * dx + dy * dy);
		multiplier = speed / distance;
		
		double xa = dx * multiplier, ya = dy * multiplier;
		System.out.println(+ distance);
		if(seePlayer) move((int) xa, (int) ya);
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
	
	public void seePlayer() {
		if(distance < 300) {
			seePlayer = true;
		} else {
			seePlayer = false;
		}
	}
	
	//Figure out how to implement wandering
	public void wander() {
	}
}
