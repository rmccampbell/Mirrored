package prototype;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.physics.Direction;

public class TrapDoor extends Sprite{

	public TrapDoor(double x, double y, DisplayObjectContainer parent) {
		super("trapDoor", "hole.png");
		parent.addChild(this);
		setPosition(x, y);
	}

	@Override
	public void collision(DisplayObject other, Direction dir) {
		super.collision(other, dir);
		other.destroy();
	}
}
