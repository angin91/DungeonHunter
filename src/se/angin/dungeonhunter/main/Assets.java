package se.angin.dungeonhunter.main;

import java.awt.image.BufferedImage;

import se.angin.gop.main.LoadImageFrom;
import se.angin.gop.main.SpriteSheet;

public class Assets {

	SpriteSheet blocks = new SpriteSheet();
	public static SpriteSheet player = new SpriteSheet();

	//MOUSE
	public static BufferedImage mouse_pressed;
	public static BufferedImage mouse_realesed;
	
	//BUTTON
	public static BufferedImage button_heldover;
	public static BufferedImage button_notover;

	//MAP TEXTURE
	public static BufferedImage stone_1;
	public static BufferedImage wall_1;
	public static BufferedImage wall_top;
	public static BufferedImage wood_floor_1;

	
	public void init(){
		blocks.setSpriteSheet(LoadImageFrom.LoadImageFrom(Main.class, "spritesheet.png"));
		player.setSpriteSheet(LoadImageFrom.LoadImageFrom(Main.class, "PlayerSheet.png"));

		mouse_realesed = player.getTile(64+32, 8, 8, 8);
		mouse_pressed = player.getTile(64+32+8, 8, 8, 8);

		button_heldover = player.getTile(64+64+32, 32, 48, 16);
		button_notover = player.getTile(64+48, 32, 48, 16);
		
		stone_1 = blocks.getTile(0, 0, 16, 16);
		wall_1 = blocks.getTile(16, 0, 16, 16);
		wall_top = blocks.getTile(32, 0, 16, 16);
		wood_floor_1 = blocks.getTile(0, 16, 16, 16);
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
	public static BufferedImage getWall_1() {
		return wall_1;
	}
	public static BufferedImage getWood_floor_1() {
		return wood_floor_1;
	}
	public static BufferedImage getWall_top() {
		return wall_top;
	}
}
