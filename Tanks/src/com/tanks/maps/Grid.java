package com.tanks.maps;

public class Grid {
	
	private boolean gridArray[][];
	
	protected final int rows = 65;
	protected final int cols = 86;
	
	public Grid() {
		gridArray = new boolean[rows][cols];
	}
	
	public void setEntity(int row, int col, boolean entity) {
		gridArray[row][col] = entity;
	}
	
	public boolean getEntity(int row, int col) {
		return gridArray[row][col];
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public int getX(int col) {
		return col*12;
	}
	
	public int getY(int row) {
		return row*12;
	}
	
	public boolean[][] getGrid() {
		return gridArray;
	}
}
