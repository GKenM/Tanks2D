package com.tanks.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class GameMenuState extends State{
	
	public static boolean pressed_down = false;
	
	
	@Override
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setColor(Color.black);	
		g2d.fillRect(262,50,500,150);
		Font font = new Font("Serif", Font.PLAIN, 60);
		g2d.setFont(font);
		g2d.setColor(Color.RED);
		g2d.drawString("GAME PAUSED", 270, 160);
		if(pressed_down == true){
			g2d.fillRect(500,500,30,20);
		} else {
			g2d.setColor(Color.blue);	
			g2d.fillRect(500,500,30,20);
		}
		
	}
	

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
