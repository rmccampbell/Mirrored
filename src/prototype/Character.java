package prototype;

import java.util.ArrayList;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.physics.Direction;

public class Character extends AnimatedSprite {
	
	boolean onGround = true;

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

}
