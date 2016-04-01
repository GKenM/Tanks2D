package com.tanks.objects;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import com.tanks.reminders.RespawnDelay;

public class ObjectInteraction {
	private Bullet bullet;
	private Walls walls;
	private GameObject wall;
	private ArrayList<RespawnDelay> respawnDelay;
	
	private Rectangle top, bottom, left, right;
	
	public ObjectInteraction() {
		bullet = new Bullet(0,0,0,0,0,0);
		walls = new Walls();
		wall = new GameObject(0,0,0,0,0);
		respawnDelay = new ArrayList<RespawnDelay>();
		
		// create boundary walls
		top = new Rectangle(0,-50,1024,50);
		bottom = new Rectangle(0,768,1024,50);
		left = new Rectangle(-50,0,50,768);
		right = new Rectangle(1024,0,50,768);
	}
	
	public void p1VsWalls(Tank tank) {
		Rectangle tankBounds = tank.getBounds();
		
		Shape front = new Rectangle((int) tankBounds.getX()+tank.getWidth(), (int) tankBounds.getY(), 0, tank.getHeight());
		Shape back = new Rectangle((int) tankBounds.getX(), (int) tankBounds.getY(), 0, tank.getHeight());	
		
		AffineTransform pf = new AffineTransform();
		pf.rotate(tank.getA()*Math.PI/180, tank.getX(), tank.getY());
		front = pf.createTransformedShape(front);
		back = pf.createTransformedShape(back);

		// Window boundary collision
		
		if (front.intersects(top)||front.intersects(bottom)||front.intersects(left)||front.intersects(right)) {
			//tank.setY(tank.getY()+tank.getSpeed()); // TO USE IF WE WANT BOUNCE
			tank.setVelocityForward(0);
		}
		if (back.intersects(top)||back.intersects(bottom)||back.intersects(left)||back.intersects(right)) {
			tank.setVelocityBackward(0);
		}
		
		
		ArrayList<GameObject> ws = walls.getWalls();
		
		for (int i = 0; i < ws.size(); i++) {
			wall = ws.get(i);
			
			Rectangle wallBounds = wall.getBounds();
	    	
			if (front.intersects(wallBounds)) {
				tank.setVelocityForward(0);
			}
			if (back.intersects(wallBounds)) {
				tank.setVelocityBackward(0);
			}
		}
	}	
	
