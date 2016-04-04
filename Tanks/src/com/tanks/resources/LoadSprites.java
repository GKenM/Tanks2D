package com.tanks.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class LoadSprites {
	
	private ArrayList<BufferedImage> images;
	
	public LoadSprites(){
		images = new ArrayList<BufferedImage>();
		 try {													// index position 
	            images.add(ImageIO.read(new File("Tanks/src/Images/tank1.png"))); //0
	            images.add(ImageIO.read(new File("Tanks/src/Images/tank2.png"))); //1
	            images.add(ImageIO.read(new File("Tanks/src/Images/tank3.png"))); //2
	            images.add(ImageIO.read(new File("Tanks/src/Images/tank4.png"))); //3
	            images.add(ImageIO.read(new File("Tanks/src/Images/missle1.png"))); //4
	            images.add(ImageIO.read(new File("Tanks/src/Images/missle2.png"))); //5
	            images.add(ImageIO.read(new File("Tanks/src/Images/wall1.png"))); //6
	            
	            
	        } catch (IOException e) {
	        }
	}
	
	public BufferedImage getSprite(int index) {
		return images.get(index);
	}
	
	
}
