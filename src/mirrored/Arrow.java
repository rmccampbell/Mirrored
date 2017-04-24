package mirrored;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.physics.Direction;

public class Arrow extends Sprite {

	public Arrow(double x, double y, double vx, double vy, DisplayObjectContainer parent) {
		super("arrow", "arrow.png");
		parent.addChild(this);
		setPosition(x, y);
		setRotation(Math.atan2(vy, vx));
		addPhysics();
		getPhysics().setVelocity(vx, vy);
		Main.getInstance().getPhysicsManager().addObject(this);
	}
	
	@Override
	public void collision(DisplayObject other, Direction dir) {
		super.collision(other, dir);
		if (other instanceof Character)
			((Character) other).die();
	}

}
