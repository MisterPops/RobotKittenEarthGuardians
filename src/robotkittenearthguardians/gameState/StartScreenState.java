package robotkittenearthguardians.gameState;

import java.awt.image.BufferStrategy;

import robotkittenearthguardians.MainGame;
import robotkittenearthguardians.graphics.Camera;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.SpriteSheets;
import robotkittenearthguardians.input.Keyboard;
import robotkittenearthguardians.input.Mouse;

public class StartScreenState extends GameState {
	
	private boolean mouseClick = false;

	public StartScreenState(Keyboard key) {
		this.key = key;
		camera = new Camera(MainGame.getScreenWidth(), MainGame.getScreenHeight());
		bg = SpriteSheets.mainBg;
	}

	public void update() {
	}
	
	public void render(Screen screen, BufferStrategy bs) {
		if(Mouse.getMouseB() == 1) mouseClick = true;
		
		screen.update(bs);
		screen.clear();

		camera.render(screen, 600, 325);
		screen.renderBackground(bg, true);
		screen.renderTitle();
		screen.drawImage();
		screen.gui(this);
		
		//My crazy fade out method
		if(mouseClick) {
			if((screen.fadeOut())) {
				MainGame.currentState = new NewGameState(key, camera);
			}
		}
		
		screen.g2Dispose();
		bs.show();
	}
}
