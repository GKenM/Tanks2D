/**
 * This class handles all the object interactions within the game
 * Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.objects;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;

import com.tanks.main.Board;
import com.tanks.main.game;
import com.tanks.reminders.RespawnDelay;
import com.tanks.states.GameState;

public class ObjectInteraction {
	private Bullet bullet;
	private Walls walls;
	private GameObject wall;
	private ArrayList<RespawnDelay> respawnDelay;
	private Rectangle top, bottom, left, right;
	
	public ObjectInteraction(Walls w) {
		bullet = new Bullet(0,0,0,0,0,0,game.BULLET);
		walls = w;
		wall = new GameObject(0,0,0,0,0,game.WALL);
		respawnDelay = new ArrayList<RespawnDelay>();
		
		// create boundary walls
		top = new Rectangle(0,-50,game.WIDTH,50);
		bottom = new Rectangle(0,game.HEIGHT,game.WIDTH,50);
		left = new Rectangle(-50,0,50,game.HEIGHT);
		right = new Rectangle(game.WIDTH,0,50,game.HEIGHT);
	}
	/*
	 * Purpose of this function is to handle collision between player tanks and walls/window boundary
	 */
	public void p1VsWalls(Tank tank) {
		Rectangle tankBounds = tank.getBounds();
		
		// Create shapes for the front and back of the tanks as they are the only sides of tank that can collide
		Shape front = new Rectangle((int) tankBounds.getMaxX(), (int) tankBounds.getY(), 0, tank.getHeight());
		Shape back = new Rectangle((int) tankBounds.getX(), (int) tankBounds.getY(), 0, tank.getHeight());	
		
		// Transform both shapes for the tanks angle
		AffineTransform pf = new AffineTransform();
		pf.rotate(tank.getA()*Math.PI/180, tank.getX(), tank.getY());
		front = pf.createTransformedShape(front);
		back = pf.createTransformedShape(back);

		// Check for window boundary collision
		if (front.intersects(top)||front.intersects(bottom)||front.intersects(left)||front.intersects(right)) {
			//tank.setY(tank.getY()+tank.getSpeed()); // TO USE IF WE WANT BOUNCE
			tank.setVelocityForward(0);
		}
		if (back.intersects(top)||back.intersects(bottom)||back.intersects(left)||back.intersects(right)) {
			tank.setVelocityBackward(0);
		}
		
		// Loop through every wall object and check for collision
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
	/*
	 * Purpose of this function is to check collision between bullets and wall and implement bounce
	 */
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
    			Board.sounds.get("bounce").play();
	    	}
	    	if (castBullet.getMaxX() > game.WIDTH) {
	    		// Bullet is going to hit the right boundary 
	    		bullet.setBounce(true);
	    		bullet.setVelX(-bullet.getVelX());
	    		bullet.setX(game.WIDTH-bullet.getWidth()/2);
    			Board.sounds.get("bounce").play();
	    	}
	    	if (castBullet.getY() < 0) {
	    		// Bullet is going to hit the top boundary
	    		bullet.setBounce(true);
	    		bullet.setVelY(-bullet.getVelY());
	    		bullet.setY(0+bullet.getHeight()/2);
    			Board.sounds.get("bounce").play();
	    	}
	    	if (castBullet.getMaxY() > game.HEIGHT) {
	    		// Bullet is going to hit the bottom boundary
	    		bullet.setBounce(true);
	    		bullet.setVelY(-bullet.getVelY());
	    		bullet.setY(game.HEIGHT-bullet.getHeight()/2);
    			Board.sounds.get("bounce").play();
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
	    			Board.sounds.get("bounce").play();
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
	/*
	 * Purpose of this function is to check collision between tanks and bullets and give scores appropriately 
	 */
	public void tankVsBullet(Tank player1, Tank player2, ArrayList<Bullet> m1, ArrayList<Bullet> m2, Shape p1Bounds, Shape p2Bounds) {
		// Loop through all player 1 bullets
		for (int i = 0; i < m1.size(); i++) {
			bullet = m1.get(i);
			Rectangle bulletBounds = bullet.getBounds();
			
			// If player 1 bullet hits player 1
			if (p1Bounds.intersects(bulletBounds)&&bullet.getBounce()) {
				bullet.setVis(false);
				// Check for bubble power up
				if (player1.isBubble() == false) {
					player1.setVis(false);
					player2.setScore(player2.getScore()+1);
					player1.setDeaths(player1.getDeaths()+1);
					respawnDelay.add(new RespawnDelay(1000));
					Board.sounds.get("le").play();
				} else {
					player1.setBubble(false);
					player1.setPU(0);
					Board.sounds.get("tap").play();
				}
			} 
			// If player 1 bullet hits player 2
			if (p2Bounds.intersects(bulletBounds)) {
				bullet.setVis(false);
				if (player2.isBubble() == false) {
					player2.setVis(false);
					player1.setScore(player1.getScore()+1);
					player1.setKills(player1.getKills()+1);
					player2.setDeaths(player2.getDeaths()+1);
					respawnDelay.add(new RespawnDelay(1000));
					Board.sounds.get("le").play();
				} else {
					player2.setBubble(false);
					player2.setPU(0);
					Board.sounds.get("tap").play();
				}
			}
		}
		// Loop through all player 2 bullets
		for (int i = 0; i < m2.size(); i++) {
			bullet = m2.get(i);
			Rectangle bulletBounds = bullet.getBounds();
			
			// If player 2 bullet hits player 1
			if (p1Bounds.intersects(bulletBounds)) {
				bullet.setVis(false);
				if (player1.isBubble() == false) {
					player1.setVis(false);
					player2.setScore(player2.getScore()+1);
					player2.setKills(player2.getKills()+1);
					player1.setDeaths(player1.getDeaths()+1);
					respawnDelay.add(new RespawnDelay(1000));
					Board.sounds.get("le").play();
				} else {
					player1.setBubble(false);
					player1.setPU(0);
					Board.sounds.get("tap").play();
				}
			} 
			
			// If player 2 bullet hits player 2
			if (p2Bounds.intersects(bulletBounds)&&bullet.getBounce()) {
				bullet.setVis(false);				
				if (player2.isBubble() == false) {
					player2.setVis(false);
					player1.setScore(player1.getScore()+1);
					player2.setDeaths(player2.getDeaths()+1);
					respawnDelay.add(new RespawnDelay(1000));
					Board.sounds.get("le").play();
				} else {
					player2.setBubble(false);
					player2.setPU(0);
					Board.sounds.get("tap").play();
				}
			}
		}
	}
	/*
	 * AI only function: used to check bullet collision with AI
	 */
	public void bulletVsBot(Tank player1, ArrayList<Enemy> bots, ArrayList<Bullet> m1, Shape p1Bounds, int scoreMP) {
		// Loop through all the active bots
		for (int i = 0; i < bots.size(); i++) {
			// Create a hitbox for bots and transform accordingly
			Shape botBounds = bots.get(i).getBounds();
			AffineTransform bf = new AffineTransform();
			bf.rotate(bots.get(i).getA() * Math.PI/180, bots.get(i).getX(), bots.get(i).getY());
			botBounds = bf.createTransformedShape(botBounds);
			
			// Loop through all player 1 bullets
			for (int j = 0; j < m1.size(); j++) {
				bullet = m1.get(j);
				
				// If bullet hits a bot
				if (botBounds.intersects(bullet.getBounds())) {
					bullet.setVis(false);
					player1.setScore(player1.getScore()+1*scoreMP);
					player1.setKills(player1.getKills()+1);
					bots.remove(i);
					bots.add(new Enemy(0,0,3,48,48,2000,game.BOT, player1, walls));
					Board.sounds.get("le").play();
				}
				// If bullet hit player
				if (p1Bounds.intersects(bullet.getBounds())&&bullet.getBounce()) {
					bullet.setVis(false);
					if (player1.isBubble() == false && player1.getVis()) {
						player1.setVis(false);
						// Lose 3 points
						player1.setScore(player1.getScore()-3);
						player1.setDeaths(player1.getDeaths()+1);
						respawnDelay.add(new RespawnDelay(1000));
						Board.sounds.get("le").play();
					} else {
						player1.setBubble(false);
						player1.setPU(0);
						Board.sounds.get("tap").play();
					}
				} 
			}
			
		    ArrayList<Bullet> botBullets = bots.get(i).getBullets();

		    for (int k = 0; k < botBullets.size(); k++) {
		    	if (p1Bounds.intersects(botBullets.get(k).getBounds())) {
		    		// player 1 is hit
					if (player1.isBubble() == false) {
						player1.setVis(false);
						botBullets.get(k).setVis(false);
						// Lose 3 points
						player1.setScore(player1.getScore()-3);
						player1.setDeaths(player1.getDeaths()+1);
						respawnDelay.add(new RespawnDelay(1000));
						Board.sounds.get("le").play();
					} else {
						player1.setBubble(false);
						player1.setPU(0);
						Board.sounds.get("tap").play();
					}
		    	}
		    }
		}
	}
	/*
	 * Purpose of this function is to check tank on tank collision
	 */
	public void tankvsTank(Tank player1, Tank player2, Shape p1Bounds, Shape p2Bounds) {	
		// Create areas for both tank hitboxes
		Area p1 = new Area(p1Bounds);
		Area p2 = new Area(p2Bounds);
		
		// Check for intersection
		p1.intersect(p2);
		
		// If player1 intersects with player2
		if (!p1.isEmpty() && player1.getVis() && player2.getVis()) {
			player1.setVis(false);
			player2.setVis(false);
			Board.sounds.get("le").play();
			// If the second player is AI, player 1 will get a score penalty
			if (player2.getID() == 0) {
				player1.setScore(player1.getScore()-5);
			}
			respawnDelay.add(new RespawnDelay(1000));
		}
		
	}
	/*
	 * Purpose of this function is to check bullet on bullet collision
	 */
	public void bulletvsBullet(ArrayList<Bullet> m1, ArrayList<Bullet> m2) {
		// Loop through player 1 bullets
		for (int i = 0; i < m1.size(); i++) {
			Rectangle bullet1 = m1.get(i).getBounds();
			// Loop through player 2 bullets
			for (int j = 0; j < m2.size(); j++) {
				Rectangle bullet2 = m2.get(j).getBounds();
				// If bullets collides, make them dissapear 
				if (bullet1.intersects(bullet2)) {
					m1.get(i).setVis(false);
					m2.get(j).setVis(false);
					Board.sounds.get("e").play();
				}
				
			}
		}
	}
	/*
	 * Purpose of this function is to check with player picks up and power up
	 */
	public void tankvsPowerUp(Tank player1, ArrayList<PowerUp> powerUps, Shape p1Bounds) {
		// Loop through all the active power ups
		for (int i = 0; i < powerUps.size(); i++) {
			// If player hits a power up, activate its effect and remove it from the board
			if (p1Bounds.intersects(powerUps.get(i).getBounds())) {
				GameState.getEffectTimer(player1.getID()).set(player1);
				Board.sounds.get("powup").play();
				player1.resetEffect();
				powerUps.get(i).applyEffect(player1);
				powerUps.remove(i);
			}
		}
	}
}