package com.tanks.objects;

public class Bullet extends GameObject{
	
	private boolean isVis;

	private double velX;
	private double velY;
	private boolean bounce;
	
	public Bullet(int x, int y, int speed, int width, int height, double a) {
		super(x, y, speed, width, height);
		this.a = a;
		this.velX = Math.round(speed * Math.cos(a * Math.PI/180));
		this.velY = Math.round(speed * Math.sin(a * Math.PI/180));
		isVis = true;
		bounce = false;
	}
	
	public void move() {
		x += velX;
		y += velY;
	}
	
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
