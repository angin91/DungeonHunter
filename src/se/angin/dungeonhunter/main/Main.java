package se.angin.dungeonhunter.main;

import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;

import se.angin.dungeonhunter.gameloop.GameLoop;
import se.angin.dungeonhunter.managers.MouseManager;
import se.angin.dungeonhunter.movableobjects.Player;
import se.angin.gop.main.GameWindow;

public class Main {
	
	public static GraphicsDevice gp = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static int width = gp.getDisplayMode().getWidth();
	public static int height = gp.getDisplayMode().getHeight();

	public static void main(String[] args) {
		GameWindow frame = new GameWindow("Dungeon Hunter", width, height);
		frame.setFullscreen(1);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor cursor = toolkit.createCustomCursor(toolkit.getImage(""), new Point(0, 0), "Cursor");
		frame.setCursor(cursor);
		
		frame.addMouseListener(new MouseManager());
		frame.addMouseMotionListener(new MouseManager());
		frame.addMouseWheelListener(new MouseManager());
		
		frame.addKeyListener(new Player());
		frame.add(new GameLoop(width, height));
		frame.setVisible(true);
	}
}
