package prototype;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.physics.CollisionEvent;
import edu.virginia.engine.physics.Direction;
import edu.virginia.engine.physics.PhysicsObject;

public class Enemy extends Sprite{

	private String type;
	private Player target;
	private double time = 0;
	private PhysicsObject physics;
	
	public Enemy(String id) {
		super(id);
	}
	
	public Enemy(String id, String fileName, String type, DisplayObjectContainer parent, Player target) {
		super(id, fileName);
		parent.addChild(this);
		physics = addPhysics();
		this.type = type;
		this.target = target;
		if(this.type=="staticX") physics.setVelocity(1, 0);
		if(this.type=="staticY") physics.setVelocity(0, 1);
	}
	
	public void setTarget(Player p){
		target = p;
	}
	
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);		
		if(type=="homing") moveTowards();
	}

	public void moveTowards(){
		int x = 0, y = 0;
		if(target.getX() > this.getX()) x = 5;
		else if(target.getX() < this.getX()) x = -5;
		if(target.getY() < this.getY()) y = -5;
		else if(target.getY() > this.getY()) y = 5;
		this.setPosition(this.getX()+x, this.getY()+y);
	}
	
	@Override
	public void collision(DisplayObject other, Direction dir, boolean isTrigger) {
		super.collision(other, dir, isTrigger);
		if (!isTrigger) {
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
}
