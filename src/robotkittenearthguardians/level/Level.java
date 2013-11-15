package robotkittenearthguardians.level;

import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.Vector2Float;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.SpriteSheets;

public class Level {
	
	//The level to be loaded
	private int levelNum = 0;
	
	public Level() {
	}

	/**
	 * Updates all entities on the level stage.
	 */
	public void update() {
		
		//When all mobs are killed, proceeds to the next level
		//then loads the mobs for the next level
		if(Entity.getMobSize() == 1) {
			GameMaster.loadLevel(levelNum);
			levelNum++;
		}
		
		for(int i = 0; i < Entity.getMobSize(); i++) {
			Entity.getIndexedMob(i).update();
		}
		
		for(int i = 0; i < Entity.getProjectilesSize(); i++) {
			Entity.getIndexedProjectile(i).update();
		}
		
		for(int i = 0; i < Entity.getParticleSize(); i++) {
			Entity.getIndexedParticle(i).update();
		}
		
		//Clears all entities flagged to be removed
		Entity.clearEntities();
	}
	
	/**
	 * Keeps track of render of the level itself and all entities on the level stage.
	 * @param xPos Level offset on the x-plane.
	 * @param yPos Level's offset on the y-plane
	 * @param screen The screen object used to set offset in the Screen class.
	 */
	//public void render(int xPos, int yPos, Screen screen) {
	public void render(Screen screen) {
		
		for(int i = 0; i < Entity.getMobSize(); i++) {
			if(!(Entity.getIndexedMob(i).getIsOnStage())) {
				Entity.getIndexedMob(i).render(screen);
			}
		}

		screen.renderLevel();
		
		for(int i = 0; i < Entity.getMobSize(); i++) {
			if(Entity.getIndexedMob(i).getIsOnStage()) {
				Entity.getIndexedMob(i).render(screen);
			}
		}
		
		for(int i = 0; i < Entity.getProjectilesSize(); i++) {
			Entity.getIndexedProjectile(i).render(screen);
		}
		
		for(int i = 0; i < Entity.getParticleSize(); i++) {
			Entity.getIndexedParticle(i).render(screen);
		}
	}
	
	/**
	 * Measures if the X and Y position of the entity is on the main stage.
	 * @param x X position of the entity
	 * @param y Y position of the entity
	 * @return true if on the stage, false if not on the stage
	 */
	public static boolean isOnStage(Vector2Float position) {
		float centerX = SpriteSheets.mainStage.getXSheetSize() / 2;
		float centerY = SpriteSheets.mainStage.getYSheetSize() / 2 - 27;
		float stageRadiusX = 1560 / 2;
		float stageRadiusY = 841 / 2;
		
		float distX = (position.x - centerX);
		float distY = (position.y - centerY) * stageRadiusX / stageRadiusY;
		
		return ((distX * distX) + (distY * distY)) <= stageRadiusX * stageRadiusX;
	}
}
