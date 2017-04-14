package mirrored;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.physics.PhysicsObject;

public class Wall extends Sprite {

	public Wall(double x, double y, double width, double height, DisplayObjectContainer parent) {
		super("platform", "platform.png");
		parent.addChild(this);
		setPosition(x, y);
		setScale(width/getUnscaledWidth(), height/getUnscaledHeight());
		addPhysics(PhysicsObject.STATIC, 0);
	}
}
