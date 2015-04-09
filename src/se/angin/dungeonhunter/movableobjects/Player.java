package se.angin.dungeonhunter.movableobjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import se.angin.dungeonhunter.gameloop.GameLoop;
import se.angin.dungeonhunter.gamestate.GameStateButton;
import se.angin.dungeonhunter.gamestates.DungeonLevelLoader;
import se.angin.dungeonhunter.generator.World;
import se.angin.dungeonhunter.main.Animator;
import se.angin.dungeonhunter.main.Assets;
import se.angin.dungeonhunter.main.Check;
import se.angin.dungeonhunter.main.Main;
import se.angin.dungeonhunter.managers.GUIManager;
import se.angin.dungeonhunter.managers.HUDManager;
import se.angin.dungeonhunter.managers.MouseManager;
import se.angin.gop.main.Vector2F;

public class Player implements KeyListener {

	Vector2F pos;
	private World world;
	private int width = 32;
	private int height = 32;
	private int scale = 2;
	private static boolean up, down, left, right, running;
	private static boolean debug = false;
	private float maxSpeed = 3*32F;

	private float speedUp = 0;
	private float speedDown = 0;
	private float speedLeft = 0;
	private float speedRight = 0;

	private float slowDown = 4.093F;

	private float fixDt = 1f / 60F;
	private long animationSpeed = 180;
	
	private static boolean moving;
	private static boolean spawned;
	
	private static boolean attack;

	
	//Rendering
	private int renderDistanceWidth = 62;
	private int renderDistanceHeight = 23;
	public static Rectangle render;
	
	private int animationState = 0;
	private static int lastAnimationState;
	/*
	 * 0 = up
	 * 1 = down
	 * 2 = right
	 * 3 = left
	 * 4 = idel
	 * */

	private ArrayList<BufferedImage> listUp;
	Animator ani_up;
	private ArrayList<BufferedImage> listDown;
	Animator ani_down;
	private ArrayList<BufferedImage> listRight;
	Animator ani_right;
	private ArrayList<BufferedImage> listLeft;
	Animator ani_left;
	private ArrayList<BufferedImage> listIdelUp;
	Animator ani_idel_up;
	private ArrayList<BufferedImage> listIdelDown;
	Animator ani_idel_down;
	private ArrayList<BufferedImage> listIdelRight;
	Animator ani_idel_right;
	private ArrayList<BufferedImage> listIdelLeft;
	Animator ani_idel_left;
	
	private HUDManager hudManager;
	private GUIManager guiManager;
	private PlayerActions playerActions;
	
	public Player() {
		pos = new Vector2F(Main.width / 2 - width / 2, Main.height / 2 - height
				/ 2);
	}

	public void init(World world) {

		playerActions = new PlayerActions(world);
		hudManager = new HUDManager(world);
		guiManager = new GUIManager();
		this.world = world;
		render = new Rectangle(
				(int) (pos.xPos - pos.getWorldLocation().xPos + pos.xPos - renderDistanceWidth*32/2 + width/2), 
				(int) (pos.yPos - pos.getWorldLocation().yPos + pos.yPos - renderDistanceHeight*32/2 + height/2),
				renderDistanceWidth*32, 
				renderDistanceHeight*32);
		
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
		
		spawned = true;
	}

