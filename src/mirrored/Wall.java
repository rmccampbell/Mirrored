package mirrored;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.physics.PhysicsObject;

public class Wall extends TexturedSprite {

	public Wall(double x, double y, double width, double height, DisplayObjectContainer parent) {
		super("platform", "wall_tiles.png", x, y, width, height);
		parent.addChild(this);
		addPhysics(PhysicsObject.STATIC, 0);
	}
}
