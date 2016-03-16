package com.tanks.main;

import java.awt.Graphics;

public abstract class State {
	private State currentState = null;
	
	public void setState(State state) {
		currentState = state;
	}
	
	public State getState() {
		return currentState;
	}

	public abstract void doDrawing(Graphics g);
	public abstract void tick();
}
