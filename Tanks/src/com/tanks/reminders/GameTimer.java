package com.tanks.reminders;

import java.awt.Color;
import java.awt.Graphics;

import com.tanks.states.GameState;

public class GameTimer {
	private int count, tick, mins, secs, startup;
	
	public GameTimer(int seconds) {
		tick = 0;
		startup = 3;
		this.count = seconds + startup;
	}
	
	public void tick() {
		if (tick == 30) {
			startup--;
			count--;
			tick = 0;
		}
		if (count == 0) {
			GameState.reset();
		}
		tick++;
	}
	
	public void doDrawing(Graphics g) {
		// draw the timer
		mins = count/60;
		secs = count%60;
		
	    g.setColor(Color.white); 
	    
	    if (count < 120) {
	    	g.drawString(mins + " : " + secs, 500, 100);
	    } else {
	    	g.drawString(0 + " : " + secs, 500, 100);
	    }
	}
	
	public void reset() {
		count = 120+startup;
	}
	
	public int getSecs() {
		return count;
	}
}
