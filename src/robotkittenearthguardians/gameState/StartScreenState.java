package robotkittenearthguardians.gameState;

import java.awt.image.BufferStrategy;

import robotkittenearthguardians.MainGame;
import robotkittenearthguardians.entity.mob.Player;
import robotkittenearthguardians.graphics.Camera;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.input.Keyboard;
import robotkittenearthguardians.level.Level;

public class StartScreenState extends GameState {

	public StartScreenState(Keyboard key) {
		this.key = key;
		level = new Level();
		player = new Player(850, 430, key);
		camera = new Camera(MainGame.getScreenWidth(), MainGame.getScreenHeight());
	}

	public void update() {
		level.update();
	}
	
	public void render(Screen screen, BufferStrategy bs) {
		screen.update(bs);
		screen.clear();

		screen.background();
		
		camera.render(screen, player);
		level.render(screen);
		screen.drawImage();
		screen.gui(player);
		screen.g2Dispose();
		bs.show();
	}
}
