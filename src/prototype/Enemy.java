package prototype;

import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.physics.Direction;
import edu.virginia.engine.physics.PhysicsObject;

public class Enemy extends Character {

	private String type;
	private Player target;
	private double time = 0;
	private PhysicsObject physics;
	private double speed = 1;
	
	public Enemy(String id) {
		super(id);
	}
	
	public Enemy(String id, String fileName, String type, DisplayObjectContainer parent, Player target) {
		super(id, fileName, 1, 1);
		parent.addChild(this);
		addAnimation("stand", 0, 1);
		setAnimation("stand");
		physics = addPhysics();
		this.type = type;
		this.target = target;
		if(this.type.equals("staticX")) physics.setVelocity(speed, 0);
		if(this.type.equals("staticY")) physics.setVelocity(0, speed);
		if(this.type.equals("homing")) physics.setVelocity(0, 0);
	}
	
	public void setTarget(Player p){
		target = p;
	}
	
	@Override
	protected void die() {
		this.destroy();
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);		
		if(type.equals("homing")) moveTowards();
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
			target.setHealth(target.getHealth()-10);
		}
	}
}
