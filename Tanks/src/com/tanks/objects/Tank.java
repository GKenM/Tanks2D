package com.tanks.objects;

import java.util.ArrayList;
import java.util.Random;

import com.tanks.reminders.BulletDelay;

public class Tank extends GameObject {
	
	private Bullet bullet;
	private ArrayList<Bullet> bullets;
	private ArrayList<BulletDelay> bulletDelay;

	private final int bulletSize = 8;
	private final int bulletSpeed = 9;
	
	private boolean isVis;
	private int score;
	
	public Tank(int x, int y, int speed, int width, int height) {
		super(x, y, speed, width, height);
		bullets = new ArrayList<Bullet>();
		bulletDelay = new ArrayList<BulletDelay>();
		isVis = true;
		score = 0;
	}
	
	public void moveForward() {
		x += Math.round(velocityForward * Math.cos(a * Math.PI/180));
		y += Math.round(velocityForward * Math.sin(a * Math.PI/180));
	}
	
	public void moveBackward() {
		x -= Math.round(velocityBackward * Math.cos(a * Math.PI/180));
		y -= Math.round(velocityBackward * Math.sin(a * Math.PI/180));
	}
	
	public void fire() {
		if (bullets.size() == 0) {		
		bullet = new Bullet(x,y,bulletSpeed,bulletSize,bulletSize,a);
		bullets.add(bullet);
		bulletDelay.add(new BulletDelay(3792, bullet));
		}
	}
	
	public void respawn(int minX, int maxX, int minY, int maxY, double a) {
		// Set proper respawn boundaries and make sure they don't collide with walls
		Random rand = new Random();
		x = rand.nextInt(maxX) + minX+1;
		y = rand.nextInt(maxY) + minY+1;
		isVis = true;
		this.a = a;
	}
	
	public ArrayList<BulletDelay> getReminder() {
		return bulletDelay;
	}
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	
	public void setVis(boolean vis) {
		isVis = vis;
	}
	
	public boolean getVis() {
		return isVis;
	}
	
	public void setScore(int newScore) {
		score = newScore;
	}
	
	public int getScore() {
		return score;
	}
}