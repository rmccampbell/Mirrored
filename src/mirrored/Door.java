package mirrored;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.events.Event;

public class Door extends AnimatedSprite {

	public Door(double x, double y, Level level) {
		super("door", "doors.png", 4, 3);
		level.addChild(this);
		setBBox(0, 0, 55, 60);
		addAnimation("door1", 0, 1);
		setAnimation("door1");
		setPosition(x, y);
		setzOrder(-1);
		level.getPhysicsManager().addTrigger(this);
	}

	@Override
	public void trigger(DisplayObject other) {
		super.trigger(other);
		if (other instanceof Player) {
			dispatchEvent(new Event(Events.DOOR, this));
		}
	}
}
