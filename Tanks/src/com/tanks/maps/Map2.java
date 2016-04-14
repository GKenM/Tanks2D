package com.tanks.maps;

public class Map2 extends Grid {
	public Map2() {
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
		
		// Top and bottom centre blocks
		for (int j = 37; j < 48; j++) {
			for (int i = 0; i < 8; i++) {
				setEntity(i,j,true);
			}
			for (int i = 57; i < 65; i++) {
				setEntity(i,j,true);
			}
		}
		
		// Three small centre blocks
		for (int j = 41; j < 44; j++) {
			for (int i = 30; i < 34; i++) {
				setEntity(i,j,true);
			}
			for (int i = 18; i < 22; i++) {
				setEntity(i,j,true);
			}
			for (int i = 42; i < 46; i++) {
				setEntity(i,j,true);
			}
		}
		
		// Left and right barriers
		for (int i = 21; i < 43; i++) {
			for (int j = 20; j < 24; j++) {
				setEntity(i,j,true);
			}
			for (int j = 61; j < 65; j++) {
				setEntity(i,j,true);
			}
		}
		for (int j = 17; j < 20; j++) {
			for (int i = 21; i < 25; i++) {
				setEntity(i,j,true);
			}
			for (int i = 39; i < 43; i++) {
				setEntity(i,j,true);
			}
		}
		for (int j = 65; j < 68; j++) {
			for (int i = 21; i < 25; i++) {
				setEntity(i,j,true);
			}
			for (int i = 39; i < 43; i++) {
				setEntity(i,j,true);
			}
		}
	}
}