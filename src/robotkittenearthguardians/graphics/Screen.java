package robotkittenearthguardians.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import robotkittenearthguardians.MainGame;
import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.mob.Mob;
import robotkittenearthguardians.entity.mob.Player;
import robotkittenearthguardians.input.Mouse;
import robotkittenearthguardians.level.GameMaster;

public class Screen {
	
	private int width, height;
	private int[] pixels;
	private BufferedImage image;
	private Graphics2D g2;
	private int cameraXCoord, cameraYCoord;
	private float cloudFloat = 0;
	
	public Screen(int screenWidth, int screenHeight, int[] pixels, BufferedImage image) {
		this.width = screenWidth;
		this.height = screenHeight;
		this.image = image;
		this.pixels = pixels;
	}
	
	/**
	 * Updates the screens graphics2d object with buffer strategy.
	 * @param bs
	 */
	public void update(BufferStrategy bs) {
		this.g2 = (Graphics2D) bs.getDrawGraphics();
	}
	
	/**
	 * Set's all pixels in Screen's pixel array to 0
	 */
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	/**
	 * Set's camera's coordinates in screen class to globally affect what renders
	 * according to the camera's position
	 * @param xOffset camera's x position
	 * @param yOffset camera's y position
	 */
	public void setCameraCoords(int xOffset, int yOffset) {
		this.cameraXCoord = xOffset;
		this.cameraYCoord = yOffset;
	}
	
