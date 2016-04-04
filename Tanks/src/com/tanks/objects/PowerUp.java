package com.tanks.objects;

import java.util.ArrayList;
import java.util.Random;

import com.tanks.main.game;

public class PowerUp extends GameObject{
	private int ID;
	private boolean isVis;
	
	public PowerUp(int x, int y, int speed, int width, int height) {
		super(x, y, speed, width, height);
		ID = 0;
		isVis = false;
	}

	public void setPosition(Tank tank, Tank tank2, ArrayList<GameObject> walls) {
		//after valid position make visible
		boolean running = true;
		Random rand = new Random();
		
		while(running) {
			x = rand.nextInt(game.WIDTH-1) + 1;
			y = rand.nextInt(game.HEIGHT-1) + 1;
			
			// Add the game boundaries check 
			
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
				} else {
					running = false;
				}
			}
		}
		
		isVis = true;
	}
	
	public void setVis(boolean vis) {
		this.isVis = vis;
	}
	
	public boolean getVis() {
		return isVis;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getID() {
		return ID;
	}
	
	public void applyEffect(int type, Tank tank) {
		if (type == 1) {
			// bubble
		} else if (type == 2) {
			// 1.5x speed
		} else if (type == 3) {
			// 0.5x speed
		} else if (type == 4) {
			// 1.5x fire rate
		} else if (type == 5) {
			// 0.5x fire rate
		}
	}
}
