package edu.virginia.engine.display;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimatedSprite extends Sprite {

	private static final int FRAME_DURATION = 8;
	
	private static class Anim {
		public Anim(String name, int start, int length) {
			this.name = name;
			this.start = start;
			this.length = length;
		}
		public String name;
		public int start;
		public int length;
	}

	private List<BufferedImage> frames;
	private Map<String, Anim> animMap;
	private Anim anim;
	private int frameNum = 0;
	private boolean playing = true;
	private int ticks = 0;
	private int frameDuration = FRAME_DURATION;

	public AnimatedSprite(String id) {
		super(id);
		animMap = new HashMap<String, Anim>();
		frames = new ArrayList<BufferedImage>();
	}

	public AnimatedSprite(String id, String fileName, int rows, int cols) {
		this(id);
		setSpriteSheet(fileName, rows, cols);
	}
	
	public void setSpriteSheet(BufferedImage spriteSheet, int rows, int cols) {
		frames = new ArrayList<BufferedImage>();
		int width = spriteSheet.getWidth() / cols;
		int height = spriteSheet.getHeight() / rows;
		for (int r=0; r < rows; r++) {
			for (int c=0; c < cols; c++) {
				frames.add(spriteSheet.getSubimage(c*width, r*height, width, height));
			}
		}
		setPivotPoint(width / 2, height / 2);
		setBBox(0, 0, width, height);
	}
	
	public void setSpriteSheet(String fileName, int rows, int cols) {
		BufferedImage spriteSheet = readImage(fileName);
		setSpriteSheet(spriteSheet, rows, cols);
	}
	
	public int getFrameNum() {
		return frameNum;
	}
	
	public void setFrameNum(int frameNum) {
		this.frameNum = Math.min(frameNum, anim != null ? anim.length : 0);
		updateImage();
	}
	
	private void nextFrame() {
		frameNum = (frameNum + 1) % (anim != null ? anim.length : 1);
		updateImage();
	}
	
	private BufferedImage getFrame() {
		return anim != null && frames.size() > 0 ?
				frames.get(frameNum + anim.start) : null;
	}
	
	private void updateImage() {
		setImage(getFrame());
	}
	
	public void addAnimation(String name, int start, int length) {
		animMap.put(name, new Anim(name, start, length));
	}
	
	public String getAnimation() {
		return anim != null ? anim.name : null;
	}
	
	public void setAnimation(String name, boolean reset) {
		if (reset || anim == null || !anim.name.equals(name)) {
			anim = animMap.get(name);
			frameNum = 0;
			updateImage();
		}
	}
	
	public void setAnimation(String name) {
		setAnimation(name, false);
	}

	public int getFrameDuration() {
		return frameDuration;
	}
	
	public void setFrameDuration(int frameDuration) {
		this.frameDuration = frameDuration;
	}
	
	public void play() {
		playing = true;
	}
	
	public void pause() {
		playing = false;
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		if (playing) {
			ticks++;
			if (ticks >= frameDuration) {
				nextFrame();
				ticks = 0;
			}
		}
	}
}
