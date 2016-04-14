package com.tanks.modes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

import com.tanks.main.Board;
import com.tanks.main.game;
import com.tanks.objects.Bullet;
import com.tanks.objects.GameObject;
import com.tanks.objects.PowerUp;
import com.tanks.objects.Tank;
import com.tanks.reminders.PowerUpDestroyer;
import com.tanks.states.GameState;
import com.tanks.states.MPOptionState;

public class LocalMP extends GameMode{
	
	private static Tank player2;		
    private static ArrayList<Bullet> p2Bullets;
    private static ArrayList<PowerUp> powerUps; 
    private PowerUp powerUp;
	public static int p1TankColour;
	public static int p2TankColour;

	public LocalMP() {
		player2 = new Tank(0,0,GameState.tankSpeed,GameState.tankSize,GameState.tankSize,GameState.tankRof,game.P2);
		p2Bullets = player2.getBullets();
		powerUps = new ArrayList<PowerUp>();
		reset();
	}
	
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;	
		


		//tank colour select
		for(int i =0; i < 4; i++){
			if( MPOptionState.p1tanks[i] == true){
				p1TankColour = i;
			}
		}
		for(int i =0; i < 4; i++){
			if( MPOptionState.p2tanks[i] == true){
				p2TankColour = i;
			}
		}



