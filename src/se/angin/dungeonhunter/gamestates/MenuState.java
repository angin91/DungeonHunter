package se.angin.dungeonhunter.gamestates;

import java.awt.Graphics2D;

import se.angin.dungeonhunter.gamestate.GameState;
import se.angin.dungeonhunter.gamestate.GameStateButton;
import se.angin.dungeonhunter.gamestate.GameStateManager;
import se.angin.dungeonhunter.main.Main;
import se.angin.dungeonhunter.managers.MouseManager;

public class MenuState extends GameState{

	GameStateButton startGame;
	GameStateButton multiPlayer;
	GameStateButton options;
	GameStateButton quit;
	MouseManager mouseManager;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		mouseManager = new MouseManager();
		startGame = new GameStateButton(Main.width/3, 200, new DungeonLevelLoader(gsm), gsm, "Start Game");
		multiPlayer = new GameStateButton(Main.width/3, 300, new DungeonLevelLoader(gsm), gsm, "Multiplayer");
		options = new GameStateButton(Main.width/3, 400, new DungeonLevelLoader(gsm), gsm, "Options");
		quit = new GameStateButton(Main.width/3, 500, new QuitState(gsm), gsm, "Quit");
	}

	@Override
	public void tick(double deltaTime) {
		mouseManager.tick();
		
		startGame.tick();
		multiPlayer.tick();
		options.tick();
		quit.tick();
	}

	@Override
	public void render(Graphics2D g) {
		
		startGame.render(g);
		multiPlayer.render(g);
		options.render(g);
		quit.render(g);
		
		mouseManager.render(g);

		g.clipRect(0, 0, Main.width, Main.height);
	}

}