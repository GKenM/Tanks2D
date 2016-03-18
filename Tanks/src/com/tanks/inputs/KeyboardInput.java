package com.tanks.inputs;

import java.awt.event.KeyEvent;

import com.tanks.objects.Tank;

public class KeyboardInput {
	
	private Tank tank;
	public boolean fire = false;
	
	public KeyboardInput(Tank tank) {
		this.tank = tank;
	}
	
	public void keyPress(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_W) {
			tank.setPath(tank.getSpeed());
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			tank.setPath(-tank.getSpeed());
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			tank.setA(tank.getA() - 22.5);
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			tank.setA(tank.getA() + 22.5);
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			tank.fire();
		}
	}
	public void keyRelease(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_W) {
			tank.setPath(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			tank.setPath(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			tank.setA(tank.getA() - 0);
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			tank.setA(tank.getA() + 0);
		}
	}
}
