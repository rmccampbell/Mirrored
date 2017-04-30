package mirrored;

import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;

public class Button extends Sprite {
	
	private boolean prevActivated = false;
	private boolean activated = false;

	public Button(String id, double x, double y, Level level) {
		super(id, "button_off.png");
		setScaleX(0.3);
		setScaleY(0.3);
		level.addChild(this);
		setPosition(x, y);
		level.getPhysicsManager().addTrigger(this);
		setzOrder(-1);
	}
	
	private void activate() {
		activated = true;
		setImage("button_on.png");
		dispatchEvent(new Event(Events.BUTTON_ON, this));
		System.out.println("activated");
	}
	
	private void deactivate() {
		activated = false;
		setImage("button_off.png");
		dispatchEvent(new Event(Events.BUTTON_OFF, this));
		System.out.println("deactivated");
	}
	
	private void holdActive() {
		dispatchEvent(new Event(Events.BUTTON_HOLD, this));
	}
	
	@Override
	public void trigger(DisplayObject other) {
		super.trigger(other);
		if (other instanceof Player){
			activated = true;
		}		
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		if (activated && !prevActivated ) activate();
		else if (!activated && prevActivated) deactivate();
		if (activated) holdActive();
		prevActivated = activated;
		activated = false;
	}
}
