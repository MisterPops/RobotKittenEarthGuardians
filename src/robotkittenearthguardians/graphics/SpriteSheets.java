package robotkittenearthguardians.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheets {

	private String path;
	private final int X_SIZE;
	private final int Y_SIZE;
	private int[] pixels;
	
	public static SpriteSheets player = new SpriteSheets("/actors/player1_1.png", 160, 160);
	public static SpriteSheets mainStage = new SpriteSheets("/bg/stage_test.png", 1600, 913);
	public static SpriteSheets mainBullet = new SpriteSheets("/projectiles/bullet.png", 16, 16);
	public static SpriteSheets bulletImpact = new SpriteSheets("/projectiles/bullet_impact.png", 128, 16);
	public static SpriteSheets waterBalloon = new SpriteSheets("/actors/waterballoon_enemy.png", 256, 32);
	
	public SpriteSheets(String path, int xSize, int ySize) {
		this.path = path;
		this.X_SIZE = xSize;
		this.Y_SIZE = ySize;
		pixels = new int[X_SIZE * Y_SIZE];
		load();
	}
	
	/**
	 * Translates the Sprite Sheet image at the given path into a pixels array.
	 */
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheets.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns SpriteSheets pixel array to the caller
	 * @return int[] pixels
	 */
	public int[] getPixels() {
		return pixels;
	}
	
	/**
	 * Returns the SpriteSheets X_SIZE to the caller
	 * @return int X_SIZE
	 */
	public int getXSheetSize() {
		return X_SIZE;
	}
	
	/**
	 * Returns the SpriteSheets Y_SIZE to the caller
	 * @return int Y_SIZE
	 */
	public int getYSheetSize() {
		return Y_SIZE;
	}
	
	public int getSpriteSheetsPixels(int i) {
		return pixels[i];
	}
}
