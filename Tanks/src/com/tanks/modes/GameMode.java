package com.tanks.modes;

import java.awt.Graphics;
import java.util.ArrayList;

import com.tanks.main.game;
import com.tanks.objects.Bullet;
import com.tanks.objects.GameObject;
import com.tanks.objects.ObjectInteraction;
import com.tanks.objects.Tank;
import com.tanks.resources.LoadSprites;

public abstract class GameMode {
	public static final int tankSize = 48;
	public static final double tankSpeed = 3;
	
	protected static Tank player1;
    protected static ArrayList<Bullet> p1Bullets;
    protected Bullet bullet;
    protected LoadSprites image;
    
	protected ObjectInteraction mechanics;
    
	public GameMode() {
		player1 = new Tank(0,0,tankSpeed,tankSize,tankSize,game.P1);
		mechanics = new ObjectInteraction();
		p1Bullets = player1.getBullets();
		image = new LoadSprites();
	}
	
	public abstract void doDrawing(Graphics g);
	public abstract void tick();
	public abstract void respawn();
	public abstract void reset();
	public abstract Tank getPlayer1();
	public abstract Tank getPlayer2();
	public abstract void spawnPowerup(ArrayList<GameObject> walls);
}
