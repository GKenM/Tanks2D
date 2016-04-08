package com.tanks.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class MPState extends State{

	public static boolean LMHighlighted = true;
	public static boolean OPHighlighted = false;
	
	@Override
	public void doDrawing(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
			g2d.setColor(Color.pink);
			g2d.fillRect(0,0,1024,768);
			g2d.setColor(Color.black);	
			g2d.fillRect(252,90,520,120);
			g2d.setColor(Color.white);	
			g2d.fillRect(262,100,500,100);
			g2d.setColor(Color.black);
			g2d.fillRect(302,240,420,120);
			g2d.fillRect(302,390,420,120);
			g2d.setColor(Color.white);
			g2d.fillRect(312,250,400,100);
			g2d.fillRect(312,400,400,100);
			Font font = new Font("Serif", Font.PLAIN, 50);
			g2d.setFont(font);
			g2d.setColor(Color.black);
			g2d.drawString("MULTIPLAYER", 330, 170);
			Font font2 = new Font("Serif", Font.PLAIN, 40);
			g2d.setFont(font2);
			g2d.drawString("LOCAL ", 420, 290);
			g2d.drawString("MULTIPLAYER", 350, 340);
			g2d.drawString("OPTIONS", 400, 460);
			
			

			g2d.setColor(Color.black);	
			g2d.fillRect(20,620,80,80);
			g2d.setColor(Color.white);	
			g2d.fillRect(30,630,60,60);
			g2d.setColor(Color.black);
			Font font3 = new Font("Serif", Font.PLAIN, 24);
			g2d.setFont(font3);
			g2d.drawString("ESC", 35, 655);
			g2d.setFont(font);
			g2d.drawString("↩", 40, 690);

			if(LMHighlighted == true){

				g2d.setColor(Color.white);
				g2d.fillRect(302,240,420,120);
				g2d.setColor(Color.black);
				g2d.fillRect(312,250,400,100);
				g2d.setColor(Color.white);	
				g2d.setFont(font2);
				g2d.drawString("LOCAL ", 420, 290);
				g2d.drawString("MULTIPLAYER", 350, 340);
			}
			if(OPHighlighted == true){

				g2d.setColor(Color.white);
				g2d.fillRect(302,390,420,120);
				g2d.setColor(Color.black);
				g2d.fillRect(312,400,400,100);
				g2d.setColor(Color.white);	
				g2d.setFont(font2);
				g2d.drawString("OPTIONS", 400, 460);
			}

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}


}