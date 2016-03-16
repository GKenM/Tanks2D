package com.tanks.main;

public class Tank extends GameObject {

	public Tank(int x, int y, int speed, int width, int height) {
		super(x, y, speed, width, height);
	}
	
	public void move() {
		x += path * Math.cos(a * Math.PI/180);
		y += path * Math.sin(a * Math.PI/180);
	}
}
