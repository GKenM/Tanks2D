package com.tanks.modes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

import com.tanks.main.game;
import com.tanks.objects.Bullet;
import com.tanks.objects.GameObject;
import com.tanks.objects.PowerUp;
import com.tanks.objects.Tank;
import com.tanks.reminders.PowerUpDestroyer;

public class TrainingMode extends GameMode {
	private Tank bot;
    private static ArrayList<Bullet> botBullets;
    private static ArrayList<PowerUp> powerUps; 
    private PowerUp powerUp;
    
    public TrainingMode() {
		bot = new Tank(0,0,tankSpeed,tankSize,tankSize, game.BOT);
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
	    if (player1.getVis()){g2d.drawImage(image.getSprite(0), (int) player1.getX() -24 , (int) player1.getY() -24, null);}
	    g2d.setTransform(oldTransform);
	    
	    // Bot Tank
		g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(bot.getA()), bot.getX(), bot.getY()));
	    if (bot.getVis()){g2d.drawImage(image.getSprite(1), (int) bot.getX() -24 , (int) bot.getY() -24, null);}
	    g2d.setTransform(oldTransform);
	    
	    // Missiles
	    for (int i = 0; i < p1Bullets.size(); i++) {
	    	bullet = p1Bullets.get(i);
	    	
	    	g2d.drawImage(image.getSprite(4) , (int) bullet.getX() - 4 , (int) bullet.getY() -4, null);
	    	
	    }
 	    
	    // Power Ups
	    g2d.setColor(Color.white);
	    for (int i = 0; i < powerUps.size(); i++) {
	    	powerUp = powerUps.get(i);
	    	
	    	g2d.fill(powerUp.getBounds());
	    }
	    
	    // Power Up id
	    g2d.setColor(Color.white); 
	    g2d.drawString("PowerUP: " + player1.getPU(), 100, 100);
		
	}

	@Override
	public void tick() {
		// Object Interactions
		Shape p1Bounds = player1.getBounds();
		AffineTransform af = new AffineTransform();
		af.rotate(player1.getA() * Math.PI/180, player1.getX(), player1.getY());
		p1Bounds = af.createTransformedShape(p1Bounds);
		
		Shape botBounds = bot.getBounds();
		
		mechanics.p1VsWalls(player1);
		mechanics.bulletVsWalls(p1Bullets);
		mechanics.tankVsBullet(player1, bot, p1Bullets, botBullets, p1Bounds, botBounds);
		mechanics.tankvsTank(player1, bot, p1Bounds, botBounds);
		mechanics.tankvsPowerUp(player1, powerUps, p1Bounds);
		
		
		// Player and bullet movement
		player1.setFireLock(false);
		player1.moveForward();
		player1.moveBackward();
		
		for (int i = 0; i < p1Bullets.size(); i++) {
			bullet = p1Bullets.get(i);
			bullet.move();
			
			if (bullet.getVis() == false) {
				p1Bullets.remove(i);
			}
		}
	}

	@Override
	public void respawn() {
		// Remove bullets
		if (p1Bullets.size() != 0) {p1Bullets.clear();}
		// Respawn tanks
		player1.respawn(player1.getWidth(), 100, 192, 384, 0);
		bot.respawn(924, 100-bot.getWidth(), 192, 384, 180);
	}
	
	public void reset() {
		this.respawn();
		
		// Reset player scores
		player1.setScore(0);
		
		// Reset PowerUps
		player1.setPU(0);
		player1.setBubble(false);
		player1.setSpeedMultiplier(1);
		player1.setRof(2000);
		
		// Delete power ups
		powerUps.clear();
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
			powerUp = new PowerUp(0,0,0,24,24,game.POWERUP);
			powerUp.setPosition(player1, bot, walls);
			
			int puID = rand.nextInt(5) + 1;
			powerUp.setPuID(puID);
			
			powerUps.add(powerUp);
		}
		new PowerUpDestroyer(10,powerUps);
	}

}
