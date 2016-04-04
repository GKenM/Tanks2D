package com.tanks.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import com.tanks.modes.GameMode;
import com.tanks.modes.LocalMP;
import com.tanks.modes.TrainingMode;
import com.tanks.objects.GameObject;
import com.tanks.objects.Walls;
import com.tanks.reminders.GameTimer;
import com.tanks.reminders.PowerUpSpawner;
import com.tanks.resources.LoadSprites;

public class GameState extends State{
	private static LocalMP localMP;
	private static TrainingMode training;
	private Walls walls;
	
	private static GameTimer timer;
	private static PowerUpSpawner powerTimer;
	
	private LoadSprites image;

	public static final int tankSize = 48;
	public static final int tankSpeed = 3;
    
	public GameState() {
		image = new LoadSprites();
		walls = new Walls();
		timer = new GameTimer(120);
		localMP = new LocalMP();
		training = new TrainingMode();
		powerTimer = new PowerUpSpawner(walls.getWalls());
		//reset();
	}
	
	@Override
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;	
		
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		timer.doDrawing(g);
	    
	    // Walls
	    g2d.setColor(Color.red);
	    ArrayList<GameObject> wall = walls.getWalls();
	    
	    for (int i = 0; i < wall.size(); i++) {
	    	g2d.drawImage(image.getSprite(6),wall.get(i).getX() - 6 , wall.get(i).getY() -6, null);
	    }
	    
	    localMP.doDrawing(g);
	    //training.doDrawing(g);
	    
	}

	@Override
	public void tick() {
		timer.tick();
		powerTimer.tick();
		
		if (timer.getSecs() < 120) {
			localMP.tick();
			//training.tick();
		}
	}
	
	public static void reset() {
		timer.reset();
		localMP.reset();
		training.reset();
	}
	
	public static GameMode getMode() {
		return localMP;
		//return training;
	}
}