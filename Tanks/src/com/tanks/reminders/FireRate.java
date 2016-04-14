/**
 * This class is the timer that handles the fire rate
 * Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.reminders;

import java.util.Timer;
import java.util.TimerTask;

import com.tanks.objects.Tank;

public class FireRate {
	
	private Tank tank;
	Timer timer;
	
	public FireRate(int milliSeconds,Tank tank) {
		timer = new Timer();
		timer.schedule(new RemindTask(), milliSeconds);
		this.tank = tank;
	}
	
	class RemindTask extends TimerTask {
		public void run() {
			tank.setFired(false);
			timer.cancel();
		}
	}
}
