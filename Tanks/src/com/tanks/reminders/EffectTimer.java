/**
 * This class keeps track of the lifetime of powerup effects
 * Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.reminders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.tanks.main.Board;
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
			// When the time is reach, reset the effect, and stop the counter till another powerup is picked up
			if (tick == 30*15) {
				Board.sounds.get("powdown").play();
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
	/*
	 * Purpose of this function is to draw the effect timer to the HUD
	 */
	public void doDrawing(Graphics g) {	
	    g.setColor(Color.white); 
		Font font = new Font("Serif", Font.PLAIN, 20);
		g.setFont(font);
	    
	    if (tank != null) {
	    	// If its player 1 tank, draw the timer on the top left corner
	    	if (tank.getID() == 1 && tank.getPU() != 0) {
		    	String temp = Integer.toString(count);
		    	String formatted = ("00" + temp).substring(temp.length());
	    		g.drawString(formatted , 40, 60);
	    	}
	    	// If its player 2 tank, draw the time on the top right corner
	    	if (tank.getID() == 2 && tank.getPU() != 0) {
		    	String temp = Integer.toString(count);
		    	String formatted = ("00" + temp).substring(temp.length());
	    		g.drawString(formatted , 960, 60);
	    	}
	    }
	}
	
	public void set(Tank tank) {
		this.tank = tank;
	}
	
	public void setStart(boolean temp) {
		start = temp;
		count = 15;
	}
	
	public void resetTick() {
		tick = 0;
		count = 10;
	}
}