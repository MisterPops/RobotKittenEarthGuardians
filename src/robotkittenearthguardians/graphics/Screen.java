package robotkittenearthguardians.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import robotkittenearthguardians.MainGame;
import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.mob.Player;
import robotkittenearthguardians.entity.particles.Particle;
import robotkittenearthguardians.entity.projectiles.Projectiles;
import robotkittenearthguardians.input.Mouse;
import robotkittenearthguardians.level.GameMaster;

public class Screen {
	
	private int width, height;
	private int[] pixels;
	private BufferedImage image;
	private Graphics2D g2;
	private int cameraXCoord, cameraYCoord;
	
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

	/**
	 * Renders the projectiles
	 * @param xPos Projectile x-coord
	 * @param yPos Projectile y-coord
	 * @param p the projectile to be rendered
	 */
	public void renderProjectile(double xPos, double yPos, Projectiles p) {
		xPos -= cameraXCoord;
		yPos -= cameraYCoord;
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

	/**
	 * Renders particles
	 * @param xPos particles x-coord
	 * @param yPos particle's y-coord
	 * @param p the particle to be rendered
	 */
	public void renderParticles(double xPos, double yPos, Particle p) {
		xPos -= cameraXCoord;
		yPos -= cameraYCoord;
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
		g2.drawString("Player Coords: X: " + player.getXCoord() + ", Y: " + player.getYCoord(), 30, 30);
		g2.drawString("Score: " + GameMaster.getScore(), 800, 30);
		g2.drawString("Mouse Coords: X: " + Mouse.getMouseX() + ", Y: " + Mouse.getMouseY(), 30, 55);
		g2.drawString("Mouse Angle: " + Mouse.mouseRadToDeg(), 30, 80);
		g2.drawString("Mouse Button: " + Mouse.getMouseB(), 30, 105);
		g2.drawString("Projectile ArrayList: " + Entity.getProjectilesSize(), 30, 130);
		g2.drawString("Particle ArrayList: " + Entity.getParticleSize(), 30, 155);
		g2.drawString("Mob ArrayList: " + Entity.getMobSize(), 30, 180);
	}
	
	public void background() {
		g2.setColor(Color.CYAN);
		g2.fillRect(0, 0, MainGame.getScreenWidth() * MainGame.getScreenScale(),
				MainGame.getScreenHeight() * MainGame.getScreenScale());
	}

	/**
	 * Disposes the g2 object freeing up system memory.
	 */
	public void g2Dispose() {
		g2.dispose();
	}
}
