/**
 *	This state draws out the LeaderBoard and reads/writes to textfile "Leaderboard.txt"
 *  Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.states;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class LeaderBoard extends State {

	// Initialize LeaderBoard arrays, data and input name
	public static String LBScoreArray[] = {"0","0","0","0","0"};
	public static String LBNameArray[] = {"Heart","Of","The","Cards","[]]]]]"};
	public static String data = ""; 
	public static String inputName = "JAKOB";

	@Override
	public void doDrawing(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
			//setfonts that are used in drawing 
			Font font = new Font("Serif", Font.PLAIN, 50);
			Font font4 = new Font("Serif", Font.PLAIN, 24);
			Font font5 = new Font("Serif", Font.PLAIN, 36);
			
			// draw out leaderboard frame and headings
			g2d.setColor(Color.green);	
			g2d.fillRect(252,90,520,120);
			g2d.fillRect(142,230,740,400);
			g2d.setColor(Color.black);
			g2d.fillRect(262,100,500,100);
			g2d.fillRect(152,240,720,380);
			g2d.setColor(Color.green);
			g2d.fillRect(142,300,740,10);
			g2d.setFont(font);
			g2d.drawString("LEADERBOARD",305 , 170);
			g2d.setFont(font5);
			g2d.drawString("Rank",160 , 290);
			g2d.drawString("Player Name",400 , 290);
			g2d.drawString("Score",750 , 290);
			
			// draw out the  5 names and high scores
			g2d.drawString("1",190 , 360);
			g2d.drawString(LBNameArray[0],450 , 360);
			g2d.drawString(LBScoreArray[0],780 , 360);
			
			g2d.drawString("2",190 , 420);
			g2d.drawString(LBNameArray[1],450 , 420);
			g2d.drawString(LBScoreArray[1],780 , 420);
			
			g2d.drawString("3",190 , 480);
			g2d.drawString(LBNameArray[2],450 , 480);
			g2d.drawString(LBScoreArray[2],780 , 480);
			
			g2d.drawString("4",190 , 540);
			g2d.drawString(LBNameArray[3],450 , 540);
			g2d.drawString(LBScoreArray[3],780 , 540);
			
			g2d.drawString("5",190 , 600);
			g2d.drawString(LBNameArray[4],450 , 600);
			g2d.drawString(LBScoreArray[4],780 , 600);
			
			// draw escape button
			g2d.setColor(Color.green);	
			g2d.fillRect(20,620,80,80);
			g2d.setColor(Color.black);	
			g2d.fillRect(30,630,60,60);
			g2d.setColor(Color.green);
			g2d.setFont(font4);
			g2d.drawString("ESC", 35, 655);
			g2d.setFont(font);
			g2d.drawString("↩", 40, 690);
			
	}
	// ask user for name to add to leaderboard
	private static String userInput() {	
		return JOptionPane.showInputDialog("Type in name:");
	}
	
	//function reads in text file containing highscores, stores the names and scores in array, if needed adds new highscore, writes new highscore to textfile
	public static void readLBFile(){
		// read in txt file
		try {	 
			String fileName = System.getProperty("user.dir") + "/Tanks/src/LeaderBoard/Leaderboard.txt";
	        File originalFile = new File(fileName);
			if(originalFile.exists() && !originalFile.isDirectory()) { 
			}
			Scanner in = new Scanner(originalFile);
			// store all txt in a single string
			List<String> temp1 = new ArrayList<String>();
			while(in.hasNext()) {
				temp1.add(in.next());
			}
			in.close();
			
			//places each name and score into appropriate array
			LBNameArray[0] = temp1.get(0);
			LBNameArray[1] = temp1.get(2);
			LBNameArray[2] = temp1.get(4);
			LBNameArray[3] = temp1.get(6);
			LBNameArray[4] = temp1.get(8);
			LBScoreArray[0] = temp1.get(1);
			LBScoreArray[1] = temp1.get(3);
			LBScoreArray[2] = temp1.get(5);
			LBScoreArray[3] = temp1.get(7);
			LBScoreArray[4] = temp1.get(9);
		
			// if score is better than any current highscore, add inputted name and score to array. Adjusts posistion of highscores in array accordingly
			if(TitleState.isLB == true){
				if(EndGameState.p1score > Integer.parseInt(temp1.get(9))){
					// asks user for name, if they click cancel instead  it adds Anon + tag to avoid errors
					inputName = userInput();
					if (inputName == null) {
						Random rand = new Random();
						int tag = rand.nextInt(10000) + 1;
						inputName = "Anon" + Integer.toString(tag);
					}
					String temp[] = inputName.split("\\s+");
					if(temp.length > 1){
						Random rand = new Random();
						int tag = rand.nextInt(10000) + 1;
						inputName = "Anon" + Integer.toString(tag);
					}
					if(EndGameState.p1score > Integer.parseInt(temp1.get(7))){
						if(EndGameState.p1score > Integer.parseInt(temp1.get(5))){
							if(EndGameState.p1score > Integer.parseInt(temp1.get(3))){
								if(EndGameState.p1score > Integer.parseInt(temp1.get(1))){
									LBNameArray[0] = inputName;
									LBScoreArray[0] = EndGameState.p1score + "";
									LBNameArray[1] = temp1.get(0);
									LBNameArray[2] = temp1.get(2);
									LBNameArray[3] = temp1.get(4);
									LBNameArray[4] = temp1.get(6);
									LBScoreArray[1] = temp1.get(1);
									LBScoreArray[2] = temp1.get(3);
									LBScoreArray[3] = temp1.get(5);
									LBScoreArray[4] = temp1.get(7);
							 
								} else {
									LBNameArray[1] = inputName;
									LBScoreArray[1] = EndGameState.p1score + "";
									LBNameArray[2] = temp1.get(2);
									LBNameArray[3] = temp1.get(4);
									LBNameArray[4] = temp1.get(6);	
									LBScoreArray[2] = temp1.get(3);
									LBScoreArray[3] = temp1.get(5);
									LBScoreArray[4] = temp1.get(7);
								}
							} else{
								LBNameArray[2] = inputName;
								LBScoreArray[2] = EndGameState.p1score + "";
								LBNameArray[3] = temp1.get(4);
								LBNameArray[4] = temp1.get(6);
								LBScoreArray[3] = temp1.get(5);
								LBScoreArray[4] = temp1.get(7);
							}
						} else {
							LBNameArray[3] = inputName;
							LBScoreArray[3] = EndGameState.p1score + "";
							LBNameArray[4] = temp1.get(6);
							LBScoreArray[4] = temp1.get(7);
						}
					} else {
						LBNameArray[4] = inputName;
						LBScoreArray[4] = EndGameState.p1score + "";
					}
				}
				//adds all names and scores to an output string 
				String outputString = LBNameArray[0] + " " + LBScoreArray[0] + " "  + LBNameArray[1] + " " + LBScoreArray[1] + " " +
						LBNameArray[2] + " " + LBScoreArray[2] + " " + LBNameArray[3] + " " + LBScoreArray[3]  + " " + LBNameArray[4] + " " + LBScoreArray[4];
		 
				//writes output string to leaderboards text file
				File file = new File(System.getProperty("user.dir") + "/Tanks/src/LeaderBoard/temp.txt");
				PrintWriter writer = new PrintWriter(file);
				writer.print("");
				writer.print(outputString);
				writer.flush();
				writer.close();	
				
				originalFile.delete();
				file.renameTo(originalFile);
			}	
		 } catch (IOException e) {} 
	}
	
	
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
	}

}