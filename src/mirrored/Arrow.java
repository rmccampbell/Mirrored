package mirrored;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.physics.Direction;

public class Arrow extends Sprite {

	public Arrow(double x, double y, double vx, double vy, Level level) {
		super("arrow", "arrow.png");
		level.addChild(this);
		setPosition(x, y);
		setRotation(Math.atan2(vy, vx));
		addPhysics();
		getPhysics().setVelocity(vx, vy);
		level.getPhysicsManager().addObject(this);
		setBBox(0,0,15,15);
	}
	
	@Override
	public void collision(DisplayObject other, Direction dir) {
		super.collision(other, dir);
		if (other instanceof Character)
			((Character) other).die();
	}

}
