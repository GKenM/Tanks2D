/**
 * This is the game board class which displays the game and handles game loop and state changes
 * Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JPanel;

import com.tanks.inputs.KeyboardInput;
import com.tanks.resources.LoadSprites;
import com.tanks.resources.Sound;
import com.tanks.states.State;
import com.tanks.states.TitleState;
import com.tanks.states.EndGameState;
import com.tanks.states.GameMenuState;
import com.tanks.states.GameState;
import com.tanks.states.LeaderBoard;
import com.tanks.states.MPOptionState;
import com.tanks.states.MPState;
import com.tanks.states.OptionState;
import com.tanks.states.SPOptionState;
import com.tanks.states.SPState;

public class Board extends JPanel implements Runnable{
	private static final long serialVersionUID = 7681044157732768854L;
	
	public static LoadSprites images;
	public static HashMap<String, Sound> sounds;
	
	private KeyboardInput keyIN;
	private static State currentState;
	private static TitleState title;
	private static GameState game;
	private static OptionState option;
	private static SPOptionState spOption;
	private static MPOptionState mpOption;
	private static GameMenuState menu;
	private static SPState singleplayer;
	private static MPState multiplayer;
	private static EndGameState endGame;
	private static LeaderBoard leaderB;
	
	private Thread thread;
	private boolean running = false;
	private static boolean pause = false;
	
	int	fps = 30;
	double averageFPS = 0;
	
	public Board() {
		super();
		// Load all music files and sound clips
		images = new LoadSprites();
		sounds = new HashMap<String, Sound>();
		sounds.put("bgm1", new Sound(Sound.bgm1, true));
		sounds.put("bgm2", new Sound(Sound.bgm2, true));
		sounds.put("tap", new Sound(Sound.sTap, false));
		sounds.put("bash", new Sound(Sound.sBash, false));
		sounds.put("fire", new Sound(Sound.sTankFiring, false));
		sounds.put("le", new Sound(Sound.sLExplosion, false));
		sounds.put("e", new Sound(Sound.sExplosion, false));
		sounds.put("powup", new Sound(Sound.sPowerUP, false));
		sounds.put("powdown", new Sound(Sound.sPowerDown, false));
		sounds.put("bounce", new Sound(Sound.sBounce, false));
		
		// Create the keyboard listener and set a deafault background
		KeyListener temp = new Control();
		addKeyListener(temp);
		setFocusable(true);
		setBackground(Color.black);
		setDoubleBuffered(true);
		setFocusable(true);
		start();
	}
	/*
	 * Purpose of this function is to create the main game thread and run it
	 */
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	/*
	 * Purpose of this function is to initialize all the states
	 */
	public void start() {
		title = new TitleState();
		game = new GameState();
		option = new OptionState();
		spOption = new SPOptionState();
		mpOption = new MPOptionState();
		menu = new GameMenuState();
		singleplayer = new SPState();
		multiplayer = new MPState();
		keyIN = new KeyboardInput();
		endGame = new EndGameState();
		leaderB = new LeaderBoard();
		
		currentState = title;
		sounds.get("bgm1").play();
	}
	/*
	 * Purpose of this function is to handle all the state changes and also play appropriate sounds for states
	 */
	public static void stateChange() {
		if (TitleState.isMenu == true){
			currentState = title;
			sounds.get("bgm2").stop();
			sounds.get("bgm1").play();
		}
		if (TitleState.isTraining == true || TitleState.isArcade == true || TitleState.isLocalMP == true){
			currentState = game; 	
			sounds.get("bgm1").stop();
			sounds.get("bgm2").play();
		}
		if (TitleState.isOption == true){
			currentState = option; 	
		}		
		if (TitleState.isSPOption == true){
			currentState = spOption;	
		}
		if(TitleState.isMPOption == true){
			currentState = mpOption;
		}
		if (TitleState.isGameMenu == true){
			currentState = menu; 	
		}
		if(TitleState.isSP == true){
			currentState = singleplayer;
		}
		if(TitleState.isMP == true){
			currentState = multiplayer;
		}
		if(TitleState.isEndGame == true){
			currentState = endGame;
			sounds.get("bgm2").stop();
			sounds.get("bgm1").play();
		}
		if(TitleState.isLB == true) {
			currentState = leaderB;
		}
	}
	
	/*
	 * This function contains the game loop which is used to refresh the game 30 times per second
	 */
	@Override
	public void run() {
		running = true;
		
		long startTime;
		long URDTimeMillis;
		long waitTime;
		long totalTime = 0;
		
		int frameCount = 0;
		int maxFrameCount = 30;
		
		long targetTime = 1000/fps;
		
		while (running) {
			startTime = System.nanoTime();
			// If game is not paused, refresh the game
			if (pause == false) {tick();}
			
			// Calculate the required target time to sleep to acheive 30 ticks per second
			URDTimeMillis = (System.nanoTime() - startTime)/1000000;
			waitTime = targetTime - URDTimeMillis;
			
			try {
				// Make the thread sleep for the calcualted wait time
				Thread.sleep(waitTime);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			// Display the average FPS every second
			totalTime += System.nanoTime() - startTime;
			frameCount++;
			if (frameCount == maxFrameCount) {
				averageFPS = Math.round(1000.0 / ((totalTime/frameCount) / 1000000));
				frameCount = 0;
				totalTime = 0;
			}
		}
	}
	
	public void tick() {
		// Ticks the current game state 
		if (currentState != null) {
			currentState.tick();
		}
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draws the current game state
		if (currentState != null) {
			currentState.doDrawing(g);
		}
		
		Toolkit.getDefaultToolkit().sync();

		// Uncomment the line below to test fps
		//System.out.println(fps);	
	}
	/*
	 * Purpose of this fucntion is pass in every key press and key release to the keyboardinputs class
	 */
	private class Control extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			keyIN.keyPress(e);
		}
		public void keyReleased(KeyEvent e) {
			keyIN.keyRelease(e);
		}
	}

	public static boolean isPause() {
		return pause;
	}

	public static void setPause(boolean p) {
		pause = p;
	}
	
}
