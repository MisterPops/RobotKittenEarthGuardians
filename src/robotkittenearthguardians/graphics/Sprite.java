package robotkittenearthguardians.graphics;

import robotkittenearthguardians.util.Vector2I;

public class Sprite {

	private final Vector2I spriteSize;
	private int x, y;
	private int[] pixels;
	private SpriteSheets sheet;
	
	//Player (!moving) sprites.
	public static Sprite[] player = spriteArray(32, 32, SpriteSheets.player);
	
	//<------Enemies------->
	//Basic Enemy: WaterBalloon enemy.
	public static Sprite[] waterBalloon = spriteArray(32, 32, SpriteSheets.waterBalloon);
	
	//Pack Enemy: Water Bottle Pack
	public static Sprite[] waterBottlePack = spriteArray(75, 48, SpriteSheets.waterBottlePack);
	//Water Bottle Single
	public static Sprite[] waterBottleSingle = spriteArray(24, 32, SpriteSheets.waterBottleSingle);
	
	//Strong Enemy: Watergun Mech
	public static Sprite[] waterGunMech = spriteArray(51, 56, SpriteSheets.waterGunMech);
	
	//<----Projectiles/Particles----->
	//mainBullet
	public static Sprite[] mainBullet = {new Sprite(16, 16, 0 , 0, SpriteSheets.mainBullet)};
	//Shotgun Bullet
	public static Sprite[] shotgunBullet = spriteArray(16, 16, SpriteSheets.shotgunBullet);
	//Missle
	public static Sprite[] missle = spriteArray(32, 32, SpriteSheets.missle);
	//Smoke Trail
	public static Sprite[] smokeTrail = spriteArray(32, 32, SpriteSheets.smokeTrail);
	//mainBullet impact
	public static Sprite[] bulletImpact = spriteArray(16, 16, SpriteSheets.bulletImpact);
	
	public static Sprite[] mainExplosion = spriteArray(64, 64, SpriteSheets.mainExplosion);
	
	/**
	 * Constructore used to set a specific sprite.
	 * @param size This will be the size of the sprite. i.e. 16, 32, 64
	 * @param x The x-coord of the sprite on the sheet. (Tile by tile basis).
	 * @param y The y-coord of the sprite on the sheet. (Tile by tile basis).
	 * @param sheet The sheet the sprite is stored in.
	 */
	public Sprite(int sizeX, int sizeY, int x, int y, SpriteSheets sheet) {
		spriteSize = new Vector2I(sizeX, sizeY);
		pixels = new int[spriteSize.getXVector() * spriteSize.getYVector()];
		this.x = x * spriteSize.getXVector();
		this.y = y * spriteSize.getYVector();
		this.sheet = sheet;
		load();
	}
	
	public static Sprite[] spriteArray(int sizeX, int sizeY, SpriteSheets sheet) {
		int spriteXnum = sheet.getXSheetSize() / sizeX;
		int spriteYnum = sheet.getYSheetSize() / sizeY;
		Sprite[] array = new Sprite[spriteXnum * spriteYnum];
		
		for(int indexY = 0; indexY < spriteYnum; indexY++) {
			for(int indexX = 0; indexX < spriteXnum; indexX++) {
				array[indexX + indexY * spriteXnum] = new Sprite(sizeX, sizeY, indexX, indexY, sheet);
			}
		}
		
		return array;
	}
	
	/**
	 * Loads the sprite from the given SpriteSheet into a Sprite int[] pixels array.
	 */
	private void load() {
		int[] SpriteSheetArray = sheet.getPixels();
		for(int y = 0; y < spriteSize.getYVector(); y++) {
			for(int x = 0; x < spriteSize.getXVector(); x++) {
				pixels[x + y * spriteSize.getXVector()] = SpriteSheetArray[(x + this.x) + (y + this.y) * sheet.getXSheetSize()];
			}
		}
	}
	
	/**
	 * Returns an int in i of Sprite int array pixels
	 * @param i The part of pixels you want to access
	 * @return What is in pixels[i]
	 */
	public int getSpritePixel(int i) {
		return pixels[i];
	}
	
	/**
	 * Returns the x-size of the requested Sprite
	 * @return returns the size of the Sprite.
	 */
	public int getSizeX() {
		return spriteSize.getXVector();
	}
	
	/**
	 * Returns the y-size of the requested Sprite
	 * @return returns the size of the Sprite.
	 */
	public int getSizeY() {
		return spriteSize.getYVector();
	}
	
	public static int getAnimationLoopSize(Sprite[] sprite) {
		return sprite[0].sheet.getXSheetSize() / sprite[0].getSizeX();
		/*
		int loopSize = 0;
		
		while(loopSize < sprite.length) {
			if(sprite[loopSize].y != 0) {
				return loopSize;
			}
			
			loopSize++;
		}
		return loopSize;*/
	}
}
