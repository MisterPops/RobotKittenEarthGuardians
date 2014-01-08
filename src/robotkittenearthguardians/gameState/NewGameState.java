package robotkittenearthguardians.gameState;

import java.awt.image.BufferStrategy;

import robotkittenearthguardians.entity.mob.Player;
import robotkittenearthguardians.graphics.Camera;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.graphics.SpriteSheets;
import robotkittenearthguardians.input.Keyboard;
import robotkittenearthguardians.level.Level;

public class NewGameState extends GameState{

	public NewGameState(Keyboard key, Camera camera) {
		this.key = key;
		level = new Level();
		player = new Player(850, 430, key);
		this.camera = camera;
		this.bg = SpriteSheets.mainBg;
	}

	public void update() {
		level.update();
	}
	
	public void render(Screen screen, BufferStrategy bs) {
		screen.update(bs);
		screen.clear();

		camera.render(screen, player);
		screen.renderBackground(bg, true);
		level.render(screen);
		screen.drawImage();
		screen.gui(this);
		screen.g2Dispose();
		bs.show();
	}
}
