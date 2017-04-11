package prototype;


import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.events.Event;

public class GameOverEvent extends Event{

	public static String EVENT_TYPE = "gameover";
	
	private DisplayObject source;
	
	public GameOverEvent(DisplayObject source) {
		super(EVENT_TYPE, source);
		this.source = source;
	}
	
	@Override
	public DisplayObject getSource() {
		return source;
	}
	
}
