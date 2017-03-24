package edu.virginia.engine.events;

public interface IEventDispatcher {
	void addEventListener(IEventListener listener, String eventType);
	void removeEventListener(IEventListener listener, String eventType);
	void dispatchEvent(Event event);
	boolean hasEventListener(IEventListener listener, String eventType);
}
