package edu.virginia.engine.physics;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.events.Event;

public class CollisionEvent extends Event {
	public static String COLLISION = "collision";
	public static String TRIGGER = "trigger";
	
	private DisplayObject source, other;
	private boolean isTrigger;
	
	public CollisionEvent(DisplayObject source, DisplayObject other, boolean isTrigger) {
		super(isTrigger ? TRIGGER : COLLISION, source);
		this.source = source;
		this.other = other;
		this.isTrigger = isTrigger;
	}
	
	@Override
	public DisplayObject getSource() {
		return source;
	}
	
	public DisplayObject getOther() {
		return other;
	}
	
	public boolean isTrigger() {
		return isTrigger;
	}
}
