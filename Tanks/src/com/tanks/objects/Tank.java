package com.tanks.objects;

import java.util.ArrayList;
import java.util.Random;

import com.tanks.main.game;
import com.tanks.reminders.BulletDelay;
import com.tanks.reminders.FireRate;
import com.tanks.states.GameState;

public class Tank extends GameObject {
	
	private Bullet bullet;
	private ArrayList<Bullet> bullets;
	private ArrayList<BulletDelay> bulletDelay;
	private ArrayList<FireRate> fireRate;
	private boolean fired;
	private int rof;
	private boolean bubble;
	private int pu;
	private double speedMultiplier;
	private boolean fireLock;

	private final int bulletSize = 8;
	private final int bulletSpeed = 9;
	
	private boolean isVis;
	private int score;
	
	public Tank(int x, int y, double speed, int width, int height, int ID) {
		super(x, y, speed, width, height, ID);
		bullets = new ArrayList<Bullet>();
		bulletDelay = new ArrayList<BulletDelay>();
		fireRate = new ArrayList<FireRate>();
		isVis = true;
		score = 0;
		rof = 2000;
		bubble = false;
		fireLock = true;
		pu = 0;
		speedMultiplier = 1;
	}
	
	public void moveForward() {
		x += velocityForward * Math.cos(a * Math.PI/180) * speedMultiplier;
		y += velocityForward * Math.sin(a * Math.PI/180) * speedMultiplier;
	}
	
	public void moveBackward() {
		x -= velocityBackward * Math.cos(a * Math.PI/180) * speedMultiplier;
		y -= velocityBackward * Math.sin(a * Math.PI/180) * speedMultiplier;
	}
	
	public void fire() {
		if (fireLock != true && isVis == true) {
			bullet = new Bullet(x,y,bulletSpeed,bulletSize,bulletSize,a, game.BULLET);
			bullets.add(bullet);
			bulletDelay.add(new BulletDelay(3792, bullet));
			fireRate.add(new FireRate(rof,this));
			fired = true;
		}
	}
	
	public void respawn(int minX, int maxX, int minY, int maxY, double a) {
		Random rand = new Random();
		x = rand.nextInt(maxX) + minX+1;
		y = rand.nextInt(maxY) + minY+1;
		isVis = true;
		this.a = a;
	}
	
	public void resetEffect() {
		setBubble(false);
		setSpeedMultiplier(1);
		setRof(2000);
		GameState.getEffectTimer(ID).resetTick();
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

	public boolean isFired() {
		return fired;
	}

	public void setFired(boolean fired) {
		this.fired = fired;
	}
	
	public void setRof(int ms) {
		rof = ms;
	}

	public boolean isBubble() {
		return bubble;
	}

	public void setBubble(boolean bubble) {
		this.bubble = bubble;
	}

	public int getPU() {
		return pu;
	}

	public void setPU(int pu) {
		this.pu = pu;
	}
	
	public void setSpeedMultiplier(double s) {
		speedMultiplier = s;
	}
	
	public void setFireLock(boolean g) {
		fireLock = g;
	}
	
}