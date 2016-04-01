package com.tanks.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import com.tanks.objects.Bullet;
import com.tanks.objects.GameObject;
import com.tanks.objects.ObjectInteraction;
import com.tanks.objects.Tank;
import com.tanks.objects.Walls;

public class GameState extends State{
	
	private static Tank tank;
	private static Tank player2;
	private Bullet bullet;
	ArrayList<Tank> enemy;
		
	private ObjectInteraction test;
	private Walls walls;
	
    private static ArrayList<Bullet> p1Bullets;
    private static ArrayList<Bullet> p2Bullets;
    
	public GameState(Tank tank, Tank player2) {
		GameState.tank = tank;
		GameState.player2 = player2;
		enemy = new ArrayList<Tank>();
		test = new ObjectInteraction();
		walls = new Walls();
		p1Bullets = tank.getBullets();
		p2Bullets = player2.getBullets();
	}
	
	@Override
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;	
		
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		// Player Tank
		g2d.setColor(Color.green);
		AffineTransform oldTransform = g2d.getTransform();
		g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(tank.getA()), tank.getX(), tank.getY()));
	    if (tank.getVis()) {g2d.fill(tank.getBounds());}
	    g2d.setTransform(oldTransform);
	    
	    // Player2 Tank
		g2d.setColor(Color.black);
		g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(player2.getA()), player2.getX(), player2.getY()));
	    if (player2.getVis()) {g2d.fill(player2.getBounds());}
	    g2d.setTransform(oldTransform);
	    
	    // Missiles
	  //  ArrayList<Bullet> ms = tank.getBullets();
	    
	    for (int i = 0; i < p1Bullets.size(); i++) {
	    	bullet = p1Bullets.get(i);
	    	
	    	g2d.fill(bullet.getBounds());
	    	
	    }
	    for (int i = 0; i < p2Bullets.size(); i++) {
	    	bullet = p2Bullets.get(i);
	    	
	    	g2d.fill(bullet.getBounds());
	    	
	    } 
	    
	    // Enemy Tank
	    g2d.setColor(Color.black);
	    if (enemy.size() != 0) {
			g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(22.5), enemy.get(0).getX(), enemy.get(0).getY()));
		    g2d.fill(enemy.get(0).getBounds());
		    g2d.setTransform(oldTransform);
	    }
    
	    // Walls
	    g2d.setColor(Color.red);
	    ArrayList<GameObject> wall = walls.getWalls();
	    
	    for (int i = 0; i < wall.size(); i++) {
	    	g2d.fill(wall.get(i).getBounds());
	    }
	    
	    // Score
	    g2d.setColor(Color.white); 
	    g2d.drawString("Player1: " + tank.getScore(), 100, 100);
	    g2d.drawString("Player2: " + player2.getScore(), 800, 100);
	    
	    
	    
	}

	@Override
	public void tick() {
		test.p1VsWalls(tank);
		test.p1VsWalls(player2);
		
		tank.moveForward();
		tank.moveBackward();
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
		
		
		test.bulletVsWalls(p1Bullets);
		test.bulletVsWalls(p2Bullets);
		test.tankVsBullet(tank, player2, p1Bullets, p2Bullets);
	}
	
	public static void reset() {
		// Remove everything
		if (p1Bullets.size() != 0) {p1Bullets.clear();}
		if (p2Bullets.size() != 0) {p2Bullets.clear();}
		// Reset everything
		tank.respawn();
		player2.respawn();
	}
}