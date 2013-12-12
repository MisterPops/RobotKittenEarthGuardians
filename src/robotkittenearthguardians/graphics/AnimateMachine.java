package robotkittenearthguardians.graphics;

import robotkittenearthguardians.entity.Entity;

public class AnimateMachine extends Entity{
	
	//Keeps track of animation loop
	private int animateIndex = 0;
	//Holds the size of the animation loop
	private int loopSize;
	
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
	 * Progresses the animation index of the mob.
	 * Returns false at the end of the animation loop.
	 * @return True if animation is still occurning, False if animation loop has restarted.
	 */
	private boolean animateIndex() {
		if(animateIndex < loopSize - 1) {
			animateIndex++;
			return true;
		} else {
			animateIndex = 0;
			return false;
		}
	}
}
