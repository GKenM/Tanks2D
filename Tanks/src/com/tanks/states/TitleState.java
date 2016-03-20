package com.tanks.states;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Color;


public class TitleState extends State{

	public static boolean isTraining = false;
	public static boolean isOption = false;
	public static boolean isMenu = true;
	
	
	@Override
	public void doDrawing(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.black);	
			g2d.fillRect(252,90,520,120);
			g2d.setColor(Color.orange);	
			g2d.fillRect(262,100,500,100);
			g2d.setColor(Color.green);
			g2d.fillRect(302,240,420,120);
			g2d.fillRect(302,390,420,120);
			g2d.fillRect(302,540,420,120);
			g2d.setColor(Color.yellow);
			g2d.fillRect(312,250,400,100);
			g2d.fillRect(312,400,400,100);
			g2d.fillRect(312,550,400,100);
			Font font = new Font("Serif", Font.PLAIN, 50);
			g2d.setFont(font);
			g2d.setColor(Color.black);
			g2d.drawString("DANK TANKS", 330, 170);
			Font font2 = new Font("Serif", Font.PLAIN, 40);
			g2d.setFont(font2);
			g2d.drawString("TRAINING MODE", 330, 290);
			g2d.drawString("ARCADE MODE", 345, 440);
			g2d.drawString("OPTIONS", 410, 590);
			Font font3 = new Font("Serif", Font.PLAIN, 30);
			g2d.setFont(font3);
			g2d.drawString("PRESS 'T'", 430,330);
			g2d.drawString("PRESS 'R'", 430,480);
			g2d.drawString("PRESS 'O'", 430,630);


	}



	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}



}