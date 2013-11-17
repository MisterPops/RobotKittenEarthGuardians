package robotkittenearthguardians.entity.particles;

import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;

public class MainBulletParticle extends Particle{
	
	public MainBulletParticle(double x, double y) {
		super(x, y);
		sprite = Sprite.bulletImpact[0];
	}

	public void update() {
		animate();
		//clearEntities();
	}
	
	public void render(Screen screen) {
		screen.renderParticles(xOrigin, yOrigin, this);
	}
	
	private void animate() {
		sprite = Sprite.bulletImpact[frame];
		frameLife++;
		if(frameLife > 4) {
			frame++;
			frameLife = 0;
		}
		if(frame > Sprite.bulletImpact.length - 1) remove();
	}
}
