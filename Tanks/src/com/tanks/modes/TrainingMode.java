package com.tanks.modes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

import com.tanks.objects.Bullet;
import com.tanks.objects.GameObject;
import com.tanks.objects.PowerUp;
import com.tanks.objects.Tank;

public class TrainingMode extends GameMode {
	private Tank bot;
    private static ArrayList<Bullet> botBullets;
    private static ArrayList<PowerUp> powerUps; 
    private PowerUp powerUp;
    
	public TrainingMode() {
		bot = new Tank(0,0,tankSpeed,tankSize,tankSize);
		botBullets = bot.getBullets();
		powerUps = new ArrayList<PowerUp>();
		reset();
	}
	@Override
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;	
		
		// Player Tank
		AffineTransform oldTransform = g2d.getTransform();
		g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(player1.getA()), player1.getX(), player1.getY()));
	    if (player1.getVis()){g2d.drawImage(image.getSprite(0), player1.getX() -24 , player1.getY() -24, null);}
	    g2d.setTransform(oldTransform);
	    
	    // Missiles
	    for (int i = 0; i < p1Bullets.size(); i++) {
	    	bullet = p1Bullets.get(i);
	    	
	    	g2d.drawImage(image.getSprite(4) ,bullet.getX() - 4 , bullet.getY() -4, null);
	    	
	    }
 	    
	    // Power Ups
	    for (int i = 0; i < powerUps.size(); i++) {
	    	powerUp = powerUps.get(i);
	    	
	    	g2d.fill(powerUp.getBounds());
	    }
		
	}

	@Override
	public void tick() {
		mechanics.p1VsWalls(player1);
		
		player1.moveForward();
		player1.moveBackward();
		
		for (int i = 0; i < p1Bullets.size(); i++) {
			bullet = p1Bullets.get(i);
			bullet.move();
			
			if (bullet.getVis() == false) {
				p1Bullets.remove(i);
			}
		}

		mechanics.bulletVsWalls(p1Bullets);
		mechanics.tankVsBullet(player1, bot, p1Bullets, botBullets);
		mechanics.tankvsTank(player1, bot);
	}

	@Override
	public void reset() {
		// Remove everything
		if (p1Bullets.size() != 0) {p1Bullets.clear();}
		// Reset everything
		player1.respawn(player1.getWidth(), 100, 192, 384, 0);
		bot.respawn(924, 100-bot.getWidth(), 192, 384, 180);
	}

	@Override
	public Tank getPlayer1() {
		return player1;
	}

	@Override
	public Tank getPlayer2() {
		return null;
	}
	
	public void spawnPowerup(ArrayList<GameObject> walls) {
		Random rand = new Random();
		int num = rand.nextInt(3) + 1;
		
		for (int i = 0; i < num; i++) {
			powerUp = new PowerUp(0,0,0,24,24);
			powerUp.setPosition(player1, bot, walls);
			
			int ID = rand.nextInt(5) + 1;
			powerUp.setID(ID);
			
			powerUps.add(powerUp);
		}	
	}

}
