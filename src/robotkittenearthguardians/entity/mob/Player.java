package robotkittenearthguardians.entity.mob;

import robotkittenearthguardians.entity.projectiles.MainBullet;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;
import robotkittenearthguardians.input.Keyboard;
import robotkittenearthguardians.input.Mouse;
import robotkittenearthguardians.level.Level;

public class Player extends Mob {
	
	private float force = 1.0f;
	private float mass = 10.0f;
	private int maxVel = 3;
	private int shootSpeed;
	private int deltaShootTime = 0;
	
	private Keyboard input;
	
	/**
	 * Constructor used when there is no specific place coord for
	 * the player.
	 * @param input Input object used for keyboard input.
	 */
	public Player(Keyboard input) {
		this.input = input;
		shootSpeed = MainBullet.FIRE_RATE;
		mobs.add(this);
	}
	
	/**
	 * Constructor used to set the player at a specific coordinate.
	 * @param x Coord on x-plane
	 * @param y Coord on y-plane
	 * @param input Input object used for keyboard input.
	 */
	public Player(int x, int y, Keyboard input) {
		health = 100.0f;
		this.x = x;
		this.y = y;
		this.input = input;
		shootSpeed = MainBullet.FIRE_RATE;
		mobs.add(this);
	}
	
	public void update() {
		
		//Moving
		int xa = 0, ya = 0;
		if(input.up) {
			if (fVel < maxVel) fVel += (force/mass);
		} else if(fVel >= 0) {
			fVel -= (force/mass);
		}
		ya -= fVel;
		if(input.down) {
			if (bVel < maxVel) bVel += (force/mass);
		} else if(bVel >= 0) {
			bVel -= (force/mass);
		}
		ya += bVel;
		if(input.left) {
			if (lVel < maxVel) lVel += (force/mass);
		} else if(lVel >= 0) {
			lVel -= (force/mass);
		}
		xa -= lVel;
		if(input.right) {
			if (rVel < maxVel) rVel += (force/mass);
		} else if(rVel >= 0) {
			rVel -= (force/mass);
		}
		xa += rVel;
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
		}
		
		//System.out.println(health);
		if(!(Level.isOnStage(x, y))) {
			falling();
		} else {
			onStage = true;
			falseFall = 0;
			updateShooting();
		}
		
		if(health < 0f) {
			remove();
		}
		
		Ai.setPlayerX(this.x);
		Ai.setPlayerY(this.y);
	}
	
	private void updateShooting() {
		double dir = Mouse.mouseRadAngle();
		double mouseX = Mouse.getMouseX();
		double mouseY = Mouse.getMouseY();
		
		if(deltaShootTime < shootSpeed) deltaShootTime++;
		
		if(Mouse.getMouseB() == 1 && deltaShootTime % shootSpeed == 0) {
			shoot(x, y, dir, mouseX, mouseY);
			deltaShootTime = 0;
		}
	}

	/**
	 * Sends sprite, x-coord, and y-coord to Screen.renderPlayer method
	 * to be rendered.
	 */
	public void render(Screen screen) {
		if(Mouse.mouseRadToDeg() < -70 && Mouse.mouseRadToDeg() >  -130) {
			screen.renderPlayer(x, y, falseFall, Sprite.player, false);
		} else if(Mouse.mouseRadToDeg() <= -130 && Mouse.mouseRadToDeg() >=  -160) {
			screen.renderPlayer(x, y, falseFall, Sprite.playerAngleUpLeft, false);
		} else if(Mouse.mouseRadToDeg() < -160 || Mouse.mouseRadToDeg() >  160) {
			screen.renderPlayer(x, y, falseFall, Sprite.playerLeft, false);
		} else if(Mouse.mouseRadToDeg() <= 160 && Mouse.mouseRadToDeg() >= 110 ) {
			screen.renderPlayer(x, y, falseFall, Sprite.playerAngleDownLeft, false);
		} else if(Mouse.mouseRadToDeg() < 110 && Mouse.mouseRadToDeg() >  50) {
			screen.renderPlayer(x, y, falseFall, Sprite.playerDown, false);
		} else if(Mouse.mouseRadToDeg() < 20 && Mouse.mouseRadToDeg() >  -20) {
			screen.renderPlayer(x, y, falseFall, Sprite.playerLeft, true);
		} else if(Mouse.mouseRadToDeg() <= -20 && Mouse.mouseRadToDeg() >= -70) {
			screen.renderPlayer(x, y, falseFall, Sprite.playerAngleUpLeft, true);
		} else if(Mouse.mouseRadToDeg() <= 70 && Mouse.mouseRadToDeg() >= 20) {
			screen.renderPlayer(x, y, falseFall, Sprite.playerAngleDownLeft, true);
		}
	}
}
