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
	
	public ParticleSystem createSystem(String id, double x, double y) {
		return add(new ParticleSystem(id, x, y, true));
	}
	
	public ParticleSystem createOneOff(String id, double x, double y) {
		return add(new ParticleSystem(id, x, y, false));
	}
	
	public ParticleSystem add(ParticleSystem ps){
		systems.add(ps);
		container.addChild(ps);
		return ps;
	}
	
	public void update() {
		for (Iterator<ParticleSystem> iterator = systems.iterator(); iterator.hasNext();) {
			ParticleSystem system = iterator.next();
			if (system.isComplete() && !system.isPermanent())
				iterator.remove();
		}
	}
	
}
