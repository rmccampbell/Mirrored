package prototype;

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
	
	protected void die() {
		System.out.println(this + " died :(");
	}
	
	@Override
	public void trigger(DisplayObject other, Direction dir) {
		super.trigger(other, dir);
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

	protected Rectangle2D getAttackBox(){
		//TODO
		switch (facing){
			case LEFT:
				break;
			case RIGHT:
				break;
			case UP:
				break;
			case DOWN:
				break;
		}

		return new Rectangle2D.Double(0, 0, 0, 0);
	}

}
