package se.angin.dungeonhunter.gamestates;

import java.awt.Graphics2D;

import se.angin.dungeonhunter.gamestate.GameState;
import se.angin.dungeonhunter.gamestate.GameStateButton;
import se.angin.dungeonhunter.gamestate.GameStateManager;
import se.angin.dungeonhunter.main.Main;
import se.angin.dungeonhunter.managers.MouseManager;

public class QuitState extends GameState{

	GameStateButton yes;
	GameStateButton no;
	MouseManager mouseManager;
	
	public QuitState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		mouseManager = new MouseManager();
		yes = new GameStateButton(Main.width/3, 100, "Yes!");
		no = new GameStateButton(Main.width/3 + 300, 100, "No!");
	}

	@Override
	public void tick(double deltaTime) {
		mouseManager.tick();
		
		yes.tick();
		no.tick();
		
		if(yes.isHeldOver()){
			if(yes.isPressed()){
				System.exit(1);
			}
		}
		if(no.isHeldOver()){
			if(no.isPressed()){
				gsm.states.push(new MenuState(gsm));
				gsm.states.peek().init();
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		
		yes.render(g);
		no.render(g);
		
		mouseManager.render(g);
		g.clipRect(0, 0, Main.width, Main.height);
	}

}