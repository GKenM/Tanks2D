package com.tanks.main;

import javax.swing.JFrame;

public class game extends JFrame {
	
	private static final long serialVersionUID = 253566021152116365L;
	public static final int WIDTH = 1024, HEIGHT = 768;
	public static Board board;
	
	public game() {
		board = new Board();
		add(board);
		board.start();
		
		setTitle("Dank Tank");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	    setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new game();
	}

}
