package com.tanks.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import com.tanks.inputs.KeyboardInput;
import com.tanks.states.State;
import com.tanks.states.TitleState;
import com.tanks.states.GameMenuState;
import com.tanks.states.GameState;
import com.tanks.states.MPOptionState;
import com.tanks.states.MPState;
import com.tanks.states.OptionState;
import com.tanks.states.SPOptionState;
import com.tanks.states.SPState;

public class Board extends JPanel implements Runnable{

	private static final long serialVersionUID = 7681044157732768854L;
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
	
	private Thread thread;
	private boolean running = false;
	private static boolean pause = false;
	
	int	fps = 30;
	double averageFPS = 0;
	
	public Board() {
		super();
		KeyListener temp = new Control();
		addKeyListener(temp);
		setFocusable(true);
		setBackground(Color.black);
		setDoubleBuffered(true);
		setFocusable(true);
		start();
	}
	
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
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
		
		currentState = title;
	
	}
	
	public static void stateChange() {
		if (TitleState.isMenu == true){
			currentState = title;
		}
		if (TitleState.isTraining == true){
			currentState = game; 	
		}
		if (TitleState.isLocalMP == true){
			currentState = game; 	
		}
		if (TitleState.isArcade == true){
			currentState = game; 	
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
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		running = true;
		
		// game loop		
		long startTime;
		long URDTimeMillis;
		long waitTime;
		long totalTime = 0;
		
		int frameCount = 0;
		int maxFrameCount = 30;
		
		long targetTime = 1000/fps;
		
		while (running) {
				// add pause
			startTime = System.nanoTime();
			
			if (pause == false) {tick();}
			
			URDTimeMillis = (System.nanoTime() - startTime)/1000000;
			waitTime = targetTime - URDTimeMillis;
			
			try {
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
		if (currentState != null) {
			currentState.tick();
		}
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (currentState != null) {
			currentState.doDrawing(g);
		}
		
		Toolkit.getDefaultToolkit().sync();

       // System.out.println(fps);
		
	}
		
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
