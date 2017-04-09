package edu.virginia.engine.particles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import edu.virginia.engine.display.Sprite;

public class Particle extends Sprite {
	
	private static Random rand = new Random();
	
	private int lifespan = 100;
	private Point2D.Double velocity = new Point2D.Double();
	private Point2D.Double gravity = new Point2D.Double();
	private double rotVel = 0;
	private double drag = 1;
	private double posNoise = 0;
	private double velNoise = 0;
	private Color color = Color.BLACK;
	
	public Particle() {
		super("");
	}

	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		lifespan -= 1;
		if (lifespan < 0)
			destroy();
		move(velocity);
		if (posNoise != 0) {
			move((rand.nextDouble() - .5) * 2 * posNoise, (rand.nextDouble() - .5) * 2 * posNoise);
		}
		velocity.x *= drag;
		velocity.y *= drag;
		velocity.x += gravity.x;
		velocity.y += gravity.y;
		if (velNoise != 0) {
			velocity.x += (rand.nextDouble() - .5) * 2 * velNoise;
			velocity.y += (rand.nextDouble() - .5) * 2 * velNoise;
		}
		rotate(rotVel);
	}
	
	@Override
	protected void drawInner(Graphics2D g) {
		super.drawInner(g);
		if (getDisplayImage() == null) {
			g.setColor(color);
			g.fill(new Ellipse2D.Double(getX() - .5, getY() - .5, 1, 1));
		}
	}

	public int getLifespan() {
		return lifespan;
	}
	
	public void setLifespan(int lifespan){
		this.lifespan = lifespan;
	}
	
	public Point2D getVelocity() {
		return velocity;
	}

	public void setVelocity(Point2D velocity) {
		this.velocity.x = velocity.getX();
		this.velocity.y = velocity.getY();
	}
	
	public Point2D getGravity() {
		return gravity;
	}
	
	public void setGravity(Point2D gravity) {
		this.gravity.x = gravity.getX();
		this.gravity.y = gravity.getY();
	}
	
	public double getRotationalVelocity() {
		return rotVel;
	}
	
	public void setRotationalVelocity(double rotVel) {
		this.rotVel = rotVel;
	}
	
	public double getDrag() {
		return drag;
	}
	
	public void setDrag(double drag) {
		this.drag = drag;
	}
	
	public void setPositionalNoise(double noise) {
		this.posNoise = noise;
	}
	
	public void setVelocityNoise(double noise) {
		this.velNoise = noise;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
