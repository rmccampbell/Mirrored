package prototype;

import java.awt.Graphics;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;

public class Ground extends Sprite {

	public Ground(double x, double y, double width, double height, DisplayObjectContainer parent) {
		super("ground", "floor_tiles.png");
		parent.addChild(this);
		setPosition(x, y);
		setScale(width/getUnscaledWidth(), height/getUnscaledHeight());
	}
	
	@Override
	public void draw(Graphics g) {
		int x1 = (int) (getX() - getPivotPoint().getX() * getScaleX());
		int y1 = (int) (getY() - getPivotPoint().getY() * getScaleY());
		int x2 = (int) (x1 + getWidth());
		int y2 = (int) (y1 + getHeight());
		g.drawImage(getDisplayImage(), x1, y1, x2, y2, x1, y1, x2, y2, null);
	}
}
