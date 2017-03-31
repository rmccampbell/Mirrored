package edu.virginia.engine.display;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import edu.virginia.engine.events.EventDispatcher;
import edu.virginia.engine.physics.CollisionEvent;
import edu.virginia.engine.physics.Direction;
import edu.virginia.engine.physics.PhysicsObject;

/**
 * A very basic display object for a java based gaming engine
 * 
 * */
public class DisplayObject extends EventDispatcher {

	/* All DisplayObject have a unique id */
	private String id;

	/* The image that is displayed by this object */
	private BufferedImage displayImage;
	
	private DisplayObjectContainer parent;
	
	private boolean isAlive = true;
	
	private boolean visible = true;
	private Point2D.Double position = new Point2D.Double(0, 0);
	private double prevX = 0, prevY = 0;
	private Point pivotPoint = new Point(0, 0);
	private double scaleX = 1, scaleY = 1;
	private double rotation = 0.0;
	private boolean flipped = false;
	private float alpha = 1.0f;
	
	private Rectangle2D.Double bbox = new Rectangle2D.Double();
	
	private AffineTransform savedTransform;
	private Composite savedComposite;
	
	private PhysicsObject physics = null;

	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */
	public DisplayObject(String id) {
		this.setId(id);
	}

	public DisplayObject(String id, String fileName) {
		this.setId(id);
		this.setImage(fileName);
		setPivotPoint(getUnscaledWidth()/2, getUnscaledHeight()/2);
		setBBox(0, 0, getUnscaledWidth(), getUnscaledHeight());
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public Point2D getPosition() {
		return position;
	}
	
	public double getX() {
		return position.x;
	}
	
	public double getY() {
		return position.y;
	}
	
	public void setX(double x) {
		position.x = x;
	}
	
	public void setY(double y) {
		position.y = y;
	}

	public void setPosition(double x, double y) {
		this.position.x = x;
		this.position.y = y;
	}
	
	public void setPosition(Point2D position) {
		setPosition(position.getX(), position.getY());
	}
	
	public void move(double dx, double dy) {
		this.position.x += dx;
		this.position.y += dy;
	}

	public void move(Point2D disp) {
		move(disp.getX(), disp.getY());
	}
	
	public void resetPosition(boolean horizontal, boolean vertical) {
		if (horizontal)
			setX(prevX);
		if (vertical)
			setY(prevY);
	}
	
	public Point getPivotPoint() {
		return pivotPoint;
	}

	public void setPivotPoint(int x, int y) {
		this.pivotPoint = new Point(x, y);
	}

	public void setPivotPoint(Point pivotPoint) {
		setPivotPoint(pivotPoint.x, pivotPoint.y);
	}
	
	public double getScaleX() {
		return scaleX;
	}
	
	public double getScaleY() {
		return scaleY;
	}
	
	public Point2D getScale() {
		return new Point2D.Double(scaleX, scaleY);
	}

	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}
	
	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}
	
	public void setScale(double scaleX, double scaleY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}
	
	public void setScale(double scale) {
		this.scaleX = this.scaleY = scale;
	}
	
	public void scale(double scale) {
		scaleX *= scale;
		scaleY *= scale;
	}
	
	public double getRotation() {
		return rotation;
	}
	
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	
	public void rotate(double rotation) {
		this.rotation += rotation;
	}

	public boolean isFlipped() {
		return flipped;
	}
	
	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
	}
	
	public float getAlpha() {
		return alpha;
	}
	
	public void setAlpha(float alpha) {
		this.alpha = Math.min(Math.max(alpha, 0f), 1f);
	}
	
	public DisplayObjectContainer getParent() {
		return parent;
	}
	
	public void setParent(DisplayObjectContainer parent) {
		this.parent = parent;
	}
	
	/**
	 * Returns the unscaled width and height of this display object
	 * */
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		return displayImage.getWidth();
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		return displayImage.getHeight();
	}

	public double getWidth() {
		return getUnscaledWidth() * scaleX;
	}
	
	public double getHeight() {
		return getUnscaledHeight() * scaleY;
	}

	public BufferedImage getDisplayImage() {
		return this.displayImage;
	}

	protected void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}


	/**
	 * Helper function that simply reads an image from the given image name
	 * (looks in resources\\) and returns the bufferedimage for that filename
	 * */
	public BufferedImage readImage(String imageName) {
		BufferedImage image = null;
		try {
			String file = ("resources" + File.separator + imageName);
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
			e.printStackTrace();
		}
		return image;
	}

	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
	}


	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * */
	public void update(ArrayList<Integer> pressedKeys) {
		prevX = getX();
		prevY = getY();
	}

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	public void draw(Graphics g) {
		
		if (visible) {
			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;
			applyTransformations(g2d);
			
			if (displayImage != null) {
				/* Actually draw the image, perform the pivot point translation here */
				g2d.drawImage(displayImage, 0, 0, null);
			}
			
			//visualize pivot point 
			//g.setColor(Color.black);
			//g.fillOval((int)(pivotPoint.getX()), (int)(pivotPoint.getY()), 10, 10);
				
			drawInner(g2d);
			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			reverseTransformations(g2d);
		}
	}
	
	/**
	 * Extra work needed for draw after transformation
	 */
	protected void drawInner(Graphics2D g2d) {
		// Override in derived class
	}

	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */
	protected void applyTransformations(Graphics2D g2d) {
		savedTransform = g2d.getTransform();
		savedComposite = g2d.getComposite();
		g2d.translate(position.x, position.y);
		g2d.rotate(rotation);
//		g2d.scale(scaleX, scaleY);
		g2d.scale(flipped ? -scaleX : scaleX, scaleY);
		g2d.translate(-pivotPoint.x, -pivotPoint.y);
//		g2d.translate(flipped ? pivotPoint.x - getUnscaledWidth() : -pivotPoint.x, -pivotPoint.y);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}

	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */
	protected void reverseTransformations(Graphics2D g2d) {
		g2d.setTransform(savedTransform);
		g2d.setComposite(savedComposite);
	}
	
	public Rectangle2D getBBox() {
		return bbox;
	}

	public void setBBox(Rectangle2D bbox) {
		this.bbox = new Rectangle2D.Double(bbox.getX(), bbox.getY(), bbox.getWidth(), bbox.getHeight());
	}

	public void setBBox(double cx, double cy, double width, double height) {
		this.bbox = new Rectangle2D.Double(cx - width / 2, cy - height / 2, width, height);
	}

	public Rectangle2D getWorldBBox() {
		return new Rectangle2D.Double(bbox.x * scaleX + position.x, bbox.y * scaleY + position.y,
				bbox.width * scaleX, bbox.height * scaleY);
	}

	public boolean collidesWith(DisplayObject other) {
		return getWorldBBox().intersects(other.getWorldBBox());
	}
	
	public void collision(DisplayObject other, Direction dir, boolean isTrigger) {
		if (!isTrigger) {
//			boolean isHorizontal = dir == Direction.LEFT || dir == Direction.RIGHT;
			resetPosition(true, true);
		}
		dispatchEvent(new CollisionEvent(this, other, isTrigger));
	}

	public boolean hasPhysics() {
		return physics != null;
	}
	
	public PhysicsObject getPhysics() {
		return physics;
	}
	
	public PhysicsObject addPhysics() {
		return physics = new PhysicsObject(this);
	}

	public PhysicsObject addPhysics(double mass, double bounciness) {
		return physics = new PhysicsObject(this, mass, bounciness);
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void destroy() {
		isAlive = false;
	}
}
