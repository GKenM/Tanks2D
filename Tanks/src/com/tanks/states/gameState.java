package com.tanks.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import com.tanks.objects.Missle;
import com.tanks.objects.Tank;

public class gameState extends State{
	
	private Tank tank;
	private Missle missle;
	
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
	    
	    ArrayList<Missle> ms = tank.getMissle();
	    
	    for (int i = 0; i < ms.size(); i++) {
	    	missle = ms.get(i);
	    	
	    	g2d.fillRect(missle.getX(),missle.getY(),missle.getWidth(),missle.getHeight());
	    	
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
		}	
	}
}
