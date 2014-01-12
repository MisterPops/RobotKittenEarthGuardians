package robotkittenearthguardians.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import robotkittenearthguardians.MainGame;
import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.mob.Mob;
import robotkittenearthguardians.entity.mob.Player;
import robotkittenearthguardians.entity.projectiles.MainBullet;
import robotkittenearthguardians.entity.projectiles.ShotgunBullet;
import robotkittenearthguardians.gameState.GameState;
import robotkittenearthguardians.gameState.NewGameState;
import robotkittenearthguardians.gameState.StartScreenState;
import robotkittenearthguardians.level.GameMaster;

public class Screen {
	
	private int width, height;
	private int[] pixels;
	private BufferedImage image;
	private Graphics2D g2;
	private int cameraXCoord, cameraYCoord;
	//Used for clouds to translate across the sky.
	private float cloudFloat = 0;
	//Used for fade in and fade out of colors.
	private float alpha = 0;
	
	/**
	 * Screen constructor. Sets the screen width, height, and sets up the pixel array.
	 * Also downloads the custom font for the game.
	 * @param screenWidth the width of the game screen.
	 * @param screenHeight the height of the game screen.
	 * @param pixels int array to represent pixels of the monitor.
	 * @param image the BufferedImage
	 */
	public Screen(int screenWidth, int screenHeight, int[] pixels, BufferedImage image) {
		this.width = screenWidth;
		this.height = screenHeight;
		this.image = image;
		this.pixels = pixels;
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/font/Ponderosa.ttf")));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	 * Renders the background of the game.
	 * If clouds is true, it will call renderClouds();
	 * @param sprite the background to be rendered.
	 * @param clouds True, will render clouds. False, no clouds.
	 */
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
	
	/**
	 * Renders title on the StartScreen.
	 */
	public void renderTitle() {
		SpriteSheets sprite = SpriteSheets.titleFont;
		int pixelIndex;
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				pixelIndex = x + y * sprite.getXSheetSize();
				if(x > 0 && y > 0 && x < sprite.getXSheetSize() && y < sprite.getYSheetSize()) {
					if(sprite.getSpriteSheetsPixels(pixelIndex) != 0xffff00ff) {
						pixels[x + y * width] = sprite.getSpriteSheetsPixels(pixelIndex);
					}
				}
			}
		}
	}
	
	/**
	 * Renders and moves the clouds throughout the sky.
	 */
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
	public void renderProjectile(double xPos, double yPos, Sprite p, boolean flip) {
		xPos -= cameraXCoord;
		yPos -= cameraYCoord;
		for(int y = 0; y < p.getSizeY(); y++) {
			double yAbs = y + yPos;
			for(int x = 0; x < p.getSizeX(); x++) {
				double xAbs = x + xPos;
				int xs = x;
				if(flip) xs = (p.getSizeX() - 1) - x;
				if(xAbs < 0 || xAbs > width - 1 || yAbs < 0 || yAbs > height - 1) break;
				int col = p.getSpritePixel(xs + y * p.getSizeX());
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
				if(xAbs < 0 || xAbs > width - 1 || yAbs < 0 || yAbs > height - 1) break;
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
	
	/**
	 * Displays the gui screen according to the current gameState.
	 * @param gamestate the current game state being used.
	 */
	public void gui(GameState gamestate) {
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Ponderosa", 0, 18));
		if(gamestate instanceof NewGameState) {
			//g2.drawString("" + Mouse.mouseRadToDeg(), 30, 55);
			//g2.drawString("Mouse Coords: X: " + Mouse.getMouseX() + ", Y: " + Mouse.getMouseY(), 30, 55);
			//g2.drawString("Player Coords: X: " + Camera.getPlayerXCoord() + ", Y: " + Camera.getPlayerYCoord(), 30, 80);
			//g2.drawString("Mouse Button: " + Mouse.getMouseB(), 30, 105);
			//g2.drawString("Projectile ArrayList: " + Entity.getProjectilesSize(), 30, 130);
			//g2.drawString("Particle ArrayList: " + Entity.getParticleSize(), 30, 155);
			//g2.drawString("Mob ArrayList: " + Entity.getMobSize(), 30, 180);
			g2.drawString("LEVEL:"+ GameMaster.getLevelName(), 30, 30);
			g2.drawString("SCORE:" + GameMaster.getScore(), (MainGame.getScreenWidth() - 180) * 
					MainGame.getScreenScale(), 30);

			//BattleGui
			drawAmmoBars();

			//Health Bar
			drawHealthBars();
		} else if(gamestate instanceof StartScreenState) {
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Ponderosa", 0, 21));
			
			g2.drawString("Your planet is being invaded by H20 filled creatures,", 130, 540);
			g2.drawString("a highly explosive substance on your planet!", 200, 570);
			g2.drawString("Click your [mouse] to start!", 330, 600);
		}
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
	 * Draws player's ammo gui bars.
	 */
	private void drawAmmoBars() {
		g2.drawString("BLASTER", 140, 583);
		g2.drawString("SHOTGUN", 140, 609);
		g2.drawString("MISSILES", 140, 635);
		
		int outlineBarWidth = 14, outlineLength = 100;
		int innerBarWidth = 8, innerLength = 93;
		BasicStroke s1 = new BasicStroke(2f);
		g2.setStroke(s1);
		
		//MainBlaster
		g2.drawRect(30, 570, outlineLength, outlineBarWidth);
		g2.fillRect(33, 573, innerLength * Player.mainShootDelta / MainBullet.FIRE_RATE, innerBarWidth);
		//Shotgun
		if(Player.shotgunUnlock) {
			g2.drawRect(30, 596, outlineLength, outlineBarWidth);
			g2.fillRect(33, 599, innerLength * Player.shotgunDelta / ShotgunBullet.FIRE_RATE, innerBarWidth);
		} else {
			g2.drawString("LOCKED:", 30, 609);
		}
		//Missile
		if(Player.missileUnlock) {
			g2.drawRect(30, 620, outlineLength, outlineBarWidth);
			g2.fillRect(33, 623, innerLength * Player.missleAmmo / 10, innerBarWidth);
		} else {
			g2.drawString("LOCKED:", 30, 635);
		}
	}
	
	/**
	 * Slowly fades in a black rectangle the size of the screen.
	 * When the rectangle is fully black it will return true.
	 * @return False when rectangle is transparent, False when Opaque
	 */
	public boolean fadeOut() {
		g2.setColor(new Color(0, 0, 0, alpha)); 
		g2.fillRect(0, 0, width, height);
		alpha += 0.02f;
		if(alpha >= 1) {
			return true;
		} else return false;
	}

	/**
	 * Disposes the g2 object freeing up system memory.
	 */
	public void g2Dispose() {
		g2.dispose();
	}
}
