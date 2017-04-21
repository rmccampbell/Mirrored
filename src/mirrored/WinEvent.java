package mirrored;


import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.events.Event;

public class WinEvent extends Event {

	public static String EVENT_TYPE = "win";
	
	private DisplayObject source;
	
	public WinEvent(DisplayObject source) {
		super(EVENT_TYPE, source);
		this.source = source;
	}
	
	@Override
	public DisplayObject getSource() {
		return source;
	}
	
}
