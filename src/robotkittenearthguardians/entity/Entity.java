package robotkittenearthguardians.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import robotkittenearthguardians.entity.mob.Mob;
import robotkittenearthguardians.entity.particles.Particle;
import robotkittenearthguardians.entity.projectiles.Projectiles;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.level.Level;

public abstract class Entity {

	protected int x;	//Location of an entity
	protected int y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	protected boolean onStage = true;
	
	protected static List<Mob> mobs = new ArrayList<Mob>();
	protected static List<Projectiles> projectiles = new ArrayList<Projectiles>();
	protected static List<Particle> particles = new ArrayList<Particle>();
	
	public Entity() {
	}
	
	public void update() {
	}
	
	public void render(Screen screen) {
	}
	
	/**
	 * Sets the remove boolean of the entity to true 
	 */
	public void remove() {
		//Remove entity from level
		removed = true;
		
	}
	
	/**
	 * Returns the boolean value of whether the entity is true removed
	 * or false not removed
	 * @return
	 */
	public boolean isRemoved() {
		return removed;
	}
	
	public boolean getIsOnStage() {
		return onStage;
	}
	
	
	/**
	 * Clears all Entities set as removed from the list in which they reside.
	 */
	//protected void clearEntities() {
	public static void clearEntities() {
		
		for(int i = 0; i < mobs.size(); i++) {
			Mob p = mobs.get(i);
			if(p.isRemoved()) {
				mobs.remove(i);
			}
		}
		
		for(int i = 0; i < projectiles.size(); i++) {
			Projectiles p = projectiles.get(i);
			if(p.isRemoved()) {
				projectiles.remove(i);
			}
		}
		
		for(int i = 0; i < particles.size(); i++) {
			Particle p = particles.get(i);
			if(p.isRemoved()) {
				particles.remove(i);
			}
		}
	}

	/**
	 * Returns the x-coord of the entity.
	 * @return int  x-coordinate
	 */
	public int getXCoord() {
		return x;
	}
	
	/**
	 * Returns the y-coord of the entity.
	 * @return int y-coordinate
	 */
	public int getYCoord() {
		return y;
	}

	/*
	 * <<<======== Entity Lists: MUTATORS & SETTERS ===========>>>
	 */
	public static Projectiles getIndexedProjectile(int i) {
		return projectiles.get(i);
	}
	
	public static int getProjectilesSize() {
		return projectiles.size();
	}
	
	public static Particle getIndexedParticle(int i) {
		return particles.get(i);
	}
	
	public static int getParticleSize() {
		return particles.size();
	}
	
	public static Mob getIndexedMob(int i) {
		return mobs.get(i);
	}
	
	public static int getMobSize() {
		return mobs.size();
	}
}
