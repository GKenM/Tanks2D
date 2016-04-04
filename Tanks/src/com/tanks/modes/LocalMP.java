package com.tanks.modes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import com.tanks.objects.Bullet;
import com.tanks.objects.GameObject;
import com.tanks.objects.Tank;

public class LocalMP extends GameMode{
	
	private Tank player2;		
    private static ArrayList<Bullet> p2Bullets;

	public LocalMP() {
		player2 = new Tank(0,0,tankSpeed,tankSize,tankSize);
		p2Bullets = player2.getBullets();
		reset();
	}
	
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;	
		
		// Player Tank
		AffineTransform oldTransform = g2d.getTransform();
		g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(player1.getA()), player1.getX(), player1.getY()));
	    if (player1.getVis()){g2d.drawImage(image.getSprite(0), player1.getX() -24 , player1.getY() -24, null);}
	    g2d.setTransform(oldTransform);
	    
	    // Player2 Tank
		g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(player2.getA()), player2.getX(), player2.getY()));
	    if (player2.getVis()){g2d.drawImage(image.getSprite(1), player2.getX() -24 , player2.getY() -24, null);}
	    g2d.setTransform(oldTransform);
	    
	    // Missiles
	    for (int i = 0; i < p1Bullets.size(); i++) {
	    	bullet = p1Bullets.get(i);
	    	
	    	g2d.drawImage(image.getSprite(4) ,bullet.getX() - 4 , bullet.getY() -4, null);
	    	
	    }
	    for (int i = 0; i < p2Bullets.size(); i++) {
	    	bullet = p2Bullets.get(i);
	    	
	    	g2d.drawImage(image.getSprite(5) ,bullet.getX() - 4 , bullet.getY() -4, null);
	    	
	    }     	  
	    
	    // Score - mabe move it somewhere else
	    g2d.setColor(Color.white); 
	    g2d.drawString("Player1: " + player1.getScore(), 100, 100);
	    g2d.drawString("Player2: " + player2.getScore(), 800, 100);
	    
	    
	}
	
	public void tick() {
		mechanics.p1VsWalls(player1);
		mechanics.p1VsWalls(player2);
		
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
		mechanics.bulletVsWalls(p1Bullets);
		mechanics.bulletVsWalls(p2Bullets);
		mechanics.tankVsBullet(player1, player2, p1Bullets, p2Bullets);
		mechanics.tankvsTank(player1, player2);
		mechanics.bulletvsBullet(p1Bullets, p2Bullets);
	}
	
	public void reset() {
		// Remove everything
		if (p1Bullets.size() != 0) {p1Bullets.clear();}
		if (p2Bullets.size() != 0) {p2Bullets.clear();}
		// Reset everything
		player1.respawn(player1.getWidth(), 100, 192, 384, 0);
		player2.respawn(924, 100-player2.getWidth(), 192, 384, 180);
	}
	
	public Tank getPlayer1() {
		return player1;
	}
	
	public Tank getPlayer2() {
		return player2;
	}

	@Override
	public void spawnPowerup(ArrayList<GameObject> walls) {
		// TODO Auto-generated method stub
		
	}

}
