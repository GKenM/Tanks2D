package com.tanks.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.tanks.inputs.KeyboardInput;
import com.tanks.objects.Tank;
import com.tanks.states.State;
import com.tanks.states.TitleState;
import com.tanks.states.GameState;
import com.tanks.states.OptionState;

public class Board extends JPanel implements ActionListener{

	private static final long serialVersionUID = 7681044157732768854L;
	private Timer timer;
	private Tank tank;
	private Tank enemy;
	private KeyboardInput keyIN;
	private State currentState;
	private TitleState title;
	private GameState game;
	private OptionState option;
	
	private final int DELAY = 1000/30;
	
	private final int tankSize = 48;
	private final int tankSpeed = 3;
	
	private long lastTime = 0;
	
	public Board() {
		KeyListener temp = new Control();
		addKeyListener(temp);
		setFocusable(true);
		setBackground(Color.blue);
		setDoubleBuffered(true);
		setFocusable(true);
		start();
	}
	
	
	public void start() {
		tank = new Tank(24,24,tankSpeed,tankSize,tankSize);
		enemy = new Tank(24,24+48,tankSpeed,tankSize,tankSize);
		//enemy.setA(180); //
		keyIN = new KeyboardInput(tank, enemy);
		timer = new Timer(DELAY,this);
		timer.start();
		
		title = new TitleState();
		game = new GameState(tank, enemy);
		option = new OptionState();
		
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
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (currentState != null) {
			currentState.doDrawing(g);
		}
		
		Toolkit.getDefaultToolkit().sync();

		// Display the FPS on the console
        long now = System.nanoTime();
		long timePassed = now - lastTime;
        lastTime = now;
        double fps = Math.round(((double) 1000000000 / timePassed));
        System.out.println(fps);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (currentState != null) {
			currentState.tick();
		}
		repaint();
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
