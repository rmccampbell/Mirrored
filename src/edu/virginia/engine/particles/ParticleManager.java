package edu.virginia.engine.particles;

import java.util.ArrayList;

import edu.virginia.engine.tweening.Tween;

public class ParticleManager {

	ArrayList<ParticleSystem> systems = new ArrayList<ParticleSystem>();
	
	public ParticleManager(){
	}
	
	public void add(ParticleSystem ps){
		systems.add(ps);
	}
	
	public void update() {
		for(int i = 0; i < systems.size(); i++){
			if (systems.get(i).isComplete())
				systems.remove(i);
		}
	}
	
}
