package mirrored;

import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.physics.Direction;

public class Player extends Character {

	private static double SPEED = 3;

	private Player otherPlayer;
	private boolean isSynced = true;
	private boolean isRight;
	private boolean hasKey;

	public Player(boolean isRight, double x, double y, Level level) {
		super("player" + (isRight ? "2" : "1"), "characterSheet2.png", 4, 4, level);
		this.isRight = isRight;
		setPosition(x, y);
		
		// animation
		setScale(3);
		setBBox(0, 0, 14, 18);
		setFrameDuration(10);
		addAnimation("standUp", 4, 1);
		addAnimation("standDown", 0, 1);
		addAnimation("standLeft", 8, 1);
		addAnimation("standRight", 13, 1);
		addAnimation("walkDown", 0, 4);
		addAnimation("walkUp", 4, 4);
		addAnimation("walkLeft", 8, 4);
		addAnimation("walkRight", 12, 4);
		setAnimation("standDown");
		
		setHealth(100);
	}

	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		this.walking = false;
		if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
			move(isRight ? -SPEED : SPEED, 0);
			this.facing = (isRight ? Direction.LEFT : Direction.RIGHT);
			this.walking = true;
		}
		if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
			move(isRight ? SPEED : -SPEED, 0);
			this.facing = (isRight? Direction.RIGHT : Direction.LEFT);
			this.walking = true;
		}
		if (pressedKeys.contains(KeyEvent.VK_UP)) {
			move(0, -SPEED);
			this.facing = Direction.UP;
			this.walking = true;			
		}
		if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
			move(0, SPEED);
			this.facing = Direction.DOWN;
			this.walking = true;
		}
//		if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
//			isSynced = !isSynced;
//		}

		switch (facing) {
		case LEFT:
			setAnimation(walking ? "walkLeft" : "standLeft");
			break;
		case RIGHT:
			setAnimation(walking ? "walkRight" : "standRight");
			break;
		case UP:
			setAnimation(walking ? "walkUp" : "standUp");
			break;
		case DOWN:
			setAnimation(walking ? "walkDown" : "standDown");
			break;
		}

		super.update(pressedKeys);
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
	
	public boolean hasKey() {
		return hasKey;
	}
	
	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}
	
	public void handleCollision(Direction dir) {
		resetPosition(true, true);
	}

	@Override
	public void die() {
		super.die();
		dispatchEvent(new Event(Events.DEATH, this));
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
