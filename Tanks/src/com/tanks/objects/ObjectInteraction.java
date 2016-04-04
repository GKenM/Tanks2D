package com.tanks.objects;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
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
	    	   	
    		int castX = (int) (bullet.getX() + bullet.getVelX());
    		int castY = (int) (bullet.getY() + bullet.getVelY());

    		Rectangle castBullet = new Rectangle(castX-bullet.getWidth()/2, castY-bullet.getHeight()/2, bullet.getWidth(), bullet.getHeight());
    		
	    	
	    	// Window boundary bounce
	    	if (castBullet.getX() < 0||castBullet.getMaxX() > 1024) {
	    		bullet.setBounce(true);
	    		bullet.setVelX(-bullet.getVelX());
	    	}
	    	if (castBullet.getY() < 0||castBullet.getMaxY() > 768) {
	    		bullet.setBounce(true);
	    		bullet.setVelY(-bullet.getVelY());
	    	}
	    	
	    	ArrayList<GameObject> ws = walls.getWalls();
	    	ArrayList<GameObject> temp = new ArrayList<GameObject>();
	    	
	    	for (int j = 0; j < ws.size(); j++) {
	    		wall = ws.get(j);
	    		
	    		Rectangle wallBounds = wall.getBounds();
	    		
	    		if (wallBounds.intersects(castBullet)) {
	    			temp.add(wall);
	    			bullet.setBounce(true);
	    		}
	    	}
	    	
	    	if (temp.size() != 0) {
	    	System.out.println(temp.size());
	    	
	    	}
 
	    	if (temp.size() == 1) {
	    		// run the full algorithm
	    		
	    		// Almost but not perfect
	    		// BUGS: when it hit the corner and has no velocity in one direction
	    		Rectangle wallBounds = temp.get(0).getBounds();
    			Rectangle insect = wallBounds.intersection(castBullet);
    			
    			boolean vert = false;
    			boolean horz = false;
    			
    			if(insect.getX() == wallBounds.getX()) {
    				horz = true;
    			} else if (insect.getX() + insect.getWidth() == wallBounds.getX() + wallBounds.getWidth()) {
    				horz = true;
    			}
    			
    			if (insect.getY() == wallBounds.getY()) {
    				vert = true;
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
    				bullet.setVelX(-bullet.getVelX());
    			} else if (vert) {
    				bullet.setVelY(-bullet.getVelY());
    			} else {
					bullet.setVelX(-bullet.getVelX());
					bullet.setVelY(-bullet.getVelY());
	    		}

	    	} else if (temp.size() == 2) {
	    		// run horz or vertical
	    		GameObject wall1 = temp.get(0);
	    		GameObject wall2 = temp.get(1);
	    		
	    		if (wall1.getX() == wall2.getX()+wall2.getWidth() || wall1.getX()+wall1.getWidth() == wall2.getX()) {
	    			bullet.setVelY(-bullet.getVelY());
	    			//System.out.println("Hit vert");
	    		}
	    		if (wall1.getY() == wall2.getY()+wall2.getHeight() || wall1.getY()+wall1.getHeight() == wall2.getY()) {
	    			bullet.setVelX(-bullet.getVelX());
	    		}
	    	} else if (temp.size() == 3) {
	    		// dnt run any
	    		bullet.setVelX(-bullet.getVelX());
	    		bullet.setVelY(-bullet.getVelY());
	    	}
	    }
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
		
		// ROTATE THE BULLET HITBOX - SHOULD I??
		
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
	
	public void tankvsTank(Tank player1, Tank player2) {
		Shape p1Bounds = player1.getBounds();
		AffineTransform af = new AffineTransform();
		af.rotate(player1.getA() * Math.PI/180, player1.getX(), player1.getY());
		p1Bounds = af.createTransformedShape(p1Bounds);
		
		Shape p2Bounds = player2.getBounds();
		AffineTransform bf = new AffineTransform();
		bf.rotate(player2.getA() * Math.PI/180, player2.getX(), player2.getY());
		p2Bounds = bf.createTransformedShape(p2Bounds);	
	
		Area p1 = new Area(p1Bounds);
		Area p2 = new Area(p2Bounds);
		
		p1.intersect(p2);
		
		if (!p1.isEmpty()) {
			player1.setVis(false);
			player2.setVis(false);
			respawnDelay.add(new RespawnDelay(1000));
		}
		
	}
	
	public void bulletvsBullet(ArrayList<Bullet> m1, ArrayList<Bullet> m2) {
		for (int i = 0; i < m1.size(); i++) {
			Rectangle bullet1 = m1.get(i).getBounds();
			
			for (int j = 0; j < m2.size(); j++) {
				Rectangle bullet2 = m2.get(j).getBounds();
				
				if (bullet1.intersects(bullet2)) {
					m1.get(i).setVis(false);
					m2.get(j).setVis(false);
				}
				
			}
		}
	}
	
	public void tankvsPowerUp() {
		
	}
	
	
}
