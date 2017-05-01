package mirrored;

import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.physics.Direction;
import edu.virginia.engine.physics.PhysicsObject;

public class Enemy extends Character {

	private EnemyType type;
	private Player target;
	private PhysicsObject physics;
	private double speed = 0.8;
	
	public Enemy(String id, String fileName, EnemyType type, Player target, Level level) {
		super(id, fileName, 3, 4, level);
		// animation
		setScaleX(0.3);
		setScaleY(0.3);
		addAnimation("standDown", 0, 12);
		setAnimation("standDown");
		setFrameDuration(4);
		this.setBBox(0, 10, 100, 100);
//		this.walking = true;
		
		this.target = target;
		physics = getPhysics();
		setType(type);
	}
	
	public void setTarget(Player p){
		target = p;
	}
	
	public void setType(EnemyType type){
		this.type = type;
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
		case radiusHoming:
			physics.setVelocity(0,0);
			break;
		default:
			break;
		}
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
			break;
		case radiusHoming:
			double radius = Math.sqrt((target.getX()-this.getX())*(target.getX()-this.getX()) + (target.getY()-this.getY())*(target.getY()-this.getY()));
			if (radius < 100) {
				moveTowards();
			}
			break;
		default:
			break;
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
	
//	@Override
//	public void draw(Graphics g) {
//		super.draw(g);
//		Rectangle2D r = getWorldBBox();
//		g.drawRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
//	}
}
