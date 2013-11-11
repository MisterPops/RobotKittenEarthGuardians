package robotkittenearthguardians.entity.particles;

import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.graphics.Sprite;

public abstract class Particle extends Entity{
	
	protected double xOrigin, yOrigin;
	protected Sprite sprite;
	
	public Particle(double x, double y) {
		xOrigin = x;
		yOrigin = y;
	}

	public void update() {	
	}
	
	public void render() {
	}
	
	public Sprite getSprite() {
		return sprite;
	}
}
