package edu.virginia.engine.particles;

import java.util.ArrayList;

public class ParticleManager {

	ArrayList<Particle> particles = new ArrayList<Particle>();
	
	public void create(int numParticles){
		for(int i = 0; i < numParticles; i++){
			particles.add(new Particle("particle", "grey_particle"));
		}
	}
}
