package se.angin.dungeonhunter.managers;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import se.angin.dungeonhunter.generator.World;
import se.angin.dungeonhunter.main.Main;
import se.angin.dungeonhunter.movableobjects.Player;
import se.angin.gop.main.Light;
import se.angin.gop.main.LoadImageFrom;
import se.angin.gop.main.Vector2F;

public class HUDManager {

	private BufferedImage lightMap = new BufferedImage(100*32, 100*32, BufferedImage.TYPE_INT_ARGB);
	private BufferedImage playerLight;
	private ArrayList<Light> lights = new ArrayList<Light>();
	private Vector2F lightM = new Vector2F();
	private World world;
	
	public HUDManager(World world) {
		this.world = world;
		playerLight = LoadImageFrom.LoadImageFrom(Main.class, "playerlight.png");
		addLights();
	}

	private void addLights() {

		lights.add(new Light(200, 200, 920, 255));
		lights.add(new Light(400, 400, 220, 255));
		lights.add(new Light(350, 300, 220, 155));

//		UpdateLights();
	}
	
//	public void UpdateLights(){
//		Graphics2D g = null;
//		if(g == null){
//			g = (Graphics2D) lightMap.getGraphics();
//		}
//		g.setColor(new Color(0, 0, 0, 255));
//		g.fillRect(0, 0, lightMap.getWidth(), lightMap.getHeight());
//		g.setComposite(AlphaComposite.DstOut);
//		
//		for(Light light : lights){
//			light.render(g);
//		}
//		g.dispose();
//	}
	

	public void render(Graphics2D g) {
		
		g.drawImage(playerLight, 0, 0, Main.width, Main.height, null);
//		g.drawImage(lightMap, (int) lightM.getWorldLocation().xPos, (int) lightM.getWorldLocation().yPos, null);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Main.width, Main.height/6);
		g.fillRect(0, Main.height - Main.height/6, Main.width, Main.height/6);
		g.setColor(Color.WHITE);
		
		if(world.getPlayer().isDebugging()){
			g.drawString("[DEBUG]", 30, 20);
			g.drawString("[MAP_XPOS] " + world.getWorldXPos(), 30, 60);
			g.drawString("[MAP_YPOS] " + world.getWorldYPos(), 30, 90);
			g.drawString("[PLAYER_XPOS] " + world.getPlayer().getPos().xPos, 30, 120);
			g.drawString("[PLAYER_YPOS] " + world.getPlayer().getPos().yPos, 30, 150);
			g.drawString("[CURRENT_WORLD_BLOCKS] " + world.getWorldBlocks().getBlocks().size(), 30, 180);
			g.drawString("[CURRENT_LOADED_WORLD_BLOCKS] " + world.getWorldBlocks().getLoadedBlocks().size(), 30, 210);
			
		}
	}
}
