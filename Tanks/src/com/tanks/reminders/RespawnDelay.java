package com.tanks.reminders;

import java.util.Timer;
import java.util.TimerTask;

import com.tanks.states.GameState;

public class RespawnDelay {
		
	Timer timer;
		
	public RespawnDelay(int milliSeconds) {
		timer = new Timer();
		timer.schedule(new RemindTask(), milliSeconds);
	}
		
	class RemindTask extends TimerTask {
		public void run() {
			// Reset the game?? GameState.reset?? which will empty out everything
			GameState.reset();
			timer.cancel();
		}
	}
}
