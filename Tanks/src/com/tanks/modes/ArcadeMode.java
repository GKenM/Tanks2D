/**
 * This class handles the arcade game mode
 * Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.modes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

import com.tanks.main.Board;
import com.tanks.main.game;
import com.tanks.objects.Bullet;
import com.tanks.objects.Enemy;
import com.tanks.objects.GameObject;
import com.tanks.objects.PowerUp;
import com.tanks.objects.Tank;
import com.tanks.objects.Walls;
import com.tanks.reminders.DifficultyTimer;
import com.tanks.reminders.PowerUpDestroyer;

public class ArcadeMode extends GameMode {

	private Walls walls;
	private Enemy bot;
	private ArrayList<Enemy> bots;
    private static ArrayList<PowerUp> powerUps; 
    private PowerUp powerUp;
	private DifficultyTimer difficulty;
	private int scoreMP = 0;
	
	private static int botSpeed = 2;
	private static int botSize = 48;
	private static int botRof = 2000;
	
	public ArcadeMode() {
		walls = new Walls();
		bot = new Enemy(0,0,botSpeed,botSize,botSize,botRof,game.BOT, player1, walls);
		bots = new ArrayList<Enemy>();
		powerUps = new ArrayList<PowerUp>();
		difficulty = new DifficultyTimer();
		
		this.reset();
	}
	/*
	 * Purpose of this function is to handle all the drawing of arcade mode
	 */
	@Override
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;	
		
		// Player Tank
		AffineTransform oldTransform = g2d.getTransform();
		g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(player1.getA()), player1.getX(), player1.getY()));
	    if (player1.getVis()){g2d.drawImage(Board.images.getSprite(0), (int) player1.getX() -24 , (int) player1.getY() -24, null);}
	    g2d.setTransform(oldTransform);
	    
	    // Player bullets
	    for (int i = 0; i < p1Bullets.size(); i++) {
	    	bullet = p1Bullets.get(i);
	    	g2d.drawImage(Board.images.getSprite(4) , (int) bullet.getX() - 4 , (int) bullet.getY() -4, null);
	    }
	    
	    // Draw the bots in play and thier bullets in play
	    for (int i = 0; i < bots.size(); i++) {
	    	bot = bots.get(i);
			g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(bot.getA()), bot.getX(), bot.getY()));
		    if (bot.getVis()){g2d.drawImage(Board.images.getSprite(1), (int) bot.getX() -24 , (int) bot.getY() -24, null);}
		    g2d.setTransform(oldTransform);
		    
		    ArrayList<Bullet> botBullets = bot.getBullets();
		    for (int j = 0; j < botBullets.size(); j++) {
		    	g2d.drawImage(Board.images.getSprite(5) ,(int) botBullets.get(j).getX() - 4 , (int) botBullets.get(j).getY() -4, null);
		    }
	    }
	    // Set up font for game HUD
	    g2d.setColor(Color.white); 
		Font font = new Font("Serif", Font.PLAIN, 20);
		g2d.setFont(font);
	    
	    // Player Scores
	    g2d.drawString("Player1: " + player1.getScore(), 10, 25);
	    
	    // Player 1 power up indicator
	    if (player1.getPU() == 1) {
	    	g2d.drawImage(Board.images.getSprite(11), 10, 40, null);
	    } else if (player1.getPU() == 2) {
	    	g2d.drawImage(Board.images.getSprite(15), 10, 40, null);
	    } else if (player1.getPU() == 3) {
	    	g2d.drawImage(Board.images.getSprite(14), 10, 40, null);
	    } else if (player1.getPU() == 4) {
	    	g2d.drawImage(Board.images.getSprite(13), 10, 40, null);
	    } else if (player1.getPU() == 5) {
	    	g2d.drawImage(Board.images.getSprite(12), 10, 40, null);
	    }
	    
	    // Power Ups
	    for (int i = 0; i < powerUps.size(); i++) {
	    	powerUp = powerUps.get(i);
	    	g2d.drawImage(Board.images.getSprite(16), (int) powerUp.getX()-12, (int) powerUp.getY()-12, null);
	    }
	    
	    // Score Multiplier display
	    if (scoreMP > 2) {
	    	 g2d.drawString("x" + scoreMP, 125, 25);
	    }
	}
	/*
	 * Purpose of this function is to handle all the game mechanics in arcade mode
	 */
	@Override
	public void tick() {
		// Run the difficulty timer and set the appropriate difficulty
		difficulty.tick();
		this.setDifficulty();
		
		// Rotate the player 1 hit box
		Shape p1Bounds = player1.getBounds();
		AffineTransform af = new AffineTransform();
		af.rotate(player1.getA() * Math.PI/180, player1.getX(), player1.getY());
		p1Bounds = af.createTransformedShape(p1Bounds);
		
		// Loop through every bot in play
		for (int i = 0; i < bots.size(); i++) {
			bot = bots.get(i);
			
			// Update each bots movement
			bot.update(player1, p1Bounds,bots);
			
			// Rotate each bots hitboxes
			Shape botBounds = bot.getBounds();
			AffineTransform bf = new AffineTransform();
			bf.rotate(bot.getA() * Math.PI/180, bot.getX(), bot.getY());
			botBounds = bf.createTransformedShape(botBounds);
			
			// Loop through each bot's bullets in play, update movement, check for interactions with walls
		    ArrayList<Bullet> botBullets = bot.getBullets();
			mechanics.bulletVsWalls(botBullets);
		    for (int j = 0; j < botBullets.size(); j++) {
		    	botBullets.get(j).move();
		    	
				if (botBullets.get(j).getVis() == false) {
					botBullets.remove(j);
				}
		    }
		    // Object interaction between each bot and player
		    mechanics.tankvsTank(player1, bot, p1Bounds, botBounds);
		}
		
		// Object Interactions for player 1 with walls, bullets, bots, and powerups
		mechanics.p1VsWalls(player1);
		mechanics.bulletVsWalls(p1Bullets);
		mechanics.bulletVsBot(player1, bots, p1Bullets, p1Bounds, scoreMP);
		mechanics.tankvsPowerUp(player1, powerUps, p1Bounds);
		
		
		// Player and bullet movement
		player1.setFireLock(false);
		player1.moveForward();
		player1.moveBackward();
		
		// Loop through player's bullets and update each one
		for (int i = 0; i < p1Bullets.size(); i++) {
			bullet = p1Bullets.get(i);
			bullet.move();
			
			if (bullet.getVis() == false) {
				p1Bullets.remove(i);
			}
		}
	}
	/*
	 * Purpose of this function is to handle respawning of player 1 and bots
	 */
	@Override
	public void respawn() {
		player1.respawn(player1.getWidth(), 100, 192, 384, 0);
		p1Bullets.clear();
		difficulty.reset(); 
		bots.clear();
	}

	public void reset() {
		this.respawn();
		
		// Reset player scores
		player1.setScore(0);
		player1.setKills(0);
		player1.setDeaths(0);
		
		// Reset PowerUps
		player1.setPU(0);
		player1.setBubble(false);
		player1.setSpeedMultiplier(1);
		player1.setRof(2000);
		
		// Delete power ups
		powerUps.clear();
	}

	@Override
	public Tank getPlayer1() {
		return player1;
	}

	@Override
	public Tank getPlayer2() {
		return null;
	}
	/*
	 * Purpose of this function is to spawn powerups in arcade mode
	 */
	@Override
	public void spawnPowerup(ArrayList<GameObject> walls) {
		// Randomly generate the number of powerups to spawn
		Random rand = new Random();
		int num = rand.nextInt(3) + 1;
		
		// Loop through the number of powerups
		for (int i = 0; i < num; i++) {
			powerUp = new PowerUp(0,0,0,24,24,game.POWERUP);
			powerUp.setPosition(player1, player1, walls, powerUps);
			
			// Randomly generate the type of powerup
			int puID = rand.nextInt(5) + 1;
			powerUp.setPuID(puID);
			
			powerUps.add(powerUp);
		}
		new PowerUpDestroyer(10,powerUps);
	}
	/*
	 * Purpose of this function is to handle the difficulty stages, according to how long player lasts without dieing
	 */
	public void setDifficulty() {
		// Add a new bot each stage and increase the score multiplier
		if (difficulty.getStage() == 1 && bots.size() == 0) {
			bots.add(new Enemy(0,0,botSpeed,botSize,botSize,botRof,game.BOT,player1,walls));
			bots.add(new Enemy(0,0,botSpeed,botSize,botSize,botRof,game.BOT,player1,walls));
			bots.add(new Enemy(0,0,botSpeed,botSize,botSize,botRof,game.BOT,player1,walls));
			scoreMP = 2;
		} else if (difficulty.getStage() == 2 && bots.size() == 3) {
			bots.add(new Enemy(0,0,botSpeed,botSize,botSize,botRof,game.BOT,player1,walls));
			scoreMP = 4;
		} else if (difficulty.getStage() == 3 && bots.size() == 4) {
			bots.add(new Enemy(0,0,botSpeed,botSize,botSize,botRof,game.BOT,player1,walls));
			scoreMP = 6;
		} else if (difficulty.getStage() == 4 && bots.size() == 5) {
			bots.add(new Enemy(0,0,botSpeed,botSize,botSize,botRof,game.BOT,player1,walls));
			scoreMP = 8;
		} else if (difficulty.getStage() == 5 && bots.size() == 6) {
			bots.add(new Enemy(0,0,botSpeed,botSize,botSize,botRof,game.BOT,player1,walls));
			scoreMP = 10;
		} else if (difficulty.getStage() == 6 && bots.size() == 7) {
			bots.add(new Enemy(0,0,botSpeed,botSize,botSize,botRof,game.BOT,player1,walls));
			scoreMP = 12;
		}
	}
	
	public void setWalls(Walls w) {
		this.walls = w;
	}

}