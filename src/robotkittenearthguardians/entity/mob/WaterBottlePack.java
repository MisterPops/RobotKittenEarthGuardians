package robotkittenearthguardians.entity.mob;

import robotkittenearthguardians.entity.AABB;
import robotkittenearthguardians.entity.HealthBar;
import robotkittenearthguardians.entity.mob.ai.WaterBottlePackAi;
import robotkittenearthguardians.graphics.AnimateMachine;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;

public class WaterBottlePack extends Mob{

	private int sightRange = 500;
	WaterBottlePackAi ai;

	public WaterBottlePack(int x, int y) {
		health = 80.0f;
		speed = 2;
		points = 10;
		damage = 0.5f;
		sprite = Sprite.waterBottlePack;
		deathParticle = Sprite.mainExplosion;
		this.x = x;
		this.y = y;
		somePosition.setXVector(x);
		somePosition.setYVector(y);
		size.setXVector(19);
		size.setYVector(17);
		mobs.add(this);
		//Creates bound box for WaterBalloon
		boundBox = new AABB(somePosition, size);
		healthBar = new HealthBar(health);
		animation = new AnimateMachine(sprite, x, y);
		//Initialize mob's Ai
		ai = new WaterBottlePackAi(this);
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
			if(hit(mobs.get(index)) && !mobs.get(index).equals(this)
					&& !(mobs.get(index) instanceof WaterBottlePack)) {
				ai.onCollide(this, mobs.get(index));
				
				if(mobs.get(index) instanceof Player) {
					mobs.get(index).hurt(damage);
				}
			}
		}
		
		animation.update(x, y, direction);
		stageUpdates();
		healthBar.update(health);
		
		//If health is 0 remove mob.
		if(health <= 0) {
			dropWaterBottles();
			mobDeath();
			
		}
	}
	
	/**
	 * Loops through all the frames in the sprite array and sends them
	 * to the screen class accordingly.
	 */
	public void render(Screen screen) {		
		animation.animateMob(screen, falseFall);
	}
	
	private void dropWaterBottles() {
		for(int count = 0; count < 7; count++) {
			@SuppressWarnings("unused")
			WaterBottleSingle waterBottleSingle = new WaterBottleSingle(x, y);
		}
	}
}