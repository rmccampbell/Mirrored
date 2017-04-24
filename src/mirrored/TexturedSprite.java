package mirrored;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import edu.virginia.engine.display.Sprite;

public class TexturedSprite extends Sprite {

	private double width, height;

	public TexturedSprite(String id, String imageFileName, double x, double y, double width, double height) {
		super(id, imageFileName);
		setPosition(x, y);
		setPivotPoint(0, 0);
		this.width = width;
		this.height = height;
		setBBox(new Rectangle2D.Double(0, 0, width, height));
	}

	@Override
	public double getWidth() {
		return width;
	}
	
	@Override
	public double getHeight() {
		return height;
	}
	
	@Override
	public void draw(Graphics g) {
		int x1 = (int) getX();
		int y1 = (int) getY();
		int x2 = x1 + (int) width;
		int y2 = y1 + (int) height;
		g.drawImage(getDisplayImage(), x1, y1, x2, y2, x1, y1, x2, y2, null);
	}
}