	public void tick(double deltaTime) {
		
		playerMM.tick();
		
		playerActions.tick();

		render = new Rectangle(
				(int) (pos.xPos - pos.getWorldLocation().xPos + pos.xPos - renderDistanceWidth*32/2 + width/2), 
				(int) (pos.yPos - pos.getWorldLocation().yPos + pos.yPos - renderDistanceHeight*32/2 + height/2),
				renderDistanceWidth*32, 
				renderDistanceHeight*32);
		
		float moveAmountUp = (float) (speedUp * fixDt);
		float moveAmountDown = (float) (speedDown * fixDt);
		float moveAmountLeft = (float) (speedLeft * fixDt);
		float moveAmountRight = (float) (speedRight * fixDt);

		//MAP MOVE
		if (up) {
			moveMapUp(moveAmountUp);
			animationState = 0;
		} else {
			moveMapUpGlide(moveAmountUp);
		}
		if (down) {
			moveMapDown(moveAmountDown);
			animationState = 1;
		} else {
			moveMapDownGlide(moveAmountDown);
		}
		if (right) {
			moveMapRight(moveAmountRight);
			animationState = 2;
		} else {
			moveMapRightGlide(moveAmountRight);
		}
		if (left) {
			moveMapLeft(moveAmountLeft);
			animationState = 3;
		} else {
			moveMapLeftGlide(moveAmountLeft);
		}
		
		//Standing still
		if(!up && !down && !right && !left){
			if(lastAnimationState == 0){
				System.out.println("UP");
				animationState = 4;
			}
			if(lastAnimationState == 1){
				System.out.println("DOWN");
				animationState = 5;
			}
			if(lastAnimationState == 2){
				System.out.println("RIGHT");
				animationState = 6;
			}
			if(lastAnimationState == 3){
				System.out.println("LEFT");
				animationState = 7;				
			}
			if(moving){
				moving = false;
			}
		}
		if(running){
			if(animationSpeed != 100){
				animationSpeed = 100;
				ani_up.setSpeed(animationSpeed);
				ani_down.setSpeed(animationSpeed);
				ani_right.setSpeed(animationSpeed);
				ani_left.setSpeed(animationSpeed);
				ani_idel_up.setSpeed(animationSpeed);
				maxSpeed += 64;
			}
		}else{
			if(animationSpeed != 180){
				animationSpeed = 180;
				ani_up.setSpeed(animationSpeed);
				ani_down.setSpeed(animationSpeed);
				ani_right.setSpeed(animationSpeed);
				ani_left.setSpeed(animationSpeed);
				ani_idel_up.setSpeed(animationSpeed);
				maxSpeed -= 64;
			}
		}
	}
/*
	public void PlayerMoveCode(){

		
		if (!mapMove) {
		//PLAYER MOVE
		if (up) {
			
			if(!Check.CollisionPlayerBlock(
					new Point((int) (pos.xPos + world.mapPos.xPos), 
							(int) (pos.yPos + world.mapPos.yPos - moveAmountUp)),
					new Point((int) (pos.xPos + world.mapPos.xPos + width),
							(int) (pos.yPos + world.mapPos.yPos - moveAmountUp)) )){
				
				if (speedUp < maxSpeed) {
					speedUp += slowDown;
				} else {
					speedUp = maxSpeed;
				}
				pos.yPos -= moveAmountUp;
			}else{
				speedUp = 0;
			}

		} else {
			
			if(!Check.CollisionPlayerBlock(
					new Point((int) (pos.xPos + world.mapPos.xPos ), 
							(int) (pos.yPos + world.mapPos.yPos - moveAmountUp)),
					new Point((int) (pos.xPos + world.mapPos.xPos + width),
							(int) (pos.yPos + world.mapPos.yPos - moveAmountUp)) )){
				
				if (speedUp != 0) {
					speedUp -= slowDown;
					if (speedUp < 0) {
						speedUp = 0;
					}
				}
				pos.yPos -= moveAmountUp;
			}else{
				speedUp = 0;
			}
		}
		if (down) {
			
			if(!Check.CollisionPlayerBlock(
					new Point((int) (pos.xPos + world.mapPos.xPos), 
							(int) (pos.yPos + world.mapPos.yPos + height + moveAmountDown)),
					new Point((int) (pos.xPos + world.mapPos.xPos + width),
							(int) (pos.yPos + world.mapPos.yPos + height + moveAmountDown)) )){
				
				
				if (speedDown < maxSpeed) {
					speedDown += slowDown;
				} else {
					speedDown = maxSpeed;
				}
				pos.yPos += moveAmountDown;
				
			}else{
				speedDown = 0;
			}
			
		} else {
			
			if(!Check.CollisionPlayerBlock(
					new Point((int) (pos.xPos + world.mapPos.xPos), 
							(int) (pos.yPos + world.mapPos.yPos + height + moveAmountDown)),
					new Point((int) (pos.xPos + world.mapPos.xPos + width),
							(int) (pos.yPos + world.mapPos.yPos + height + moveAmountDown)) )){
				
				if (speedDown != 0) {
					speedDown -= slowDown;
					if (speedDown < 0) {
						speedDown = 0;
					}
				}
				pos.yPos += moveAmountDown;
			}else{
				speedDown = 0;
			}
		}
		
		
		if (left) {
			
			if(!Check.CollisionPlayerBlock(
					new Point((int) (pos.xPos + world.mapPos.xPos - moveAmountLeft), 
							(int) (pos.yPos + world.mapPos.yPos + height)),
					new Point((int) (pos.xPos + world.mapPos.xPos - moveAmountLeft),
							(int) (pos.yPos + world.mapPos.yPos)) )){
				
				if (speedLeft < maxSpeed) {
					speedLeft += slowDown;
				} else {
					speedLeft = maxSpeed;
				}
				pos.xPos -= moveAmountLeft;
				
			}else{
				speedLeft = 0;
			}

		} else {
			
			if(!Check.CollisionPlayerBlock(
					new Point((int) (pos.xPos + world.mapPos.xPos - moveAmountLeft), 
							(int) (pos.yPos + world.mapPos.yPos + height)),
					new Point((int) (pos.xPos + world.mapPos.xPos - moveAmountLeft),
							(int) (pos.yPos + world.mapPos.yPos)) )){
				
				if (speedLeft != 0) {
					speedLeft -= slowDown;
					if (speedLeft < 0) {
						speedLeft = 0;
					}
				}
				pos.xPos -= moveAmountLeft;
			}else{
				speedLeft = 0;
			}
			
		}
		if (right) {
			
			if(!Check.CollisionPlayerBlock(
					new Point((int) (pos.xPos + world.mapPos.xPos + width + moveAmountRight), 
							(int) (pos.yPos + world.mapPos.yPos)),
					new Point((int) (pos.xPos + world.mapPos.xPos + width + moveAmountRight),
							(int) (pos.yPos + world.mapPos.yPos + height)) )){
				
				if (speedRight < maxSpeed) {
					speedRight += slowDown;
				} else {
					speedRight = maxSpeed;
				}
				pos.xPos += moveAmountRight;
				
			}else{
				speedRight = 0;
			}
		} else {
			
			if(!Check.CollisionPlayerBlock(
					new Point((int) (pos.xPos + world.mapPos.xPos + width + moveAmountRight), 
							(int) (pos.yPos + world.mapPos.yPos)),
					new Point((int) (pos.xPos + world.mapPos.xPos + width + moveAmountRight),
							(int) (pos.yPos + world.mapPos.yPos + height)) )){
				
				if (speedRight != 0) {
					speedRight -= slowDown;
					if (speedRight < 0) {
						speedRight = 0;
					}
				}
				pos.xPos += moveAmountRight;
				
			}else{
				speedRight = 0;
			}
			
		}
		
		}else{
			
		}
	}*/
	
