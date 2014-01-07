package robotkittenearthguardians.entity.particles;

import robotkittenearthguardians.entity.AABB;
import robotkittenearthguardians.entity.mob.ai.Ai;
import robotkittenearthguardians.graphics.AnimateMachine;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;
import robotkittenearthguardians.util.Vector2F;
import robotkittenearthguardians.util.Vector2I;

public class MissleAmmo extends Particle{
	
	private Vector2F velocity = new Vector2F();
	private int life = 0;

	public MissleAmmo(int x, int y) {
		super(x, y);
		points = 5;
		this.x = x;
		this.y = y;
		sprite = Sprite.missleAmmo;
		animation = new AnimateMachine(sprite, x, y);
		somePosition.setXVector(x);
		somePosition.setYVector(y);
		size.setXVector(12);
		size.setYVector(12);
		boundBox = new AABB(somePosition, size);
		particles.add(this);
	}

	public void update() {
		gravityWell();
		
		somePosition.setXVector((float) this.x);
		somePosition.setYVector((float) this.y);
		boundBox.update(somePosition);
		animation.update((int) x, (int) y, direction);
		
		life++;
		if(life > 1500) {
			remove();
		}
		
	}
	
	public void render(Screen screen) {
		animation.animateProjectile(screen);
	}
	
	private void gravityWell() {
		Vector2I player = new Vector2I(Ai.getPlayerX(), Ai.getPlayerY());
		float dx = player.getXVector() - x, dy = player.getYVector() - y;
		if(dx * dx + dy * dy < 128.0f * 128.0f) {
			float dist = (float) Math.sqrt(dx * dx + dy * dy);
			dx /= dist;
			dy /= dist;
			velocity.addToXVector(dx * .1f * 54);
			velocity.addToYVector(dy * .1f * 60);
		}
		velocity.subtractXVector(velocity.getXVector() * .1f * 2);
		velocity.subtractYVector(velocity.getYVector() * .1f * 2);
		this.x += velocity.getXVector() * .1f;
		this.y += velocity.getYVector() * .1f;
	}
}
