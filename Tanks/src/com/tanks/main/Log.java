/**
 * This class handles the keyboard logging
 * Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.tanks.inputs.KeyboardInput;
import com.tanks.states.TitleState;

public class Log {
	Calendar cal;
	SimpleDateFormat sdf;
	String fileName;
	File file;
	/*
	 * Create a new file with an unique name
	 */
	public Log() {
		// Generate a random file name based on current date and time
		cal = Calendar.getInstance();
		sdf = new SimpleDateFormat("yyMMddHHmmss");
		fileName = "Log" + sdf.format(cal.getTime());
		try {
			// Create a new file using the randomly generated name
			file = new File(System.getProperty("user.dir") + "/Tanks/src/Logs", fileName);
			if (!file.createNewFile()) {
				System.out.println("FILE EXISTS");
			}
			
			// In the first line of the file, print the game mode and titles
			FileWriter fw = new FileWriter(file.getAbsolutePath(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			
			String gameMode = "";
			if (TitleState.isArcade) {gameMode = "Arcade Mode";}
			if (TitleState.isLocalMP) {gameMode = "Local Multiplayer";}
			if (TitleState.isTraining) {gameMode = "Training Mode";}
			
			out.println(gameMode);
			String format = "%-20s%s%n";
			out.format(format, "Time", "Keys in action");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Write a new line to file every game tick
	 */
	public void writeFile() {
		try(FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw))
		{
			// Write Current Time 
			cal = Calendar.getInstance();
			sdf = new SimpleDateFormat("HH:mm:ss");
			int temp = Calendar.getInstance().get(Calendar.MILLISECOND);
			
			// Read in each key in action at that time
			String keys = "";
			if(KeyboardInput.logW) {keys += " W ";}
			if(KeyboardInput.logS) {keys += " S ";}
			if(KeyboardInput.logA) {keys += " A ";}
			if(KeyboardInput.logD) {keys += " D ";}
			if(KeyboardInput.logSpace) {keys += " Space ";}
			if(KeyboardInput.logUp) {keys += " Up ";}
			if(KeyboardInput.logDown) {keys += " Down ";}
			if(KeyboardInput.logLeft) {keys += " Left ";}
			if(KeyboardInput.logRight) {keys += " Right ";}
			if(KeyboardInput.logEnter) {keys += " Enter ";}
			
			
			// Formating and writing new lines
			String format = "%-20s%s%n";
			out.format(format, sdf.format(cal.getTime()) + ":" + Integer.toString(temp), keys);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}