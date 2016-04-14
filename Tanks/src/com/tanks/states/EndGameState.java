/**
 *	This state draws out the ENDGAME STATE
 *  Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.tanks.modes.LocalMP;
import com.tanks.resources.LoadSprites;

public class EndGameState extends State{
		
	private LoadSprites image;
	private boolean drawing1;
	
	//Boolean values dictate what option is highlighted and which game mode was played
	public static boolean training1 = false;
	public static boolean arcade1 = true;
	public static boolean localM1 = false;
	public static boolean RSHighlighted = true;
	public static boolean MMHighlighted = false;
	public static int p1score = 0;
	private int p1Kills = 0;
	private int p1Deaths = 0;
	private double p1KD = 0.00;
	private int p2score = 0;
	private int p2Kills = 0;
	private int p2Deaths = 0;
	private double p2KD = 0.00;

	@Override
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		//set fonts
		Font font = new Font("Serif", Font.PLAIN, 50);
		Font font2 = new Font("Serif", Font.PLAIN, 40);
		Font font3 = new Font("Serif", Font.PLAIN, 60);
		Font font4 = new Font("Serif", Font.PLAIN, 16);
		Font font5 = new Font("Serif", Font.PLAIN, 24);
		Font font6 = new Font("Serif", Font.PLAIN, 14);
		//draw default boxes and frames for all modes
		image = new LoadSprites();
		g2d.setColor(Color.green);
		g2d.fillRect(60,60,904,666);
		g2d.setColor(Color.black);
		g2d.fillRect(80,80,864,626);
		g2d.setFont(font2);
		g2d.setColor(Color.green);
		g2d.fillRect(302,420,420,120);
		g2d.fillRect(302,570,420,120);
		g2d.setColor(Color.black);
		g2d.fillRect(312,430,400,100);
		g2d.fillRect(312,580,400,100);
		g2d.setColor(Color.green);
		g2d.drawString("RESTART", 400, 490);
		g2d.drawString("MAIN MENU", 380, 640);
		
		
		// add text and images for Training mode end state
		if(training1 == true){
			g2d.setFont(font3);
			g2d.drawString("TRAINING OVER",250,180);
			g2d.drawImage(image.getSprite(19),350,210, null);
			g2d.setFont(font4);
			g2d.drawString("\"Looks like you got the", 380, 250);
			g2d.drawString("hang of it, how about try", 380, 270);
			g2d.drawString("out ARCADE MODE now!\"", 380, 290);
		}
		
		//add text and images for arcade mode
		if(arcade1 == true){
			g2d.setFont(font3);
			g2d.drawString("TIMES UP!",320,160);
			g2d.fillRect(300,170,385,5);
			g2d.drawImage(image.getSprite(19),600,210, null);
			g2d.setFont(font4);
			// tank displays certain message depending on score
			if(p1score < 40){
				g2d.drawString("\"Come on mate, that was ", 630, 250);
				g2d.drawString("bloody dreadful, next time", 630, 270);
				g2d.drawString("kill something, Godammit!!\"", 630, 290);
			}
			if((p1score > 39) && (p1score < 100)){
				g2d.drawString("\"Not a bad effort, but", 630, 250);
				g2d.drawString("I'm sure you can do a lot ", 630, 270);
				g2d.drawString("better, try again!!\"", 630, 290);
			}
			if(p1score > 99){
				g2d.drawString("\"Hey that was some DANK ", 630, 250);
				g2d.drawString("performance, now lets see", 630, 270);
				g2d.drawString("you beat that high score!\" ", 630, 290);
			}
			g2d.setColor(Color.green);
			g2d.fillRect(100,200,400,200);
			g2d.setColor(Color.black);
			g2d.fillRect(110,210,380,180);
			g2d.setColor(Color.green);
			g2d.setFont(font5);
			g2d.drawString("RESULTS",140,250);
			g2d.fillRect(130,260,140,5);
			g2d.fillRect(130,345,140,5);
			g2d.setFont(font4);
			
			//draw p1 stats
			g2d.drawString("KILLS - ",140,285);
			p1Kills = GameState.getMode().getPlayer1().getKills();
			g2d.drawString(p1Kills + "",210,285);
						
			g2d.drawString("DEATHS - ",140,310);
			p1Deaths = GameState.getMode().getPlayer1().getDeaths();
			g2d.drawString(p1Deaths + "",230,310);
			
			g2d.drawString("K/D - ",140,335);
			if(p1Deaths == 0){
				p1KD = p1Kills;
				g2d.drawString("DANK",185,335);
			} else{
				p1KD = (double)p1Kills / (double) p1Deaths;
				p1KD = Math.round(p1KD*100)/100.0d;
				g2d.drawString(p1KD + "",185,335);
			}

			g2d.drawString("TOTAL SCORE :",140,375);
			p1score = GameState.getMode().getPlayer1().getScore();
			g2d.drawString((p1score + ""),280,375);
			
			// draw high scores and leaderboard  + info
			g2d.setColor(Color.green);
			g2d.fillRect(100,420,190,270);
			g2d.fillRect(735,420,190,270);
			g2d.setColor(Color.black);
			g2d.fillRect(110,430,170,250);
			g2d.fillRect(745,430,170,250);
			g2d.setColor(Color.green);
			g2d.setFont(font2);
			g2d.drawString("Press" ,140,480);
			g2d.drawString("\"L\"" ,165,520);
			g2d.drawString("for" ,165,560);
			g2d.drawString("Leader" ,120,600);
			g2d.drawString("Board" ,130,640);
			
			g2d.setFont(font4);
			g2d.drawString("HIGH SCORES" ,770,460);
			g2d.setFont(font6);
			g2d.drawString("1" ,760,500);
			g2d.drawString("2" ,760,530);
			g2d.drawString("3" ,760,560);
			g2d.drawString("4" ,760,590);
			g2d.drawString("5" ,760,620);
			g2d.drawString(LeaderBoard.LBNameArray[0] ,790,500);
			g2d.drawString(LeaderBoard.LBNameArray[1] ,790,530);
			g2d.drawString(LeaderBoard.LBNameArray[2] ,790,560);
			g2d.drawString(LeaderBoard.LBNameArray[3] ,790,590);
			g2d.drawString(LeaderBoard.LBNameArray[4] ,790,620);
			g2d.drawString(LeaderBoard.LBScoreArray[0] ,880,500);
			g2d.drawString(LeaderBoard.LBScoreArray[1] ,880,530);
			g2d.drawString(LeaderBoard.LBScoreArray[2] ,880,560);
			g2d.drawString(LeaderBoard.LBScoreArray[3] ,880,590);
			g2d.drawString(LeaderBoard.LBScoreArray[4] ,880,620);
			
			if(p1score > Integer.parseInt(LeaderBoard.LBScoreArray[4])){
				g2d.setColor(Color.yellow);
				g2d.setFont(font4);
				g2d.drawString("\"NEW HIGHSCORE!!",290,260);
				g2d.drawString("GO TO LEADERBOARD",290,300);
				g2d.drawString("TO SAVE SCORE\"",290,340);
			}
			
			
		}
		//draw multiplayer stats and results
		if(localM1 == true){
			g2d.setColor(Color.green);
			g2d.fillRect(100,100,825,300);
			g2d.setColor(Color.black);
			g2d.fillRect(110,110,805,280);
			g2d.setColor(Color.green);
			g2d.setFont(font2);
			g2d.drawString("PLAYER 1",140,160);
			g2d.drawString("PLAYER 2",680,160);
			g2d.fillRect(130,170,225,5);
			g2d.fillRect(670,170,225,5);
			g2d.drawImage(image.getSprite(LocalMP.p1TankColour),390 ,130 , null);
			g2d.drawImage(image.getSprite(LocalMP.p2TankColour),590 ,130 , null);
			g2d.setColor(Color.green);
			g2d.setFont(font5);
			g2d.fillRect(130,255,180,5);
			g2d.fillRect(670,255,180,5);
			g2d.setFont(font4);
			
			//draw p1 stats
			g2d.drawString("KILLS - ",140,195);
			p1Kills = GameState.getMode().getPlayer1().getKills();
			g2d.drawString(p1Kills + "",210,195);
						
			g2d.drawString("DEATHS - ",140,220);
			p1Deaths = GameState.getMode().getPlayer1().getDeaths();
			g2d.drawString(p1Deaths + "",230,220);
			
			g2d.drawString("K/D - ",140,245);
			if(p1Deaths == 0){
				p1KD = p1Kills;
				g2d.drawString("DANK",185,245);
			} else{
				p1KD = (double)p1Kills / (double) p1Deaths;
				p1KD = Math.round(p1KD*100)/100.0d;
				g2d.drawString(p1KD + "",185,245);
			}


			g2d.drawString("TOTAL SCORE :",140,280);
			p1score = GameState.getMode().getPlayer1().getScore();
			g2d.drawString((p1score + ""),280,280);
			
			//draw p2 stats
			g2d.drawString("KILLS - ",680,195);
			p2Kills = GameState.getMode().getPlayer2().getKills();
			g2d.drawString(p2Kills + "",750,195);
						
			g2d.drawString("DEATHS - ",680,220);
			p2Deaths = GameState.getMode().getPlayer2().getDeaths();
			g2d.drawString(p2Deaths + "",770,220);
			
			g2d.drawString("K/D - ",680,245);
			if(p2Deaths == 0){
				p2KD = p2Kills;
				g2d.drawString("DANK",725,245);
			} else{
				p2KD = (double)p2Kills / (double) p2Deaths;
				p2KD = Math.round(p2KD*100)/100.0d;
				g2d.drawString(p2KD + "",725,245);
			}
			g2d.drawString("TOTAL SCORE :",680,280);
			p2score = GameState.getMode().getPlayer2().getScore();
			g2d.drawString((p2score + ""),820,280);
			
			//draw flashing winner under the winning player
			if(p1score > p2score){
				g2d.setColor(Color.white);
				g2d.setFont(font);
				if (drawing1 == true){
					g2d.drawString("WINNER", 130, 350);
					
					drawing1 = false;
				} else {
					drawing1 = true;
				}
			}
			else if(p1score < p2score){
				g2d.setColor(Color.white);
				g2d.setFont(font);
				if (drawing1 == true){
					g2d.drawString("WINNER", 670, 350);
					
					drawing1 = false;
				} else {
					drawing1 = true;
				}
			} else {
				g2d.setColor(Color.white);
				g2d.setFont(font);
				g2d.drawString("DRAW", 430, 350);
			}
			
		}
		
		// highlight restart box
		if(RSHighlighted == true){

			g2d.setColor(Color.black);
			g2d.fillRect(302,420,420,120);
			g2d.setColor(Color.green);
			g2d.fillRect(312,430,400,100);
			g2d.setColor(Color.black);	
			g2d.setFont(font2);
			g2d.drawString("RESTART", 400, 490);
		}
		// highlights main menu
		if(MMHighlighted == true){

			g2d.setColor(Color.black);
			g2d.fillRect(302,570,420,120);
			g2d.setColor(Color.green);
			g2d.fillRect(312,580,400,100);
			g2d.setColor(Color.black);	
			g2d.setFont(font2);
			g2d.drawString("MAIN MENU", 380, 640);
		}

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
	}	

}