	public void moveMapUp(float speed){
		if(!Check.CollisionPlayerBlock(
				new Point((int) (pos.xPos + world.mapPos.xPos), 
						(int) (pos.yPos + world.mapPos.yPos - speed)),
				new Point((int) (pos.xPos + world.mapPos.xPos + width),
						(int) (pos.yPos + world.mapPos.yPos - speed)) )){
			
			if (speedUp < maxSpeed) {
				speedUp += slowDown;
			} else {
				speedUp = maxSpeed;
			}
			world.mapPos.yPos -= speed;
			
		}else{
			speedUp = 0;
		}
	}
	public void moveMapUpGlide(float speed){
		if(!Check.CollisionPlayerBlock(
				new Point((int) (pos.xPos + world.mapPos.xPos), 
						(int) (pos.yPos + world.mapPos.yPos - speed)),
				new Point((int) (pos.xPos + world.mapPos.xPos + width),
						(int) (pos.yPos + world.mapPos.yPos - speed)) )){
			
			if (speedUp != 0) {
				speedUp -= slowDown;
				if (speedUp < 0) {
					speedUp = 0;
				}
			}
//			if (!mapMove) {
//				pos.yPos -= speed;
//			}else{
				world.mapPos.yPos -= speed;
//			}
			
		}else{
			speedUp = 0;
		}
	}
	
	public void moveMapDown(float speed){
		if(!Check.CollisionPlayerBlock(
				new Point((int) (pos.xPos + world.mapPos.xPos), 
						(int) (pos.yPos + world.mapPos.yPos + height + speed)),
				new Point((int) (pos.xPos + world.mapPos.xPos + width),
						(int) (pos.yPos + world.mapPos.yPos + height + speed)) )){
			
			if (speedDown < maxSpeed) {
				speedDown += slowDown;
			} else {
				speedDown = maxSpeed;
			}
			world.mapPos.yPos += speed;
			
		}else{
			speedDown = 0;
		}
	}
	public void moveMapDownGlide(float speed){
		if(!Check.CollisionPlayerBlock(
				new Point((int) (pos.xPos + world.mapPos.xPos), 
						(int) (pos.yPos + world.mapPos.yPos + height + speed)),
				new Point((int) (pos.xPos + world.mapPos.xPos + width),
						(int) (pos.yPos + world.mapPos.yPos + height + speed)) )){
			
			if (speedDown != 0) {
				speedDown -= slowDown;
				if (speedDown < 0) {
					speedDown = 0;
				}
			}
			world.mapPos.yPos += speed;
			
		}else{
			speedDown = 0;
		}
	}
	
