package com.tanks.main;

import java.util.Timer;
import java.util.TimerTask;

import com.tanks.objects.Missle;

public class Reminder {
	
	private Missle missle;
	Timer timer;
	
	public Reminder(int seconds, Missle missle) {
		timer = new Timer();
		timer.schedule(new RemindTask(), seconds*1000);
		this.missle = missle;
	}
	
	class RemindTask extends TimerTask {
		public void run() {
			missle.setVis(false);
			timer.cancel();
		}
	}
}
