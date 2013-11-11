package robotkittenearthguardians.entity.particles;

import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;

public class MainBulletParticle extends Particle{

	private int animFrame = 0;
	private int frameLife = 0;
	
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
		sprite = Sprite.bulletImpact[animFrame];
		frameLife++;
		if(frameLife == 4) {
			animFrame++;
			frameLife = 0;
		}
		if(animFrame == Sprite.bulletImpact.length) remove();
	}
}
