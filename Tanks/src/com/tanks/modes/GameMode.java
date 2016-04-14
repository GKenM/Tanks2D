/**
 * This abstract class handles all game mode classes
 * Author: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.modes;

import java.awt.Graphics;
import java.util.ArrayList;

import com.tanks.main.game;
import com.tanks.objects.Bullet;
import com.tanks.objects.GameObject;
import com.tanks.objects.ObjectInteraction;
import com.tanks.objects.Tank;
import com.tanks.states.GameState;

public abstract class GameMode {	
	protected static Tank player1;
    protected static ArrayList<Bullet> p1Bullets;
    protected Bullet bullet;
	protected ObjectInteraction mechanics;
    
	public GameMode() {
		// Create player 1 here as player 1 is used in all game modes
		player1 = new Tank(0,0,GameState.tankSpeed,GameState.tankSize,GameState.tankSize,GameState.tankRof,game.P1);
		p1Bullets = player1.getBullets();
	}
	/*
	 * Set the game mechanics for different maps
	 */
	public void setMechanics(ObjectInteraction mech) {
		mechanics = mech;
	}
	/*
	 * Purpose of this function is to load unique attributes for arcade mode
	 */
	public void setArcadeSpecs() {
		player1.setFireLock(true);
		player1.setSpeed(5);
		player1.setRof(500);
	}
	/*
	 * Purpose of this function is to load deafault attributes for other game modes
	 */
	public void setDefault() {
		player1.setFireLock(true);
		if (GameState.getMode().getPlayer2() != null) {GameState.getMode().getPlayer2().setFireLock(true);}
		player1.setSpeed(GameState.tankSpeed);
		player1.setRof(2000);
	}
	
	// Abstract methods which are required in all game mode classes
	public abstract void doDrawing(Graphics g);
	public abstract void tick();
	public abstract void respawn();
	public abstract void reset();
	public abstract Tank getPlayer1();
	public abstract Tank getPlayer2();
	public abstract void spawnPowerup(ArrayList<GameObject> walls);
}




