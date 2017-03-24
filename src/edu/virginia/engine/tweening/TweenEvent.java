package edu.virginia.engine.tweening;

import edu.virginia.engine.events.Event;

public class TweenEvent extends Event {
	
	public static final String TWEEN_COMPLETE = "tween_complete";

	private Tween tween;

	public TweenEvent(String eventType, Tween tween) {
		super(eventType, tween);
		this.tween = tween;
	}
	
	public Tween getTween() {
		return tween;
	}

}
