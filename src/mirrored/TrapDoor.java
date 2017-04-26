package mirrored;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Sprite;

public class TrapDoor extends Sprite{

	public TrapDoor(double x, double y, Level level) {
		super("trapDoor", "hole.png");
		level.addChild(this);
		setPosition(x, y);
		setBBox(0,0, 40, 20);
		setzOrder(-1);
		level.getPhysicsManager().addTrigger(this);
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
