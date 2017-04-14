package mirrored;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.physics.Direction;

public class Character extends AnimatedSprite {
	
	boolean onGround = true;
	private int health;
	private Direction facing;

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

	protected Rectangle2D getAttackBox(double attackRadius){
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

}
