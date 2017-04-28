package mirrored;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.DisplayObject;

public class TrapDoor extends AnimatedSprite {

	public TrapDoor(double x, double y, Level level) {
		super("trapDoor", "trap_door.png", 4, 1);
		addAnimation("closed", 0, 1);
		addAnimation("opening", 0, 4);
		addAnimation("open", 3, 1);
		setAnimation("opening");
		setFrameDuration(4);
		level.addChild(this);
		level.getPhysicsManager().addTrigger(this);
		setPosition(x, y);
		setBBox(0,0, 40, 30);
		setzOrder(-1);
	}
	
	@Override
	protected void animationDone() {
		if (getAnimation().equals("opening"))
			setAnimation("open");
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
