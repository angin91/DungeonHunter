package se.angin.dungeonhunter.generator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import se.angin.dungeonhunter.main.Assets;
import se.angin.gop.main.Vector2F;

public class Block extends Rectangle{
	
	Vector2F pos = new Vector2F();
	private int blockSize = 48;
	private BlockType blockType;
	private BufferedImage block;
	private boolean isSolid;
	private boolean isAlive;
	private boolean dropped = false;
	
	public Block(Vector2F pos) {
		setBounds((int) pos.xPos, (int)pos.yPos, blockSize, blockSize);
		this.pos = pos;
		isAlive = true;
	}
	
	public Block(Vector2F pos, BlockType blockType) {
		setBounds((int) pos.xPos, (int)pos.yPos, blockSize, blockSize);
		this.pos = pos;
		isAlive = true;
		this.blockType = blockType;
		init();
	}
	

	public Block isSolid(boolean isSolid){
		this.isSolid = isSolid;
		return this;
	}
	
	public void init(){
		switch (blockType) {
		case STONE_1:
			block = Assets.getStone_1();
			break;
		case STONE_GROUND_1:
			block = Assets.getStone_ground_1();
			break;
		case WALL_1:
			block = Assets.getWall_1();
			break;
		case WALL_TOP:
			block = Assets.getWall_top();
			break;
		case WOOD_FLOOR_1:
			block = Assets.getWood_floor_1();
			break;
		case STONE_WALL_LEFT_1:
			block = Assets.getStone_wall_left_1();
			break;
		case STONE_WALL_TOP_1:
			block = Assets.getStone_wall_top_1();
			break;
		case STONE_WALL_RIGHT_1:
			block = Assets.getStone_wall_right_1();
			break;
		case STONE_WALL_DOWN_1:
			block = Assets.getStone_wall_down_1();
			break;
		case STONE_WALL_CORNER_LEFT_TOP_1:
			block = Assets.getStone_wall_corner_left_top_1();
			break;
		case STONE_WALL_CORNER_RIGHT_TOP_1:
			block = Assets.getStone_wall_corner_right_top_1();
			break;
		case STONE_WALL_CORNER_LEFT_DOWN_1:
			block = Assets.getStone_wall_corner_left_down_1();
			break;
		case STONE_WALL_CORNER_RIGHT_DOWN_1:
			block = Assets.getStone_wall_corner_right_down_1();
			break;
		case STONE_WALL_UP_DOWN_PLATEAU_BOTTOM_1:
			block = Assets.getStone_wall_up_down_plateau_bottom_1();
			break;
		case STONE_WALL_LEFT_RIGHT_PLATEAU_BOTTOM_1:
			block = Assets.getStone_wall_left_right_plateau_bottom_1();
			break;
		case STAIR_1:
			block = Assets.getStair_1();
			break;
		}
		
	}
	
	public void tick(double deltaTime){
		if(isAlive){
			setBounds((int) pos.xPos, (int)pos.yPos, blockSize, blockSize);
		}
	}
	
	public void render(Graphics2D g){
		if(isAlive){
			if(block != null){
				g.drawImage(block, (int)pos.getWorldLocation().xPos, (int)pos.getWorldLocation().yPos, blockSize, blockSize, null);
			}else{
				g.fillRect((int)pos.getWorldLocation().xPos, (int)pos.getWorldLocation().yPos, blockSize, blockSize);
			}
			
//			g.drawRect((int)pos.getWorldLocation().xPos, (int)pos.getWorldLocation().yPos, blockSize, blockSize);
//			if(isSolid){
//				g.drawRect((int)pos.getWorldLocation().xPos, (int)pos.getWorldLocation().yPos, blockSize, blockSize);
//			}
			
		}
	}

	public enum BlockType{
		//MAP TEXTURE
		STONE_1,
		STONE_GROUND_1,
		//CAVE WALL
		STONE_WALL_LEFT_1,
		STONE_WALL_TOP_1,
		STONE_WALL_RIGHT_1,
		STONE_WALL_DOWN_1,
		STONE_WALL_CORNER_LEFT_TOP_1,
		STONE_WALL_CORNER_RIGHT_TOP_1,
		STONE_WALL_CORNER_LEFT_DOWN_1,
		STONE_WALL_CORNER_RIGHT_DOWN_1,
		STONE_WALL_UP_DOWN_PLATEAU_BOTTOM_1,
		STONE_WALL_LEFT_RIGHT_PLATEAU_BOTTOM_1,
		//HOUSE
		WALL_1,
		WALL_TOP,
		WOOD_FLOOR_1,
		//STAIRS
		STAIR_1
	}

	public boolean isSolid() {
		return isSolid;
	}
	public boolean isAlive(){
		return isAlive;
	}
	
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public Vector2F getBlockLocation() {
		return pos;
	}
}
