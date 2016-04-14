/**
 * This class will place the wall objects based on a grid based map
 * Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.objects;

import java.util.ArrayList;
import java.util.Random;

import com.tanks.main.game;
import com.tanks.maps.Grid;
import com.tanks.maps.Map1;
import com.tanks.maps.Map2;
import com.tanks.maps.Map3;

public class Walls {
	
	private GameObject wall;
	private Grid map;
	private Map1 map1;
	private Map2 map2;
	private Map3 map3;
	private ArrayList<GameObject> walls;
	/*
	 * Purpose of this constructor is to randomly select a grid map and place wall objects accordingly
	 */
	public Walls() {
		wall = new GameObject(0,0,0,0,0,game.WALL);
		map = new Grid();
		map1 = new Map1();
		map2 = new Map2();
		map3 = new Map3();
		walls = new ArrayList<GameObject>();
		
		Random rand = new Random();
		
		int mapNo = rand.nextInt(3) + 1;
		
		if (mapNo == 1) {
			map = map1;
		} else if (mapNo == 2) {
			map = map2;
		} else {
			map = map3;
		}
				
	    for (int i = 0; i < map.getRows(); i++) {
	    	for (int j = 0; j < map.getCols(); j++) {
	    		if (map.getEntity(i,j)) {
	    			wall = new GameObject(map.getX(j), map.getY(i), 0, 12, 12,game.WALL);
	    			walls.add(wall);
	    		}
	    	}
	    }
	}
	/*
	 * Return an array list of all the wall objects
	 */
	public ArrayList<GameObject> getWalls() {
		return walls;
	}
}