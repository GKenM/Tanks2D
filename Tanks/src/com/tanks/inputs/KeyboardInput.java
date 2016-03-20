package com.tanks.inputs;

import java.awt.event.KeyEvent;

import com.tanks.main.Board;
import com.tanks.main.game;
import com.tanks.objects.Tank;
import com.tanks.states.OptionState;
import com.tanks.states.TitleState;

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
		if (e.getKeyCode() == KeyEvent.VK_T) {
			TitleState.isTraining = true;
			TitleState.isMenu = false;
			TitleState.isOption = false;
			game.board.start();
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			TitleState.isTraining = false;
			TitleState.isMenu = true;
			TitleState.isOption = false;
			game.board.start();
		} 
		if (e.getKeyCode() == KeyEvent.VK_O) {
			TitleState.isTraining = false;
			TitleState.isMenu = false;
			TitleState.isOption = true;
			game.board.start();
		} 
		/* if (TitleState.isOption == true){
			if(e.getKeyCode() == KeyEvent.VK_1){
				OptionState.control1 = true;
				OptionState.control2 = false;
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
			if(e.getKeyCode() == KeyEvent.VK_2){
				OptionState.control1 = false;
				OptionState.control2 = true;
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					tank.setPath(tank.getSpeed());
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					tank.setPath(-tank.getSpeed());
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					tank.setA(tank.getA() - 22.5);
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					tank.setA(tank.getA() + 22.5);
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					tank.fire();
					
				}
			}
		}
		*/
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
