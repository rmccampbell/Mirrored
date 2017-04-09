package particle_demo;

import java.awt.Color;
import java.util.ArrayList;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.particles.ParticleManager;
import edu.virginia.engine.particles.ParticleSystem;

public class ParticleDemo2 extends Game {

	private ParticleManager particleMan;

	public ParticleDemo2() {
		super("Particle Demo", 1200, 800);
		
		particleMan = new ParticleManager(this);

		ParticleSystem part1 = particleMan.createSystem("part1", 200, 300)
				.setRate(2)
				.setColor(Color.RED)
				.setSize(3, 4)
				.setLifespan(200, 200)
				.setVelocity(.1, .1)
				.setGravity(-.003)
				.setPositionalNoise(.1)
				.setVelocityNoise(.02);
		part1.start();

		ParticleSystem part2 = particleMan.createSystem("part2", 600, 250)
				.setRate(2)
				.setColor(Color.GREEN)
				.setSize(3, 4)
				.setLifespan(200, 200)
				.setVelocity(.75, .75)
				.setGravity(.01)
				.setAngle(250, 290);
		part2.start();

		ParticleSystem part3 = particleMan.createSystem("part3", 1000, 400)
				.setRate(2)
				.setColor(Color.BLUE)
				.setSize(5, 5)
				.setLifespan(100, 100)
				.setVelocity(.1, .1)
				.setVelocityNoise(.1)
				.setDrag(.95);
		part3.start();
		
		ParticleSystem part4 = particleMan.createSystem("part4", 200, 650)
				.setRate(.2)
				.setSprite("part_spark.png")
				.setSize(.2, .4)
				.setRotation(0, 360)
				.setRotationalVelocity(-.1, .1)
				.setLifespan(200, 200)
				.setVelocity(.4, .4)
				.setGravity(-.01)
				.setPositionalNoise(.1)
				.setVelocityNoise(.02);
		part4.start();

		particleMan.createOneOff("part5", 600, 400)
				.setColor(Color.MAGENTA)
				.setSize(3, 4)
				.setLifespan(100, 200)
				.setVelocity(0, .75)
				.setGravity(.01)
				.setAngle(0, 360)
				.setDrag(.99)
				.create(1000);
	}

	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		particleMan.update();
	}
	
	public static void main(String[] args) {
		ParticleDemo2 game = new ParticleDemo2();
		game.start();
	}
}
