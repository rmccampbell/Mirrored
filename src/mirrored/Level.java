package mirrored;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.physics.PhysicsManager;

public class Level extends DisplayObjectContainer {
	
	public Level(String id) {
		super(id);
		setPivotPoint(0, 0);
	}
	
	public Level(String id, String fileName) {
		super(id, fileName);
		setPivotPoint(0,0);
	}

	static final int gameWidth = Main.gameWidth;
	static final int gameHeight = Main.gameHeight;

	protected PhysicsManager physicsManager;

	public PhysicsManager getPhysicsManager() {
		return physicsManager;
	}

}