	public void bulletVsWalls(ArrayList<Bullet> ts) {
	    for (int i = 0; i < ts.size(); i++) {
	    	bullet = ts.get(i);
	    	
	    	Rectangle bulletBounds = bullet.getBounds();
	    	
	    	// Window boundary bounce
	    	if (bulletBounds.getX() < 0||bulletBounds.getMaxX() > 1024) {
	    		bullet.setBounce(true);
	    		bullet.setSpeedX(-bullet.getSpeedX());
	    	}
	    	if (bulletBounds.getY() < 0||bulletBounds.getMaxY() > 768) {
	    		bullet.setBounce(true);
	    		bullet.setSpeedY(-bullet.getSpeedY());
	    	}
	    	
    	
	    	// TOO GLITCHY - PLZ GABEN FIX
	    	/*ArrayList<GameObject> ws = walls.getWalls();
	    	
	    	for (int j = 0; j < ws.size(); j++) {
	    		wall = ws.get(j);
	    		
	    		Rectangle wallBounds = wall.getBounds();

	    		if (wallBounds.intersects(bulletBounds)) {
	    			bullet.setBounce(true);
	    			Rectangle insect = wallBounds.intersection(bulletBounds);
	    			
	    			boolean vert = false;
	    			boolean horz = false;
	    			boolean isLeft = false;
	    			boolean isTop = false;
	    			
	    			if(insect.getX() == wallBounds.getX()) {
	    				horz = true;
	    				isLeft = true;
	    			} else if (insect.getX() + insect.getWidth() == wallBounds.getX() + wallBounds.getWidth()) {
	    				horz = true;
	    			}
	    			
	    			if (insect.getY() == wallBounds.getY()) {
	    				vert = true;
	    				isTop = true;
	    			} else if (insect.getY() + insect.getHeight() == wallBounds.getY() + wallBounds.getHeight()) {
	    				vert = true;
	    			}
	    			
	    			if (horz && vert) {
	    				if (insect.getWidth() == insect.getHeight()) {
	    					horz = false;
	    					vert = false;
	    					//System.out.println("FK");
	    				} else if (insect.getWidth() > insect.getHeight()) {
	    					horz = false;
	    				} else {
	    					vert = false;
	    				}
	    			}
	    			
	    			if (horz) {
	    				bullet.setSpeedX(-bullet.getSpeedX());
	    				
	    				if (isLeft) {
	    					bulletBounds.x = wallBounds.x - bulletBounds.width;
	    				} else {
	    					bulletBounds.x = wallBounds.x + wallBounds.width;
	    				}
	    			} else if (vert) {
	    				bullet.setSpeedY(-bullet.getSpeedY());
	    				
	    				if(isTop) {
	    					bulletBounds.y = wallBounds.y - bulletBounds.height;
	    				} else {
	    					bulletBounds.y = wallBounds.y  + wallBounds.height;
	    				}
	    			} else {
    					bullet.setSpeedX(-bullet.getSpeedX());
    					bullet.setSpeedY(-bullet.getSpeedY());
    					
    					// Rewrite more efficiently
    					if (wallBounds.getX() == insect.getX() && wallBounds.getY() == insect.getY()) {
    						// top left corner
    						bulletBounds.x = wallBounds.x - bulletBounds.width;
    						bulletBounds.y = wallBounds.y - bulletBounds.height;
    					} else if (wallBounds.getMaxX() == insect.getMaxX() && wallBounds.getY() == insect.getY()) {
    						// top right corner
    						bulletBounds.x = wallBounds.x + bulletBounds.width;
    						bulletBounds.y = wallBounds.y - bulletBounds.height;
    					} else if (wallBounds.getMaxY() == insect.getMaxY() && wallBounds.getX() == insect.getX()) {
    						// bottom left
    						bulletBounds.x = wallBounds.x - bulletBounds.width;
    						bulletBounds.y = wallBounds.y + bulletBounds.height;
    					} else if (wallBounds.getMaxX() == insect.getMaxX() && wallBounds.getMaxY() == insect.getMaxY()) {
    						// bottom right
    						bulletBounds.x = wallBounds.x + bulletBounds.width;
    						bulletBounds.y = wallBounds.y + bulletBounds.height;
    					}
    	    		}
	    		}
	    	}
*/	    }
	}
	
	public void tankVsBullet(Tank player1, Tank player2, ArrayList<Bullet> m1, ArrayList<Bullet> m2) {
		Shape p1Bounds = player1.getBounds();
		AffineTransform af = new AffineTransform();
		af.rotate(player1.getA() * Math.PI/180, player1.getX(), player1.getY());
		p1Bounds = af.createTransformedShape(p1Bounds);
		
		Shape p2Bounds = player2.getBounds();
		AffineTransform bf = new AffineTransform();
		bf.rotate(player2.getA() * Math.PI/180, player2.getX(), player2.getY());
		p2Bounds = bf.createTransformedShape(p2Bounds);	
		
		for (int i = 0; i < m1.size(); i++) {
			bullet = m1.get(i);
			Rectangle bulletBounds = bullet.getBounds();
			
			if (p1Bounds.intersects(bulletBounds)&&bullet.getBounce()) {
				bullet.setVis(false);
				// increment p2 score and respawn
				player2.setScore(player2.getScore()+1);
				respawnDelay.add(new RespawnDelay(1000));
				//set p1 to invisible
				player1.setVis(false);
			} 
			if (p2Bounds.intersects(bulletBounds)) {
				bullet.setVis(false);
				//increment p1 score and respawn
				player1.setScore(player1.getScore()+1);
				respawnDelay.add(new RespawnDelay(1000));
				//set p2 to invisible
				player2.setVis(false);
			}
		}
		
		for (int i = 0; i < m2.size(); i++) {
			bullet = m2.get(i);
			Rectangle bulletBounds = bullet.getBounds();
			
			if (p1Bounds.intersects(bulletBounds)) {
				bullet.setVis(false);
				// increment p2 score and respawn
				player2.setScore(player2.getScore()+1);
				respawnDelay.add(new RespawnDelay(1000));
				//set p1 to invisible
				player1.setVis(false);
			} else if (p2Bounds.intersects(bulletBounds)&&bullet.getBounce()) {
				bullet.setVis(false);
				//increment p1 score and respawn
				player1.setScore(player1.getScore()+1);
				respawnDelay.add(new RespawnDelay(1000));
				//set p2 to invisible
				player2.setVis(false);
			}
		}
	}
}
