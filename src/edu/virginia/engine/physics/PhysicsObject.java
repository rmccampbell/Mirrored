package edu.virginia.engine.physics;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import edu.virginia.engine.display.DisplayObject;;

public class PhysicsObject {
	
	public static final double STATIC = 0;

	private static final double MAX_VELOCITY = 10;

	private DisplayObject sprite;

	private Point2D.Double velocity = new Point2D.Double();
	private Point2D.Double accel = new Point2D.Double();
	private double mass = 1.0;
	private double bounciness = 0.0;
	private boolean grounded;

	public PhysicsObject(DisplayObject sprite) {
		this.sprite = sprite;
	}

	/**
	 * A mass of <code>PhysicsObject.STATIC</code> will make a static object
	 */
	public PhysicsObject(DisplayObject sprite, double mass, double bounciness) {
		this.sprite = sprite;
		this.mass = mass;
		this.bounciness = bounciness;
	}

	public DisplayObject getSprite() {
		return sprite;
	}
	
	public Rectangle2D getWorldBBox() {
		return sprite.getWorldBBox();
	}

	public boolean isStatic() {
		return mass == STATIC;
	}
	
	public void setStatic(boolean isStatic) {
		if (isStatic) {
			mass = STATIC;
			velocity.x = velocity.y = 0;
			accel.x = accel.y = 0;
		} else if (mass == STATIC) {
			mass = 1;
		}
	}

	public double getMass() {
		return mass;
	}
	
	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getBounciness() {
		return bounciness;
	}

	public void setBounciness(double bounciness) {
		this.bounciness = bounciness;
	}

	public Point2D getPosition() {
		return sprite.getPosition();
	}

	public Point2D getVelocity() {
		return velocity;
	}
	
	public double getXVelocity() {
		return velocity.getX();
	}
	
	public double getYVelocity() {
		return velocity.getY();
	}
	
	public void setVelocity(double vx, double vy) {
		if (!isStatic()) {
			this.velocity = new Point2D.Double(vx, vy);
		}
	}

	public void setVelocity(Point2D vel) {
		setVelocity(vel.getX(), vel.getY());
	}

	public Point2D getAcceleration() {
		return accel;
	}

	public Point2D getForce() {
		return new Point2D.Double(mass * accel.x, mass * accel.y);
	}

	public boolean collidesWith(PhysicsObject other) {
		return sprite.collidesWith(other.sprite);
	}

	public void update() {
		if (!isStatic()) {
			velocity.x += accel.x;
			velocity.y += accel.y;
			velocity.x = Math.min(velocity.x, MAX_VELOCITY);
			velocity.y = Math.min(velocity.y, MAX_VELOCITY);
			sprite.move(velocity);
			accel.x = accel.y = 0;
			grounded = false;
		}
	}

	public void addForce(double fx, double fy) {
		if (!isStatic()) {
			accel.x += fx / mass;
			accel.y += fy / mass;
		}
	}
	
	public void addForce(Point2D force) {
		addForce(force.getX(), force.getY());
	}
	
	public void accelerate(double ax, double ay) {
		if (!isStatic()) {
			accel.x += ax;
			accel.y += ay;
		}
	}

	public void accelerate(Point2D accel) {
		accelerate(accel.getX(), accel.getY());
	}
	
	public void collision(PhysicsObject other, Direction dir) {
		sprite.collision(other.sprite, dir);
	}

	public void ground() {
		grounded = true;
	}
	
	public boolean isGrounded() {
		return grounded;
	}

}
