package prototype;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.physics.PhysicsManager;

public class Level extends DisplayObjectContainer {

	PhysicsManager physicsManager;

	public Level(String id) {
		super(id);
	}
	
	public PhysicsManager getPhysicsManager() {
		return physicsManager;
	}

}
