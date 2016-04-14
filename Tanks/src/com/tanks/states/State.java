/**
 *	This state is the parent class to all other states
 *  Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.states;

import java.awt.Graphics;

// currentState is the active state
public abstract class State {
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}

	// all states adopt drawing and tick which runs operations 30 times a second.
	public void doDrawing(Graphics g) {
		currentState.doDrawing(g);
	}
	public abstract void tick();
}
