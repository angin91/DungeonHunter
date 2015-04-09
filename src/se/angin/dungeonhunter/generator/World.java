package se.angin.dungeonhunter.generator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;

import se.angin.dungeonhunter.gamestate.GameStateManager;
import se.angin.dungeonhunter.gamestates.DungeonLevelLoader;
import se.angin.dungeonhunter.generator.Block.BlockType;
import se.angin.dungeonhunter.main.Main;
import se.angin.dungeonhunter.movableobjects.Player;
import se.angin.gop.main.LoadImageFrom;
import se.angin.gop.main.Vector2F;

public class World {

	public static Vector2F mapPos = new Vector2F();
	public static BufferedImage map;
	private String worldName;
	private int worldWidth;
	private int worldHeight;
	private int blockSize = 48;
	private static Player player;
	private boolean hasGenerated;
	
	//LISTS
	private CopyOnWriteArrayList<BlockEntity> blockEntities;
	public TileManager tiles;
	
	//WORLD SPAWN
	private Block spawn;
	
	//BOOLEANS
	private boolean hasSize = false;
	private GameStateManager gsm;
	
	public World(String worldName, GameStateManager gsm) {
		this.worldName = worldName;
		this.gsm = gsm;
		Vector2F.setWorldVaribles(mapPos.xPos, mapPos.yPos);
	}

	public void init() {
		blockEntities = new CopyOnWriteArrayList<BlockEntity>();
		tiles = new TileManager(this);

		mapPos.xPos = spawn.getBlockLocation().xPos - player.getPos().xPos;
		mapPos.yPos = spawn.getBlockLocation().yPos - player.getPos().yPos;
		
		if(player != null){
			player.init(this);
		}
	}

	public void tick(double deltaTime) {
		Vector2F.setWorldVaribles(mapPos.xPos, mapPos.yPos);
		
		if(!player.hasSpawned()){
			spawn.tick(deltaTime);
		}
		
		tiles.tick(deltaTime);

		if(!blockEntities.isEmpty()){
			for(BlockEntity entity : blockEntities){
				if(player.render.intersects(entity)){
					entity.tick(deltaTime);
					entity.setAlive(true);
				}else{
					entity.setAlive(false);
				}
			}
		}
		
		if(player != null){
			player.tick(deltaTime);
		}
	}

	public void render(Graphics2D g) {
		tiles.render(g);
		
		if(!player.hasSpawned()){
			spawn.render(g);
		}
		for(BlockEntity entity : blockEntities){
			if(player.render.intersects(entity)){
				entity.render(g);
			}
		}
		
		if(player != null){
			player.render(g);
		}
	}

