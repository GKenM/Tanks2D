/**
 * This class handles the lifetime of powerup objects in play
 * Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.reminders;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.tanks.objects.PowerUp;

public class PowerUpDestroyer {
	
	private ArrayList<PowerUp> powerUps;
	Timer timer;
	
	public PowerUpDestroyer(int Seconds,ArrayList<PowerUp> powerUps) {
		timer = new Timer();
		timer.schedule(new RemindTask(), Seconds*1000);
		this.powerUps = powerUps;
	}
	
	class RemindTask extends TimerTask {
		public void run() {
			powerUps.clear();
			timer.cancel();
		}
	}
}
