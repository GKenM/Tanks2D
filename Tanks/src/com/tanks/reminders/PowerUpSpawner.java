package com.tanks.reminders;

import java.util.ArrayList;

import com.tanks.objects.GameObject;
import com.tanks.states.GameState;

public class PowerUpSpawner {
	private int tick;
	private ArrayList<GameObject> walls;
	
	public PowerUpSpawner(ArrayList<GameObject> walls) {
		this.walls = walls;
		tick = 0-3*30;
	}
	
	public void tick() {
		if (tick == 30*30) {
			GameState.getMode().spawnPowerup(walls);
			tick = 0;
		}
		tick++;
	}
}