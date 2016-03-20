package com.tanks.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class OptionState extends State {

	public static boolean control1 = true;
	public static boolean control2 = false;
	
	
	
	@Override
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);	
		g2d.fillRect(40,40,442,300);
		g2d.fillRect(542,40,442,300);
		g2d.fillRect(141,50,80,80);
		g2d.setColor(Color.green);	
		g2d.fillRect(50,50,422,280);
		g2d.fillRect(552,50,422,280);
		g2d.setColor(Color.black);	
		g2d.fillRect(181,60,80,80);
		g2d.fillRect(101,160,80,80);
		g2d.fillRect(201,160,80,80);
		g2d.fillRect(301,160,80,80);
		g2d.fillRect(360,280,40,40);
		g2d.fillRect(703,60,80,80);
		g2d.fillRect(603,160,80,80);
		g2d.fillRect(703,160,80,80);
		g2d.fillRect(803,160,80,80);
		g2d.fillRect(862,280,40,40);
		Font font1 = new Font("Serif", Font.PLAIN, 30);
		g2d.setFont(font1);
		g2d.drawString("CONTROLS 1", 120, 310);
		g2d.drawString("CONTROLS 2", 622, 310);
		g2d.drawString("Press '1'", 310, 120);
		g2d.drawString("Press '2'", 812, 120);
		g2d.setColor(Color.white);
		Font font = new Font("Serif", Font.PLAIN, 60);
		g2d.setFont(font);
		g2d.drawString("W",191, 120);
		g2d.drawString("A",121, 220);
		g2d.drawString("S",221, 220);
		g2d.drawString("D",321, 220);
		g2d.drawString("↑",718, 120);
		g2d.drawString("←",623, 220);
		g2d.drawString("↓",718, 220);
		g2d.drawString("→",823, 220);
		
		if( control1 = true){
			g2d.setColor(Color.white);
			g2d.fillRect(370,290,20,20);
			g2d.fillRect(872,290,20,20);
			g2d.setColor(Color.black);	
			g2d.fillRect(375,295,10,10);
		}	
		else {
			g2d.setColor(Color.white);
			g2d.fillRect(370,290,20,20);
			g2d.fillRect(872,290,20,20);
			g2d.setColor(Color.black);	
			g2d.fillRect(877,295,10,10);
		}				
		

			
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}