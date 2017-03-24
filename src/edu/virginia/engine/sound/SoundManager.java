package edu.virginia.engine.sound;

import java.io.File;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {
	
	private HashMap<String, Clip> sounds = new HashMap<>();
	
	public SoundManager() {
	}

	public void loadSound(String id, String filename) {
		try {
			Clip clip = AudioSystem.getClip();
			File file = new File("resources/" + filename);
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			clip.open(stream);
			sounds.put(id, clip);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void playSound(String id, boolean loop) {
		Clip clip = sounds.get(id);
		clip.setFramePosition(0);
		if (loop)
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		else
			clip.start();
	}
	
	public void playSound(String id) {
		playSound(id, false);
	}
	
	public void stopSound(String id) {
		sounds.get(id).stop();
	}
	
//	public void loadMusic(String id, String filename) {
//		
//	}
//	
//	public void playMusic(String id, boolean loop) {
//		
//	}
}
