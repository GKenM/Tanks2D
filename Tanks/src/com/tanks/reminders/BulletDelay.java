package com.tanks.reminders;

import java.util.Timer;
import java.util.TimerTask;

import com.tanks.objects.Bullet;

public class BulletDelay {
	
	private Bullet bullet;
	Timer timer;
	
	public BulletDelay(int milliSeconds, Bullet bullet) {
		timer = new Timer();
		timer.schedule(new RemindTask(), milliSeconds);
		this.bullet = bullet;
	}
	
	class RemindTask extends TimerTask {
		public void run() {
			bullet.setVis(false);
			timer.cancel();
		}
	}
}
