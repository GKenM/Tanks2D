/**
 * This class handles the difficulty of arcade mode based on the time of survival
 * Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.reminders;

public class DifficultyTimer {
	private int tick,stage;
	
	public DifficultyTimer() {
		tick = 0;
		stage = 0;
	}
	/*
	 * Change the stage of difficulty depending on how long the player has survived for
	 */
	public void tick() {
		// for the first 10 seconds, stage 1
		if (tick == 0) {
			stage = 1;
		} else if (tick == 30*20) { // 20secs
			stage = 2;
		} else if (tick == 30*40) { // 40secs
			stage = 3;
		} else if (tick == 30*60) { // 1min
			stage = 4;
		} else if (tick == 30*80) { // 1min 20secs
			stage = 5;
		} else if (tick == 30*100) { // 1min 40secs
			stage = 6;
		}

		tick++;
	}
	
	public int getStage() {
		return stage;
	}
	/*
	 * Reset the counter if the player dies
	 */
	public void reset() {
		tick = 0;
	}
}