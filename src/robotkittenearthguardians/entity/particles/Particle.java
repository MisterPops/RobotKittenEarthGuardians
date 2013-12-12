package robotkittenearthguardians.entity.particles;

import robotkittenearthguardians.entity.Entity;

public abstract class Particle extends Entity{
	
	protected int xOrigin, yOrigin;
	
	public Particle(int x, int y) {
		xOrigin = x;
		yOrigin = y;
	}

	public void update() {	
	}
	
	public void render() {
	}
}
