package com.tanks.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

import com.tanks.objects.Missle;
import com.tanks.objects.Tank;

public class GameState extends State{
	
	private Tank tank;
	private Missle missle;
	ArrayList<Tank> enemy;
	
	public GameState(Tank tank) {
		this.tank = tank;
		enemy = new ArrayList<Tank>();
		spawnEnemy();
	}
	
	@Override
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;	
		
		// Player Tank
		Rectangle temp = new Rectangle(tank.getX(),tank.getY(),tank.getWidth(),tank.getHeight());
		g2d.setColor(Color.green);
		AffineTransform oldTransform = g2d.getTransform();
		g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(tank.getA()), tank.getX()+tank.getWidth()/2, tank.getY()+tank.getHeight()/2));
	    g2d.fill(temp);
	    g2d.setColor(Color.orange);
	    g2d.fillRect(tank.getX()+tank.getWidth(), tank.getY()+5, 10, 40);
	    g2d.setTransform(oldTransform);
	    
	    // Missles
	    ArrayList<Missle> ms = tank.getMissle();
	    
	    for (int i = 0; i < ms.size(); i++) {
	    	missle = ms.get(i);
	    	
	    	g2d.fillRect(missle.getX(),missle.getY(),missle.getWidth(),missle.getHeight());
	    	
	    }
	    
	    // Enemy Tank
	    g2d.setColor(Color.black);
	    if (enemy.size() != 0) {
	    	g2d.fillRect(enemy.get(0).getX(), enemy.get(0).getY(), enemy.get(0).getWidth(), enemy.get(0).getHeight());
	    }
	}

	@Override
	public void tick() {
		tank.move();
		
		ArrayList<Missle> ms = tank.getMissle();
		
		for (int i = 0; i < ms.size(); i++) {
			missle = ms.get(i);			
			missle.move();
			
			if (missle.getVis() == false) {
				ms.remove(i);
			}
			if (enemy.size() != 0) {
				if (collision(enemy.get(0),missle)) {
					enemy.remove(0);
					ms.remove(i);
					spawnEnemy();
				}
			}
		}
	}
	
	private void spawnEnemy() {
		Random rand = new Random();
		// Fix the bounds
		int x = rand.nextInt(1024-50) + 50;
		int y = rand.nextInt(768-50) + 50;
		enemy.add(new Tank(x,y,8, 50, 50));
	}
	
	private boolean collision(Tank getrekt, Missle missle) {
		Rectangle enemyBounds = new Rectangle(getrekt.getX(), getrekt.getY(), getrekt.getWidth(), getrekt.getHeight());
		Rectangle missleBounds = new Rectangle(missle.getX(), missle.getY(), missle.getWidth(), missle.getHeight());
		
		if (enemyBounds.intersects(missleBounds)) {
			return true;
		} else {
			return false;
		}
	}
}