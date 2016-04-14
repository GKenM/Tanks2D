/**
 * This class creates the first map using the grid based map
 * Author: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.maps;

public class Map1 extends Grid {
	public Map1() {
		// Centre blocks
		for (int i = 30; i < 34; i++) {
			for (int j = 37; j < 49; j++) {
				setEntity(i,j,true);
			}
		}
		for (int j = 41; j < 45; j++) {
			for (int i = 26; i < 38; i++) {
				setEntity(i,j,true);
			}
		}
		// Surrounding broken box
		for (int j = 20; j < 37; j++) {
			for (int i = 9; i < 14; i++) {
				setEntity(i ,j, true);
			}
			for (int i = 49; i < 54; i++) {
				setEntity(i, j, true);
			}
		}
		
		for (int j = 48; j < 65; j++) {
			for (int i = 9; i < 14; i++) {
				setEntity(i ,j, true);
			}
			for (int i = 49; i < 54; i++) {
				setEntity(i, j, true);
			}
		}
					
		for (int i = 14; i < 20; i++) {
			for (int j = 20; j < 25; j++) {
				setEntity(i, j, true);
			}
			for (int j = 60; j < 65; j++) {
				setEntity(i ,j, true);
			}
		}
		
		for (int i = 43; i < 49; i++) {
			for (int j = 20; j < 25; j++) {
				setEntity(i, j, true);
			}
			for (int j = 60; j < 65; j++) {
				setEntity(i, j, true);
			}
		}
		// 4 corner walls
		for (int i = 6; i < 9; i++) {
			for (int j = 6; j < 14; j++) {
				setEntity(i,j,true);
			}
			for (int j = 71; j < 79; j++) {
				setEntity(i,j,true);
			}
		}
		for (int i = 56; i < 59; i++) {
			for (int j = 6; j < 14; j++) {
				setEntity(i,j,true);
			}
			for (int j = 71; j < 79; j++) {
				setEntity(i,j,true);
			}
		}
	}
}