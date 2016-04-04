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
import com.tanks.states.OptionState;

public class Board extends JPanel implements Runnable{

	private static final long serialVersionUID = 7681044157732768854L;
	private KeyboardInput keyIN;
	private State currentState;
	private TitleState title;
	private GameState game;
	private OptionState option;
	private GameMenuState menu;
	
	private Thread thread;
	private boolean running = false;
	
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
		menu = new GameMenuState();
		keyIN = new KeyboardInput();
		
		currentState = title;
	
	}
	
	public void stateChange() {
		if (TitleState.isMenu == true){
			currentState = title;
		}

		if (TitleState.isTraining == true){
			currentState = game; 	
		}
		
		if (TitleState.isOption == true){
			currentState = option; 	
		}		
		if (TitleState.isGameMenu == true){
			currentState = menu; 	
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
			
			tick();
			
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

        System.out.println(fps);
		
	}
		
	private class Control extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			keyIN.keyPress(e);
		}
		public void keyReleased(KeyEvent e) {
			keyIN.keyRelease(e);
		}
	}
}
