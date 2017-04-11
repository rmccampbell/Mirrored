package prototype;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.physics.Direction;

public class Door extends AnimatedSprite {

	public Door(double x, double y, DisplayObjectContainer parent) {
		super("door", "doors.png", 4, 3);
		parent.addChild(this);
		addAnimation("door1", 0, 1);
		setAnimation("door1");
		setPosition(x, y);
	}

	@Override
	public void trigger(DisplayObject other) {
		super.trigger(other);
		if (other instanceof Player) {
			dispatchEvent(new Event("door", this));
		}
	}
}
