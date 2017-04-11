package prototype;

import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.physics.Direction;

public class Player extends Character {

	private static double SPEED = 4;

	private boolean isSynced = true;
	private boolean isRight;
	private Player otherPlayer;

	public Player(boolean isRight, double x, double y, DisplayObjectContainer parent) {
		super("player" + (isRight ? "2" : "1"), "mario_run.png", 1, 2);
		parent.addChild(this);
		this.isRight = isRight;
		setPosition(x, y);
		setFlipped(isRight);
		addAnimation("stand", 0, 1);
		addAnimation("walk", 0, 2);
		setAnimation("stand");
		addPhysics(1, 0);
		setHealth(100);
	}

	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
			setFlipped(!isRight);
			move(isRight ? SPEED : -SPEED, 0);
		}
		if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
			setFlipped(isRight);
			move(isRight ? -SPEED : SPEED, 0);
		}
		if (pressedKeys.contains(KeyEvent.VK_UP)) {
			move(0, -SPEED);
		}
		if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
			move(0, SPEED);
		}
		if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
			isSynced = !isSynced;
		}
	}
	
	public void setOtherPlayer(Player otherPlayer) {
		this.otherPlayer = otherPlayer;
	}
	
	public boolean isSynced() {
		return isSynced;
	}
	
	public void setSynced(boolean isSynced) {
		this.isSynced = isSynced;
	}
	
	public void handleCollision(Direction dir) {
		resetPosition(true, true);
	}

	@Override
	protected void die() {
		super.die();
		dispatchEvent(new GameOverEvent(this));
	}
	
	@Override
	public void collision(DisplayObject other, Direction dir) {
		super.collision(other, dir);
		if (isSynced)
			otherPlayer.setPosition(mirrored(getPosition()));
	}
	
	private static Point2D mirrored(Point2D position) {
		return new Point2D.Double(Main.gameWidth - position.getX(), position.getY());
	}

}
