package mirrored;


import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.events.Event;

public class SwitchEvent extends Event{
	
	public static String EVENT_TYPE = "switch";
	
	private DisplayObject source;
	
	public SwitchEvent(DisplayObject source) {
		super(EVENT_TYPE, source);
		this.source = source;
	}
	
	@Override
	public DisplayObject getSource() {
		return source;
	}
	
}
