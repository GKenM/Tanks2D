/**
 * This class is the main executable for the game
 * Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class game extends JFrame {
	
	private static final long serialVersionUID = 253566021152116365L;
	public static final int WIDTH = 1024, HEIGHT = 768;
	public static final int BOT = 0, P1 = 1, P2 = 2, WALL = 3, BULLET = 4, POWERUP = 5;
	public static Board board;
	/*
	 * Purpose of this function is to setup the window and add the game to that window
	 */
	public game() {
		board = new Board();

		setTitle("Dank Tank");

		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setMaximumSize(new Dimension(WIDTH,HEIGHT));
		setMinimumSize(new Dimension(WIDTH,HEIGHT));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	    setLocationRelativeTo(null);
	    add(board);
	}

	public static void main(String[] args) {
		new game();
	}

}
