package mirrored;

import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;

public class Button extends Sprite {
	
	private boolean previouslyActivated = false;
	private boolean activated = false;

	public Button(String id, double x, double y, Level level) {
		super(id, "button_off.png");
		setScaleX(0.3);
		setScaleY(0.3);
		level.addChild(this);
		setPosition(x, y);
		level.getPhysicsManager().addTrigger(this);
	}
	
	private void activate() {
		activated = true;
		setImage("button_on.png");
		dispatchEvent(new Event(Events.BUTTON_ON, this));
	}
	
	private void deactivate() {
		activated = false;
		setImage("button_off.png");
		dispatchEvent(new Event(Events.BUTTON_OFF, this));
	}
	
	@Override
	public void trigger(DisplayObject other) {
		super.trigger(other);
		if (!activated && other instanceof Player){
			activate();
		}		
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		if(activated && !previouslyActivated ) deactivate();
	}
}
