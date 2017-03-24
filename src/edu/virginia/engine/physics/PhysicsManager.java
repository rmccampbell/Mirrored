package edu.virginia.engine.physics;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;

/**
 * Singleton class for managing physics
 *
 */
public class PhysicsManager {
	private Point2D.Double gravity = new Point2D.Double();
	private ArrayList<PhysicsObject> objects = new ArrayList<>();
	private ArrayList<DisplayObject> triggers = new ArrayList<>();

	public void update() {
		for (PhysicsObject object : objects) {
			object.accelerate(gravity);
			object.update();
		}
		for (int i = 0; i < objects.size(); i++) {
			PhysicsObject object = objects.get(i);
			for (int j = i + 1; j < objects.size(); j++) {
				PhysicsObject object2 = objects.get(j);
				if (object.collidesWith(object2)) {
					handleCollision(object, object2);
				}
			}
			for (DisplayObject trigger : triggers) {
				if (object.getSprite().collidesWith(trigger)) {
					trigger.collision(object.getSprite(), true);
					object.getSprite().collision(trigger, true);
				}
			}
		}
	}

	private static void handleCollision(PhysicsObject object1, PhysicsObject object2) {
		if (object1.isStatic()) {
			staticCollision(object2, object1);
		} else if (object2.isStatic()) {
			staticCollision(object1, object2);
		} else {
			dynamicCollision(object1, object2);
		}
		object1.collision(object2);
		object2.collision(object1);
	}

	private static void staticCollision(PhysicsObject object1, PhysicsObject object2) {
		// object1 is dynamic, object2 is static
		boolean isVertical = resolveCollision(object1, object2);
		double CR = (object1.getBounciness() + object2.getBounciness()) / 2;
		Point2D v = object1.getVelocity();
		if (isVertical)
			object1.setVelocity(v.getX(), -CR * v.getY());
		else
			object1.setVelocity(-CR * v.getX(), v.getY());
	}

	private static void dynamicCollision(PhysicsObject object1, PhysicsObject object2) {
		// Algorithm from https://en.wikipedia.org/wiki/Inelastic_collision
		boolean isVertical = resolveCollision(object1, object2);
		double CR = (object1.getBounciness() + object2.getBounciness()) / 2;
		double m1 = object1.getMass();
		double m2 = object2.getMass();
		Point2D v1 = object1.getVelocity();
		Point2D v2 = object2.getVelocity();
		double vi1 = isVertical ? v1.getY() : v1.getX();
		double vi2 = isVertical ? v2.getY() : v2.getX();
		double vf1 = (CR * m2 * (vi2 - vi1) + m1 * vi1 + m2 * vi2) / (m1 + m2);
		double vf2 = (CR * m1 * (vi1 - vi2) + m1 * vi1 + m2 * vi2) / (m1 + m2);
		if (isVertical) {
			object1.setVelocity(v1.getX(), vf1);
			object2.setVelocity(v2.getX(), vf2);
		} else {
			object1.setVelocity(vf1, v1.getY());
			object2.setVelocity(vf2, v2.getY());
		}
	}

	private static boolean resolveCollision(PhysicsObject object1, PhysicsObject object2) {
		Point2D disp = movRectOut(object1.getWorldBBox(), object2.getWorldBBox());
		object1.getSprite().move(disp);
		if (disp.getY() < 0) {
			object1.ground();
		} else if (disp.getY() > 0 && !object2.isStatic()) {
			object2.ground();
		}
		return disp.getY() != 0;
	}

	private static Point2D movRectOut(Rectangle2D r1, Rectangle2D r2) {
		double du = r1.getMaxY() - r2.getMinY();
		double dd = r2.getMaxY() - r1.getMinY();
		double dl = r1.getMaxX() - r2.getMinX();
		double dr = r2.getMaxX() - r1.getMinX();
		double dy = du <= dd ? -du : dd;
		double dx = dl <= dr ? -dl : dr;
		if (Math.abs(dy) <= Math.abs(dx))
			return new Point2D.Double(0, dy);
		else
			return new Point2D.Double(dx, 0);
	}

	public Point2D getGravity() {
		return gravity;
	}
	
	public void setGravity(double gx, double gy) {
		this.gravity = new Point2D.Double(gx, gy);
	}

	public void setGravity(Point2D gravity) {
		setGravity(gravity.getX(), gravity.getY());
	}

	public void setGravity(double gy) {
		setGravity(0, gy);
	}
	
	public void addObject(PhysicsObject object) {
		objects.add(object);
	}
	
	public void addObject(DisplayObject object) {
		if (object.hasPhysics())
			objects.add(object.getPhysics());
	}
	
	public void addTrigger(DisplayObject object) {
		triggers.add(object);
	}
}
