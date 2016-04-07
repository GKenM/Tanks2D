package com.tanks.modes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import com.tanks.main.game;
import com.tanks.objects.Bullet;
import com.tanks.objects.Enemy;
import com.tanks.objects.GameObject;
import com.tanks.objects.Tank;

public class ArcadeMode extends GameMode {
	
	private Enemy bot;
	private static ArrayList<Bullet> botBullets;
	
	public ArcadeMode() {
		bot = new Enemy(0,0,tankSpeed,tankSize,tankSize, game.BOT);
		botBullets = bot.getBullets();
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
	    
	    // Missiles
	    for (int i = 0; i < p1Bullets.size(); i++) {
	    	bullet = p1Bullets.get(i);
	    	
	    	g2d.drawImage(image.getSprite(4) , (int) bullet.getX() - 4 , (int) bullet.getY() -4, null);
	    	
	    }
	    
	    // Bot Tank
		g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(bot.getA()), bot.getX(), bot.getY()));
	    if (bot.getVis()){g2d.drawImage(image.getSprite(1), (int) bot.getX() -24 , (int) bot.getY() -24, null);}
	    g2d.setTransform(oldTransform);
	}

	@Override
	public void tick() {
		// Object Interactions
		Shape p1Bounds = player1.getBounds();
		AffineTransform af = new AffineTransform();
		af.rotate(player1.getA() * Math.PI/180, player1.getX(), player1.getY());
		p1Bounds = af.createTransformedShape(p1Bounds);
		
		Shape botBounds = bot.getBounds();
		AffineTransform bf = new AffineTransform();
		bf.rotate(bot.getA() * Math.PI/180, bot.getX(), bot.getY());
		botBounds = bf.createTransformedShape(botBounds);
		
		mechanics.p1VsWalls(player1);
		mechanics.bulletVsWalls(p1Bullets);
		mechanics.tankVsBullet(player1, bot, p1Bullets, botBullets, p1Bounds, botBounds);
		//mechanics.tankvsTank(player1, bot, p1Bounds, botBounds);
		//mechanics.tankvsPowerUp(player1, powerUps, p1Bounds);
		
		
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
		
		// AI
		bot.update(player1);
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

	@Override
	public void spawnPowerup(ArrayList<GameObject> walls) {
		
	}

}
