package robotkittenearthguardians.entity.mob;

import robotkittenearthguardians.entity.AABB;
import robotkittenearthguardians.entity.HealthBar;
import robotkittenearthguardians.entity.mob.ai.Ai;
import robotkittenearthguardians.entity.particles.MissleAmmo;
import robotkittenearthguardians.entity.projectiles.MainBullet;
import robotkittenearthguardians.entity.projectiles.Missle;
import robotkittenearthguardians.entity.projectiles.ShotgunBullet;
import robotkittenearthguardians.graphics.AnimateMachine;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;
import robotkittenearthguardians.input.Keyboard;
import robotkittenearthguardians.input.Mouse;
import robotkittenearthguardians.level.GameMaster;

public class Player extends Mob {
	
	private float force = 0.7f;
	private float mass = 10.0f;
	//Should be private. Used to calculate when
	//Specific bullets should be shot and bullet's ammo.
	public static int mainShootDelta = 0;
	public static int shotgunDelta = 0;
	public static int missleDelta = 0;
	public static int missleAmmo = 10;
	
	private Keyboard input;
	
	/**
	 * Constructor used to set the player at a specific coordinate.
	 * @param x Coord on x-plane
	 * @param y Coord on y-plane
	 * @param input Input object used for keyboard input.w
	 */
	public Player(int x, int y, Keyboard input) {
		health = 100.0f;
		speed = 3f;
		this.x = x;
		this.y = y;
		sprite = Sprite.player;
		somePosition.setXVector(x);
		somePosition.setYVector(y);
		size.setXVector(14);
		size.setYVector(14);
		this.input = input;
		sprite = Sprite.player;
		mobs.add(this);
		boundBox = new AABB(somePosition, size);
		healthBar = new HealthBar(health);
		animation = new AnimateMachine(sprite, x, y);
	}
	
	public void update() {
		
		//Upates bounding box with x/y pos
		somePosition.setXVector(this.x);
		somePosition.setYVector(this.y);
		boundBox.update(somePosition);
		updatePlayerDirection();
		
		//Moving
		int xa = 0, ya = 0;
		if(input.up) {
			if (fVel < speed) fVel += (force/mass);
		} else if(fVel >= 0) {
			fVel -= (force/mass);
		}
		ya -= fVel;
		if(input.down) {
			if (bVel < speed) bVel += (force/mass);
		} else if(bVel >= 0) {
			bVel -= (force/mass);
		}
		ya += bVel;
		if(input.left) {
			if (lVel < speed) lVel += (force/mass);
		} else if(lVel >= 0) {
			lVel -= (force/mass);
		}
		xa -= lVel;
		if(input.right) {
			if (rVel < speed) rVel += (force/mass);
		} else if(rVel >= 0) {
			rVel -= (force/mass);
		}
		xa += rVel;
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
		}
		
		for(int index = 0; index < particles.size(); index++) {
			if(particles.get(index) instanceof MissleAmmo) {
				if(hit(particles.get(index))) {
					if(missleAmmo < 10) {
						missleAmmo++;
					}
					GameMaster.addScore(particles.get(index).getPoints());
					particles.remove(index);
				}
			}
		}
		
		animation.update(x, y, direction);
		stageUpdates();
		
		//If health is 0 remove player
		if(health < 0f) {
			remove();
		}
		
		//health bar tasks
		health += health < 100 && !damaged ? 0.1 : 0;
		healthBar.update(health);
		damaged = false;
		
		//Sends player coords for AI to use
		Ai.setPlayerX(this.x);
		Ai.setPlayerY(this.y);
	}

	/**
	 * Sends sprite, x-coord, and y-coord to Screen.renderPlayer method
	 * to be rendered.
	 * ** Probably make a seperate class for animations...maybe **
	 */
	public void render(Screen screen) {
		animation.animateMob(screen, falseFall);
	}
	
	/**
	 * Allows player to shoot weapon at specific intervals according to shootSpeed.
	 */
	protected void updateShooting() {
		double dir = Mouse.mouseRadAngle();
		
		if(mainShootDelta < MainBullet.FIRE_RATE) mainShootDelta++;
		if(shotgunDelta < ShotgunBullet.FIRE_RATE) shotgunDelta++;
		if(missleDelta < Missle.FIRE_RATE) missleDelta++;
		
		if(Mouse.getMouseB() == 1 && mainShootDelta >= MainBullet.FIRE_RATE) {
			shoot(x, y, dir, 0);
			mainShootDelta = 0;
		} else if(input.space && missleAmmo != 0 && missleDelta >= Missle.FIRE_RATE && mobs.size() > 1) { 
			shoot(x, y, dir, 1);
			missleAmmo--;
			missleDelta = 0;
		} else if(Mouse.getMouseB() == 3 && shotgunDelta >= ShotgunBullet.FIRE_RATE) {
			shoot(x, y, dir, 2);
			shotgunDelta = 0;
		}
	}
	
	/**
	 * Updates the direction that the player is currently facing using mouse coords.
	 */
	private void updatePlayerDirection() {
		if(Mouse.mouseRadToDeg() < -70 && Mouse.mouseRadToDeg() >  -130) {
			direction = 0;
		} else if(Mouse.mouseRadToDeg() <= -130 && Mouse.mouseRadToDeg() >=  -160) {
			direction = 1;
		} else if(Mouse.mouseRadToDeg() < -160 || Mouse.mouseRadToDeg() >  160) {
			direction = 2;
		} else if(Mouse.mouseRadToDeg() <= 160 && Mouse.mouseRadToDeg() >= 110 ) {
			direction = 3;
		} else if(Mouse.mouseRadToDeg() < 110 && Mouse.mouseRadToDeg() >  50) {
			direction = 4;
		} else if(Mouse.mouseRadToDeg() < 20 && Mouse.mouseRadToDeg() >  -20) {
			direction = 6;
		} else if(Mouse.mouseRadToDeg() <= -20 && Mouse.mouseRadToDeg() >= -70) {
			direction = 7;
		} else if(Mouse.mouseRadToDeg() <= 70 && Mouse.mouseRadToDeg() >= 20) {
			direction = 5;
		}
	}
}
