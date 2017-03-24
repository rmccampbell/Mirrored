package prototype;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.DisplayObjectContainer;

public class Player extends AnimatedSprite {

	private static double SPEED = 4;

	boolean isRight;

	public Player(boolean isRight, double x, double y, DisplayObjectContainer parent) {
		super("player" + (isRight ? "1" : "2"), "mario_run2.png", 1, 2);
		this.isRight = isRight;
		parent.addChild(this);
		setPosition(x, y);
		setFlipped(isRight);
		addAnimation("stand", 0, 1);
		addAnimation("walk", 0, 2);
		setAnimation("stand");
		addPhysics(1, 0);
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
	}
}
