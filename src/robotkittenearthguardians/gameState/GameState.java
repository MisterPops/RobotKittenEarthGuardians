package robotkittenearthguardians.gameState;

import java.awt.image.BufferStrategy;

import robotkittenearthguardians.entity.mob.Player;
import robotkittenearthguardians.graphics.Camera;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.SpriteSheets;
import robotkittenearthguardians.input.Keyboard;
import robotkittenearthguardians.level.Level;

public class GameState {
		
	protected StartScreenState currentState;
	protected Keyboard key;
	protected Level level;
	protected Player player;
	protected Camera camera;
	protected BufferStrategy bs;
	protected SpriteSheets bg;
		
	public GameState() {
	}
		
	public void update() {
	}
		
	public void render(Screen screen, BufferStrategy bs) {
	}
	
	public Player getPlayer() {
		return player;
	}
}