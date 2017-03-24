package edu.virginia.engine.events;

import java.util.ArrayList;
import java.util.HashMap;

public class EventDispatcher implements IEventDispatcher {
	private HashMap<String, ArrayList<IEventListener>> eventListeners = new HashMap<>();

	@Override
	public void addEventListener(IEventListener listener, String eventType) {
		ArrayList<IEventListener> listeners = eventListeners.get(eventType);
		if (listeners == null) {
			listeners = new ArrayList<>();
			eventListeners.put(eventType, listeners);
		}
		listeners.add(listener);
	}

	@Override
	public void removeEventListener(IEventListener listener, String eventType) {
		if (eventListeners.containsKey(eventType))
			eventListeners.get(eventType).remove(listener);
	}

	@Override
	public void dispatchEvent(Event event) {
		if (eventListeners.containsKey(event.getType())) {
			for (IEventListener listener : eventListeners.get(event.getType())) {
				listener.handleEvent(event);
			}
		}
	}

	@Override
	public boolean hasEventListener(IEventListener listener, String eventType) {
		return eventListeners.containsKey(eventType) && eventListeners.get(eventType).contains(listener);
	}

}
