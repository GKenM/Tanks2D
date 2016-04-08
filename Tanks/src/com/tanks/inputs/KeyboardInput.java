package com.tanks.inputs;

import java.awt.event.KeyEvent;

import com.tanks.main.Board;
import com.tanks.objects.Tank;
import com.tanks.states.GameState;
import com.tanks.states.MPOptionState;
import com.tanks.states.MPState;
import com.tanks.states.OptionState;
import com.tanks.states.SPOptionState;
import com.tanks.states.SPState;
import com.tanks.states.TitleState;

public class KeyboardInput {
	
	private Tank player1;
	private Tank player2;
	public boolean fire = false;
	
	public KeyboardInput() {
		this.player1 = null;
		this.player2 = null;
	}
	
	public void loadTanks() {
		if (TitleState.isTraining == true || TitleState.isArcade == true) {
			this.player1 = GameState.getMode().getPlayer1();
		} else if (TitleState.isLocalMP == true) {
			this.player1 = GameState.getMode().getPlayer1();
			this.player2 = GameState.getMode().getPlayer2();
		}
	}
	
	public void keyPress(KeyEvent e) {
		// ####################################################################################
		// Tank controls
		// ####################################################################################
		
		// Player 1 controls for single player
		if (TitleState.isTraining == true || TitleState.isArcade == true) {
			if (SPOptionState.control1 == true) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					player1.setVelocityForward(player1.getSpeed());
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					player1.setVelocityBackward(player1.getSpeed());
				}
				if (e.getKeyCode() == KeyEvent.VK_A) {
					player1.setA(player1.getA() - 22.5);
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					player1.setA(player1.getA() + 22.5);
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (player1.isFired() == false) {player1.fire();}
				}
			} else if (SPOptionState.control2 == true) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					player1.setVelocityForward(player1.getSpeed());
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					player1.setVelocityBackward(player1.getSpeed());
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					player1.setA(player1.getA() - 22.5);
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player1.setA(player1.getA() + 22.5);
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (player1.isFired() == false) {player1.fire();}
				}
			}
		}	
			
		// Player 1 and player 2 controls for local multiplayer
		else if (TitleState.isLocalMP == true) {
			// Player 1
			if (e.getKeyCode() == KeyEvent.VK_W) {
				player1.setVelocityForward(player1.getSpeed());
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				player1.setVelocityBackward(player1.getSpeed());
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				player1.setA(player1.getA() - 22.5);
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				player1.setA(player1.getA() + 22.5);
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				if (player1.isFired() == false) {player1.fire();}
			}
			// Player 2
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				player2.setVelocityForward(player2.getSpeed());
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				player2.setVelocityBackward(player2.getSpeed());
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				player2.setA(player2.getA() - 22.5);
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				player2.setA(player2.getA() + 22.5);
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (player2.isFired() == false) {player2.fire();}
			}
		}
		
		// ####################################################################################
		// Titlescreen menu controls
		// #################################################################################### 
		if(TitleState.isMenu == true){
			// Highlighting each option as you navigate them
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (TitleState.SPHighlighted == true){
					TitleState.SPHighlighted = false;
					TitleState.MPHighlighted = true;
					TitleState.OPHighlighted = false;
				} else if (TitleState.MPHighlighted == true){
					TitleState.MPHighlighted = false;
					TitleState.OPHighlighted = true;
					TitleState.SPHighlighted = false;
				} else if (TitleState.OPHighlighted == true){
					TitleState.OPHighlighted = false;
					TitleState.SPHighlighted = true;
					TitleState.MPHighlighted = false;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (TitleState.SPHighlighted == true){
					TitleState.SPHighlighted = false;
					TitleState.OPHighlighted = true;
					TitleState.MPHighlighted = false;
				} else if (TitleState.MPHighlighted == true){
					TitleState.MPHighlighted = false;
					TitleState.SPHighlighted = true;
					TitleState.OPHighlighted = false;
				} else if (TitleState.OPHighlighted == true){
					TitleState.OPHighlighted = false;
					TitleState.MPHighlighted = true;
					TitleState.SPHighlighted = false;
				}
			}
			
			// Press you press enter on a highlighted option, you change state
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				// Go into Singleplayer menu
				if (TitleState.SPHighlighted == true){
					TitleState.isSP = true;
					TitleState.isOption = false;
					TitleState.isMP = false;
					TitleState.isMenu = false;
					Board.stateChange();
				}
				// Go into Multiplayer menu
				if (TitleState.MPHighlighted == true){
					TitleState.isSP= false;
					TitleState.isOption = false;
					TitleState.isMP = true;
					TitleState.isMenu = false;
					Board.stateChange();
				}
				// Go into options menu
				if (TitleState.OPHighlighted == true){
					TitleState.isSP = false;
					TitleState.isOption = true;
					TitleState.isMP = false;
					TitleState.isMenu = false;
					Board.stateChange();
				}	
			}
		}

		// ####################################################################################
		// Singleplayer menu controls
		// #################################################################################### 
		else if(TitleState.isSP == true){
			// Highlight each option as you naviagte 
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (SPState.TMHighlighted == true){
					SPState.TMHighlighted = false;
					SPState.AMHighlighted = true;
				} else if (SPState.AMHighlighted == true){
					SPState.AMHighlighted = false;
					SPState.LBHighlighted = true;
					SPState.TMHighlighted = false;
				} else if (SPState.LBHighlighted == true){
					SPState.AMHighlighted = false;
					SPState.TMHighlighted = true;
					SPState.LBHighlighted = false;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (SPState.TMHighlighted == true){
					SPState.TMHighlighted = false;
					SPState.LBHighlighted = true;
					SPState.AMHighlighted = false;
				} else if (SPState.AMHighlighted == true){
					SPState.AMHighlighted = false;
					SPState.TMHighlighted = true;
					SPState.LBHighlighted = false;
				} else if (SPState.LBHighlighted == true){
					SPState.TMHighlighted = false;
					SPState.AMHighlighted = true;
					SPState.LBHighlighted = false;
				}
			}
			
			// When you press enter on a highlighted option, change the state to that option
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				// Go into training mode
				if (SPState.TMHighlighted == true){
					TitleState.isTraining = true;
					TitleState.isArcade = false;
					TitleState.isOption = false;
					TitleState.isSP = false;
					TitleState.prevState = 4;
					Board.stateChange();
					
					GameState.setMode();
					this.loadTanks();
					// Change the mode in the gamestate to training ##############################################
					// set the currentstate to gamestate
				}
				// go into arcade mode
				if (SPState.AMHighlighted == true){
					TitleState.isTraining = false;
					TitleState.isOption = false;
					TitleState.isArcade = true;
					TitleState.isSP = false;
					TitleState.prevState = 5;
					Board.stateChange();
					
					GameState.setMode();
					this.loadTanks();
					// change the mode in the gamestate to arcade #################################################
					// set the currentstate to gamestate
				}
				// go into leaderboards
				if (SPState.LBHighlighted == true){
					TitleState.isTraining = false;
					TitleState.isArcade = false;
					TitleState.isOption = true;
					TitleState.prevState = 2; // necessary?
					TitleState.isSP = false;
					Board.stateChange();
				}	
			}
			// If you press escape, go back to the titlescreen state
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if(TitleState.isSP == true){
					TitleState.isSP = false;
					TitleState.isMenu = true;
					Board.stateChange();
				}
			}
		}
		
		// ####################################################################################
		// Multiplayer menu controls
		// #################################################################################### 		
		else if (TitleState.isMP == true){
			// Highlight each option as you navigate
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (MPState.LMHighlighted == true){
					MPState.OPHighlighted = true;
					MPState.LMHighlighted = false;
				} else if (MPState.OPHighlighted == true){
					MPState.LMHighlighted = true;
					MPState.OPHighlighted = false;
				}	
			}	
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (MPState.LMHighlighted == true){
					MPState.OPHighlighted = true;
					MPState.LMHighlighted = false;
				} else if (MPState.OPHighlighted == true){
					MPState.LMHighlighted = true;
					MPState.OPHighlighted = false;
				}
			}
			
			// If you press enter on a highlighted option, go into that option
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				// go into local multiplayer mode
				if (MPState.LMHighlighted == true){
					TitleState.isLocalMP = true;
					TitleState.isMPOption = false;
					TitleState.isMP = false;
					TitleState.prevState = 6;
					Board.stateChange();
					
					GameState.setMode();
					this.loadTanks();
					// change the mode in the gamestate to localMP #################################################
					// set the currentstate to gamestate
				}
				// go into game options - REMOVE
				if (MPState.OPHighlighted == true){
					TitleState.isLocalMP = false;
					TitleState.isMPOption = true;
					TitleState.prevState = 3; // necessary??
					TitleState.isMP = false;
					Board.stateChange();
				}
			}
			
			// if you press escape, go back to titlescreen
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if(TitleState.isMP == true){
					TitleState.isMP = false;
					TitleState.isMenu = true;
					Board.stateChange();
				}
			}
		}

		// ####################################################################################
		// Option menu controls
		// #################################################################################### 	
		else if(TitleState.isOption == true){
			// Highlight option as you navigate
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (OptionState.SOHighlighted == true){
					OptionState.MOHighlighted = true;
					OptionState.SOHighlighted = false;
				} else if (OptionState.MOHighlighted == true){
					OptionState.SOHighlighted = true;
					OptionState.MOHighlighted = false;
				}	
			}	
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (OptionState.SOHighlighted == true){
					OptionState.MOHighlighted = true;
					OptionState.SOHighlighted = false;
				} else if (OptionState.MOHighlighted == true){
					OptionState.SOHighlighted = true;
					OptionState.MOHighlighted = false;
				}
			}
			
			// If you press enter on a highlighted option, go into that option
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (OptionState.SOHighlighted == true){
					TitleState.isSPOption = true;
					TitleState.isMPOption = false;
					TitleState.isOption = false;
					Board.stateChange();
					
				}
				if (OptionState.MOHighlighted == true){
					TitleState.isSPOption= false;
					TitleState.isMPOption = true;
					TitleState.isOption = false;
					Board.stateChange();
				}
			}
			
			// Go back to title screen when you press escape
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				TitleState.isOption = false;
				TitleState.isMenu = true;
				Board.stateChange();
			}
		}

		// ####################################################################################
		// Singleplayer option menu controls
		// #################################################################################### 
		else if(TitleState.isSPOption == true){
			// Go back to the options menu
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				TitleState.isSPOption = false;
				TitleState.isOption = true;
				Board.stateChange();
			}
			// Navigate and enable between the two control options
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				if(SPOptionState.control1 == true){
					SPOptionState.control1 = false;
					SPOptionState.control2 = true;
				}
			}	
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				if(SPOptionState.control2 == true){
					SPOptionState.control2 = false;
					SPOptionState.control1 = true;					
				}
			}
		}

		// ####################################################################################
		// Multiplayer option menu controls
		// #################################################################################### 
		else if(TitleState.isMPOption == true){
			// Go back to options menu
			if((e.getKeyCode() == KeyEvent.VK_ESCAPE) && (TitleState.prevState == 3)){
				TitleState.isMPOption = false;
				TitleState.isMP= true;
				Board.stateChange();
			} else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {	
				TitleState.isMPOption = false;
				TitleState.isOption = true;
				Board.stateChange();
			}
			// Navigate for player 1 tank selection
			if(e.getKeyCode() == KeyEvent.VK_W){
				MPOptionState.leftUp = true;
				if(MPOptionState.p1redtank == true){
					MPOptionState.p1redtank = false;
					MPOptionState.p1violettank = true;
				} else if(MPOptionState.p1violettank == true){
					MPOptionState.p1bluetank = true;
					MPOptionState.p1violettank = false;
				} else if(MPOptionState.p1bluetank == true){
					MPOptionState.p1bluetank = false;
					MPOptionState.p1yellowtank = true;
				} else if(MPOptionState.p1yellowtank == true){
					MPOptionState.p1redtank = true;
					MPOptionState.p1yellowtank = false;
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_S){
				MPOptionState.leftDown = true;
				if(MPOptionState.p1redtank == true){
					MPOptionState.p1redtank = false;
					MPOptionState.p1yellowtank = true;
				} else if(MPOptionState.p1yellowtank == true){
					MPOptionState.p1bluetank = true;
					MPOptionState.p1yellowtank = false;
				} else if(MPOptionState.p1bluetank == true){
					MPOptionState.p1bluetank = false;
					MPOptionState.p1violettank = true;
				} else if(MPOptionState.p1violettank == true){
					MPOptionState.p1redtank = true;
					MPOptionState.p1violettank = false;
				}
			}
			
			// Navigate for player 2 control selection
			if(e.getKeyCode() == KeyEvent.VK_UP){
				MPOptionState.rightUp = true;
				if(MPOptionState.p2redtank == true){
					MPOptionState.p2redtank = false;
					MPOptionState.p2violettank = true;
				} else if(MPOptionState.p2violettank == true){
					MPOptionState.p2bluetank = true;
					MPOptionState.p2violettank = false;
				} else if(MPOptionState.p2bluetank == true){
					MPOptionState.p2bluetank = false;
					MPOptionState.p2yellowtank = true;
				} else if(MPOptionState.p2yellowtank == true){
					MPOptionState.p2redtank = true;
					MPOptionState.p2yellowtank = false;
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN){
				MPOptionState.rightDown = true;
				if(MPOptionState.p2redtank == true){
					MPOptionState.p2redtank = false;
					MPOptionState.p2yellowtank = true;
				} else if(MPOptionState.p2yellowtank == true){
					MPOptionState.p2bluetank = true;
					MPOptionState.p2yellowtank = false;
				} else if(MPOptionState.p2bluetank == true){
					MPOptionState.p2bluetank = false;
					MPOptionState.p2violettank = true;
				} else if(MPOptionState.p2violettank == true){
					MPOptionState.p2redtank = true;
					MPOptionState.p2violettank = false;
				}
			}
			
		}

		// ####################################################################################
		// In-game menu controls - TO DO
		// #################################################################################### 
		
		// In training mode
		else if(TitleState.isTraining == true){	
			// Pause and resume training mode
			if(e.getKeyCode() == KeyEvent.VK_P){
				if (Board.isPause() == false) {
					Board.setPause(true);
				} else if (Board.isPause() == true) {
					Board.setPause(false);
				}
			}
			// Go to escape menu
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				TitleState.isTraining = false;
				TitleState.isGameMenu = true;
				Board.stateChange();
			}
			// Skip the game and go to end game menu
			if(e.getKeyCode() == KeyEvent.VK_PAGE_DOWN){
				// Go to end menu state
			}
			
		}
		// In arcade mode
		else if(TitleState.isArcade == true){	
			// Pause and resume arcade mode
			if(e.getKeyCode() == KeyEvent.VK_P){
				if (Board.isPause() == false) {
					Board.setPause(true);
				} else if (Board.isPause() == true) {
					Board.setPause(false);
				}
			}
			// Go to escape menu
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				TitleState.isArcade = false;
				TitleState.isGameMenu = true;
				Board.stateChange();
			}
			// Skip the game and go to end game menu
			if(e.getKeyCode() == KeyEvent.VK_PAGE_DOWN){
				// Go to end menu state
			}
		}
		// In local multiplayer mode
		else if(TitleState.isLocalMP == true){	
			// Pause and resume local multiplayer mode
			if(e.getKeyCode() == KeyEvent.VK_P){
				if (Board.isPause() == false) {
					Board.setPause(true);
				} else if (Board.isPause() == true) {
					Board.setPause(false);
				}
			}
			// Go to escape menu
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				TitleState.isLocalMP = false;
				TitleState.isGameMenu = true;
				Board.stateChange();
			}
			// Skip the game and go to end game menu
			if(e.getKeyCode() == KeyEvent.VK_PAGE_DOWN){
				// Go to end menu state
			}
		}		

		// ####################################################################################
		// Escape menu controls - PLZ FIX, ADD A OPTIONS AND MAIN MENU BUTTON
		// #################################################################################### 
		else if(TitleState.isGameMenu){
			// If you click on the game menu button, go to game menu
			if(e.getKeyCode() == KeyEvent.VK_P){
				TitleState.isGameMenu = false;
				TitleState.isMenu = true;
				GameState.reset();
				Board.stateChange();
			}	
			// If you press escape again, you go back to your game
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				if(TitleState.prevState == 4){
					TitleState.isTraining = true;
					TitleState.isGameMenu = false;
					Board.stateChange();
				}
				if(TitleState.prevState == 5){
					TitleState.isArcade = true;
					TitleState.isGameMenu = false;
					Board.stateChange();
				}
				if(TitleState.prevState == 6){
					TitleState.isLocalMP = true;
					TitleState.isGameMenu = false;
					Board.stateChange();
				}
			}
		}
		// ####################################################################################
		// End game menu controls - TO DO - have a restart button, display scores and winner 
		// #################################################################################### 
		
		// Call GameState.reset() when you restart the game or go back to main menu
	}		
	
	// Key release events for tank controls and tank select
	public void keyRelease(KeyEvent e) {
		// Player 1 controls for single player
		if (TitleState.isTraining == true || TitleState.isArcade == true) {
			if (SPOptionState.control1 == true) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					player1.setVelocityForward(0);
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					player1.setVelocityBackward(0);
				}
				if (e.getKeyCode() == KeyEvent.VK_A) {
					player1.setA(player1.getA() - 0);
				}
				if (e.getKeyCode() == KeyEvent.VK_D) {
					player1.setA(player1.getA() + 0);
				}
			} else if (SPOptionState.control2 == true) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					player1.setVelocityForward(0);
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					player1.setVelocityBackward(0);
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					player1.setA(player1.getA() - 0);
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player1.setA(player1.getA() + 0);
				}
			}
		}	
			
		// Player 1 and player 2 controls for local multiplayer
		if (TitleState.isLocalMP == true) {
			// Player 1
			if (e.getKeyCode() == KeyEvent.VK_W) {
				player1.setVelocityForward(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				player1.setVelocityBackward(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				player1.setA(player1.getA() - 0);
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				player1.setA(player1.getA() + 0);
			}
			// Player 2
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				player2.setVelocityForward(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				player2.setVelocityBackward(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				player2.setA(player2.getA() - 0);
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				player2.setA(player2.getA() + 0);
			}
		}
		
		// Key Release for multiplayer option state
		if(TitleState.isMPOption == true){
			if(e.getKeyCode() == KeyEvent.VK_UP){
				MPOptionState.rightUp = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN){
				MPOptionState.rightDown = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_W){
				MPOptionState.leftUp = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_S){
				MPOptionState.leftDown = false;
			}
		}
	}
}
