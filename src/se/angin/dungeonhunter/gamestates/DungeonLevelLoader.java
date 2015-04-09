package se.angin.dungeonhunter.gamestates;

import java.awt.Graphics2D;

import se.angin.dungeonhunter.gamestate.GameState;
import se.angin.dungeonhunter.gamestate.GameStateManager;
import se.angin.dungeonhunter.generator.World;
import se.angin.dungeonhunter.main.Main;
import se.angin.dungeonhunter.movableobjects.Player;

public class DungeonLevelLoader extends GameState{
	
	public static World world;
	private String worldName;
	private String mapName;
	
	public DungeonLevelLoader(GameStateManager gsm) {
		super(gsm);
	}
	
	public DungeonLevelLoader(GameStateManager gsm, String worldName, String mapName) {
		super(gsm);
		this.worldName = worldName;
		this.mapName = mapName;
	}

	@Override
	public void init() {
		
		if(worldName == null){
			worldName = "NULL";
			mapName = "map";
		}
		world = new World(worldName, gsm);
		world.setSize(100, 100);
		world.setWorldSpawn(8, 8);
		world.addPlayer(new Player());
		
		world.init();
		world.generate(mapName);
	}

	@Override
	public void tick(double deltaTime) {
		if(world.hasGenerated()){
			world.tick(deltaTime);
		}
	}

	@Override
	public void render(Graphics2D g) {
		if(world.hasGenerated()){
			world.render(g);
		}
		g.clipRect(0, 0, Main.width, Main.height);
	}
}
