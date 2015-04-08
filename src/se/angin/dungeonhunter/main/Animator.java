package se.angin.dungeonhunter.main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animator {
	
	private ArrayList<BufferedImage> frames;
	private volatile boolean running = false;
	public BufferedImage sprite;
	
	private long prevTime, speed;
	private int frameAtPause, currentFrame;

	public Animator(ArrayList<BufferedImage> frames){
		this.frames = frames;
	}
	
	public void setSpeed(long speed){
		this.speed = speed;
	}
	
	public void update(long time){
		if(running){
			if(time - prevTime >= speed){
				currentFrame++;
				try{
					sprite = frames.get(currentFrame);
				}catch(IndexOutOfBoundsException e){
					Reset();
					sprite = frames.get(currentFrame);
				}
				prevTime = time;
			}
		}
	}
	
	public void play(){
		running = true;
		prevTime = 0;
		frameAtPause = 0;
		currentFrame = 0;
	}
	
	public void Stop(){
		running = false;
		prevTime = 0;
		frameAtPause = 0;
		currentFrame = 0;
	}
	
	public void Pause(){
		frameAtPause = currentFrame;
		running = false;
	}
	
	public void Resume(){
		currentFrame = frameAtPause;
	}
	
	public void Reset(){
		currentFrame = 0;
	}
	
	public boolean isDoneAnimating(){
		if(currentFrame == frames.size()){
			return true;
		}else{
			return false;
		}
	}
}
