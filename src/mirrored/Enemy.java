package mirrored;

import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.physics.Direction;
import edu.virginia.engine.physics.PhysicsObject;

public class Enemy extends Character {

	private EnemyType type;
	private Player target;
	private PhysicsObject physics;
	private double speed = 1;
	
	public Enemy(String id) {
		super(id);
	}
	
	public Enemy(String id, String fileName, EnemyType type, DisplayObjectContainer parent, Player target) {
		super(id, fileName, 3, 4);
		parent.addChild(this);
		
		// animation
		setScaleX(0.3);
		setScaleY(0.3);
		addAnimation("stand", 0, 12);
		setAnimation("stand");
		this.walking = true;
		
		physics = addPhysics();
		Main.getInstance().getPhysicsManager().addObject(this);
		this.type = type;
		this.target = target;
		switch (type) {
		case staticX:
			physics.setVelocity(speed, 0);
			break;
		case staticY:
			physics.setVelocity(0, speed);
			break;
		case homing:
			physics.setVelocity(0, 0);
			break;
		}
	}
	
	public void setTarget(Player p){
		target = p;
	}
	
	@Override
	public void die() {
		super.die();
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		switch (type) {
		case homing:
			moveTowards();
		}
	}

	public void moveTowards(){
		double targetX = target.getX();
		double targetY = target.getY();
		double thisX = this.getX();
		double thisY = this.getY();
		double xDistance = targetX - thisX;
		double yDistance = targetY - thisY;
		double h = Math.sqrt(xDistance*xDistance + yDistance*yDistance);
		physics.setVelocity(speed*xDistance/h, speed*yDistance/h);
	}
	
	@Override
	public void collision(DisplayObject other, Direction dir) {
		super.collision(other, dir);
		if(dir==Direction.LEFT || dir==Direction.RIGHT){
			physics.setVelocity(physics.getXVelocity()*-1, physics.getYVelocity());
		}
		if(dir==Direction.UP || dir==Direction.DOWN){
			physics.setVelocity(physics.getXVelocity(), physics.getYVelocity()*-1);
		}
		if(other==target){
			target.die();
		}
	}
}
