package prototype;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;

public class TrapDoor extends Sprite{

	public TrapDoor(double x, double y, DisplayObjectContainer parent) {
		super("trapDoor", "hole.jpg");
		parent.addChild(this);
		setPosition(x, y);
	}

}
