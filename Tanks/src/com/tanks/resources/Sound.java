/**
 * This class handle all the sound operations
 * Authors: Jakob Ettles, Ken Malavisuriya
 */
package com.tanks.resources;


import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	public static String sTankFiring = "/Tanks/src/Sounds/Tank_Firing.wav";
	// http://soundbible.com/1326-Tank-Firing.html
	public static String bgm1 = "/Tanks/src/Sounds/bensound-theduel.wav";
	//http://www.bensound.com
	public static String bgm2 = "/Tanks/src/Sounds/bensound-epic.wav";
	//http://www.bensound.com
	public static String sExplosion = "/Tanks/src/Sounds/explosion.wav";
	//http://www.freesound.org/people/LiamG_SFX/sounds/322508/
	public static String sTap = "/Tanks/src/Sounds/tap.wav";
	//http://www.freesound.org/people/MrOwn1/sounds/110313/
	public static String sPowerUP = "/Tanks/src/Sounds/PowerUp.wav";
	//http://www.freesound.org/people/RandomationPictures/sounds/138491/
	public static String sPowerDown = "/Tanks/src/Sounds/powerDown.wav";
	//http://www.freesound.org/people/RandomationPictures/sounds/138490/
	public static String sLExplosion = "/Tanks/src/Sounds/largeExplosion.wav";
	//http://www.freesound.org/people/Cyberios/sounds/145788/
	public static String sBash = "/Tanks/src/Sounds/bash.wav";
	//http://www.freesound.org/people/j1987/sounds/106125/
	public static String sBounce = "/Tanks/src/Sounds/Ball_Bounce.wav";
	//http://soundbible.com/1626-Ball-Bounce.html
	
	private Clip clip;
	private boolean loop = false;
	
	/*
	 * Purpose of this constructor is to load the sound clip using the file url and have it ready to play
	 */
	public Sound(String s, boolean loop) {
		this.loop = loop;
    	String absUrl = "File://" + System.getProperty("user.dir") + s; 
    	try {
    		URL url1 = new URL(absUrl); 
		    clip = AudioSystem.getClip(null);
		    AudioInputStream inputStream = AudioSystem.getAudioInputStream(url1);
		    clip.open(inputStream);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	public void play() {
		if (clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
		// If its background music, loop it continuously
		if (loop) {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
		
	public void stop() {
		if (clip.isRunning()) {
			clip.stop();
		}
	}
	
	public void close() {
		stop();
		clip.close();
	}
}
