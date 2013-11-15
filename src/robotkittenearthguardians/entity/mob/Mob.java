package robotkittenearthguardians.entity.mob;

import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.Vector2Float;
import robotkittenearthguardians.entity.projectiles.MainBullet;
import robotkittenearthguardians.entity.projectiles.Projectiles;
import robotkittenearthguardians.graphics.Sprite;

public abstract class Mob extends Entity {
	
	//Mobs sprite array
	Sprite[] sprite;
	//Mobs health
	protected float health;
	protected float falseFall;
	//Mobs's front, right, left, and back velocity.
	protected float fVel = 0f, bVel = 0f, rVel = 0f, lVel = 0f;
	//If the mob is moving
	protected boolean moving = false;
	//If the mob sees the player.
	protected boolean seePlayer = false;
	//Vector to hold mobs movement speed
	protected Vector2Float movement = new Vector2Float();
	
	/**
	 * Moves the coordinate of the mob on the X/Y plane
	 * @param xa Left and right on plane
	 * @param ya Up and down on plane
	 */
	public void move(int xa, int ya) {
		if(!collision()) {
		//-1, 0, or 1
			x += xa;
			y += ya;
		}
	}
	
	public void shoot(int x, int y, double dir, double mouseX, double mouseY) {
		Projectiles mainShot = new MainBullet(x, y, dir, mouseX, mouseY);
		projectiles.add(mainShot);
	}
	
	public void update() {
	}
	
	public void render() {
	}
	
	private boolean collision() {
		return false;
	}
	
	protected void falling() {
		health -= 0.5f;
		onStage = false;
		falseFall += 3.5f;
	}
	
	public boolean getSeePlayer() {
		return seePlayer;
	}
}
