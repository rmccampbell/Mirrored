package edu.virginia.engine.particles;

import java.util.ArrayList;
import java.util.Iterator;

import edu.virginia.engine.display.DisplayObjectContainer;

public class ParticleSystem extends DisplayObjectContainer {

	ArrayList<Particle> particles = new ArrayList<Particle>();
	boolean isComplete = false;
	
	public ParticleSystem(String id) {
		super(id);
	}
	
	public ParticleSystem(String id, String imageFileName) {
		super(id, imageFileName);
	}
	
	public void create(int numParticles){
		for(int i = 0; i < numParticles; i++){
			Particle p = new Particle("particle", "platform.png");
			particles.add(p);
			addChild(p);
		}
	}
	
	public boolean isComplete(){
		return isComplete;
	}

	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		isComplete = true;
		for (Iterator<Particle> iterator = particles.iterator(); iterator.hasNext();) {
			Particle p = iterator.next();
			if (p.isAlive()) {
				iterator.remove();
			} else {
				isComplete = false;
			}
		}
		if (isComplete)
			destroy();
	}
	
}
