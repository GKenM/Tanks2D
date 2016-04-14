/*
 * This class opens all the images used in the game and stores them in an arraylist of which makes them easily accessible.  
 * Authors: Jakob Ettles, Ken Malavisuriya
 */

package com.tanks.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class LoadSprites {
	
	private ArrayList<BufferedImage> images;
	
	//images loaded and stored in arraylist, can be accessed by the assigned intereger number
	public LoadSprites(){
		images = new ArrayList<BufferedImage>();   // "*" created own images using Pinta Image Editor
		 try {													// index position 
	            images.add(ImageIO.read(new File("Tanks/src/Images/tank1.png"))); //0 *
	            images.add(ImageIO.read(new File("Tanks/src/Images/tank2.png"))); //1 *
	            images.add(ImageIO.read(new File("Tanks/src/Images/tank3.png"))); //2 *
	            images.add(ImageIO.read(new File("Tanks/src/Images/tank4.png"))); //3 *
	            images.add(ImageIO.read(new File("Tanks/src/Images/missle1.png"))); //4 *
	            images.add(ImageIO.read(new File("Tanks/src/Images/missle2.png"))); //5 *
	            images.add(ImageIO.read(new File("Tanks/src/Images/wall1.png"))); //6 *
	            images.add(ImageIO.read(new File("Tanks/src/Images/sidetank12.png")));//7 *
	            images.add(ImageIO.read(new File("Tanks/src/Images/sidetank21.png")));//8 *
	            images.add(ImageIO.read(new File("Tanks/src/Images/sidetank31.png")));//9 *
	            images.add(ImageIO.read(new File("Tanks/src/Images/sidetank41.png")));//10 *
	            images.add(ImageIO.read(new File("Tanks/src/Images/bubble.png")));//11
	            images.add(ImageIO.read(new File("Tanks/src/Images/fireRateDown.png")));//12
	            images.add(ImageIO.read(new File("Tanks/src/Images/fireRateUp.png")));//13
	            images.add(ImageIO.read(new File("Tanks/src/Images/SpeedDown.png")));//14
	            images.add(ImageIO.read(new File("Tanks/src/Images/SpeedUp.png")));//15
	            images.add(ImageIO.read(new File("Tanks/src/Images/powerUp.png")));//16	            
	            images.add(ImageIO.read(new File("Tanks/src/Images/panzer_tank.png")));//17
	            //https://pixabay.com/en/water-priroda-drops-rain-815271/
	            images.add(ImageIO.read(new File("Tanks/src/Images/rain.jpg")));//18
	            //http://callofduty.wikia.com/wiki/Panzer_IV
	            images.add(ImageIO.read(new File("Tanks/src/Images/tankSpeech.png")));//19 *
	            
	        } catch (IOException e) {
	        }
	}
	
	public BufferedImage getSprite(int index) {
		return images.get(index);
	}
	
	
}
