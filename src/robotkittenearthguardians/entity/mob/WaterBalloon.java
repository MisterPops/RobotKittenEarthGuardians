package robotkittenearthguardians.entity.mob;

import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;

public class WaterBalloon extends Mob{
	
	//For testing
	int frame = 0;
	int frameLife = 0;

	public WaterBalloon(int x, int y) {
		health = 100.0f;
		this.x = x;
		this.y = y;
		mobs.add(this);
	}
	
	public void update() {
		
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
}
