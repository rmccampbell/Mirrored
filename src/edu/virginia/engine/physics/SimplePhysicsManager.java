package edu.virginia.engine.physics;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;

/**
 * Singleton class for managing physics
 *
 */
public class SimplePhysicsManager {
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
		object1.collision(object2);
		object2.collision(object1);
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
