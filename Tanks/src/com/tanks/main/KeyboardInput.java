package com.tanks.main;

import java.awt.event.KeyEvent;

public class KeyboardInput {
	
	private Tank tank;
	
	public KeyboardInput(Tank tank) {
		this.tank = tank;
	}
	
	public void keyPress(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_W) {
			tank.path = tank.speed;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			tank.path = -tank.speed;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			tank.a -= 22.5;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			tank.a += 22.5;
		}
	}
	public void keyRelease(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_W) {
			tank.path = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			tank.path = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			tank.a -= 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			tank.a += 0;
		}
	}
}
