/**
 * This Class handles all the bullet objects
 * Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.objects;

public class Bullet extends GameObject{
	
	private boolean isVis;
	private double velX;
	private double velY;
	private boolean bounce;
	
	public Bullet(double x, double y, double speed, int width, int height, double a, int ID) {
		super(x, y, speed, width, height, ID);
		this.a = a;
		// Create the initial velocity vectors according to the angle of which they are shot at
		this.velX = Math.round(speed * Math.cos(a * Math.PI/180));
		this.velY = Math.round(speed * Math.sin(a * Math.PI/180));
		isVis = true;
		bounce = false;
	}
	/*
	 * This function moves the bullet by its velocity vectors, every tick
	 */
	public void move() {
		x += velX;
		y += velY;
	}
	/*
	 * Getters and setters functions for useful variables
	 */
	public void setVis(boolean vis) {
		this.isVis = vis;
	}
	
	public boolean getVis() {
		return isVis;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	public void setBounce(boolean b) {
		bounce = b;
	}
	
	public boolean getBounce() {
		return bounce;
	}
	
}
