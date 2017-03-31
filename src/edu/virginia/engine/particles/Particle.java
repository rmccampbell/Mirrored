package edu.virginia.engine.particles;

import java.util.ArrayList;

import edu.virginia.engine.display.Sprite;

public class Particle extends Sprite {
	
	private int lifespan = 100;
	
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
		if (lifespan < 0)
			destroy();
		this.move(1,1);
	}

}
