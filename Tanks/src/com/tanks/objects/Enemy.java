package com.tanks.objects;

public class Enemy extends Tank{
		
	public Enemy(int x, int y, double speed, int width, int height, int ID) {
		super(x, y, speed, width, height, ID);
	}

	public void update(Tank target) {
		this.faceTarget(target);
		this.moveToTarget(target);
	}
	
	private void faceTarget(Tank target) {
		double deltaY = target.getY()-this.y;
		double deltaX = target.getX()-this.x;
		
		this.a = Math.atan2(deltaY, deltaX) * 180/Math.PI;
	}
	
	private void moveToTarget(Tank target) {
		this.setVelocityForward(3);
		this.moveForward();
	}
	
	private void fireInRange() {
		// fire if player appears in range - add on if there is no wall in the way
	}
	
}
