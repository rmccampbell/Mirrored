package mirrored;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.physics.PhysicsManager;

public class Level2 extends Level implements IEventListener {

	public Level2() {
		super("Level 2");
		physicsManager = new PhysicsManager();
		
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		
	}
}
