package mirrored;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;

public class Button extends Sprite {
	
	private boolean activated = false;

	public Button(String id, double x, double y, DisplayObjectContainer parent) {
		super(id, "button_off.png");
		setScaleX(0.3);
		setScaleY(0.3);
		parent.addChild(this);
		setPosition(x, y);
		Main.getInstance().getPhysicsManager().addTrigger(this);
	}
	
	private void activate() {
		activated = true;
		setImage("button_on.png");
		dispatchEvent(new Event(Events.BUTTON_ON, this));
		System.out.println("Button on");
	}
	
	private void deactivate() {
		activated = false;
		setImage("button_off.png");
		dispatchEvent(new Event(Events.BUTTON_OFF, this));
		System.out.println("Button off");
	}
	
	@Override
	public void trigger(DisplayObject other) {
		super.trigger(other);
		if (!activated && other instanceof Player)
			activate();
	}
}
