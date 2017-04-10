package prototype;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;

public class Switch extends Sprite {
	
	private boolean activated = false;

	public Switch(String id, double x, double y, DisplayObjectContainer parent) {
		super(id, "lever_off.png");
		parent.addChild(this);
		setPosition(x, y);
	}
	
	private void activate() {
		activated = true;
		setImage("lever_on.png");
		dispatchEvent(new SwitchEvent(this));
		System.out.println("Switch triggered");
	}
	
	@Override
	public void trigger(DisplayObject other) {
		super.trigger(other);
		if (!activated && other instanceof Player)
			activate();
	}
}