		// Player Tank
		AffineTransform oldTransform = g2d.getTransform();
		g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(player1.getA()), player1.getX(), player1.getY()));
		if (player1.getVis()){g2d.drawImage(Board.images.getSprite(p1TankColour), (int) player1.getX() -24 , (int) player1.getY() -24, null);}
		g2d.setTransform(oldTransform);

		// Player2 Tank
		g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(player2.getA()), player2.getX(), player2.getY()));
		if (player2.getVis()){g2d.drawImage(Board.images.getSprite(p2TankColour), (int) player2.getX() -24 , (int) player2.getY() -24, null);}
		g2d.setTransform(oldTransform);
	    
	    // Missiles
	    for (int i = 0; i < p1Bullets.size(); i++) {
	    	bullet = p1Bullets.get(i);
	    	
	    	g2d.drawImage(Board.images.getSprite(4) ,(int) bullet.getX()-4 , (int) bullet.getY()-4, null);
	    }
	    for (int i = 0; i < p2Bullets.size(); i++) {
	    	bullet = p2Bullets.get(i);

	    	g2d.drawImage(Board.images.getSprite(5) ,(int) bullet.getX()-4 , (int) bullet.getY()-4, null);
	    	
	    }     	  

	    g2d.setColor(Color.white); 
		Font font = new Font("Serif", Font.PLAIN, 20);
		g2d.setFont(font);
	    
	    // Player Scores
	    g2d.drawString("Player1: " + player1.getScore(), 10, 25);
	    g2d.drawString("Player2: " + player2.getScore(), 910, 25);
	    
	    // Player 1 power up indicator
	    if (player1.getPU() == 1) {
	    	g2d.drawImage(Board.images.getSprite(11), 10, 40, null);
	    } else if (player1.getPU() == 2) {
	    	g2d.drawImage(Board.images.getSprite(15), 10, 40, null);
	    } else if (player1.getPU() == 3) {
	    	g2d.drawImage(Board.images.getSprite(14), 10, 40, null);
	    } else if (player1.getPU() == 4) {
	    	g2d.drawImage(Board.images.getSprite(13), 10, 40, null);
	    } else if (player1.getPU() == 5) {
	    	g2d.drawImage(Board.images.getSprite(12), 10, 40, null);
	    }
	    
	    // Player 2 power up indicator
	    if (player2.getPU() == 1) {
	    	g2d.drawImage(Board.images.getSprite(11), 990, 40, null);
	    } else if (player2.getPU() == 2) {
	    	g2d.drawImage(Board.images.getSprite(15), 990, 40, null);
	    } else if (player2.getPU() == 3) {
	    	g2d.drawImage(Board.images.getSprite(14), 990, 40, null);
	    } else if (player2.getPU() == 4) {
	    	g2d.drawImage(Board.images.getSprite(13), 990, 40, null);
	    } else if (player2.getPU() == 5) {
	    	g2d.drawImage(Board.images.getSprite(12), 990, 40, null);
	    }
	    
	    // Power Ups
	    for (int i = 0; i < powerUps.size(); i++) {
	    	powerUp = powerUps.get(i);
	    	g2d.drawImage(Board.images.getSprite(16), (int) powerUp.getX()-12, (int) powerUp.getY()-12, null);
	    }
	}
	
	public void tick() {
		// Object Interactions
		Shape p1Bounds = player1.getBounds();
		AffineTransform af = new AffineTransform();
		af.rotate(player1.getA() * Math.PI/180, player1.getX(), player1.getY());
		p1Bounds = af.createTransformedShape(p1Bounds);
		
		Shape p2Bounds = player2.getBounds();
		AffineTransform bf = new AffineTransform();
		bf.rotate(player2.getA() * Math.PI/180, player2.getX(), player2.getY());
		p2Bounds = bf.createTransformedShape(p2Bounds);	
		
		mechanics.p1VsWalls(player1);
		mechanics.p1VsWalls(player2);
		mechanics.bulletVsWalls(p1Bullets);
		mechanics.bulletVsWalls(p2Bullets);
		mechanics.tankVsBullet(player1, player2, p1Bullets, p2Bullets, p1Bounds, p2Bounds);
		mechanics.tankvsTank(player1, player2, p1Bounds, p2Bounds);
		mechanics.bulletvsBullet(p1Bullets, p2Bullets);
		mechanics.tankvsPowerUp(player1, powerUps, p1Bounds);
		mechanics.tankvsPowerUp(player2, powerUps, p2Bounds);
	
		// Player and bullet movement
		player1.setFireLock(false);
		player2.setFireLock(false);
		
		player1.moveForward();
		player1.moveBackward();
		player2.moveForward();
		player2.moveBackward();
		
		for (int i = 0; i < p1Bullets.size(); i++) {
			bullet = p1Bullets.get(i);
			bullet.move();
			
			if (bullet.getVis() == false) {
				p1Bullets.remove(i);
			}
		}
		for (int i = 0; i < p2Bullets.size(); i++) {
			bullet = p2Bullets.get(i);			
			bullet.move();
			
			if (bullet.getVis() == false) {
				p2Bullets.remove(i);
			}
		}
	}
	
	public void respawn() {
		// Remove bullets
		if (p1Bullets.size() != 0) {p1Bullets.clear();}
		if (p2Bullets.size() != 0) {p2Bullets.clear();}
		// Respawn tanks
		player1.respawn(player1.getWidth(), 100, 192, 384, 0);
		player2.respawn(924, 100-player2.getWidth(), 192, 384, 180);
	}
	
	public void reset() {
		this.respawn();
		
		// Reset player scores
		player1.setScore(0);
		player1.setKills(0);
		player1.setDeaths(0);
		player2.setScore(0);
		player2.setKills(0);
		player2.setDeaths(0);
		
		// Reset PowerUps
		player1.setPU(0);
		player1.setBubble(false);
		player1.setSpeedMultiplier(1);
		player1.setRof(2000);
		
		player2.setPU(0);
		player2.setBubble(false);
		player2.setSpeedMultiplier(1);
		player2.setRof(2000);
		
		// Delete power ups
		powerUps.clear();
	}
	
	public Tank getPlayer1() {
		return player1;
	}
	
	public Tank getPlayer2() {
		return player2;
	}

	@Override
	public void spawnPowerup(ArrayList<GameObject> walls) {
		Random rand = new Random();
		int num = rand.nextInt(3) + 1;
		
		for (int i = 0; i < num; i++) {
			powerUp = new PowerUp(0,0,0,24,24,game.POWERUP);
			powerUp.setPosition(player1, player2, walls, powerUps);
			
			int puID = rand.nextInt(5) + 1;
			powerUp.setPuID(puID);
			
			powerUps.add(powerUp);
		}
		new PowerUpDestroyer(10,powerUps);
	}
}