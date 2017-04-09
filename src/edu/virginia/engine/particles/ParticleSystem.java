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
	private double minRot = 0, maxRot = 0;
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

	public ParticleSystem(String id, double x, double y, boolean permanent) {
		super(id);
		setPosition(x, y);
		setPermanent(permanent);
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
		if (sprite != null)
			p.setPivotPoint(p.getUnscaledWidth() / 2, p.getUnscaledHeight() / 2);
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
		p.setVelocity(vx, vy);
		p.setRotation(randDouble(minRot, maxRot));
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
	
	private double randDouble(double min, double max) {
		return rand.nextDouble() * (max - min) + min;
	}

	private int randInt(int min, int max) {
		return rand.nextInt(max - min + 1) + min;
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
	
	public boolean isPermanent() {
		return permanent;
	}
	
	public ParticleSystem setPermanent(boolean permanent) {
		this.permanent = permanent;
		return this;
	}

	public double getRate() {
		return rate;
	}
	
	public ParticleSystem setRate(double rate) {
		this.rate = rate;
		return this;
	}

	public Color getColor() {
		return color;
	}
	
	public ParticleSystem setColor(Color color) {
		this.color = color;
		return this;
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}
	
	public ParticleSystem setSprite(BufferedImage sprite) {
		this.sprite = sprite;
		return this;
	}
	
	public ParticleSystem setSprite(String fileName) {
		this.sprite = readImage(fileName);
		return this;
	}
	
	public double getMinSize() {
		return minSize;
	}
	
	public double getMaxSize() {
		return maxSize;
	}
	
	public ParticleSystem setSize(double minSize, double maxSize) {
		this.minSize = minSize;
		this.maxSize = maxSize;
		return this;
	}
	
	public double getMinAngle() {
		return minAngle;
	}
	
	public double getMaxAngle() {
		return maxAngle;
	}
	
	public ParticleSystem setAngle(double minAngle, double maxAngle) {
		this.minAngle = minAngle;
		this.maxAngle = maxAngle;
		return this;
	}
	
	public double getMinRotation() {
		return minRot;
	}
	
	public double getMaxRotation() {
		return maxRot;
	}
	
	public ParticleSystem setRotation(double minRot, double maxRot) {
		this.minRot = minRot;
		this.maxRot = maxRot;
		return this;
	}
	
	public double getMinVelocity() {
		return minVel;
	}
	
	public double getMaxVelocity() {
		return maxVel;
	}
	
	public ParticleSystem setVelocity(double minVel, double maxVel) {
		this.minVel = minVel;
		this.maxVel = maxVel;
		return this;
	}
	
	public double getMinRotationalVelocity() {
		return minRotVel;
	}
	
	public double getMaxRotationalVelocity() {
		return maxRotVel;
	}

	public ParticleSystem setRotationalVelocity(double minRotVel, double maxRotVel) {
		this.minRotVel = minRotVel;
		this.maxRotVel = maxRotVel;
		return this;
	}
	
	public int getMinLifespan() {
		return minLifespan;
	}
	
	public int getMaxLifespan() {
		return maxLifespan;
	}
	
	public ParticleSystem setLifespan(int minLifespan, int maxLifespan) {
		this.minLifespan = minLifespan;
		this.maxLifespan = maxLifespan;
		return this;
	}
	
	public Point2D getGravity() {
		return gravity;
	}
	
	public ParticleSystem setGravity(double gravity) {
		this.gravity = new Point2D.Double(0, gravity);
		return this;
	}
	
	public ParticleSystem setGravity(double gravityX, double gravityY) {
		this.gravity = new Point2D.Double(gravityX, gravityY);
		return this;
	}
	
	public double getDrag() {
		return drag;
	}
	
	public ParticleSystem setDrag(double drag) {
		this.drag = drag;
		return this;
	}
	
	public double getPositionalNoise() {
		return posNoise;
	}
	
	public ParticleSystem setPositionalNoise(double noise) {
		this.posNoise = noise;
		return this;
	}
	
	public double getVelocityNoise() {
		return velNoise;
	}
	
	public ParticleSystem setVelocityNoise(double velNoise) {
		this.velNoise = velNoise;
		return this;
	}
}
