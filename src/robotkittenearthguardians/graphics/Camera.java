package robotkittenearthguardians.graphics;

import robotkittenearthguardians.entity.mob.Player;

public class Camera {
	
	private int width, height;
	private static double cameraXCoord, cameraYCoord;
	private static int playerXCoord, playerYCoord;
	
	public Camera(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void render(Screen screen, Player player) {
		if(player.getXCoord() >= (width / 2) && player.getXCoord() <= SpriteSheets.mainStage.getXSheetSize() - (width / 2)) {
			cameraXCoord = player.getXCoord() - width / 2;
		}
		if(player.getYCoord() >= (height / 2) && player.getYCoord() <= SpriteSheets.mainStage.getYSheetSize() - (height / 2)) {
			cameraYCoord = player.getYCoord() - height / 2;
		}
		screen.setCameraCoords((int) cameraXCoord, (int) cameraYCoord);
		
		playerXCoord = player.getXCoord();
		playerYCoord = player.getYCoord();
	}
	
	public static double getCameraXCoord() {
		return cameraXCoord;
	}
	
	public static double getCameraYCoord() {
		return cameraYCoord;
	}
	
	public static int getPlayerXCoord() {
		return playerXCoord;
	}
	
	public static int getPlayerYCoord() {
		return playerYCoord;
	}
}
