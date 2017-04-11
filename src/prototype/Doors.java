package prototype;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;

public class Doors extends Sprite{
	
	private boolean activated = false;

	public Doors(String id, double x, double y, DisplayObjectContainer parent) {
		super(id, "door.png");
		parent.addChild(this);
		setPosition(x, y);
	}
	
	private void activate() {
		activated = true;
		dispatchEvent(new WinEvent(this));
		System.out.println("door opened");
	}
	
	@Override
	public void trigger(DisplayObject other) {
		super.trigger(other);
		if (!activated && other instanceof Player)
			activate();
	}
}
