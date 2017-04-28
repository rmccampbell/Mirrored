package mirrored;

import edu.virginia.engine.physics.PhysicsObject;

public class Wall extends TexturedSprite {

	public Wall(double x, double y, double width, double height, Level level) {
		super("platform", "wall_tiles.png", x, y, width, height);
		level.addChild(this);
		addPhysics(PhysicsObject.STATIC, 0);
		level.getPhysicsManager().addObject(this);
	}
}
