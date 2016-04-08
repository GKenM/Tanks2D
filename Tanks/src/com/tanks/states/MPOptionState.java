package com.tanks.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.tanks.resources.LoadSprites;

public class MPOptionState extends State {	
	private LoadSprites image;
	
	public static boolean p1redtank = false;
	public static boolean p1yellowtank = true;
	public static boolean p1bluetank = false;
	public static boolean p1violettank = false;
	
	public static boolean p2redtank = true;
	public static boolean p2yellowtank = false;
	public static boolean p2bluetank = false;
	public static boolean p2violettank = false;
	
	public static boolean leftDown = false;
	public static boolean leftUp = false;
	public static boolean rightDown = false;
	public static boolean rightUp = false;
	
	@Override
	public void doDrawing(Graphics g) {
		image = new LoadSprites();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setColor(Color.pink);	
		g2d.fillRect(0,0,1024,768);
		g2d.setColor(Color.black);	
		g2d.fillRect(40,40,442,300);
		g2d.fillRect(542,40,442,300);
		g2d.setColor(Color.white);	
		g2d.fillRect(50,50,422,280);
		g2d.fillRect(552,50,422,280);
		
		if( p1redtank == true){
		g2d.drawImage(image.getSprite(7), 170 , 100, null);
		}
		if( p2redtank == true){
		g2d.drawImage(image.getSprite(7), 670 , 100, null);
		}
		if( p1yellowtank == true){
		g2d.drawImage(image.getSprite(8), 170 , 100, null);
		}
		if( p2yellowtank == true){
		g2d.drawImage(image.getSprite(8), 670 , 100, null);
		}
		if( p1bluetank == true){
		g2d.drawImage(image.getSprite(9), 170 , 100, null);
		}
		if( p2bluetank == true){
		g2d.drawImage(image.getSprite(9), 670 , 100, null);
		}
		if( p1violettank == true){
		g2d.drawImage(image.getSprite(10), 170 , 100, null);
		}
		if( p2violettank == true){
		g2d.drawImage(image.getSprite(10), 670 , 100, null);
		}
		
		Font font = new Font("Serif", Font.PLAIN, 60);
		g2d.setFont(font);
		
		g2d.setColor(Color.gray);
		g2d.drawString("W ▲", 230, 100);
		g2d.drawString("S ▼", 230, 300);
		g2d.drawString("▲ ↑", 730, 100);
		g2d.drawString("▼ ↓", 730, 300);
		
		if(leftUp == true){
			g2d.setColor(Color.black);
			g2d.drawString("W ▲", 230, 100);
		}
		if(leftDown == true){
			g2d.setColor(Color.black);
			g2d.drawString("S ▼", 230, 300);
		}
		if(rightUp == true){
			g2d.setColor(Color.black);
			g2d.drawString("▲ ↑", 730, 100);
		}
		if(rightDown == true){
			g2d.setColor(Color.black);
			g2d.drawString("▼ ↓", 730, 300);
		}
		
		g2d.setColor(Color.black);
		g2d.drawString("PLAYER 1", 100, 400);
		g2d.drawString("PLAYER 2", 600, 400);
			
		g2d.setColor(Color.black);	
		g2d.fillRect(20,620,80,80);
		g2d.setColor(Color.white);	
		g2d.fillRect(30,630,60,60);
		g2d.setColor(Color.black);
		Font font3 = new Font("Serif", Font.PLAIN, 24);
		g2d.setFont(font3);
		g2d.drawString("ESC", 35, 655);
		g2d.setFont(font);
		g2d.drawString("↩", 35, 695);

		
			
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}