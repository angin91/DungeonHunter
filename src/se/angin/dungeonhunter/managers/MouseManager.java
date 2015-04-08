package se.angin.dungeonhunter.managers;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import se.angin.dungeonhunter.main.Assets;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {
	
	private static int mouseMovedX, mouseMovedY;
	public static Point mouse;
	
	public static boolean pressed;
	
	public void tick(){
		mouse = new Point(mouseMovedX, mouseMovedY);
	}
	
	public void render(Graphics2D g){
		g.fillRect(mouseMovedX, mouseMovedY, 4, 4);
		
		if(pressed){
			g.drawImage(Assets.getMouse_pressed(), mouseMovedX, mouseMovedY, 32, 32, null);
		}else{
			g.drawImage(Assets.getMouse_realesed(), mouseMovedX, mouseMovedY, 32, 32,  null);
		}
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseMovedX = e.getX();
		mouseMovedY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			pressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			pressed = false;
		}
	}
}
