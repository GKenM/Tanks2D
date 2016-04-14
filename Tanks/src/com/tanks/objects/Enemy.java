/**
 * This class handles the enemy AI movement and firing
 * Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.objects;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Random;

import com.tanks.main.game;

public class Enemy extends Tank{
	private double targetX, targetY;
	private Walls walls;
	private GameObject wall;
	private boolean checkWalls, checkBots, checkP1 = false;
	private boolean targetReached = true;
	
	private Rectangle top = new Rectangle(0,-50,game.WIDTH,50);
	private Rectangle bottom = new Rectangle(0,game.HEIGHT,game.WIDTH,50);
	private Rectangle left = new Rectangle(-50,0,50,game.HEIGHT);
	private Rectangle right = new Rectangle(game.WIDTH,0,50,game.HEIGHT);
		
	public Enemy(int x, int y, double speed, int width, int height, int rof, int ID, Tank player1, Walls w) {
		super(x, y, speed, width, height, rof, ID);
		wall = new GameObject(0,0,0,0,0,game.WALL);
		this.walls = w;
		this.respawn(player1);
		this.setFireLock(false);
		this.targetX = player1.getX();
		this.targetY = player1.getY();
	}
	
	/*
	 * Purpose of this function is to handle all the update methods of the AI
	 */
	public void update(Tank target, Shape p1Bounds, ArrayList<Enemy> bots) {
		// Check if the temporary target is reached
		if (this.getBounds().contains(targetX, targetY)) {
			targetReached = true;
		}
		// If the temporary target is reach, set the focus back on player
		if (targetReached) {
			this.targetX = target.getX();
			this.targetY = target.getY();
		}
		this.faceTarget();
		this.moveToTarget(bots);
		this.fireInRange(p1Bounds, bots);
	}
	/*
	 * Purpose of this function is to set a temporary target if the AI gets stuck by a wall
	 */
	private void tempTarget(ArrayList<Enemy> bots) {
		boolean running = true;
		Random rand = new Random();
		
		// Run a while loop to find an appropriate temporary target
		while(running) {	
			targetX = rand.nextInt((int) (this.x + 50)) + this.x - 50;
			targetY = rand.nextInt((int) (this.y + 50)) + this.y - 50;
			running = false;
			
			// Check for window boundary
	    	if (targetX < this.width||targetX > game.WIDTH - this.width) {
	    		running = true;
	    	} 
	    	if (targetY < this.height||targetY > game.HEIGHT - this.height) {
	    		running = true;
	    	}
						
			// Check for other bots
			for (int i = 0; i < bots.size(); i++) {
				if (bots.get(i).getBounds().contains(targetX, targetY)) {
					running = true;
					break;
				}
			}
	    	
			// Check for walls
			ArrayList<GameObject> ws = walls.getWalls();
			for (int j = 0; j < ws.size(); j++) {
				if (ws.get(j).getBounds().contains(targetX, targetY)) {
					running = true;
					break;
				}
			}
		}
		targetReached = false;
	}
	/*
	 * Purpose of this function is to make AI face towards the target
	 */
	private void faceTarget() {
		double deltaY = this.targetY-this.y;
		double deltaX = this.targetX-this.x;
		
		this.a = Math.atan2(deltaY, deltaX) * 180/Math.PI;
	}
	/*
	 * Purpose of this function is to move towards the target
	 */
	private void moveToTarget(ArrayList<Enemy> bots) {
		boolean colliding = false;
		
		Shape front = new Rectangle((int) getBounds().getMaxX(), (int) getBounds().getY(), 1, this.getHeight());

		AffineTransform pf = new AffineTransform();
		pf.rotate(this.getA()*Math.PI/180, this.getX(), this.getY());
		front = pf.createTransformedShape(front);
		
		// Check for window boundary collision, if yes, set a temporary target
		if (front.intersects(top)||front.intersects(bottom)||front.intersects(left)||front.intersects(right)) {
				colliding = true;
				this.tempTarget(bots);
		}
		// Loop through all walls and check for any collision, if yes, set a temporary target
		ArrayList<GameObject> ws = walls.getWalls();
		for (int i = 0; i < ws.size(); i++) {
			wall = ws.get(i);
			
			Rectangle wallBounds = wall.getBounds();
	    	
			if (front.intersects(wallBounds)) {
				colliding = true;
				this.tempTarget(bots);
				break;
			}
		}
		// Check for collision with other enemy AI
		for (int i = 0; i < bots.size(); i++) {
			Shape botBounds = bots.get(i).getBounds();
			
			AffineTransform af = new AffineTransform();
			pf.rotate(bots.get(i).getA()*Math.PI/180, bots.get(i).getX(), bots.get(i).getY());
			botBounds = af.createTransformedShape(botBounds);
			
			Area p1 = new Area(front);
			Area p2 = new Area(botBounds);
			
			p1.intersect(p2);
			
			if (bots.get(i).getX() != this.getX()) {
				if (!p1.isEmpty()) {
					colliding = true;
					this.tempTarget(bots);
					break;
				}
			}
		}

		if (colliding) {
			this.setVelocityForward(0);
		} else {
			this.setVelocityForward(2);
		}
		this.moveForward();
	}
	/*
	 * Purpose of this function is to select appropriate respawn points for enemy AI
	 */
	public void respawn(Tank tank) {
		boolean running = true;
		Random rand = new Random();
		
		while(running) {
			x = rand.nextInt(game.WIDTH-1) + 1;
			y = rand.nextInt(game.HEIGHT-1) + 1;
			
			running = false;
			// Check for window boundary
	    	if (getBounds().getX() < this.width||getBounds().getMaxX() > game.WIDTH - this.width) {
	    		running = true;
	    	}
	    	if (getBounds().getY() < this.height||getBounds().getMaxY() > game.HEIGHT - this.height) {
	    		running = true;
	    	}
			
	    	// Create a range for the player tank, and make sure enemy spawn far away
			Rectangle tankBounds = new Rectangle((int) tank.getX() - 250, (int) tank.getY() - 250, 500, 500);
			if (tankBounds.intersects(getBounds())) {
				running = true;
			} 
			
			// Check for walls
			ArrayList<GameObject> ws = walls.getWalls();
			for (int j = 0; j < ws.size(); j++) {
				if (ws.get(j).getBounds().intersects(getBounds())) {
					running = true;
					break;
				}
			}
		}
	}
	/*
	 * Purpose of this function is to fire at player, if the player is in range and there are no other objects in the way
	 */
	private void fireInRange(Shape p1Bounds, ArrayList<Enemy> bots) {
		Shape barrel = new Rectangle((int) getBounds().getMaxX(), (int) getBounds().getY(), 100, 10);
		
		AffineTransform pf = new AffineTransform();
		pf.rotate(this.getA()*Math.PI/180, this.getX(), this.getY());
		barrel = pf.createTransformedShape(barrel);
		
		// Check for any other enemy AI in the way of firing
		for (int i = 0; i < bots.size(); i++) {
			if (barrel.intersects(bots.get(i).getBounds())) {
				checkBots = false;
				break;
			} else {
				checkBots = true;
			}
		}
		
		// Check if any wall are in the way of firing
		ArrayList<GameObject> ws = walls.getWalls();
		for (int i = 0; i < ws.size(); i++) {
			if (barrel.intersects(ws.get(i).getBounds())) {
				checkWalls = false;
				break;
			} else {
				checkWalls = true;
			}
		}

		// Check with player is within the range of firing
		Area p1 = new Area(barrel);
		Area p2 = new Area(p1Bounds);
		
		p1.intersect(p2);
		
		if (!p1.isEmpty()) {
			checkP1 = true;
		} else {
			checkP1 = false;
		}
		
		if (this.isFired() == false && checkP1 == true && checkWalls == true && checkBots == true) {
			this.fire();
		}
	}
}