package mirrored;

import edu.virginia.engine.display.DisplayObjectContainer;

public class Ground extends TexturedSprite {

	public Ground(double x, double y, double width, double height, DisplayObjectContainer parent) {
		super("ground", "floor_tiles.png", x, y, width, height);
		parent.addChild(this);
		Main.getInstance().getPhysicsManager().addTrigger(this);
	}
}
