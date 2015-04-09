package se.angin.dungeonhunter.main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import se.angin.gop.main.LoadImageFrom;
import se.angin.gop.main.SpriteSheet;

public class Assets {

	SpriteSheet blocks = new SpriteSheet();
	public static SpriteSheet player = new SpriteSheet();
	
	public static long animationSpeed = 180;

	//PLAYER
	/*
	 * 0 = up
	 * 1 = down
	 * 2 = right
	 * 3 = left
	 * 4 = idel
	 * */
	private ArrayList<BufferedImage> listUp;
	public static Animator ani_up;
	private ArrayList<BufferedImage> listDown;
	public static Animator ani_down;
	private ArrayList<BufferedImage> listRight;
	public static Animator ani_right;
	private ArrayList<BufferedImage> listLeft;
	public static Animator ani_left;
	private ArrayList<BufferedImage> listIdelUp;
	public static Animator ani_idel_up;
	private ArrayList<BufferedImage> listIdelDown;
	public static Animator ani_idel_down;
	private ArrayList<BufferedImage> listIdelRight;
	public static Animator ani_idel_right;
	private ArrayList<BufferedImage> listIdelLeft;
	public static Animator ani_idel_left;
	
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
		
		listUp = new ArrayList<BufferedImage>();
		listDown = new ArrayList<BufferedImage>();
		listRight = new ArrayList<BufferedImage>();
		listLeft = new ArrayList<BufferedImage>();
		listIdelUp = new ArrayList<BufferedImage>();
		listIdelDown = new ArrayList<BufferedImage>();
		listIdelRight = new ArrayList<BufferedImage>();
		listIdelLeft = new ArrayList<BufferedImage>();

		listUp.add(Assets.player.getTile(0, 0, 16, 16));
		listUp.add(Assets.player.getTile(16, 0, 16, 16));
		
		listDown.add(Assets.player.getTile(0, 16, 16, 16));
		listDown.add(Assets.player.getTile(16, 16, 16, 16));

		listRight.add(Assets.player.getTile(32, 16, 16, 16));
		listRight.add(Assets.player.getTile(48, 16, 16, 16));
		listRight.add(Assets.player.getTile(64, 16, 16, 16));
		listRight.add(Assets.player.getTile(80, 16, 16, 16));

		listLeft.add(Assets.player.getTile(32, 0, 16, 16));
		listLeft.add(Assets.player.getTile(48, 0, 16, 16));
		listLeft.add(Assets.player.getTile(64, 0, 16, 16));
		listLeft.add(Assets.player.getTile(80, 0, 16, 16));


		listIdelUp.add(Assets.player.getTile(64, 32, 16, 16));
		
		listIdelDown.add(Assets.player.getTile(0, 32, 16, 16));
		listIdelDown.add(Assets.player.getTile(16, 32, 16, 16));
		listIdelDown.add(Assets.player.getTile(32, 32, 16, 16));
		listIdelDown.add(Assets.player.getTile(48, 32, 16, 16));

		listIdelRight.add(Assets.player.getTile(0, 48, 16, 16));
		listIdelRight.add(Assets.player.getTile(16, 48, 16, 16));
		listIdelRight.add(Assets.player.getTile(32, 48, 16, 16));
		listIdelRight.add(Assets.player.getTile(48, 48, 16, 16));
		
		listIdelLeft.add(Assets.player.getTile(0, 64, 16, 16));
		listIdelLeft.add(Assets.player.getTile(16, 64, 16, 16));
		listIdelLeft.add(Assets.player.getTile(32, 64, 16, 16));
		listIdelLeft.add(Assets.player.getTile(48, 64, 16, 16));
		
		
		//UP
		ani_up = new Animator(listUp);
		ani_up.setSpeed(animationSpeed);
		ani_up.play();

		//DOWN
		ani_down = new Animator(listDown);
		ani_down.setSpeed(animationSpeed);
		ani_down.play();
		
		//RIGHT
		ani_right = new Animator(listRight);
		ani_right.setSpeed(animationSpeed);
		ani_right.play();
		
		//LEFT
		ani_left = new Animator(listLeft);
		ani_left.setSpeed(animationSpeed);
		ani_left.play();
		
		//IDEL UP
		ani_idel_up = new Animator(listIdelUp);
		ani_idel_up.setSpeed(animationSpeed);
		ani_idel_up.play();
		//IDEL DOWN
		ani_idel_down = new Animator(listIdelDown);
		ani_idel_down.setSpeed(animationSpeed);
		ani_idel_down.play();
		//IDEL RIGHT
		ani_idel_right = new Animator(listIdelRight);
		ani_idel_right.setSpeed(animationSpeed);
		ani_idel_right.play();
		//IDEL LEFT
		ani_idel_left = new Animator(listIdelLeft);
		ani_idel_left.setSpeed(animationSpeed);
		ani_idel_left.play();

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
