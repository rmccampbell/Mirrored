package edu.virginia.engine.particles;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import edu.virginia.engine.display.Sprite;

public class Particle extends Sprite{
	
	private int lifespan = 100;
	private static double SPEED = 4;
	
	public Particle(String id){
		super(id);
	}

	public Particle(String id, String imageFileName) {
		super(id, imageFileName);
	}
	
	public Particle(String id, String imageFileName, int l) {
		super(id, imageFileName);
		lifespan = l;
	}
	
	public Particle(String id, int lifespan){
		super(id);
		this.lifespan = lifespan;
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		lifespan -= 1;
		this.move(1,1);
	}
	
	public boolean isDead(){
		if(lifespan < 0){
			return true;
		} else{
			return false;
		}
	}
	
}
