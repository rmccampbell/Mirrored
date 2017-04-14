package prototype;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;

public class TrapDoor extends Sprite{

	public TrapDoor(double x, double y, DisplayObjectContainer parent) {
		super("trapDoor", "hole.png");
		parent.addChild(this);
		setPosition(x, y);
	}

	@Override
	public void trigger(DisplayObject other) {
		super.trigger(other);
		if (other instanceof Character) {
			System.out.println(other + " fell in trapdoor");
			((Character) other).die();
		}
	}
}
