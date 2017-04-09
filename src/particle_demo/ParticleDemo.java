package particle_demo;

import java.awt.Color;
import java.util.ArrayList;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.particles.ParticleManager;
import edu.virginia.engine.particles.ParticleSystem;

public class ParticleDemo extends Game {

	private ParticleManager particleMan;

	public ParticleDemo() {
		super("Particle Demo", 1200, 800);
		
		particleMan = new ParticleManager(this);

		ParticleSystem part1 = new ParticleSystem("part1", 200, 400);
		particleMan.add(part1);
		part1.setRate(2);
		part1.setColor(Color.RED);
		part1.setSize(3, 4);
		part1.setLifespan(200, 200);
		part1.setVelocity(.1, .1);
		part1.setGravity(-.003);
		part1.setPositionalNoise(.1);
		part1.setVelocityNoise(.02);
		part1.start();

		ParticleSystem part2 = new ParticleSystem("part2", 600, 400);
		particleMan.add(part2);
		part2.setRate(2);
		part2.setColor(Color.GREEN);
		part2.setSize(3, 4);
		part2.setLifespan(200, 200);
		part2.setVelocity(.75, .75);
		part2.setGravity(.01);
		part2.setAngle(250, 290);
		part2.start();

		ParticleSystem part3 = new ParticleSystem("part3", 1000, 400);
		particleMan.add(part3);
		part3.setRate(2);
		part3.setColor(Color.BLUE);
		part3.setSize(5, 5);
		part3.setLifespan(100, 100);
		part3.setVelocity(.1, .1);
		part3.setVelocityNoise(.1);
		part3.setDrag(.95);
		part3.start();
	}

	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		particleMan.update();
	}
	
	public static void main(String[] args) {
		ParticleDemo game = new ParticleDemo();
		game.start();
	}
}
