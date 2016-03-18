package com.tanks.objects;

public class Missle extends GameObject{
	
	protected double a;
	private boolean isVis;

	public Missle(int x, int y, int speed, int width, int height, double a) {
		super(x, y, speed, width, height);
		this.a = a;
		isVis = true;
	}
	
	public void move() {
		x += Math.round(speed * Math.cos(a * Math.PI/180));
		y += Math.round(speed * Math.sin(a * Math.PI/180));
	}
	
	public void setVis(boolean vis) {
		this.isVis = vis;
	}
	
	public boolean getVis() {
		return isVis;
	}
}
