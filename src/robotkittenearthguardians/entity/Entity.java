package robotkittenearthguardians.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import robotkittenearthguardians.entity.mob.Mob;
import robotkittenearthguardians.entity.particles.Particle;
import robotkittenearthguardians.entity.projectiles.Projectiles;
import robotkittenearthguardians.graphics.AnimateMachine;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.Sprite;
import robotkittenearthguardians.util.Vector2F;

public abstract class Entity {

	protected int x;	//Location of an entity
	protected int y;
	//sprite array
	protected Sprite[] sprite;
	//Entities score when destroyed/picked up
	protected int points;
	//If the entity is set to be removed
	protected boolean removed = false;
	//If the move is on the stage or not
	protected boolean onStage = true;
	//Vector for entity's size
	protected Vector2F size = new Vector2F();
	//Entity's bounding box
	protected AABB boundBox;
	//Vector to hold entity's position
	protected Vector2F somePosition = new Vector2F();
	//Which sprite in the Sprite array to render
	protected int frame = 0;
	//Counter to count up till next frame is rendered
	protected int frameLife = 0;
	//Damage the entity does
	protected float damage;
	//Vector to hold mobs movement speed
	protected Vector2F movement = new Vector2F();
	//Direction: 0 up, 1 angleUpRight, 2 left, 3 angleDownLeft, 4 down, ect.
	protected int direction = 0;
	//object in charge of animation.
	protected AnimateMachine animation;
	//Particle that appears when the entity dies/ends
	protected Sprite[] deathParticle;
	
	protected final Random random = new Random();
	
	//<<------------ ENTITY LISTS ------------>>
	//Holds all enemy mobs
	protected static List<Mob> mobs = new ArrayList<Mob>();
	//Holds all projectiles
	protected static List<Projectiles> projectiles = new ArrayList<Projectiles>();
	//Holds all particle effects
	protected static List<Particle> particles = new ArrayList<Particle>();
	
	public Entity() {
	}
	
	public void update() {
	}
	
	public void render(Screen screen) {
	}
	
	public static void sortMobsList( ) {
		for(int indexOne = mobs.size() - 1; indexOne > 0; indexOne--) {
			for(int indexTwo = 0; indexTwo < indexOne; indexTwo++) {
				if(mobs.get(indexOne).getYCoord() < mobs.get(indexTwo).getYCoord()) {
					Collections.swap(mobs, indexOne, indexTwo);
				}
			}
		}
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
	
	/**
	 * Moves the coordinate of the mob on the X/Y plane
	 * @param xa Left and right on plane
	 * @param ya Up and down on plane
	 */
	public void move(int xa, int ya) {
		//-1, 0, or 1
		x += xa;
		y += ya;
	}
	
	/**
	 * Returns boolean value, true if mob is on stage.
	 * False otherwise.
	 * @return
	 */
	public boolean getIsOnStage() {
		return onStage;
	}
	
	
	/**
	 * Clears all Entities set as removed from the list in which they reside.
	 */
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
	
	/**
	 * Returns the AABB bounding box to the
	 * caller.
	 * @return
	 */
	public AABB getAABB() {
		return boundBox;
	}
	
	/**
	 * returns the vector somePosition to the caller.
	 * @return vector somePosition. Holds the x/y position of the object
	 */
	public Vector2F getSomePosition() {
		return somePosition;
	}
	
	/**
	 * Checks if a projectile's bounding box collides with the mob's
	 * bounding box
	 * @param projectile the projectile to be compared
	 * @return true if collides, false otherwise
	 */
	public boolean hit(Mob mob) {
		return CollisionLibrary.testAABBAABB(boundBox, mob.getAABB());
	}
	
	public Vector2F getSizeVector() {
		return size;
	}
	
	public Vector2F getMovementVector() {
		return movement;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
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
