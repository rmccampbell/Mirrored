package edu.virginia.engine.physics;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import edu.virginia.engine.display.DisplayObject;

/**
 * Singleton class for managing physics
 *
 */
public class PhysicsManager {
	private static boolean fullCollisions = false;

	private Point2D.Double gravity = new Point2D.Double();
	private ArrayList<PhysicsObject> objects = new ArrayList<>();
	private ArrayList<DisplayObject> triggers = new ArrayList<>();

	public void update() {
		for (Iterator<PhysicsObject> iterator = objects.iterator(); iterator.hasNext();) {
			PhysicsObject object = iterator.next();
			if (object.getSprite().isAlive()) {
				object.accelerate(gravity);
				object.update();
			} else {
				iterator.remove();
			}
		}
		for (int i = 0; i < objects.size(); i++) {
			PhysicsObject object = objects.get(i);
			for (int j = i + 1; j < objects.size(); j++) {
				PhysicsObject object2 = objects.get(j);
				if (object.collidesWith(object2)) {
					handleCollision(object, object2);
				}
			}
			for (int j = 0; j < triggers.size(); j++) {
				DisplayObject trigger = triggers.get(j);
				if (object.getSprite().collidesWith(trigger)) {
					object.getSprite().trigger(trigger);
					trigger.trigger(object.getSprite());
				}
			}
		}
	}

	private static void handleCollision(PhysicsObject object1, PhysicsObject object2) {
		Point2D disp = resolveCollision(object1, object2);
		if (fullCollisions) {
			if (object1.isStatic()) {
				staticCollision(object2, object1, disp.getY() != 0);
			} else if (object2.isStatic()) {
				staticCollision(object1, object2, disp.getY() != 0);
			} else {
				dynamicCollision(object1, object2, disp.getY() != 0);
			}
		}
		Direction dir1, dir2;
		if (disp.getX() < 0) {
			dir1 = Direction.RIGHT;
			dir2 = Direction.LEFT;
		} else if (disp.getX() > 0) {
			dir1 = Direction.LEFT;
			dir2 = Direction.RIGHT;
		} else if (disp.getY() < 0) {
			dir1 = Direction.DOWN;
			dir2 = Direction.UP;
		} else {
			dir1 = Direction.UP;
			dir2 = Direction.DOWN;
		}
		object1.collision(object2, dir1);
		object2.collision(object1, dir2);
	}

	public ArrayList<PhysicsObject> getCollisions(Rectangle2D r){
		ArrayList<PhysicsObject> collided = new ArrayList<PhysicsObject>();

		for (PhysicsObject o : objects) {
			if( o.getWorldBBox().intersects(r)){
				collided.add(o);
			}
		}

		return collided;
	}

	private static void staticCollision(PhysicsObject object1, PhysicsObject object2, boolean isVertical) {
		// object1 is dynamic, object2 is static
		double CR = (object1.getBounciness() + object2.getBounciness()) / 2;
		Point2D v = object1.getVelocity();
		if (isVertical)
			object1.setVelocity(v.getX(), -CR * v.getY());
		else
			object1.setVelocity(-CR * v.getX(), v.getY());
	}

	private static void dynamicCollision(PhysicsObject object1, PhysicsObject object2, boolean isVertical) {
		// Algorithm from https://en.wikipedia.org/wiki/Inelastic_collision
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

	private static Point2D resolveCollision(PhysicsObject object1, PhysicsObject object2) {
		Point2D disp = movRectOut(object1.getWorldBBox(), object2.getWorldBBox());
		if (object2.isStatic()) {
			object1.getSprite().move(disp);
		} else if (object1.isStatic()) {
			object2.getSprite().move(-disp.getX(), -disp.getY());
		} else {
			object1.getSprite().move(disp.getX() / 2, disp.getY() / 2);
			object2.getSprite().move(-disp.getX() / 2, -disp.getY() / 2);
		}
		return disp;
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