	public void moveMapLeft(float speed){
		if(!Check.CollisionPlayerBlock(
				new Point((int) (pos.xPos + world.mapPos.xPos - speed), 
						(int) (pos.yPos + world.mapPos.yPos + height)),
				new Point((int) (pos.xPos + world.mapPos.xPos - speed),
						(int) (pos.yPos + world.mapPos.yPos)) )){
			
			if (speedLeft < maxSpeed) {
				speedLeft += slowDown;
			} else {
				speedLeft = maxSpeed;
			}
			world.mapPos.xPos -= speed;
			
		}else{
			speedLeft = 0;
		}
	}
	public void moveMapLeftGlide(float speed){
		if(!Check.CollisionPlayerBlock(
				new Point((int) (pos.xPos + world.mapPos.xPos - speed), 
						(int) (pos.yPos + world.mapPos.yPos + height)),
				new Point((int) (pos.xPos + world.mapPos.xPos - speed),
						(int) (pos.yPos + world.mapPos.yPos)) )){
			
			if (speedLeft != 0) {
				speedLeft -= slowDown;
				if (speedLeft < 0) {
					speedLeft = 0;
				}
			}
			world.mapPos.xPos -= speed;
			
		}else{
			speedLeft = 0;
		}
	}
	
	public void moveMapRight(float speed){
		if(!Check.CollisionPlayerBlock(
				new Point((int) (pos.xPos + world.mapPos.xPos + width + speed), 
						(int) (pos.yPos + world.mapPos.yPos)),
						new Point((int) (pos.xPos + world.mapPos.xPos + width + speed),
								(int) (pos.yPos + world.mapPos.yPos + height)) )){
			
			if (speedRight < maxSpeed) {
				speedRight += slowDown;
			} else {
				speedRight = maxSpeed;
			}
			world.mapPos.xPos += speed;
		}else{
			speedRight = 0;
		}
	}
	public void moveMapRightGlide(float speed){
		if(!Check.CollisionPlayerBlock(
				new Point((int) (pos.xPos + world.mapPos.xPos + width + speed), 
						(int) (pos.yPos + world.mapPos.yPos)),
				new Point((int) (pos.xPos + world.mapPos.xPos + width + speed),
						(int) (pos.yPos + world.mapPos.yPos + height)) )){
			
			if (speedRight != 0) {
				speedRight -= slowDown;
				if (speedRight < 0) {
					speedRight = 0;
				}
			}
			world.mapPos.xPos += speed;
			
		}else{
			speedRight = 0;
		}
	}

