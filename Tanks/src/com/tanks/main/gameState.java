package com.tanks.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class gameState extends State{
	
	private Tank tank;
	
	public gameState(Tank tank) {
		this.tank = tank;
	}
	
	@Override
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;	
		Rectangle temp = new Rectangle(tank.getX(),tank.getY(),tank.getWidth(),tank.getHeight());
		g2d.setColor(Color.green);
		AffineTransform oldTransform = g2d.getTransform();
		g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(tank.getA()), tank.getX()+tank.getWidth()/2, tank.getY()+tank.getHeight()/2));
	    g2d.fill(temp);
	    g2d.setColor(Color.orange);
	    g2d.fillRect(tank.getX()+tank.getWidth(), tank.getY()+5, 10, 40);
	    g2d.setTransform(oldTransform);
	}

	@Override
	public void tick() {
		tank.move();
	}

}