	public void generate(String worldImageName) {
		
		map = null;
		if(hasSize){
			try{
				map = LoadImageFrom.LoadImageFrom(Main.class, worldImageName + ".png");
			}catch(Exception e){
				e.printStackTrace();
			}
			
			for (int x = 0; x < worldWidth; x++) {
				for (int y = 0; y < worldHeight; y++) {
					
					int col = map.getRGB(x, y);
					
					switch (col & 0xFFFFFF) {
					case 0x808080:
						tiles.blocks.add(new Block(new Vector2F(x * 48, y * 48), BlockType.STONE_1));
						break;
					case 0x9B7555:
						TileManager.blocks.add(new Block(new Vector2F(x * 48, y * 48), BlockType.STONE_GROUND_1));
						break;
					case 0x7F5F47:
						tiles.blocks.add(new Block(new Vector2F(x * 48, y * 48), BlockType.STONE_WALL_LEFT_1).isSolid(true));
						break;
					case 0x7F5F48:
						tiles.blocks.add(new Block(new Vector2F(x * 48, y * 48), BlockType.STONE_WALL_TOP_1).isSolid(true));
						break;
					case 0x7F5F49:
						tiles.blocks.add(new Block(new Vector2F(x * 48, y * 48), BlockType.STONE_WALL_RIGHT_1).isSolid(true));
						break;
					case 0x7F5F50:
						tiles.blocks.add(new Block(new Vector2F(x * 48, y * 48), BlockType.STONE_WALL_DOWN_1).isSolid(true));
						break;
					case 0x7F5F51:
						tiles.blocks.add(new Block(new Vector2F(x * 48, y * 48), BlockType.STONE_WALL_CORNER_LEFT_TOP_1).isSolid(true));
						break;
					case 0x7F5F52:
						tiles.blocks.add(new Block(new Vector2F(x * 48, y * 48), BlockType.STONE_WALL_CORNER_RIGHT_TOP_1).isSolid(true));
						break;
					case 0x7F5F53:
						tiles.blocks.add(new Block(new Vector2F(x * 48, y * 48), BlockType.STONE_WALL_CORNER_LEFT_DOWN_1).isSolid(true));
						break;
					case 0x7F5F54:
						tiles.blocks.add(new Block(new Vector2F(x * 48, y * 48), BlockType.STONE_WALL_CORNER_RIGHT_DOWN_1).isSolid(true));
						break;
					case 0x7F5F55:
						tiles.blocks.add(new Block(new Vector2F(x * 48, y * 48), BlockType.STONE_WALL_UP_DOWN_PLATEAU_BOTTOM_1).isSolid(true));
						break;
					case 0x7F5F56:
						tiles.blocks.add(new Block(new Vector2F(x * 48, y * 48), BlockType.STONE_WALL_LEFT_RIGHT_PLATEAU_BOTTOM_1).isSolid(true));
						break;
					case 0x404040:
						tiles.blocks.add(new Block(new Vector2F(x * 48, y * 48), BlockType.WALL_1).isSolid(true));
						break;
					case 0x606060:
						tiles.blocks.add(new Block(new Vector2F(x * 48, y * 48), BlockType.WALL_TOP).isSolid(true));
						break;
					case 0x844E3E:
						tiles.blocks.add(new Block(new Vector2F(x * 48, y * 48), BlockType.WOOD_FLOOR_1));
						break;
					case 0x505050:
						tiles.blocks.add(new Block(new Vector2F(x * 48, y * 48), BlockType.STAIR_1));
						break;
					}
					
				}
			}
		}
		
		hasGenerated = true;
	}

	public void setSize(int worldWidth, int worldHeight) {
		this.worldWidth = worldWidth;
		this.worldHeight = worldHeight;
		hasSize = true;
	}
	
	
	public Vector2F getWorldPos(){
		return mapPos;
	}

	public float getWorldXPos(){
		return mapPos.xPos;
	}

	public float getWorldYPos(){
		return mapPos.yPos;
	}

	public void addPlayer(Player player) {
		this.player = player;
	}
	
	public void dropBlockEntity(Vector2F pos, BufferedImage block_image){
		BlockEntity entity = new BlockEntity(pos, block_image);
		if(!blockEntities.contains(entity)){
			blockEntities.add(entity);
		}
	}
	
	public void setWorldSpawn(float xPos, float yPos){
		if(xPos < worldWidth){
			if(yPos < worldHeight){
				Block spawn = new Block(new Vector2F(xPos*blockSize, yPos*blockSize));
				this.spawn = spawn;
			}
		}
	}
	
	public Vector2F getWorldSpawn(){
		return spawn.pos;
	}
	
	public void removeDropedEntity(BlockEntity entity) {
		if(blockEntities.contains(entity)){
			blockEntities.remove(entity);
		}
	}
	
	public TileManager getWorldBlocks() {
		return tiles;
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	public boolean hasGenerated() {
		return hasGenerated;
	}
	
	public void resetWorld(){
		tiles.getBlocks().clear();
		tiles.getLoadedBlocks().clear();
		blockEntities.clear();
		spawn=null;
	}
	
	public void changeToWorld(String worldName, String mapName){
		if(worldName != this.worldName){
			resetWorld();
			gsm.states.push(new DungeonLevelLoader(gsm, worldName, mapName));
			gsm.states.peek().init();
		}
	}

}
