package com.tanks.reminders;

import java.awt.Color;
import java.awt.Graphics;

import com.tanks.objects.Tank;

public class EffectTimer {
	
	private Tank tank;
	private int tick, count, displayTick;
	private boolean start;
	
	public EffectTimer() {
		tick = 0;
		count = 0;
		displayTick = 0;
		start = false;
	}
	
	public void tick() {
		if (start) {
			if (tick == 30*10) {
				tank.resetEffect();
				tank.setPU(0);
				tick = 0;
				count = 0;
				start = false;
				tank = null;
			}
			if (displayTick == 30) {
				count--;
				displayTick = 0;
			}
			tick++;
			displayTick++;
		}
	}
	
	public void doDrawing(Graphics g) {
		// draw the timer
		
	    g.setColor(Color.white); 
	    
	    if (tank != null) {
	    	if (tank.getID() == 1) {g.drawString("Powerup time : " + count, 100, 200);}
	    	
	    	if (tank.getID() == 2) {g.drawString("Powerup time : " + count, 900, 200);}
	    }
	}
	
	public void set(Tank tank) {
		this.tank = tank;
	}
	
	public void setStart(boolean temp) {
		start = temp;
		count = 10;
	}
	
	public void resetTick() {
		tick = 0;
		count = 10;
	}
}
