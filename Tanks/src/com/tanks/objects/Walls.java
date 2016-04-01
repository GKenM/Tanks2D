package com.tanks.objects;

import java.util.ArrayList;

import com.tanks.maps.MapTraining;

public class Walls {
	
	private GameObject wall;
	private MapTraining map;
	private ArrayList<GameObject> walls;
	
	public Walls() {
		wall = new GameObject(0,0,0,0,0);
		map = new MapTraining();
		walls = new ArrayList<GameObject>();
				
	    for (int i = 0; i < map.getRows(); i++) {
	    	for (int j = 0; j < map.getCols(); j++) {
	    		if (map.getEntity(i,j)) {
	    			wall = new GameObject(map.getX(j), map.getY(i), 0, 12, 12);
	    			walls.add(wall);
	    		}
	    	}
	    }
	    
	    wall = new GameObject(1024/2, 768/2, 0, 12, 12);
	    walls.add(wall);
	}
	public ArrayList<GameObject> getWalls() {
		return walls;
	}
}
