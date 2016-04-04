package com.tanks.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class game extends JFrame {
	
	private static final long serialVersionUID = 253566021152116365L;
	public static final int WIDTH = 1024, HEIGHT = 768;
	public static Board board;
	
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
