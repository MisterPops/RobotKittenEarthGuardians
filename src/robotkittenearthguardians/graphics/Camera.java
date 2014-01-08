package robotkittenearthguardians.graphics;

import robotkittenearthguardians.entity.mob.Player;

public class Camera {
	
	private int width, height;
	private static double cameraXCoord, cameraYCoord;
	private static int playerXCoord, playerYCoord;
	//Used to calculate the camera shake.
	private static int shake = 0;
	
	public Camera(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Camera follows player putting them in the center of the screen. Does not go past the maximum height and width
	 * of the background.
	 * @param screen the screen object for rendering
	 * @param player the player to follow.
	 */
	public void render(Screen screen, Player player) {
		if(player.getXCoord() >= (width / 2) && player.getXCoord() <= SpriteSheets.mainStage.getXSheetSize() - (width / 2)) {
			cameraXCoord = player.getXCoord() - (float) width / 2;
		}
		if(player.getYCoord() >= (height / 2) && player.getYCoord() <= SpriteSheets.mainStage.getYSheetSize() - (height / 2)) {
			cameraYCoord = player.getYCoord() - (float) height / 2;
		}
		
		if(shake > 0) {
			if(shake % 2 == 0) {
				cameraXCoord += 3;
				cameraYCoord += 3;
				shake--;
			} else {
				cameraXCoord -= 3;
				cameraYCoord -= 3;
				shake--;
			}
		}
		
		screen.setCameraCoords((int) cameraXCoord, (int) cameraYCoord);
		
		playerXCoord = player.getXCoord();
		playerYCoord = player.getYCoord();
	}
	
	/**
	 * The camera focuses the X and Y coord given to the center of the screen.
	 * @param screen the screen object for rendering.
	 * @param coordX the X coord to focus on.
	 * @param coordY the Y coord to focus on.
	 */
	public void render(Screen screen, int coordX, int coordY) {
		cameraXCoord = coordX - (float) width / 2;
		cameraYCoord = coordY - (float) height / 2;
		screen.setCameraCoords((int) cameraXCoord, (int) cameraYCoord);
	}
	
	/**
	 * Sends the caller the X coordinate of the camera.
	 * @return the camera's x coordinate.
	 */
	public static double getCameraXCoord() {
		return cameraXCoord;
	}
	
	/**
	 * Sends the caller the Y coordinate of the camera.
	 * @return the camera's y coordinate.
	 */
	public static double getCameraYCoord() {
		return cameraYCoord;
	}
	
	/**
	 * Returns the player's X coordinate.
	 * @return the player's x coordiunate.
	 */
	public static int getPlayerXCoord() {
		return playerXCoord;
	}
	
	/**
	 * Returns the player's Y coordinate.
	 * @return the player's y coordiunate.
	 */
	public static int getPlayerYCoord() {
		return playerYCoord;
	}
	
	/**
	 * Sets the shake int to 10 which shakes the
	 * camera position at regular intervals till shake == 0.
	 */
	public static void shake() {
		if(shake % 2 != 0) {
			shake = 9;
		} else shake = 10;
	}
}
