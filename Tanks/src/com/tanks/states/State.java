package com.tanks.states;

import java.awt.Graphics;

public abstract class State {
	public static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public State getState() {
		return currentState;
	}

	public abstract void doDrawing(Graphics g);
	public abstract void tick();
}