	public void renderBackground(SpriteSheets sprite, boolean clouds) {
		int pixelIndex;
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				int xAbs = x + cameraXCoord;
				int yAbs = y + cameraYCoord;
				pixelIndex = (xAbs) + (yAbs) * sprite.getXSheetSize();
				if(xAbs > 0 && yAbs > 0 && xAbs < sprite.getXSheetSize() && yAbs < sprite.getYSheetSize()) {
					pixels[x + y * width] = sprite.getSpriteSheetsPixels(pixelIndex);
				}
			}
		}
		
		if(clouds) {
			renderClouds();
		}
	}
	
	public void renderClouds(){
		cloudFloat -= .3;
		if(cloudFloat < -1 * SpriteSheets.clouds.getXSheetSize()) {
			cloudFloat = 0;
		}
		
		int pixelIndex;
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				int xAbs = x + cameraXCoord + (int)cloudFloat;
				int yAbs = y + cameraYCoord;
				pixelIndex = (xAbs) + (yAbs) * SpriteSheets.clouds.getXSheetSize();
				if(yAbs > 0 && yAbs < SpriteSheets.clouds.getYSheetSize()) {
					if(SpriteSheets.clouds.getSpriteSheetsPixels(pixelIndex) != 0xffff00ff) {
						pixels[x + y * width] = SpriteSheets.clouds.getSpriteSheetsPixels(pixelIndex);
					}
				}
			}
		}
	}
	
	/**
	 * Renders the level
	 */
	public void renderLevel() {
		int pixelIndex;
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				int xAbs = x + cameraXCoord;
				int yAbs = y + cameraYCoord;
				pixelIndex = (x + cameraXCoord) + (y + cameraYCoord) * SpriteSheets.mainStage.getXSheetSize();
				if(xAbs > 0 && yAbs > 0 && xAbs < SpriteSheets.mainStage.getXSheetSize() && yAbs < SpriteSheets.mainStage.getYSheetSize()) {
					if(SpriteSheets.mainStage.getSpriteSheetsPixels(pixelIndex) != 0xffff00ff) {
						pixels[x + y * width] = SpriteSheets.mainStage.getSpriteSheetsPixels(pixelIndex);
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
		xPos -= cameraXCoord;
		yPos -= cameraYCoord;
		yPos += falseFall;
		for(int y = 0; y < sprite.getSizeY(); y++) {
			double yAbs = y + yPos;
			for(int x = 0; x < sprite.getSizeX(); x++) {
				double xAbs = x + xPos;
				int xs = x;
				if(flip) xs = (sprite.getSizeX() - 1) - x;
				if(xAbs < -sprite.getSizeX() || xAbs >= width || yAbs < 0 || yAbs >= height) break;
				if(xAbs < 0) xAbs = 0;
				int col = sprite.getSpritePixel(xs + y * sprite.getSizeX());
				if(!(col == 0xffff00ff)) {
					pixels[(int)xAbs + (int)yAbs * width] = col;
				}
			}
		}
	}

	/**
	 * Renders the projectiles
	 * @param xPos Projectile x-coord
	 * @param yPos Projectile y-coord
	 * @param p the projectile to be rendered
	 */
	public void renderProjectile(double xPos, double yPos, Sprite p) {
		xPos -= cameraXCoord;
		yPos -= cameraYCoord;
		for(int y = 0; y < p.getSizeY(); y++) {
			double yAbs = y + yPos;
			for(int x = 0; x < p.getSizeX(); x++) {
				double xAbs = x + xPos;
				if(xAbs < - 1 || xAbs > width || yAbs < 0 || yAbs > height - 1) break;
				int col = p.getSpritePixel(x + y * p.getSizeX());
				if(!(col == 0xffff00ff)) {
					pixels[(int) xAbs + (int) yAbs * width] = col;
				}
			}
		}
	}

	/**
	 * Renders particles
	 * @param xPos particles x-coord
	 * @param yPos particle's y-coord
	 * @param p the particle to be rendered
	 */
	public void renderParticles(int xPos, int yPos, Sprite p) {
		xPos -= cameraXCoord;
		yPos -= cameraYCoord;
		for(int y = 0; y < p.getSizeY(); y++) {
			int yAbs = y + yPos;
			for(int x = 0; x < p.getSizeX(); x++) {
				int xAbs = x + xPos;
				if(xAbs < -5 || xAbs > width || yAbs < 0 || yAbs > height) break;
				int col = p.getSpritePixel(x + y * p.getSizeX());
				if(!(col == 0xffff00ff)) {
					pixels[xAbs + yAbs * width] = col;
				}
			}
		}
	}
	
	/**
	 * Draws the rendered image to screen
	 */
	public void drawImage() {
		g2.drawImage(image, 0, 0, MainGame.getScreenWidth() * MainGame.getScreenScale(),
				MainGame.getScreenHeight() * MainGame.getScreenScale(), null);
	}
	
	public void gui(Player player) {
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Verdana", 0, 20));
		g2.drawString(""+ GameMaster.getLevelName(), 30, 30);
		g2.drawString("Mouse Coords: X: " + Mouse.getMouseX() + ", Y: " + Mouse.getMouseY(), 30, 55);
		g2.drawString("Player Coords: X: " + player.getXCoord() + ", Y: " + player.getYCoord(), 30, 80);
		g2.drawString("Mouse Button: " + Mouse.getMouseB(), 30, 105);
		g2.drawString("Projectile ArrayList: " + Entity.getProjectilesSize(), 30, 130);
		g2.drawString("Particle ArrayList: " + Entity.getParticleSize(), 30, 155);
		g2.drawString("Mob ArrayList: " + Entity.getMobSize(), 30, 180);
		g2.drawString("Score: " + GameMaster.getScore(), (MainGame.getScreenWidth() - 150) * 
				MainGame.getScreenScale(), 30);
		
		//Health Bar
		drawHealthBars();
	}
	
	/**
	 * Draws the health bar for all mobs in the game 8 pixels below the mob.
	 * Draws the bar depending on the mobs current health.
	 */
	private void drawHealthBars() {
		//offsets healthbar # pixels below the mob
		int healthBarOffset = 5;
		//healthbar size
		int healthBarSize = 32;
		for(int index = 0; index < Entity.getMobSize(); index++) {
			Mob mob = Entity.getIndexedMob(index);
			int mobXSize = mob.getSprite()[0].getSizeX();
			int mobYSize = mob.getSprite()[0].getSizeY();
			int xAbs = (int) (mob.getXCoord() - Camera.getCameraXCoord());
			int yAbs = (int) (mob.getYCoord() - Camera.getCameraYCoord());
			//Checks if mob is on screen before wasting resources rendering
			if(xAbs < width && yAbs < height && (xAbs + mobXSize + healthBarOffset) > 0 
					&& (yAbs + mobYSize + (healthBarOffset * 2)) > 0) {
				
				float maxHealth = mob.getHealthBar().getMaxHealth();
				float realHealth = mob.getHealthBar().getRealHealth();

				if(realHealth < maxHealth) {
					//Red underlying health bar
					g2.setColor(Color.RED);
					g2.fillRect(mob.getXCoord() - (int) Camera.getCameraXCoord(),
							mob.getYCoord() - (int) Camera.getCameraYCoord() + (mobYSize + healthBarOffset), healthBarSize, 3);
					//Health Left
					g2.setColor(Color.GREEN);
					g2.fillRect(mob.getXCoord() - (int) Camera.getCameraXCoord(),
							mob.getYCoord() - (int) Camera.getCameraYCoord() + (mobYSize + healthBarOffset),
							(int) (healthBarSize * (realHealth / maxHealth)), 3);
				}
			}
		}
	}

	/**
	 * Disposes the g2 object freeing up system memory.
	 */
	public void g2Dispose() {
		g2.dispose();
	}
}
