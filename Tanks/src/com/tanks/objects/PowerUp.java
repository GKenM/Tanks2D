/**
 * This class spawns all the powerups and handles there effects
 * Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.objects;

import java.util.ArrayList;
import java.util.Random;

import com.tanks.main.game;
import com.tanks.states.GameState;

public class PowerUp extends GameObject{
	private int puID;
	private boolean isVis;
	
	public PowerUp(int x, int y, double speed, int width, int height, int ID) {
		super(x, y, speed, width, height, ID);
		puID = 0;
		isVis = false;
	}
	/*
	 * Purpose of this function is to select an appropriate random position for the powerups to spawn
	 */
	public void setPosition(Tank tank, Tank tank2, ArrayList<GameObject> walls, ArrayList<PowerUp> powerUps) {
		boolean running = true;
		Random rand = new Random();
		
		// Loop till a suitable position is found
		while(running) {
			running = false;
			x = rand.nextInt(game.WIDTH-1) + 1;
			y = rand.nextInt(game.HEIGHT-1) + 1;
			
			// Check for window boundary
	    	if (getBounds().getX() < this.width || getBounds().getMaxX() > game.WIDTH - this.width) {
	    		running = true;
	    	}
	    	if (getBounds().getY() < this.height || getBounds().getMaxY() > game.HEIGHT - this.height) {
	    		running = true;
	    	}
			
	    	// Check for other player tanks
			if (tank.getBounds().intersects(getBounds())) {
				running = true;
			} 
			if (tank2.getBounds().intersects(getBounds())) {
				running = true;
			} 
			
			// Check for walls
			for (int j = 0; j < walls.size(); j++) {
				if (walls.get(j).getBounds().intersects(getBounds())) {
					running = true;
					break;
				}
			}
			
			// Check for other powerups in play
			for (int k = 0; k < powerUps.size(); k++) {
				if (powerUps.get(k).getBounds().intersects(getBounds())) {
					running = true;
					break;
				}
			}
		}
		isVis = true;
	}
	/*
	 * Purpose of this function is to apply the power up effect to the appropriate tank
	 */
	public void applyEffect(Tank tank) {
		tank.setPU(puID);
		if (puID == 1) {
			// bubble
			tank.setBubble(true);
		} else if (puID == 2) {
			// 1.5x speed
			tank.setSpeedMultiplier(1.5);
		} else if (puID == 3) {
			// 0.5x speed
			tank.setSpeedMultiplier(0.5);
		} else if (puID == 4) {
			// 1.5x fire rate
			tank.setRofMultiplier(1.5);
		} else if (puID == 5) {
			// 0.5x fire rate
			tank.setRofMultiplier(0.5);
		}
		GameState.getEffectTimer(tank.getID()).setStart(true);
	}
	
	// Getters and setters for useful local variables
	public void setVis(boolean vis) {
		this.isVis = vis;
	}
	
	public boolean getVis() {
		return isVis;
	}
	
	public void setPuID(int puID) {
		this.puID = puID;
	}
	
	public int getPuID() {
		return puID;
	}
}