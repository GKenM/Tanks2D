package com.tanks.maps;

public class MapTraining extends Grid {
	public MapTraining() {
		for (int j = 20; j < 65; j++) {
			for (int i = 9; i < 14; i++) {
				setEntity(i ,j, true);
			}
			for (int i = 49; i < 54; i++) {
				setEntity(i, j, true);
			}
		}
			
		for (int i = 14; i < 24; i++) {
			for (int j = 20; j < 25; j++) {
				setEntity(i, j, true);
			}
			for (int j = 60; j < 65; j++) {
				setEntity(i ,j, true);
			}
		}
		
		for (int i = 39; i < 49; i++) {
			for (int j = 20; j < 25; j++) {
				setEntity(i, j, true);
			}
			for (int j = 60; j < 65; j++) {
				setEntity(i, j, true);
			}
		}
	}
}
