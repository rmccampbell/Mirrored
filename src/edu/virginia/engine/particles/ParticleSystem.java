package edu.virginia.engine.particles;

import java.awt.Graphics;
import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;

public class ParticleSystem extends DisplayObjectContainer{

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
			particles.add(new Particle("particle", "platform.png"));
		}
	}
	
	public boolean isComplete(){
		for(Particle p: particles){
			if(p.isDead()){
				isComplete = true;
			}else{
				isComplete = false; 
				break;
			}
		}
		return isComplete;
	}

	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		for(Particle p: particles){
			p.update(pressedKeys);
		}
	}
	
	@Override
	public void draw(Graphics g){
		for(Particle p: particles){
			p.draw(g);
		}
	}
	
	
}
