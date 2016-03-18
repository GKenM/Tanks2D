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
import com.tanks.states.gameState;

public class Board extends JPanel implements ActionListener{

	private static final long serialVersionUID = 7681044157732768854L;
	private Timer timer;
	private Tank tank;
	private KeyboardInput keyIN;
	private State currentState;
	
	private final int DELAY = 1000/30;

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
		tank = new Tank(400,300,8, 50, 50);
		keyIN = new KeyboardInput(tank);
		timer = new Timer(DELAY,this);
		timer.start();
		
		currentState = new gameState(tank);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (currentState != null) {
			currentState.doDrawing(g);
		}

		Toolkit.getDefaultToolkit().sync();
		
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
