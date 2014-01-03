package robotkittenearthguardians.entity.particles;

import robotkittenearthguardians.graphics.AnimateMachine;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;

public class Explosion extends Particle{

	public Explosion(int x, int y) {
		super(x, y);
		sprite = Sprite.mainExplosion;
		animation = new AnimateMachine(sprite, x, y);
	}
	
	public Explosion(Sprite[] sprite, int x, int y) {
		super(sprite, x, y);
		this.sprite = sprite;
		animation = new AnimateMachine(sprite, x, y);
	}
	
	public void update() {
	}
	
	public void render(Screen screen) {
		//Checks if it's removed was re-looping the animation
		//before it was fully removed
		if(!removed) {
			if(!animation.animateParticle(screen)) {
				remove();
			}
		}
	}
}
