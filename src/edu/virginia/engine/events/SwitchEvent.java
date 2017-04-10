package edu.virginia.engine.events;


import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.events.Event;

public class SwitchEvent extends Event{
	
	public static String SWITCH = "switch";
	public static String TRIGGER = "trigger";
	
	private DisplayObject source;
	private boolean isTrigger;
	
	public SwitchEvent(DisplayObject source, boolean isTrigger) {
		super(isTrigger ? TRIGGER : SWITCH, source);
		this.source = source;
		this.isTrigger = isTrigger;
	}
	
	@Override
	public DisplayObject getSource() {
		return source;
	}
	
	public boolean isTrigger() {
		return isTrigger;
	}
}
