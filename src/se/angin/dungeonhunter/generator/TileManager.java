package se.angin.dungeonhunter.generator;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import se.angin.dungeonhunter.movableobjects.Player;

public class TileManager {

	public static CopyOnWriteArrayList<Block> blocks = new CopyOnWriteArrayList<Block>();
	public static CopyOnWriteArrayList<Block> loadedBlocks = new CopyOnWriteArrayList<Block>();
	private World world;
	
	public TileManager(World world) {
		this.world = world;
	}

	public void tick(double deltaTime){
		for(Block block: blocks){
			block.tick(deltaTime);
			
			if(Player.render.intersects(block)){
				block.setAlive(true);
				
				if(!loadedBlocks.contains(block)){
					loadedBlocks.add(block);
				}
			}else{
				if(loadedBlocks.contains(block)){
					loadedBlocks.remove(block);
				}
				block.setAlive(false);
			}
		}
		
		if(!world.getPlayer().isDebugging()){
			if(!loadedBlocks.isEmpty()){
				loadedBlocks.clear();
			}
		}
	}
	
	public void render(Graphics2D g){
		for(Block block: blocks){
			block.render(g);
		}
	}
	
	public static CopyOnWriteArrayList<Block> getBlocks() {
		return blocks;
	}
	
	public static CopyOnWriteArrayList<Block> getLoadedBlocks() {
		return loadedBlocks;
	}
}
