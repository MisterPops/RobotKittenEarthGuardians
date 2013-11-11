package robotkittenearthguardians.graphics;

import robotkittenearthguardians.entity.mob.Player;

public class Camera {
	
	private int width, height;
	private static double xScroll, yScroll;
	private static int playerXCoord, playerYCoord;
	
	public Camera(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void render(Screen screen, Player player) {
		if(player.getXCoord() >= (width / 2) && player.getXCoord() <= SpriteSheets.mainStage.getXSheetSize() - (width / 2)) {
			xScroll = player.getXCoord() - width / 2;
		}
		if(player.getYCoord() >= (height / 2) && player.getYCoord() <= SpriteSheets.mainStage.getYSheetSize() - (height / 2)) {
			yScroll = player.getYCoord() - height / 2;
		}
		screen.setOffset((int) xScroll, (int) yScroll);
		
		playerXCoord = player.getXCoord();
		playerYCoord = player.getYCoord();
	}
	
	public static double getXScroll() {
		return xScroll;
	}
	
	public static double getYScroll() {
		return yScroll;
	}
	
	public static int getPlayerXCoord() {
		return playerXCoord;
	}
	
	public static int getPlayerYCoord() {
		return playerYCoord;
	}
}