	public void render(Graphics2D g) {
		
		if(playerActions.attack_state != null){
			if(!playerActions.hasCompleted){
				if(playerActions.attack){
					
					if(playerActions.getAttack_state() == Assets.getAttack_up()){
						g.drawImage(playerActions.attack_state, 
								(int) pos.xPos - width/2, 
								(int) pos.yPos - height - 16,
								width * scale, 
								height * scale, 
								null);
					}
					if(playerActions.getAttack_state() == Assets.getAttack_down()){
						g.drawImage(playerActions.attack_state, 
								(int) pos.xPos - width/2, 
								(int) pos.yPos - height + 16*3,
								width * scale, 
								height * scale, 
								null);
					}
					if(playerActions.getAttack_state() == Assets.getAttack_right()){
						g.drawImage(playerActions.attack_state, 
								(int) pos.xPos - width/2  + 16*3, 
								(int) pos.yPos - height,
								width * scale, 
								height * scale, 
								null);
					}
					if(playerActions.getAttack_state() == Assets.getAttack_left()){
						g.drawImage(playerActions.attack_state, 
								(int) pos.xPos - width/2  - 16*3, 
								(int) pos.yPos - height,
								width * scale, 
								height * scale, 
								null);
					}
				}
			}
		}
		
		//UP
		if(animationState == 0){
			g.drawImage(ani_up.sprite, (int) pos.xPos - width/2, (int) pos.yPos - height, width * scale, height * scale, null);
			if(up){
				ani_up.update(System.currentTimeMillis());
			}
		}
		//DOWN
		if(animationState == 1){
			g.drawImage(ani_down.sprite, (int) pos.xPos - width/2, (int) pos.yPos - height, width * scale, height * scale, null);
			if(down){
				ani_down.update(System.currentTimeMillis());
			}
		}
		//RIGHT
		if(animationState == 2){
			g.drawImage(ani_right.sprite, (int) pos.xPos - width/2, (int) pos.yPos - height, width * scale, height * scale, null);
			if(right){
				ani_right.update(System.currentTimeMillis());
			}
		}
		//LEFT
		if(animationState == 3){
			g.drawImage(ani_left.sprite, (int) pos.xPos - width/2, (int) pos.yPos - height, width * scale, height * scale, null);
			if(left){
				ani_left.update(System.currentTimeMillis());
			}
		}
		//IDEL UP
		if(animationState == 4){
			g.drawImage(ani_idel_up.sprite, (int) pos.xPos - width/2, (int) pos.yPos - height, width * scale, height * scale, null);
			ani_idel_up.update(System.currentTimeMillis());
		}
		//IDEL DOWN
		if(animationState == 5){
			g.drawImage(ani_idel_down.sprite, (int) pos.xPos - width/2, (int) pos.yPos - height, width * scale, height * scale, null);
			ani_idel_down.update(System.currentTimeMillis());
		}
		//IDEL RIGHT
		if(animationState == 6){
			g.drawImage(ani_idel_right.sprite, (int) pos.xPos - width/2, (int) pos.yPos - height, width * scale, height * scale, null);
			ani_idel_right.update(System.currentTimeMillis());
		}//IDEL LEFT
		if(animationState == 7){
			g.drawImage(ani_idel_left.sprite, (int) pos.xPos - width/2, (int) pos.yPos - height, width * scale, height * scale, null);
			ani_idel_left.update(System.currentTimeMillis());
		}
		
		g.drawRect((int) pos.xPos - renderDistanceWidth*32/2 + width/2, (int) pos.yPos - renderDistanceHeight*32/2 + height/2, renderDistanceWidth*32, renderDistanceHeight*32);

		guiManager.render(g);
		hudManager.render(g);
		playerMM.render(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			if(!moving){
				moving=true;
			}
			up = true;
		}
		if (key == KeyEvent.VK_S) {
			if(!moving){
				moving=true;
			}
			down = true;
		}
		if (key == KeyEvent.VK_D) {
			if(!moving){
				moving=true;
			}
			right = true;
		}
		if (key == KeyEvent.VK_A) {
			if(!moving){
				moving=true;
			}
			left = true;
		}
		if (key == KeyEvent.VK_SHIFT) {
			running= true;
		}
		if (key == KeyEvent.VK_F3) {
			if(!debug){
				debug= true;
			}else{
				debug = false;
			}
		}
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			lastAnimationState = 0;
			up = false;
		}
		if (key == KeyEvent.VK_S) {
			lastAnimationState = 1;
			down = false;
		}
		if (key == KeyEvent.VK_D) {
			lastAnimationState = 2;
			right = false;
		}
		if (key == KeyEvent.VK_A) {
			lastAnimationState = 3;
			left = false;
		}
		if (key == KeyEvent.VK_P) {
			DungeonLevelLoader.world.changeToWorld("World2", "map2");
			//world.changeToWorld("World2", "map2");
		}
		if (key == KeyEvent.VK_SHIFT) {
			running= false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
	
	public Vector2F getPos() {
		return pos;
	}
	
	public float getMaxSpeed() {
		return maxSpeed;
	}
	
	public float getSlowDown() {
		return slowDown;
	}
	
	public World getWorld() {
		return world;
	}
	
	public static boolean isDebugging(){
		return debug;
	}
	
	public boolean isMoving(){
		return moving;
	}
	
	public boolean hasSpawned(){
		return spawned;
	}
	
	public PlayerActions getPlayerActions() {
		return playerActions;
	}
	
	
	public static class PlayerActions{
		
		private World world;
		private BufferedImage attack_state;
		private static boolean hasCompleted = true;
		private static boolean attack = false;
		private double attackTime = 1;

		public PlayerActions(World world) {
			this.world = world;
		}
		
		public void tick(){
			if(!hasCompleted){
				if(attack){
					if(attack_state != null){
						startAttack();
					}
				}
			}
		}
		
		private void startAttack() {
			if(attackTime != 0){
				attackTime -= 0.1;
			}
			if(attackTime <= 0){
				attack = false;
				hasCompleted = true;
				attack_state = null;
				attackTime = 1;
			}
		}

		public void attackUp(){
			attack_state = Assets.getAttack_up();
			attack = true;
			hasCompleted = false;
		}
		public void attackDown(){
			attack_state = Assets.getAttack_down();
			attack = true;
			hasCompleted = false;
		}
		public void attackRight(){
			attack_state = Assets.getAttack_right();
			attack = true;
			hasCompleted = false;
		}
		public void attackLeft(){
			attack_state = Assets.getAttack_left();
			attack = true;
			hasCompleted = false;
		}
		public void run(){
			
		}
		
		public BufferedImage getAttack_state() {
			return attack_state;
		}

		public boolean hasCompleted() {
			return hasCompleted;
		}
		
		public boolean attacked(){
			return attack;
		}
		public double getAttackTime() {
			return attackTime;
		}
	}
}
