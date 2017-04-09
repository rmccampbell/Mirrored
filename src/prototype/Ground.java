package prototype;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;

public class Ground extends Sprite {

	public Ground(double x, double y, double width, double height, DisplayObjectContainer parent) {
		super("ground", "ground.png");
		parent.addChild(this);
		setPosition(x, y);
		setScale(width/getUnscaledWidth(), height/getUnscaledHeight());
	}
}
