package com.tanks.inputs;

import java.awt.event.KeyEvent;

import com.tanks.main.game;
import com.tanks.objects.Tank;
import com.tanks.states.TitleState;

public class KeyboardInput {
	
	private Tank tank;
	private Tank enemy;
	public boolean fire = false;
	
	public KeyboardInput(Tank tank, Tank enemy) {
		this.tank = tank;
		this.enemy = enemy;
	}
	
	public void keyPress(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_W) {
			tank.setVelocityForward(tank.getSpeed());
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			tank.setVelocityBackward(tank.getSpeed());
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
		if (e.getKeyCode() == KeyEvent.VK_T) {
			TitleState.isTraining = true;
			TitleState.isMenu = false;
			TitleState.isOption = false;
			game.board.stateChange();
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			TitleState.isTraining = false;
			TitleState.isMenu = true;
			TitleState.isOption = false;
			game.board.stateChange();
		} 
		if (e.getKeyCode() == KeyEvent.VK_O) {
			TitleState.isTraining = false;
			TitleState.isMenu = false;
			TitleState.isOption = true;
			game.board.stateChange();
		} 
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			enemy.setVelocityForward(enemy.getSpeed());
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			enemy.setVelocityBackward(enemy.getSpeed());
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			enemy.setA(enemy.getA() - 22.5);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			enemy.setA(enemy.getA() + 22.5);
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			enemy.fire();
		}
	}		
	public void keyRelease(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_W) {
			tank.setVelocityForward(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			tank.setVelocityBackward(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			tank.setA(tank.getA() - 0);
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			tank.setA(tank.getA() + 0);
		}
		
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			enemy.setVelocityForward(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			enemy.setVelocityBackward(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			enemy.setA(enemy.getA() - 0);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			enemy.setA(enemy.getA() + 0);
		}
	}
}
