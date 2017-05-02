package mirrored;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Sprite;

public class Key extends Sprite {
	
	public Key(String id, double x, double y, Level level) {
		super(id, "key.png");
		level.addChild(this);
		setPosition(x, y);
		level.getPhysicsManager().addTrigger(this);
		setzOrder(-1);
	}
	
	@Override
	public void trigger(DisplayObject other) {
		super.trigger(other);
		if (other instanceof Player){
			((Player) other).setHasKey(true);
			destroy();
		}		
	}
}
