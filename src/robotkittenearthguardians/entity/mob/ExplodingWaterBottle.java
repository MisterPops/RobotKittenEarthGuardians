package robotkittenearthguardians.entity.mob;

import robotkittenearthguardians.entity.AABB;
import robotkittenearthguardians.entity.HealthBar;
import robotkittenearthguardians.entity.mob.ai.WaterBalloonAi;
import robotkittenearthguardians.graphics.AnimateMachine;
import robotkittenearthguardians.graphics.Camera;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;
import robotkittenearthguardians.level.GameMaster;

public class ExplodingWaterBottle extends Mob{
	private int sightRange = 9001;
	WaterBalloonAi ai;

	public ExplodingWaterBottle(int x, int y) {
		health = 30.0f;
		speed = 3.7;
		points = 3;
		damage = 0.1f;
		sprite = Sprite.explodingBottleGreen;
		deathParticle = Sprite.mainExplosion;
		this.x = x;
		this.y = y;
		somePosition.setXVector(x);
		somePosition.setYVector(y);
		size.setXVector(14);
		size.setYVector(14);
		mobs.add(this);
		//Creates bound box for WaterBalloon
		boundBox = new AABB(somePosition, size);
		healthBar = new HealthBar(health);
		animation = new AnimateMachine(sprite, x, y);
		//Initialize mob's Ai
		ai = new WaterBalloonAi(this);
	}
	
	public void update() {
		//Updates mob's x/y to somePostion vector
		somePosition.setXVector(this.x);
		somePosition.setYVector(this.y);
		boundBox.update(somePosition);
		
		//Updates ai with mob's x/y vector
		ai.update(somePosition);
		
		//Checks if mob can see the player
		seePlayer = ai.seePlayer(sightRange);
		
		//Mobs movement patterns
		ai.ai(speed, this);
		direction = ai.aiDirection(true, false);
		
		//If collides with other mobs
		for(int index = 0; index < mobs.size(); index++) {
			if(hit(mobs.get(index)) && !mobs.get(index).equals(this)) {
				ai.onCollide(this, mobs.get(index));
				
				if(mobs.get(index) instanceof Player) {
					mobs.get(index).hurt(damage);
				}
			}
		}
		
		spriteRadius();
		animation.update(x, y, direction, sprite);
		stageUpdates();
		healthBar.update(health);
		
		//If health is 0 remove mob.
		if(health <= 0 || getPlayerDistance() < 40) {
			explode();
		}
	}
	
	/**
	 * Loops through all the frames in the sprite array and sends them
	 * to the screen class accordingly.
	 */
	public void render(Screen screen) {		
		animation.animateMob(screen, falseFall);
	}
	
	private float getPlayerDistance() {
		float dx = Camera.getPlayerXCoord() - this.x, dy = Camera.getPlayerYCoord() - this.y;
		return (float) Math.sqrt(dx * dx + dy * dy);
	}
	
	private void spriteRadius() {
		float dist = getPlayerDistance();
		if(dist > 120) {
			this.sprite = Sprite.explodingBottleGreen;
		} else if(dist > 60) {
			this.sprite = Sprite.explodingBottleYellow;
		} else {
			this.sprite = Sprite.explodingBottleRed;
		}
	}
	
	private void explode() {
		final float EXPLOSIVE_DMG = 10;
		final float DMG_RADIUS = 60;
		GameMaster.addScore(points);
		mainExplode(deathParticle);
		
		for(int index = 0; index < mobs.size(); index++) {
			if(mobs.get(index) instanceof Player) {
				if(getPlayerDistance() < DMG_RADIUS) {
					mobs.get(index).hurt(EXPLOSIVE_DMG);
				}
			}
		}
		
		dropMissleAmmo();
		Camera.shake();
		remove();
	}
}
