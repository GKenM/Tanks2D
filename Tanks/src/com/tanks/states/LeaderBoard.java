package com.tanks.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class LeaderBoard extends State {

	public static String LBScoreArray[] = {"0","0","0","0","0"};
	public static String LBNameArray[] = {"Heart","Of","The","Cards","[]]]]]"};
	public static String data = ""; 
	public static String inputName = "JAKOB";


	@Override
	public void doDrawing(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
			Font font = new Font("Serif", Font.PLAIN, 50);
			Font font4 = new Font("Serif", Font.PLAIN, 24);
			Font font5 = new Font("Serif", Font.PLAIN, 36);
			
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
	
	private static String userInput() {	
		return JOptionPane.showInputDialog("Type in name:");
	}
			
	public static void readLBFile(){
		try {	 
			String fileName = System.getProperty("user.dir") + "/Tanks/src/LeaderBoard/Leaderboard.txt";
	        File originalFile = new File(fileName);
			if(originalFile.exists() && !originalFile.isDirectory()) { 
			}
			Scanner in = new Scanner(originalFile);
		
			while(in.hasNextLine()){
				String line = in.nextLine();
				data = data + line + " ";
			}
			in.close();
			
			String[] words = data.split("\\s+");
			for (int i = 0; i < words.length; i++) {
				words[i] = words[i].replaceAll("[^\\w]", "");
			}

			LBNameArray[0] = words[0];
			LBNameArray[1] = words[2];
			LBNameArray[2] = words[4];
			LBNameArray[3] = words[6];
			LBNameArray[4] = words[8];
			LBScoreArray[0] = words[1];
			LBScoreArray[1] = words[3];
			LBScoreArray[2] = words[5];
			LBScoreArray[3] = words[7];
			LBScoreArray[4] = words[9];
		 
			if(TitleState.isLB == true){
				if(EndGameState.p1score > Integer.parseInt(words[9])){
					// call the function here
					inputName = userInput();
					if (inputName == null) {
						Random rand = new Random();
						int tag = rand.nextInt(10000) + 1;
						inputName = "Anon" + Integer.toString(tag);
					}
					if(EndGameState.p1score > Integer.parseInt(words[7])){
						if(EndGameState.p1score > Integer.parseInt(words[5])){
							if(EndGameState.p1score > Integer.parseInt(words[3])){
								if(EndGameState.p1score > Integer.parseInt(words[1])){
									LBNameArray[0] = inputName;
									LBScoreArray[0] = EndGameState.p1score + "";
									LBNameArray[1] = words[0];
									LBNameArray[2] = words[2];
									LBNameArray[3] = words[4];
									LBNameArray[4] = words[6];
									LBScoreArray[1] = words[1];
									LBScoreArray[2] = words[3];
									LBScoreArray[3] = words[5];
									LBScoreArray[4] = words[7];
							 
								} else {
									LBNameArray[1] = inputName;
									LBScoreArray[1] = EndGameState.p1score + "";
									LBNameArray[2] = words[2];
									LBNameArray[3] = words[4];
									LBNameArray[4] = words[6];	
									LBScoreArray[2] = words[3];
									LBScoreArray[3] = words[5];
									LBScoreArray[4] = words[7];
								}
							} else{
								LBNameArray[2] = inputName;
								LBScoreArray[2] = EndGameState.p1score + "";
								LBNameArray[3] = words[4];
								LBNameArray[4] = words[6];
								LBScoreArray[3] = words[5];
								LBScoreArray[4] = words[7];
							}
						} else {
							LBNameArray[3] = inputName;
							LBScoreArray[3] = EndGameState.p1score + "";
							LBNameArray[4] = words[6];
							LBScoreArray[4] = words[7];
						}
					} else {
						LBNameArray[4] = inputName;
						LBScoreArray[4] = EndGameState.p1score + "";
					}
				}
	 
				String outputString = LBNameArray[0] + " " + LBScoreArray[0] + " "  + LBNameArray[1] + " " + LBScoreArray[1] + " " +
						LBNameArray[2] + " " + LBScoreArray[2] + " " + LBNameArray[3] + " " + LBScoreArray[3]  + " " + LBNameArray[4] + " " + LBScoreArray[4];
		 
		 
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