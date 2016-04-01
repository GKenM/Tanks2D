package com.tanks.objects;

public class Bullet extends GameObject{
	
	private boolean isVis;

	private int speedX;
	private int speedY;
	private boolean bounce;
	
	public Bullet(int x, int y, int speed, int width, int height, double a) {
		super(x, y, speed, width, height);
		this.a = a;
		this.speedX = speed;
		this.speedY = speed;
		isVis = true;
		bounce = false;
	}
	
	public void move() {
		x += Math.round(speedX * Math.cos(a * Math.PI/180));
		y += Math.round(speedY * Math.sin(a * Math.PI/180));
	}
	
	public void setVis(boolean vis) {
		this.isVis = vis;
	}
	
	public boolean getVis() {
		return isVis;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	public void setBounce(boolean b) {
		bounce = b;
	}
	
	public boolean getBounce() {
		return bounce;
	}
	
}
