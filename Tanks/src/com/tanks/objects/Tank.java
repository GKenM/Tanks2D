package com.tanks.objects;

import java.util.ArrayList;

import com.tanks.main.Reminder;

public class Tank extends GameObject {
	
	private Missle missle;
	private ArrayList<Missle> missles;
	private ArrayList<Reminder> reminder;
	
	private static int bullet_delay = 30;
	private static int time = 0;

	public Tank(int x, int y, int speed, int width, int height) {
		super(x, y, speed, width, height);
		missles = new ArrayList<Missle>();
		reminder = new ArrayList<Reminder>();
	}
	
	public void move() {
		time = time - 1;
		x += Math.round(path * Math.cos(a * Math.PI/180));
		y += Math.round(path * Math.sin(a * Math.PI/180));
	}
	
	public void fire() {
		if (time <= 0) {
		missle = new Missle(x+width/2,y+height/2, 5, 10, 10,a);
		missles.add(missle);
		reminder.add(new Reminder(2, missle));
		time = bullet_delay;
		}
	}
	
	public ArrayList<Reminder> getReminder() {
		return reminder;
	}
	
	public ArrayList<Missle> getMissle() {
		return missles;
	}
}