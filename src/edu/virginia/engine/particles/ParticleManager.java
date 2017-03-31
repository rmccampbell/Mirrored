package edu.virginia.engine.particles;

import java.util.ArrayList;
import java.util.Iterator;

import edu.virginia.engine.display.DisplayObjectContainer;

public class ParticleManager {

	private ArrayList<ParticleSystem> systems = new ArrayList<ParticleSystem>();
	private DisplayObjectContainer container;
	
	public ParticleManager(DisplayObjectContainer container){
		this.container = container;
	}
	
	public void add(ParticleSystem ps){
		systems.add(ps);
		container.addChild(ps);
	}
	
	public void update() {
		for (Iterator<ParticleSystem> iterator = systems.iterator(); iterator.hasNext();) {
			ParticleSystem system = iterator.next();
			if (system.isComplete())
				iterator.remove();
		}
	}
	
}
