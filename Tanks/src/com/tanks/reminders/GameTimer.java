/**
 * This class handles the main game timer
 * Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.reminders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.tanks.main.Board;
import com.tanks.states.EndGameState;
import com.tanks.states.LeaderBoard;
import com.tanks.states.TitleState;

public class GameTimer {
	private int count, tick, mins, secs, startup;
	
	public GameTimer(int seconds) {
		tick = 0;
		// Start up is the 3 second countdown
		startup = 3;
		this.count = seconds + startup;
	}
	
	public void tick() {
		if (tick == 30) {
			startup--;
			count--;
			tick = 0;
		}
		// When the timer runs down to 0, change to the endgame state
		if (count == 0) {
			TitleState.isArcade = false;
			TitleState.isTraining = false;
			TitleState.isLocalMP = false;
			TitleState.isEndGame = true;
			if(TitleState.prevState == 4){
				EndGameState.arcade1 = false;
				EndGameState.localM1 = false;
				EndGameState.training1 = true;
			}
			if(TitleState.prevState == 5){
				EndGameState.arcade1 = true;
				EndGameState.localM1 = false;
				EndGameState.training1 = false;
				LeaderBoard.readLBFile();// read in old list of highscores
			}
			if(TitleState.prevState == 6){
				EndGameState.arcade1 = false;
				EndGameState.localM1 = true;
				EndGameState.training1 = false;
			}
			Board.stateChange();
		}
		tick++;
	}
	/*
	 * Purpose of this function is to draw the game timer in the HUD
	 */
	public void doDrawing(Graphics g) {
		mins = count/60;
		secs = count%60;
		
	    g.setColor(Color.white); 
		Font font = new Font("Serif", Font.PLAIN, 30);
		g.setFont(font);
	    
		// Draw the 2min counter
	    if (count < 120) {
	    	String temp = Integer.toString(secs);
	    	String formatted = ("00" + temp).substring(temp.length());
	    	g.drawString(mins + ":" + formatted, 475, 30);
	    } else {
	    	// Draw the 3sec countdown
	    	String temp = Integer.toString(secs);
	    	String formatted = ("00" + temp).substring(temp.length());
	    	g.drawString("0" + ":" + formatted, 475, 30);
	    }
	}
	
	public void reset() {
		count = 120+startup;
		tick = 0;
	}
	
	public int getSecs() {
		return count;
	}
}
