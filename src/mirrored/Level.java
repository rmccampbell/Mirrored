package mirrored;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.physics.PhysicsManager;

public class Level extends DisplayObjectContainer {
	
	static final int gameWidth = Main.gameWidth;
	static final int gameHeight = Main.gameHeight;

	protected PhysicsManager physicsManager;

	public Level(String id) {
		super(id);
	}
	
	public PhysicsManager getPhysicsManager() {
		return physicsManager;
	}

}
