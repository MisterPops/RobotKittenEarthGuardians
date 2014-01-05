package robotkittenearthguardians.graphics;

import robotkittenearthguardians.entity.Entity;

public class AnimateMachine extends Entity{
	
	//Keeps track of animation loop
	private int animateIndex = 0;
	//Holds the size of the animation loop
	private int loopSize;
	//Animation timer
	private int frameLife;
	
	public AnimateMachine(Sprite[] sprite, int x, int y) {
		this.sprite = sprite;
		this.loopSize = Sprite.getAnimationLoopSize(sprite);
		this.x = x;
		this.y = y;
	}
	
	public void update(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	/**
	 * Animates mobs according to their direction.
	 * @param screen object for rendering
	 * @param falseFall mobs falling float
	 */
	public void animateMob(Screen screen, float falseFall) {
		//up, up-left, left, down-left, down
		if(direction < 5) {
			screen.renderPlayer(x, y, falseFall, sprite[loopSize * direction + animateIndex], false);
		//down-right, right, up right
		} else {
			//8 - direction because (direction 5 needs to be 3, 6 => 2, and 7 => 1) flipped.
			screen.renderPlayer(x, y, falseFall, sprite[loopSize * (8 - direction) + animateIndex], true);
		}
		
		animateIndex();
		
	}
	
	/**
	 * Animates projectiles. Will add directions and sprite flipping later when projectiles
	 * that need it are created.
	 * @param screen screen object for rendering
	 */
	public void animateProjectile(Screen screen) {
		//up, up-left, left, down-left, down
		if(direction < 5) {
			screen.renderProjectile(x, y, sprite[loopSize * direction + animateIndex], false);
		//down-right, right, up right
		} else {
			//8 - direction because (direction 5 needs to be 3, 6 => 2, and 7 => 1) flipped.
			screen.renderProjectile(x, y, sprite[loopSize * (8 - direction) + animateIndex], true);
		}
		
		animateIndex();
	}
	
	/**
	 * Animates particles
	 * @param screen object for rendering
	 * @return true if particle is still animating, false if it is done.
	 */
	public boolean animateParticle(Screen screen) {
		if(animateIndex()) {
			screen.renderParticles(x, y, sprite[animateIndex]);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Progresses the animation index of the mob.
	 * Returns false at the end of the animation loop.
	 * @return True if animation is still occurning, False if animation loop has restarted.
	 */
	private boolean animateIndex() {
		if(frameLife == 5) {
			if(animateIndex < loopSize - 1) {
				animateIndex++;
				frameLife = 0;
				return true;
			} else {
				animateIndex = 0;
				frameLife = 0;
				return false;
			}
		} else {
			frameLife++;
			return true;
		}
	}
}
