package robotkittenearthguardians.graphics;

import robotkittenearthguardians.util.Vector2I;

public class Sprite {

	private final Vector2I spriteSize;
	private int x, y;
	private int[] pixels;
	private SpriteSheets sheet;
	
	//Player (!moving) sprites.
	public static Sprite[] player = {new Sprite(32, 32, 0, 0, SpriteSheets.player), new Sprite(32, 32, 1, 0, SpriteSheets.player),
		new Sprite(32, 32, 2, 0, SpriteSheets.player), new Sprite(32, 32, 3, 0, SpriteSheets.player),
		new Sprite(32, 32, 0, 1, SpriteSheets.player), new Sprite(32, 32, 1, 1, SpriteSheets.player),
		new Sprite(32, 32, 2, 1, SpriteSheets.player), new Sprite(32, 32, 3, 1, SpriteSheets.player),
		new Sprite(32, 32, 0, 2, SpriteSheets.player), new Sprite(32, 32, 1, 2, SpriteSheets.player),
		new Sprite(32, 32, 2, 2, SpriteSheets.player), new Sprite(32, 32, 3, 2, SpriteSheets.player),
		new Sprite(32, 32, 0, 3, SpriteSheets.player), new Sprite(32, 32, 1, 3, SpriteSheets.player),
		new Sprite(32, 32, 2, 3, SpriteSheets.player), new Sprite(32, 32, 3, 3, SpriteSheets.player),
		new Sprite(32, 32, 0, 4, SpriteSheets.player), new Sprite(32, 32, 1, 4, SpriteSheets.player),
		new Sprite(32, 32, 2, 4, SpriteSheets.player), new Sprite(32, 32, 3, 4, SpriteSheets.player)};
	
	//Enemies
	//Basic Enemy: WaterBalloon enemy.
	public static Sprite[] waterBalloon = {new Sprite(32, 32, 0, 0, SpriteSheets.waterBalloon),
		new Sprite(32, 32, 1, 0, SpriteSheets.waterBalloon), new Sprite(32, 32, 2, 0, SpriteSheets.waterBalloon),
		new Sprite(32, 32, 3, 0, SpriteSheets.waterBalloon), new Sprite(32, 32, 4, 0, SpriteSheets.waterBalloon),
		new Sprite(32, 32, 5, 0, SpriteSheets.waterBalloon), new Sprite(32, 32, 6, 0, SpriteSheets.waterBalloon),
		new Sprite(32, 32, 7, 0, SpriteSheets.waterBalloon)};
	
	//Pack Enemy: Water Bottle Pack
	public static Sprite[] waterBottlePack = {new Sprite(75, 48, 0, 0, SpriteSheets.waterBottlePack), 
		new Sprite(75, 48, 1, 0, SpriteSheets.waterBottlePack), new Sprite(75, 48, 2, 0, SpriteSheets.waterBottlePack), 
		new Sprite(75, 48, 0, 1, SpriteSheets.waterBottlePack), new Sprite(75, 48, 1, 1, SpriteSheets.waterBottlePack), 
		new Sprite(75, 48, 2, 1, SpriteSheets.waterBottlePack)};
	//Water Bottle Single
	public static Sprite[] waterBottleSingle = {new Sprite(24, 32, 0, 0, SpriteSheets.waterBottleSingle), 
		new Sprite(24, 32, 1, 0, SpriteSheets.waterBottleSingle), new Sprite(24, 32, 2, 0, SpriteSheets.waterBottleSingle), 
		new Sprite(24, 32, 0, 1, SpriteSheets.waterBottleSingle), new Sprite(24, 32, 1, 1, SpriteSheets.waterBottleSingle), 
		new Sprite(24, 32, 2, 1, SpriteSheets.waterBottleSingle)};
	
	//Strong Enemy: Watergun Mech
	public static Sprite[] waterGunMech = {new Sprite(52, 56, 0, 0, SpriteSheets.waterGunMech), 
		new Sprite(52, 56, 1, 0, SpriteSheets.waterGunMech), new Sprite(52, 56, 2, 0, SpriteSheets.waterGunMech), 
		new Sprite(52, 56, 3, 0, SpriteSheets.waterGunMech), new Sprite(52, 56, 0, 1, SpriteSheets.waterGunMech), 
		new Sprite(52, 56, 1, 1, SpriteSheets.waterGunMech), new Sprite(52, 56, 2, 1, SpriteSheets.waterGunMech), 
		new Sprite(52, 56, 3, 1, SpriteSheets.waterGunMech)};
	
	//Projectiles
	//mainBullet
	public static Sprite[] mainBullet = {new Sprite(16, 16, 0 , 0, SpriteSheets.mainBullet)};
	//mainBullet impact
	public static Sprite[] bulletImpact = {new Sprite(16, 16, 0 , 0, SpriteSheets.bulletImpact),
		new Sprite(16, 16, 1 , 0, SpriteSheets.bulletImpact), new Sprite(16, 16, 2 , 0, SpriteSheets.bulletImpact),
		new Sprite(16, 16, 3 , 0, SpriteSheets.bulletImpact), new Sprite(16, 16, 4 , 0, SpriteSheets.bulletImpact),
		new Sprite(16, 16, 5 , 0, SpriteSheets.bulletImpact), new Sprite(16, 16, 6 , 0, SpriteSheets.bulletImpact),
		new Sprite(16, 16, 7 , 0, SpriteSheets.bulletImpact)};
	
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
		int loopSize = 0;
		
		while(loopSize < sprite.length) {
			if(sprite[loopSize].y != 0) {
				return loopSize;
			}
			
			loopSize++;
		}
		return loopSize;
	}
}
