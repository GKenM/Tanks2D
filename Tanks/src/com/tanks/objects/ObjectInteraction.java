package com.tanks.objects;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;

import com.tanks.main.game;
import com.tanks.reminders.RespawnDelay;
import com.tanks.states.GameState;

public class ObjectInteraction {
	private Bullet bullet;
	private Walls walls;
	private GameObject wall;
	private ArrayList<RespawnDelay> respawnDelay;
	
	private Rectangle top, bottom, left, right;
	
	public ObjectInteraction() {
		bullet = new Bullet(0,0,0,0,0,0,game.BULLET);
		walls = new Walls();
		wall = new GameObject(0,0,0,0,0,game.WALL);
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
	    	if (castBullet.getX() < 0) {
	    		// Bullet is going to hit the left boundary
	    		bullet.setBounce(true);
	    		bullet.setVelX(-bullet.getVelX());
	    		bullet.setX(0+bullet.getWidth()/2);
	    	}
	    	if (castBullet.getMaxX() > game.WIDTH) {
	    		// Bullet is going to hit the right boundary 
	    		bullet.setBounce(true);
	    		bullet.setVelX(-bullet.getVelX());
	    		bullet.setX(game.WIDTH-bullet.getWidth()/2);
	    	}
	    	if (castBullet.getY() < 0) {
	    		// Bullet is going to hit the top boundary
	    		bullet.setBounce(true);
	    		bullet.setVelY(-bullet.getVelY());
	    		bullet.setY(0+bullet.getHeight()/2);
	    	}
	    	if (castBullet.getMaxY() > game.HEIGHT) {
	    		// Bullet is going to hit the bottom boundary
	    		bullet.setBounce(true);
	    		bullet.setVelY(-bullet.getVelY());
	    		bullet.setY(game.HEIGHT-bullet.getHeight()/2);
	    	}
	    	
	    	ArrayList<GameObject> ws = walls.getWalls();
	    	ArrayList<GameObject> temp = new ArrayList<GameObject>();
	    	
	    	// Calculate all the walls the bullet is going to hit
	    	for (int j = 0; j < ws.size(); j++) {
	    		wall = ws.get(j);
	    		
	    		Rectangle wallBounds = wall.getBounds();
	    		
	    		if (wallBounds.intersects(castBullet)) {
	    			temp.add(wall);
	    			bullet.setBounce(true);
	    		}
	    	}
	    	// If the bullet is only going to collide with one wall
	    	if (temp.size() == 1) {
	    		Rectangle wallBounds = temp.get(0).getBounds();
    			Rectangle insect = wallBounds.intersection(castBullet);
    			
    			boolean vert = false;
    			boolean horz = false;
    			boolean topRight = false;
    			boolean topLeft = false;
    			boolean bottomRight = false;
    			boolean bottomLeft = false;
    			
    			// Check if the bullet is going to collide on the the left or right of the wall
    			if(insect.getX() == wallBounds.getX()) {
    				horz = true;
    			} else if (insect.getX() + insect.getWidth() == wallBounds.getX() + wallBounds.getWidth()) {
    				horz = true;
    			}
    			
    			// Check if the bullet is going to collide on the top or bottom of the wall
    			if (insect.getY() == wallBounds.getY()) {
    				vert = true;
    			} else if (insect.getY() + insect.getHeight() == wallBounds.getY() + wallBounds.getHeight()) {
    				vert = true;
    			}
    			
    			// If the bullet is going to collide on the corner of the wall
    			if (horz && vert) {
    				horz = false;
    				vert = false;
    				// Check for which corner the the bullet will hit
    				// If the bullet axis's aligns with the corner then disregard it as a corner hit
    				// and figure out if its a vertical or horizontal hit
					if (insect.getX() == wallBounds.getX() && insect.getY() == wallBounds.getY()) {
						if (castBullet.getX() == wallBounds.getX()) {
							vert = true;
						} else if (castBullet.getY() == wallBounds.getY()) {
							horz = true;
						} else {
							topLeft = true;
						}
					} else if (insect.getMaxX() == wallBounds.getMaxX() && insect.getY() == wallBounds.getY()) {
						if (castBullet.getMaxX() == wallBounds.getMaxX()) {
							vert = true;
						} else if (castBullet.getY() == wallBounds.getY()) {
							horz = true;
						} else {
							topRight = true;
						}
					} else if (insect.getX() == wallBounds.getX() && insect.getMaxY() == wallBounds.getMaxY()) {
						if (castBullet.getX() == wallBounds.getX()) {
							vert = true;
						} else if (castBullet.getMaxY() == wallBounds.getMaxY()) {
							horz = true;
						} else {
							bottomLeft = true;
						}
					} else if (insect.getMaxX() == wallBounds.getMaxX() && insect.getMaxY() == wallBounds.getMaxY()) {
						if (castBullet.getMaxX() == wallBounds.getMaxX()) {
							vert = true;
						} else if (castBullet.getMaxY() == wallBounds.getMaxY()) {
							horz = true;
						} else {
							bottomRight = true;
						}
					}
    			}
    			
    			if (horz) {
    				// If its a horizontal collision, invert the horizontal velocity vector
    				bullet.setVelX(-bullet.getVelX());
    			} else if (vert) {
    				// If its a vertical collision, invert the vertical velocity vector
    				bullet.setVelY(-bullet.getVelY());
    			} else {
    				// If the collision is in the corner
					if (topLeft) {
						// If the collision is in top left, then velocity x and velocity y should both be turned negative
						if (bullet.getVelX() > 0) {bullet.setVelX(-bullet.getVelX());}
						if (bullet.getVelY() > 0) {bullet.setVelY(-bullet.getVelY());}
					} else if (topRight) {
						// If top right, velocity x should be positive and velocity y should be negative
						if (bullet.getVelX() < 0) {bullet.setVelX(-bullet.getVelX());}
						if (bullet.getVelY() > 0) {bullet.setVelY(-bullet.getVelY());}
					} else if (bottomLeft) {
						// If bottom left, velocity x should be negative and velocity y should be positive
						if (bullet.getVelX() > 0) {bullet.setVelX(-bullet.getVelX());}
						if (bullet.getVelY() < 0) {bullet.setVelY(-bullet.getVelY());}
					} else if (bottomRight) {
						// If bottom right, both velocity x and velocity y should be positive
						if (bullet.getVelX() < 0) {bullet.setVelX(-bullet.getVelX());}
						if (bullet.getVelY() < 0) {bullet.setVelY(-bullet.getVelY());}
					}
					
	    		}

	    	} else if (temp.size() == 2) {
	    		// If the bullet is hitting two walls at the same time
	    		GameObject wall1 = temp.get(0);
	    		GameObject wall2 = temp.get(1);
	    		
	    		// Figure out if its a vertical hit or horizontal hit, invert vectors accordingly
	    		if (wall1.getX() == wall2.getX()+wall2.getWidth() || wall1.getX()+wall1.getWidth() == wall2.getX()) {
	    			bullet.setVelY(-bullet.getVelY());
	    		}
	    		if (wall1.getY() == wall2.getY()+wall2.getHeight() || wall1.getY()+wall1.getHeight() == wall2.getY()) {
	    			bullet.setVelX(-bullet.getVelX());
	    		}
	    	} else if (temp.size() == 3) {
	    		// If the bullet is hitting 3 walls at the same time, then it is a corner, so invert both vectors
	    		bullet.setVelX(-bullet.getVelX());
	    		bullet.setVelY(-bullet.getVelY());
	    	}
	    }
	}
	
	public void tankVsBullet(Tank player1, Tank player2, ArrayList<Bullet> m1, ArrayList<Bullet> m2, Shape p1Bounds, Shape p2Bounds) {
		for (int i = 0; i < m1.size(); i++) {
			bullet = m1.get(i);
			Rectangle bulletBounds = bullet.getBounds();
			
			// If player 1 bullet hits player 1
			if (p1Bounds.intersects(bulletBounds)&&bullet.getBounce()) {
				bullet.setVis(false);
				if (player1.isBubble() == false) {
					player1.setVis(false);
					player2.setScore(player2.getScore()+1);
					respawnDelay.add(new RespawnDelay(1000));
				} else {
					player1.setBubble(false);
					player1.setPU(0);
				}
			} 
			// If player 1 bullet hits player 2
			if (p2Bounds.intersects(bulletBounds)) {
				bullet.setVis(false);
				if (player2.isBubble() == false) {
					player2.setVis(false);
					player1.setScore(player2.getScore()+1);
					respawnDelay.add(new RespawnDelay(1000));
				} else {
					player2.setBubble(false);
					player2.setPU(0);
				}
			}
		}
		
		for (int i = 0; i < m2.size(); i++) {
			bullet = m2.get(i);
			Rectangle bulletBounds = bullet.getBounds();
			
			// If player 2 bullet hits player 1
			if (p1Bounds.intersects(bulletBounds)) {
				bullet.setVis(false);
				if (player1.isBubble() == false) {
					player1.setVis(false);
					player2.setScore(player2.getScore()+1);
					respawnDelay.add(new RespawnDelay(1000));
				} else {
					player1.setBubble(false);
					player1.setPU(0);
				}
			} 
			
			// If player 2 bullet hits player 2
			if (p2Bounds.intersects(bulletBounds)&&bullet.getBounce()) {
				bullet.setVis(false);				
				if (player2.isBubble() == false) {
					player2.setVis(false);
					player1.setScore(player2.getScore()+1);
					respawnDelay.add(new RespawnDelay(1000));
				} else {
					player2.setBubble(false);
					player2.setPU(0);
				}
			}
		}
	}
	
	public void tankvsTank(Tank player1, Tank player2, Shape p1Bounds, Shape p2Bounds) {	
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
	
	public void tankvsPowerUp(Tank player1, ArrayList<PowerUp> powerUps, Shape p1Bounds) {
		for (int i = 0; i < powerUps.size(); i++) {
			if (p1Bounds.intersects(powerUps.get(i).getBounds())) {
				GameState.getEffectTimer(player1.getID()).set(player1);
				
				player1.resetEffect();
				powerUps.get(i).applyEffect(player1);
				powerUps.remove(i);
			}
		}
	}
}
