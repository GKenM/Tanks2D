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

	public void setPosition(Tank tank, Tank tank2, ArrayList<GameObject> walls) {
		//after valid position make visible
		boolean running = true;
		Random rand = new Random();
		
		while(running) {
			x = rand.nextInt(game.WIDTH-1) + 1;
			y = rand.nextInt(game.HEIGHT-1) + 1;
					
			if (tank.getBounds().intersects(getBounds())) {
				running = true;
			} else if (tank2.getBounds().intersects(getBounds())) {
				running = true;
			} else {
				running = false;
			}
			
			for (int j = 0; j < walls.size(); j++) {
				if (walls.get(j).getBounds().intersects(getBounds())) {
					running = true;
					break;
				} else {
					running = false;
				}
			}
			
	    	if (getBounds().getX() < 0||getBounds().getMaxX() > 1024) {
	    		running = true;
	    	}
	    	if (getBounds().getY() < 0||getBounds().getMaxY() > 768) {
	    		running = true;
	    	}
		}
		
		isVis = true;
	}
	
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
			tank.setRof(1000);
		} else if (puID == 5) {
			// 0.5x fire rate
			tank.setRof(3000);
		}
		GameState.getEffectTimer(tank.getID()).setStart(true);
	}
	
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
