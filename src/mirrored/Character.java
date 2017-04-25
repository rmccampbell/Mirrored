package mirrored;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.physics.Direction;
import edu.virginia.engine.physics.PhysicsObject;

public class Character extends AnimatedSprite {
	
	private boolean onGround = true;
	protected int health;
	protected Direction facing = Direction.DOWN;
	protected boolean walking = false;
	protected double attackRadius = 20;

	public Character(String id) {
		super(id);
	}

	public Character(String id, String fileName, int rows, int cols) {
		super(id, fileName, rows, cols);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		if (!onGround)
			fall();
		onGround = false;
		switch (facing) {
		case LEFT:
			setAnimation(walking ? "walkLeft" : "standLeft");
			break;
		case RIGHT:
			setAnimation(walking ? "walkRight" : "standRight");
			break;
		case UP:
			setAnimation(walking ? "walkUp" : "standUp");
			break;
		case DOWN:
			setAnimation(walking ? "walkDown" : "standDown");
			break;
		}
	}
	
	private void fall() {
		die();
	}
	
	public void die() {
		System.out.println(this + " died :(");
		destroy();
	}
	
	@Override
	public void trigger(DisplayObject other) {
		super.trigger(other);
		if (other instanceof Ground) {
			onGround = true;
		}
	}
	
	public void setHealth(int h){
		this.health = h;
	}
	
	public int getHealth(){
		return health;
	}

	public double getAttackRadius() {
		return attackRadius;
	}

	public void setAttackRadius(double attackRadius) {
		this.attackRadius = attackRadius;
	}

	protected Rectangle2D getAttackBox(){
		Rectangle2D attack = new Rectangle2D.Double();

		switch (facing){
			case LEFT:
				attack = new Rectangle2D.Double(this.getX() - attackRadius, this.getY() - attackRadius, attackRadius, 2*attackRadius );
				break;
			case RIGHT:
				attack = new Rectangle2D.Double(this.getX(), this.getY() - attackRadius, attackRadius, 2 * attackRadius );
				break;
			case UP:
				attack = new Rectangle2D.Double(this.getX() - attackRadius, this.getY() - attackRadius, 2 * attackRadius, attackRadius );
				break;
			case DOWN:
				attack = new Rectangle2D.Double(this.getX() - attackRadius, this.getY(), 2 * attackRadius, attackRadius );
				break;
		}

		return attack;
	}

	public void attack(){
		ArrayList<PhysicsObject> collided = Main.getInstance().getPhysicsManager().getCollisions(this.getAttackBox());

		for (PhysicsObject c : collided) {
			if(c.getSprite() instanceof Character && c.getSprite() != this){
				Character character = (Character) c.getSprite();
				character.die();
			}
		}
	}

}
