/**
 *	This State draws out the title menu and contains boolean values that also manage all other states in the game
 *  Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.states;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.tanks.resources.LoadSprites;

import java.awt.Color;


public class TitleState extends State{

	// boolean values for every state in the game
	public static boolean isMenu = true;
	public static boolean isOption = false;
	public static boolean isMPOption = false;
	public static boolean isSPOption = false;
	public static boolean isGameMenu = false;
	public static boolean isSP = false;
	public static boolean isMP = false;
	public static boolean isTraining = false;
	public static boolean isArcade = false;
	public static boolean isLocalMP = false;
	public static boolean isEndGame = false;
	public static boolean isLB = false;

	//prevState keeps track of previous states with assigning a number to them
	//1 Title Menu, 2 SP Menu, 3 MP Menu, 
	//4 Training Mode, 5 Arcade Mode, 6 LocalM Mode, 
	//7 OptionState, 8 Game Menu, 9 EndGameState
	public static int prevState = 0; 
	//for when entering option menu through game menu
	public static int prevState2 = 0; 
	
	//Boolean values dictate what option is highlighted
	public static boolean SPHighlighted = true;
	public static boolean MPHighlighted = false;
	public static boolean OPHighlighted = false;

	private LoadSprites image;
	

	@Override
	public void doDrawing(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
			//draw out background image rain and tank
			image = new LoadSprites();
			g2d.setColor(Color.gray);
			g2d.fillRect(0,0,1024,768);
			g2d.drawImage(image.getSprite(18),0,0, null);
			g2d.drawImage(image.getSprite(17),-40,-40, null);
			
			//draw out Title and select boxes
			g2d.setColor(Color.black);	
			g2d.fillRect(252,90,520,120);
			g2d.setColor(Color.white);	
			g2d.fillRect(262,100,500,100);
			g2d.setColor(Color.black);
			g2d.fillRect(302,240,420,120);
			g2d.fillRect(302,390,420,120);
			g2d.fillRect(302,540,420,120);
			g2d.setColor(Color.white);
			g2d.fillRect(312,250,400,100);
			g2d.fillRect(312,400,400,100);
			g2d.fillRect(312,550,400,100);
			Font font = new Font("Serif", Font.PLAIN, 50);
			g2d.setFont(font);
			g2d.setColor(Color.black);
			g2d.drawString("DANK TANKS", 330, 170);
			Font font2 = new Font("Serif", Font.PLAIN, 40);
			g2d.setFont(font2);
			g2d.drawString("SINGLEPLAYER", 330, 310);
			g2d.drawString("MULTIPLAYER", 345, 460);
			g2d.drawString("OPTIONS", 410, 610);

			// highlight single player box
			if(SPHighlighted == true){
				g2d.setColor(Color.white);
				g2d.fillRect(302,240,420,120);
				g2d.setColor(Color.black);
				g2d.fillRect(312,250,400,100);
				g2d.setColor(Color.white);	
				g2d.setFont(font2);
				g2d.drawString("SINGLEPLAYER", 330, 310);
			}
			//highlight multiplayer box
			if(MPHighlighted == true){
				g2d.setColor(Color.white);
				g2d.fillRect(302,390,420,120);
				g2d.setColor(Color.black);
				g2d.fillRect(312,400,400,100);
				g2d.setColor(Color.white);	
				g2d.setFont(font2);
				g2d.drawString("MULTIPLAYER", 345, 460);
			}
			//highlight option box
			if(OPHighlighted == true){
				g2d.setColor(Color.white);			
				g2d.fillRect(302,540,420,120);
				g2d.setColor(Color.black);
				g2d.fillRect(312,550,400,100);
				g2d.setColor(Color.white);	
				g2d.setFont(font2);
				g2d.drawString("OPTIONS", 410, 610);
			}	
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub	
	}
}