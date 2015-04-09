package se.angin.dungeonhunter.main;

import java.awt.image.BufferedImage;

import se.angin.gop.main.LoadImageFrom;
import se.angin.gop.main.SpriteSheet;

public class Assets {

	SpriteSheet blocks = new SpriteSheet();
	public static SpriteSheet player = new SpriteSheet();

	//PLAYER ATTACK
	public static BufferedImage attack_up;
	public static BufferedImage attack_down;
	public static BufferedImage attack_right;
	public static BufferedImage attack_left;
	
	//MOUSE TEXTURE
	public static BufferedImage mouse_pressed;
	public static BufferedImage mouse_realesed;
	
	//BUTTON TEXTURE
	public static BufferedImage button_heldover;
	public static BufferedImage button_notover;

	//MAP TEXTURE
	public static BufferedImage stone_1;
	public static BufferedImage stone_ground_1;
	
	//HOUSE TEXTURE
	public static BufferedImage wall_1;
	public static BufferedImage wall_top;
	public static BufferedImage wood_floor_1;
	
	//CAVE WALL
	public static BufferedImage stone_wall_left_1;
	public static BufferedImage stone_wall_top_1;
	public static BufferedImage stone_wall_right_1;
	public static BufferedImage stone_wall_down_1;
	public static BufferedImage stone_wall_corner_left_top_1;
	public static BufferedImage stone_wall_corner_right_top_1;
	public static BufferedImage stone_wall_corner_left_down_1;
	public static BufferedImage stone_wall_corner_right_down_1;
	public static BufferedImage stone_wall_up_down_plateau_bottom_1;
	public static BufferedImage stone_wall_left_right_plateau_bottom_1;

	//STAIRS
	public static BufferedImage stair_1;

	
	public void init(){
		blocks.setSpriteSheet(LoadImageFrom.LoadImageFrom(Main.class, "TextureSheet.png"));
		player.setSpriteSheet(LoadImageFrom.LoadImageFrom(Main.class, "EntitySheet.png"));

		//PLAYER ATTACK
		attack_up = player.getTile(0, 64+16, 16, 16);
		attack_down = player.getTile(16, 64+16, 16, 16);
		attack_right = player.getTile(32, 64+16, 16, 16);
		attack_left = player.getTile(48, 64+16, 16, 16);
		
		//MOUSE TEXTURE
		mouse_realesed = player.getTile(64+32, 8, 8, 8);
		mouse_pressed = player.getTile(64+32+8, 8, 8, 8);

		//BUTTON TEXTURE
		button_heldover = player.getTile(64+64+32, 32, 48, 16);
		button_notover = player.getTile(64+48, 32, 48, 16);

		//MAP TEXTURE
		stone_1 = blocks.getTile(0, 0, 16, 16);
		stone_ground_1 = blocks.getTile(16, 0, 16, 16);

		//HOUSE TEXTURE
		wall_1 = blocks.getTile(16, 16, 16, 16);
		wall_top = blocks.getTile(32, 16, 16, 16);
		wood_floor_1 = blocks.getTile(0, 16, 16, 16);
		
		//CAVE WALL
		stone_wall_left_1 = blocks.getTile(32, 0, 16, 16);
		stone_wall_top_1 = blocks.getTile(48, 0, 16, 16);
		stone_wall_right_1 = blocks.getTile(64, 0, 16, 16);
		stone_wall_down_1 = blocks.getTile(64+16, 0, 16, 16);
		stone_wall_corner_left_top_1 = blocks.getTile(64+32, 0, 16, 16);
		stone_wall_corner_right_top_1 = blocks.getTile(64+48, 0, 16, 16);
		stone_wall_corner_left_down_1 = blocks.getTile(64+64, 0, 16, 16);
		stone_wall_corner_right_down_1 = blocks.getTile(64+64+16, 0, 16, 16);
		stone_wall_up_down_plateau_bottom_1 = blocks.getTile(64+64+32, 0, 16, 16);
		stone_wall_left_right_plateau_bottom_1 = blocks.getTile(64+64+48, 0, 16, 16);
		
		//STAIRS
		stair_1 = blocks.getTile(48, 16, 16, 16);
	}
	
	
	
	public static BufferedImage getMouse_pressed() {
		return mouse_pressed;
	}
	public static BufferedImage getMouse_realesed() {
		return mouse_realesed;
	}
	public static BufferedImage getButton_heldover() {
		return button_heldover;
	}
	public static BufferedImage getButton_notover() {
		return button_notover;
	}
	public static BufferedImage getStone_1() {
		return stone_1;
	}
	public static BufferedImage getStone_ground_1() {
		return stone_ground_1;
	}
	public static BufferedImage getWall_1() {
		return wall_1;
	}
	public static BufferedImage getWood_floor_1() {
		return wood_floor_1;
	}
	public static BufferedImage getWall_top() {
		return wall_top;
	}
	public static BufferedImage getStone_wall_left_1() {
		return stone_wall_left_1;
	}
	public static BufferedImage getStone_wall_top_1() {
		return stone_wall_top_1;
	}
	public static BufferedImage getStone_wall_right_1() {
		return stone_wall_right_1;
	}
	public static BufferedImage getStone_wall_down_1() {
		return stone_wall_down_1;
	}
	public static BufferedImage getStone_wall_corner_left_top_1() {
		return stone_wall_corner_left_top_1;
	}
	public static BufferedImage getStone_wall_corner_right_top_1() {
		return stone_wall_corner_right_top_1;
	}
	public static BufferedImage getStone_wall_corner_left_down_1() {
		return stone_wall_corner_left_down_1;
	}
	public static BufferedImage getStone_wall_corner_right_down_1() {
		return stone_wall_corner_right_down_1;
	}
	public static BufferedImage getStair_1() {
		return stair_1;
	}
	public static BufferedImage getStone_wall_up_down_plateau_bottom_1() {
		return stone_wall_up_down_plateau_bottom_1;
	}
	public static BufferedImage getStone_wall_left_right_plateau_bottom_1() {
		return stone_wall_left_right_plateau_bottom_1;
	}
	public static BufferedImage getAttack_up() {
		return attack_up;
	}
	public static BufferedImage getAttack_down() {
		return attack_down;
	}
	public static BufferedImage getAttack_right() {
		return attack_right;
	}
	public static BufferedImage getAttack_left() {
		return attack_left;
	}
}
