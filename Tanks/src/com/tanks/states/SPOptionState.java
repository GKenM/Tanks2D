package com.tanks.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class SPOptionState extends State {

	public static boolean control1 = true;
	public static boolean control2 = false;
	
	
	
	@Override
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setColor(Color.blue);
		g2d.fillRect(0,0,1024,768);
		g2d.setColor(Color.black);	
		g2d.fillRect(40,40,442,300);
		g2d.fillRect(542,40,442,300);
		g2d.fillRect(141,50,80,80);		
		g2d.setColor(Color.white);	
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
		g2d.fillRect(312,350,400,100);
		Font font1 = new Font("Serif", Font.PLAIN, 36);
		g2d.setFont(font1);
		g2d.drawString("CONTROLS 1", 80, 320);
		g2d.drawString("CONTROLS 2", 582, 320);
		g2d.setColor(Color.gray);
		g2d.fillRect(332,360,360,80);
		g2d.fillRect(191,63,60,72);
		g2d.fillRect(111,164,60,72);
		g2d.fillRect(211,164,60,72);
		g2d.fillRect(311,164,60,72);
		g2d.fillRect(713,63,60,72);
		g2d.fillRect(613,164,60,72);
		g2d.fillRect(713,164,60,72);
		g2d.fillRect(813,164,60,72);
		g2d.fillRect(332,360,360,80);
		g2d.setColor(Color.black);
		g2d.fillRect(360,280,40,40);
		g2d.fillRect(860,280,40,40);
		Font font = new Font("Serif", Font.PLAIN, 50);
		g2d.setFont(font);
		g2d.drawString("W",194, 120);
		g2d.drawString("A",121, 220);
		g2d.drawString("S",226, 220);
		g2d.drawString("D",321, 220);
		g2d.drawString("↑",722, 120);
		g2d.drawString("←",623, 220);
		g2d.drawString("↓",722, 220);
		g2d.drawString("→",823, 220);
		g2d.setColor(Color.blue);
		Font font2 = new Font("Serif", Font.PLAIN, 20);
		g2d.setFont(font2);
		g2d.drawString("Rotate Right", 320, 270);
		g2d.drawString("Rotate Right", 820, 270);
		g2d.drawString("Rotate Left", 60, 270);
		g2d.drawString("Rotate Left", 560, 270);
		g2d.drawString("Reverse", 200, 270);
		g2d.drawString("Reverse", 700, 270);
		g2d.drawString("Forward", 80, 110);
		g2d.drawString("Forward", 580, 110);
		
		g2d.setColor(Color.gray);
		g2d.fillRect(332,360,360,80);
		
		g2d.setColor(Color.black);
		Font font3 = new Font("Serif", Font.PLAIN, 40);
		g2d.setFont(font3);
		g2d.drawString("SPACE ",360, 410);
		g2d.setColor(Color.blue);
		g2d.drawString("- Shoot",520, 410);
		
		g2d.setColor(Color.black);	
		g2d.fillRect(20,620,80,80);
		g2d.setColor(Color.white);	
		g2d.fillRect(30,630,60,60);
		g2d.setColor(Color.black);
		Font font4 = new Font("Serif", Font.PLAIN, 24);
		g2d.setFont(font4);
		g2d.drawString("ESC", 35, 655);
		g2d.setFont(font);
		g2d.drawString("↩", 40, 690);

		
		if( control1 == true){
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