/**
 * This class creates the third map using the grid based map
 * Author: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.maps;

public class Map3 extends Grid {
	public Map3() {
		// Centre blocks
		for (int i = 30; i < 34; i++) {
			for (int j = 41; j < 45; j++) {
				setEntity(i,j,true);
			}
		}
		for (int i = 26; i < 30; i++) {
			for (int j = 37; j < 41; j++) {
				setEntity(i,j,true);
			}
			for (int j = 45; j < 49; j++) {
				setEntity(i,j,true);
			}
		}
		for (int i = 34; i < 38; i++) {
			for (int j = 37; j < 41; j++) {
				setEntity(i,j,true);
			}
			for (int j = 45; j < 49; j++) {
				setEntity(i,j,true);
			}
		}
		
		// Top right blocks
		for (int i = 10; i < 14; i++) {
			for (int j = 61; j < 65; j++) {
				setEntity(i,j,true);
			}
		}
		for (int i = 6; i < 10; i++) {
			for (int j = 57; j < 61; j++) {
				setEntity(i,j,true);
			}
			for (int j = 65; j < 69; j++) {
				setEntity(i,j,true);
			}
		}
		for (int i = 14; i < 18; i++) {
			for (int j = 57; j < 61; j++) {
				setEntity(i,j,true);
			}
			for (int j = 65; j < 69; j++) {
				setEntity(i,j,true);
			}
		}
		
		// Top left blocks
		for (int i = 10; i < 14; i++) {
			for (int j = 21; j < 25; j++) {
				setEntity(i,j,true);
			}
		}
		for (int i = 6; i < 10; i++) {
			for (int j = 17; j < 21; j++) {
				setEntity(i,j,true);
			}
			for (int j = 25; j < 29; j++) {
				setEntity(i,j,true);
			}
		}
		for (int i = 14; i < 18; i++) {
			for (int j = 17; j < 21; j++) {
				setEntity(i,j,true);
			}
			for (int j = 25; j < 29; j++) {
				setEntity(i,j,true);
			}
		}
		
		// Bottom left blocks
		for (int i = 50; i < 54; i++) {
			for (int j = 21; j < 25; j++) {
				setEntity(i,j,true);
			}
		}
		for (int i = 46; i < 50; i++) {
			for (int j = 17; j < 21; j++) {
				setEntity(i,j,true);
			}
			for (int j = 25; j < 29; j++) {
				setEntity(i,j,true);
			}
		}
		for (int i = 54; i < 58; i++) {
			for (int j = 17; j < 21; j++) {
				setEntity(i,j,true);
			}
			for (int j = 25; j < 29; j++) {
				setEntity(i,j,true);
			}
		}
		
		// Bottom right blocks
		for (int i = 50; i < 54; i++) {
			for (int j = 61; j < 65; j++) {
				setEntity(i,j,true);
			}
		}
		for (int i = 46; i < 50; i++) {
			for (int j = 57; j < 61; j++) {
				setEntity(i,j,true);
			}
			for (int j = 65; j < 69; j++) {
				setEntity(i,j,true);
			}
		}
		for (int i = 54; i < 58; i++) {
			for (int j = 57; j < 61; j++) {
				setEntity(i,j,true);
			}
			for (int j = 65; j < 69; j++) {
				setEntity(i,j,true);
			}
		}
		
		// Left right bars
		for (int i = 28; i < 36; i++) {
			for (int j = 21; j < 25; j++) {
				setEntity(i,j,true);
			}
			for (int j = 61; j < 65; j++) {
				setEntity(i,j,true);
			}
		}
		
		// Top bottom bars
		for (int j = 39; j < 47; j++) {
			for (int i = 10; i < 14; i++) {
				setEntity(i,j,true);
			}
			for (int i = 50; i < 54; i++) {
				setEntity(i,j,true);
			}
		} 
	}
}