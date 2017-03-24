package edu.virginia.engine.events;

public class Event {
	private String eventType;
	private IEventDispatcher source;

	public Event(String eventType, IEventDispatcher source) {
		this.eventType = eventType;
		this.source = source;
	}
	
	public String getType() {
		return eventType;
	}
	
	public IEventDispatcher getSource() {
		return source;
	}
	
	public void setType(String eventType) {
		this.eventType = eventType;
	}
	
	public void setSource(IEventDispatcher source) {
		this.source = source;
	}
}
