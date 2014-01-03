package robotkittenearthguardians.entity.particles;

import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.graphics.Sprite;

public abstract class Particle extends Entity{
	
	protected int xOrigin, yOrigin;
	
	public Particle(int x, int y) {
		xOrigin = x;
		yOrigin = y;
	}
	
	public Particle(Sprite[] sprite, int x, int y) {
		this.sprite = sprite;
		xOrigin = x;
		yOrigin = y;
	}

	public void update() {	
	}
	
	public void render() {
	}
}
