package edu.virginia.engine.particles;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import edu.virginia.engine.display.DisplayObjectContainer;

public class ParticleSystem extends DisplayObjectContainer {

	private static Random rand = new Random();
	private ArrayList<Particle> particles = new ArrayList<Particle>();

	private boolean isComplete = false;
	private boolean running = false;
	private boolean permanent = true;
	private double rate = 0;
	private double partCounter;

	private double minVel = 1, maxVel = 1;
	private double minAngle = 0, maxAngle = 360;
	private double minRotVel = 0, maxRotVel = 0;
	private double minSize = 1, maxSize = 1;
	private int minLifespan = 100, maxLifespan = 100;
	private double drag = 1;
	private double posNoise = 0;
	private double velNoise = 0;
	private Point2D.Double gravity = new Point2D.Double();
	private Color color = Color.BLACK;
	private BufferedImage sprite;
	
	public ParticleSystem(String id, double x, double y) {
		super(id);
		setPosition(x, y);
	}

	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		if (running) {
			partCounter += rate;
			if (partCounter >= 1) {
				int num = (int) partCounter;
				partCounter -= num;
				create(num);
			}
		}
		isComplete = true;
		for (Iterator<Particle> iterator = particles.iterator(); iterator.hasNext();) {
			Particle p = iterator.next();
			if (p.isAlive()) {
				isComplete = false;
			} else {
				iterator.remove();
			}
		}
		if (isComplete && !permanent)
			destroy();
	}
	
	public void createParticle() {
		Particle p = new Particle();
		p.setImage(sprite);
		p.setColor(color);
		p.setScale(randDouble(minSize, maxSize));
		p.setGravity(gravity);
		p.setDrag(drag);
		p.setPositionalNoise(posNoise);
		p.setVelocityNoise(velNoise);
		double vel = randDouble(minVel, maxVel);
		double angle = Math.toRadians(randDouble(minAngle, maxAngle));
		double vx = Math.cos(angle) * vel;
		double vy = Math.sin(angle) * vel;
		p.setVelocity(new Point2D.Double(vx, vy));
		p.setRotationalVelocity(randDouble(minRotVel, maxRotVel));
		p.setLifespan(randInt(minLifespan, maxLifespan));
		particles.add(p);
		addChild(p);
	}

	public void create(int numParticles) {
		for(int i = 0; i < numParticles; i++){
			createParticle();
		}
	}
	
	public boolean isComplete(){
		return isComplete;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void start() {
		running = true;
	}

	public void stop() {
		running = false;
	}
	
	public void setPermanent(boolean permanent) {
		this.permanent = permanent;
	}
	
	public boolean isPermanent() {
		return permanent;
	}

	public double getRate() {
		return rate;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	public void setSprite(String fileName) {
		this.sprite = readImage(fileName);
	}
	
	public void setSize(double minSize, double maxSize) {
		this.minSize = minSize;
		this.maxSize = maxSize;
	}
	
	public void setAngle(double minAngle, double maxAngle) {
		this.minAngle = minAngle;
		this.maxAngle = maxAngle;
	}

	public void setVelocity(double minVel, double maxVel) {
		this.minVel = minVel;
		this.maxVel = maxVel;
	}

	public void setRotationalVelocity(double minRotVel, double maxRotVel) {
		this.minRotVel = minRotVel;
		this.maxRotVel = maxRotVel;
	}
	
	public void setLifespan(int minLifespan, int maxLifespan) {
		this.minLifespan = minLifespan;
		this.maxLifespan = maxLifespan;
	}
	
	public void setGravity(double gravity) {
		this.gravity = new Point2D.Double(0, gravity);
	}
	
	public void setGravity(double gravityX, double gravityY) {
		this.gravity = new Point2D.Double(gravityX, gravityY);
	}
	
	public void setDrag(double drag) {
		this.drag = drag;
	}
	
	public void setPositionalNoise(double noise) {
		this.posNoise = noise;
	}
	
	public void setVelocityNoise(double velNoise) {
		this.velNoise = velNoise;
	}
	
	private double randDouble(double min, double max) {
		return rand.nextDouble() * (max - min) + min;
	}

	private int randInt(int min, int max) {
		return rand.nextInt(max - min + 1) + min;
	}
}
