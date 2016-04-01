package com.tanks.objects;

import java.awt.Rectangle;

public class GameObject {

	protected int x,y;
	protected int velocityForward, velocityBackward;
	protected double a;
	protected int speed;
	protected int width, height;
	
	public GameObject(int x, int y, int speed, int width, int height) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
		velocityForward = 0;
		velocityBackward = 0;
		a = 0.0;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelocityForward() {
		return velocityForward;
	}

	public void setVelocityForward(int velocityForward) {
		this.velocityForward = velocityForward;
	}
	
	public int getVelocityBackward() {
		return velocityBackward;
	}

	public void setVelocityBackward(int velocityBackward) {
		this.velocityBackward = velocityBackward;
	}

	public double getA() {
		return a;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setA(double a) {
		this.a = a;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x-width/2, y-height/2, width,height);
	}
}
