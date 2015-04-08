package se.angin.dungeonhunter.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import se.angin.dungeonhunter.main.Assets;
import se.angin.dungeonhunter.managers.MouseManager;
import se.angin.gop.main.Vector2F;

public class GameStateButton extends Rectangle {

	private Vector2F pos = new Vector2F();
	private GameState gameState;
	private GameStateManager gsm;
	private boolean isHeldOver;
	private int width = 96 * 3;
	private int height = 96;
	private BufferedImage defaultImage;
	private String buttonMessage;
	
	public GameStateButton(float xPos, float yPos, GameState gameState, GameStateManager gsm, String buttonMessage) {
		this.gameState = gameState;
		this.gsm = gsm;
		this.pos.xPos = xPos;
		this.pos.yPos = yPos;
		this.buttonMessage = buttonMessage;
		setBounds((int) pos.xPos, (int) pos.yPos, width, height);
		defaultImage = Assets.getButton_notover();
	}

	public GameStateButton(float xPos, float yPos, String buttonMessage) {
		this.pos.xPos = xPos;
		this.pos.yPos = yPos;
		this.buttonMessage = buttonMessage;
		setBounds((int) pos.xPos, (int) pos.yPos, width, height);
		defaultImage = Assets.getButton_notover();
	}

	public void tick(){
		setBounds((int) pos.xPos,(int) pos.yPos, width, height);
		
		
		if(getBounds().contains(MouseManager.mouse)){
			isHeldOver = true;
		}else{
			isHeldOver = false;
		}
		
		
		if(isHeldOver){
			if(defaultImage != Assets.getButton_heldover()){
				defaultImage = Assets.getButton_heldover();
			}
		}else{
			if(defaultImage != Assets.getButton_notover()){
				defaultImage = Assets.getButton_notover();
			}
		}
		
		if(gameState != null){
			if(isHeldOver){
				if(isPressed()){
					gsm.states.push(gameState);
					gsm.states.peek().init();
					isHeldOver = false;
					MouseManager.pressed = false;
				}
			}
		}
	}
	
	Font font = new Font("RETRO COMPUTER", 10, 30);
	
	public void render(Graphics2D g){
		g.drawImage(defaultImage, (int) pos.xPos,(int) pos.yPos, width, height, null);
		
		g.setFont(font);
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);
		int tw = (int) font.getStringBounds(buttonMessage, frc).getWidth();
		
		g.drawString(buttonMessage, pos.xPos + width/2 - tw/2, pos.yPos + height/2 + 8);
	}

	public boolean isHeldOver(){
		return isHeldOver;
	}
	
	public boolean isPressed(){
		return MouseManager.pressed;
	}
}
