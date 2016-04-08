package com.tanks.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import com.tanks.modes.ArcadeMode;
import com.tanks.modes.GameMode;
import com.tanks.modes.LocalMP;
import com.tanks.modes.TrainingMode;
import com.tanks.objects.GameObject;
import com.tanks.objects.Walls;
import com.tanks.reminders.EffectTimer;
import com.tanks.reminders.GameTimer;
import com.tanks.reminders.PowerUpSpawner;
import com.tanks.resources.LoadSprites;

public class GameState extends State{
	private static GameMode gameMode;
	private static LocalMP localMP;
	private static TrainingMode training;
	private static ArcadeMode arcade;
	private static Walls walls;
	
	private static GameTimer gameTimer;
	private static PowerUpSpawner powerTimer;
	private static EffectTimer player1Effect;
	private static EffectTimer player2Effect;
	
	private LoadSprites image;

	public static final int tankSize = 48;
	public static final int tankSpeed = 3;
    
	public GameState() {
		image = new LoadSprites();
		walls = new Walls();
		gameTimer = new GameTimer(120);
		localMP = new LocalMP();
		training = new TrainingMode();
		arcade = new ArcadeMode();
		powerTimer = new PowerUpSpawner(walls.getWalls());
		player1Effect = new EffectTimer();
		player2Effect = new EffectTimer();
		
		gameMode = training;
	}
	
	public static void setMode() {
		if (TitleState.isTraining == true) {
			gameMode = training;
		}
		if (TitleState.isArcade == true) {
			gameMode = arcade;
		}
		if (TitleState.isLocalMP == true) {
			gameMode = localMP;
		}
	}
	
	@Override
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;	
		
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		gameTimer.doDrawing(g);
		player1Effect.doDrawing(g);
		player2Effect.doDrawing(g);
	    
	    // Walls
	    g2d.setColor(Color.red);
	    ArrayList<GameObject> wall = walls.getWalls();
	    
	    for (int i = 0; i < wall.size(); i++) {
	    	g2d.drawImage(image.getSprite(6),(int) wall.get(i).getX() - 6 , (int) wall.get(i).getY() -6, null);
	    }
	    
	    gameMode.doDrawing(g);
	    
	}

	@Override
	public void tick() {
		gameTimer.tick();
		powerTimer.tick();
		player1Effect.tick();
		player2Effect.tick();
		
		if (gameTimer.getSecs() < 120) {
			gameMode.tick();
		}
	}
	
	public static void reset() {
		// reset the tick timer
		gameTimer = new GameTimer(120);
		powerTimer = new PowerUpSpawner(walls.getWalls());
		player1Effect = new EffectTimer();
		player2Effect = new EffectTimer();
		
		localMP.reset();
		training.reset();
	}
	
	public static GameMode getMode() {
		return gameMode;
	}
	
	public static EffectTimer getEffectTimer(int id) {
		if (id == 1) {return player1Effect;}
		return player2Effect;
	}
}