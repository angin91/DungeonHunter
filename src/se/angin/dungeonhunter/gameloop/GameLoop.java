package se.angin.dungeonhunter.gameloop;

import se.angin.dungeonhunter.gamestate.GameStateManager;
import se.angin.dungeonhunter.main.Assets;
import se.angin.gop.main.IDGameLoop;
import se.angin.gop.main.Vector2F;

public class GameLoop extends IDGameLoop {

	GameStateManager gsm;
	public static Assets assets = new Assets();
	
	public GameLoop(int width, int height) {
		super(width, height);
	}
	
	@Override
	public void init() {
		assets.init();
		gsm = new GameStateManager();
		gsm.init();
		super.init();
	}
	
	@Override
	public void tick(double deltaTime) {
		gsm.tick(deltaTime);
	}
	
	@Override
	public void render() {
		super.render();
		gsm.render(graphics2D);
		clear();
	}
	
	@Override
	public void clear() {
		super.clear();
	}

}
