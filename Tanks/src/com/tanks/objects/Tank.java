/**
 * This class handles all the player tanks and also acts as parent class for enemy AI
 * Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.objects;

import java.util.ArrayList;
import java.util.Random;

import com.tanks.main.Board;
import com.tanks.main.game;
import com.tanks.reminders.BulletDelay;
import com.tanks.reminders.FireRate;
import com.tanks.states.GameState;

public class Tank extends GameObject {
	
	protected Bullet bullet;
	protected ArrayList<Bullet> bullets;
	protected int kills = 0;
	protected int deaths = 0;
	protected final int bulletSize = 8;
	protected final int bulletSpeed = 9;
	
	private ArrayList<BulletDelay> bulletDelay;
	private ArrayList<FireRate> fireRate;
	private boolean fired;
	private int rof;
	private boolean bubble;
	private int pu;
	private double speedMultiplier;
	private double rofMultiplier;
	private boolean fireLock;	
	private boolean isVis;
	private int score;
	
	public Tank(int x, int y, double speed, int width, int height, int rof, int ID) {
		super(x, y, speed, width, height, ID);
		bullets = new ArrayList<Bullet>();
		bulletDelay = new ArrayList<BulletDelay>();
		fireRate = new ArrayList<FireRate>();
		this.rof = rof;
		isVis = true;
		score = 0;
		bubble = false;
		fireLock = true;
		pu = 0;
		speedMultiplier = 1;
		rofMultiplier = 1;
		kills = 0;
		deaths = 0;
	}
	/*
	 * Purpose of these two functions are to handle forward and backward movement of the tanks
	 */
	public void moveForward() {
		x += velocityForward * Math.cos(a * Math.PI/180) * speedMultiplier;
		y += velocityForward * Math.sin(a * Math.PI/180) * speedMultiplier;
	}
	
	public void moveBackward() {
		x -= velocityBackward * Math.cos(a * Math.PI/180) * speedMultiplier;
		y -= velocityBackward * Math.sin(a * Math.PI/180) * speedMultiplier;
	}
	/*
	 * Creates a bullet object eveytime tank fires
	 */
	public void fire() {
		if (fireLock != true && isVis == true) {
			bullet = new Bullet(x,y,bulletSpeed,bulletSize,bulletSize,a, game.BULLET);
			bullets.add(bullet);
			// Create the bullet lifetime
			bulletDelay.add(new BulletDelay(3792, bullet));
			// Create the timer to control fire rate
			fireRate.add(new FireRate((int) (rof*rofMultiplier),this));
			fired = true;
			Board.sounds.get("fire").play();
		}
	}
	/*
	 * Purpose of this function is to respawn the tanks at a random position of a given area
	 */
	public void respawn(int minX, int maxX, int minY, int maxY, double a) {
		Random rand = new Random();
		x = rand.nextInt(maxX) + minX+1;
		y = rand.nextInt(maxY) + minY+1;
		isVis = true;
		this.a = a;
	}
	/*
	 * Purpose of this function is to reset the effect of powerups
	 */
	public void resetEffect() {
		setBubble(false);
		setSpeedMultiplier(1);
		setRofMultiplier(1);
		GameState.getEffectTimer(ID).resetTick();
	}
	/*
	 * Getters and setters for the useful local variables
	 */
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
	
	public void setRofMultiplier(double r) {
		rofMultiplier = r;
	}
	
	public void setFireLock(boolean g) {
		fireLock = g;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}	
}