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

	public Level(String id, String fileName) {
		super(id, fileName);
	}
	
	public PhysicsManager getPhysicsManager() {
		return physicsManager;
	}

}
