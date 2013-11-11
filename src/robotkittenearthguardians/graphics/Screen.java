package robotkittenearthguardians.graphics;

import robotkittenearthguardians.entity.particles.Particle;
import robotkittenearthguardians.entity.projectiles.Projectiles;

public class Screen {
	
	private int width, height;
	private int[] pixels;
	private int xOffset, yOffset;
	
	public Screen(int screenWidth, int screenHeight) {
		this.width = screenWidth;
		this.height = screenHeight;
		pixels = new int[screenWidth * screenHeight];
	}
	
	/**
	 * Returns the entire Screen pixels array.
	 * @return int[] pixels
	 */
	public int[] getPixelsArray() {
		return pixels;
	}
	
	/**
	 * Returns a specific item in the Screens pixels array
	 * @param i Position in the int[] pixels
	 * @return Item in i position in int[] pixels
	 */
	public int getPixels(int i) {
		return pixels[i];
	}
	
	/**
	 * Sets i in Screen's pixel array to the given int.
	 * @param i Position in int[] pixels
	 * @param result The int that pixel[i] will be set to.
	 */
	public void setPixels(int i, int result) {
		pixels[i] = result;
	}
	
	/**
	 * Set's all pixels in Screen's pixel array to 0
	 */
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderLevel() {
		int pixelIndex;
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				int xAbs = x + xOffset;
				int yAbs = y + yOffset;
				pixelIndex = (x + xOffset) + (y + yOffset) * SpriteSheets.mainStage.getXSheetSize();
				//tileIndex = ((x + xOffset >> 5) & MASK_SIZE) + ((y + yOffset >> 5) & MASK_SIZE) * SIZE;
				if(xAbs > 0 && yAbs > 0 && xAbs < SpriteSheets.mainStage.getXSheetSize() && yAbs < SpriteSheets.mainStage.getYSheetSize()) {
					if(SpriteSheets.mainStage.getSpriteSheetsPixels(pixelIndex) != 0xffff00ff) {
						pixels[x + y * width] = SpriteSheets.mainStage.getSpriteSheetsPixels(pixelIndex);
						//screen.setPixels(x + y * screenWidth, levelPixels[pixelIndex]);
						//screen.setPixels(x + y * screenWidth, tiles[tileIndex]);
					}
				}
			}
		}
	}
	
	/**
	 * Render's a specific Sprite at a certain x/y coord.
	 * @param xPos Sprite's x-coord
	 * @param yPos Sprite's y-coord
	 * @param sprite The sprite to be rendered
	 * @param flip is true; will render the player flipped on x-axis
	 */
	public void renderPlayer(double xPos, double yPos, float falseFall, Sprite sprite, boolean flip) {
		xPos -= xOffset;
		yPos -= yOffset;
		yPos += falseFall;
		for(int y = 0; y < Sprite.player.getSize(); y++) {
			double yAbs = y + yPos;
			for(int x = 0; x < Sprite.player.getSize(); x++) {
				double xAbs = x + xPos;
				int xs = x;
				if(flip) xs = 31 - x;
				if(xAbs < -Sprite.player.getSize() || xAbs >= width || yAbs < 0 || yAbs >= height) break;
				if(xAbs < 0) xAbs = 0;
				int col = sprite.getSpritePixel(xs + y * Sprite.player.getSize());
				if(!(col == 0xffff00ff)) {
					pixels[(int)xAbs + (int)yAbs * width] = col;
				}
			}
		}
		
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void renderProjectile(double xPos, double yPos, Projectiles p) {
		xPos -= xOffset;
		yPos -= yOffset;
		for(int y = 0; y < p.getSprite().getSize(); y++) {
			double yAbs = y + yPos;
			for(int x = 0; x < p.getSprite().getSize(); x++) {
				double xAbs = x + xPos;
				if(xAbs < - 1 || xAbs > width || yAbs < 0 || yAbs > height - 1) break;
				int col = p.getSprite().getSpritePixel(x + y * p.getSprite().getSize());
				if(!(col == 0xffff00ff)) {
					pixels[(int) xAbs + (int) yAbs * width] = col;
				}
			}
		}
	}

	public void renderParticles(double xPos, double yPos, Particle p) {
		xPos -= xOffset;
		yPos -= yOffset;
		for(int y = 0; y < p.getSprite().getSize(); y++) {
			double yAbs = y + yPos;
			for(int x = 0; x < p.getSprite().getSize(); x++) {
				double xAbs = x + xPos;
				if(xAbs < -5 || xAbs > width || yAbs < 0 || yAbs > height) break;
				int col = p.getSprite().getSpritePixel(x + y * p.getSprite().getSize());
				if(!(col == 0xffff00ff)) {
					pixels[(int) xAbs + (int) yAbs * width] = col;
				}
			}
		}
	}
}
