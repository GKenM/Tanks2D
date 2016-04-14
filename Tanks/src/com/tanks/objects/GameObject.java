/**
 * This class is the parent class for all the objects in the game
 * Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.objects;

import java.awt.Rectangle;

public class GameObject {

	protected double x,y;
	protected double velocityForward, velocityBackward;
	protected double a;
	protected double speed;
	protected int width, height;
	protected int ID; 
	
	public GameObject(double x, double y,double speed, int width, int height, int ID) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.ID = ID;
		velocityForward = 0;
		velocityBackward = 0;
		a = 0.0;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVelocityForward() {
		return velocityForward;
	}

	public void setVelocityForward(double velocityForward) {
		this.velocityForward = velocityForward;
	}
	
	public double getVelocityBackward() {
		return velocityBackward;
	}

	public void setVelocityBackward(double velocityBackward) {
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

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setA(double a) {
		this.a = a;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x-width/2, (int)y-height/2, width,height);
	}
}
