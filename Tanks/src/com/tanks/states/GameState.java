/**
 * This class handles all the game modes basic game mechanics
 * Authors: Jakob Ettles, Ken Malavisuriya 
 */
package com.tanks.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import com.tanks.main.Board;
import com.tanks.main.Log;
import com.tanks.modes.ArcadeMode;
import com.tanks.modes.GameMode;
import com.tanks.modes.LocalMP;
import com.tanks.modes.TrainingMode;
import com.tanks.objects.GameObject;
import com.tanks.objects.ObjectInteraction;
import com.tanks.objects.Walls;
import com.tanks.reminders.EffectTimer;
import com.tanks.reminders.GameTimer;
import com.tanks.reminders.PowerUpSpawner;

public class GameState extends State{
	private static GameMode gameMode;
	private static LocalMP localMP;
	private static TrainingMode training;
	private static ArcadeMode arcade;
	private static Walls walls;
	private static ObjectInteraction mechanics;
	
	private static GameTimer gameTimer;
	private static PowerUpSpawner powerTimer;
	private static EffectTimer player1Effect;
	private static EffectTimer player2Effect;
	
	public static final int tankSize = 48;
	public static final int tankSpeed = 3;
	public static final int tankRof = 2000;
	
	public static Log log;
    
	public GameState() {
		walls = new Walls();
		gameTimer = new GameTimer(120);
		localMP = new LocalMP();
		training = new TrainingMode();
		arcade = new ArcadeMode();
		powerTimer = new PowerUpSpawner(walls.getWalls());
		player1Effect = new EffectTimer();
		player2Effect = new EffectTimer();
		mechanics = new ObjectInteraction(walls);
		
		gameMode = training;
	}
	/*
	 * Purpose of this function is to change game modes and pass in the ranomdly generated map to necessary functions
	 */
	public static void setMode() {
		walls = new Walls();
		powerTimer = new PowerUpSpawner(walls.getWalls());
		mechanics = new ObjectInteraction(walls);
		training.setMechanics(mechanics);
		arcade.setMechanics(mechanics);
		arcade.setWalls(walls);
		localMP.setMechanics(mechanics);
		
		log = new Log();
		
		if (TitleState.isTraining == true) {
			gameMode = training;
			gameMode.setDefault();
		}
		if (TitleState.isArcade == true) {
			gameMode = arcade;
			gameMode.setArcadeSpecs();
		}
		if (TitleState.isLocalMP == true) {
			gameMode = localMP;
			gameMode.setDefault();
		}
	}
	/*
	 * Purpose of this function is draw the map and display the game timers and effect timers
	 */
	@Override
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;	
		
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			    
	    // Walls
	    g2d.setColor(Color.red);
	    ArrayList<GameObject> wall = walls.getWalls();
	    
	    for (int i = 0; i < wall.size(); i++) {
	    	g2d.drawImage(Board.images.getSprite(6),(int) wall.get(i).getX() - 6 , (int) wall.get(i).getY() -6, null);
	    }
	    
	    gameMode.doDrawing(g);
		gameTimer.doDrawing(g);
		player1Effect.doDrawing(g);
		player2Effect.doDrawing(g);
	    
	}
	/*
	 * Purpose of this function is to update all the tick based functions 
	 */
	@Override
	public void tick() {		
		gameTimer.tick();
		powerTimer.tick();
		player1Effect.tick();
		player2Effect.tick();
		
		// When the 3 second countdown is over, run the update method for game mode
		if (gameTimer.getSecs() < 120) {
			gameMode.tick();
			log.writeFile();
		}
	}
	/*
	 * Purpose of this function is to reset all the timers and game modes
	 */
	public static void reset() {
		gameTimer = new GameTimer(120);
		powerTimer = new PowerUpSpawner(walls.getWalls());
		player1Effect = new EffectTimer();
		player2Effect = new EffectTimer();
		
		localMP.reset();
		training.reset();
		arcade.reset();
	}
	
	public static GameMode getMode() {
		return gameMode;
	}
	
	public static EffectTimer getEffectTimer(int id) {
		if (id == 1) {return player1Effect;}
		return player2Effect;
	}
}