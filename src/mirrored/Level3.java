package mirrored;

import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.physics.PhysicsManager;

/**
 * Created by Laith on 4/24/17.
 */
public class Level3 extends Level implements IEventListener {
	SplashTower tower1;
	SplashTower tower2;
	Door door1, door2;
	private boolean winScreenShown;

	public Level3() {
		super("Level 3");
		physicsManager = new PhysicsManager();

		// level 3
		new Ground(0, 0, gameWidth, 250, this);
		new Ground(0, 250, gameWidth / 2, gameHeight - 250, this);
		new Ground(gameWidth / 2, 350, gameWidth / 2, gameHeight - 350, this);

		// boundaries
		new Wall(gameWidth / 2 - 10, 0, 20, gameHeight, this);
		new Wall(0, 0, 20, gameHeight, this);
		new Wall(gameWidth - 20, 0, 20, gameHeight, this);
		new Wall(0, 0, gameWidth, 20, this);
		new Wall(0, gameHeight - 20, gameWidth, 20, this);

		// game walls
		new Wall(100, gameHeight - 200, 300, 20, this);
		new Wall(350, gameHeight - 300, 150, 20, this);
		new Wall(0, gameHeight - 300, 150, 20, this);
		new Wall(740, gameHeight - 400, 20, 220, this);

		// splash tower
		tower1 = new SplashTower("tower1", 250, gameHeight - 290, this);
		tower2 = new SplashTower("tower2", 250, gameHeight - 490, this);

		// switches
		Switch switch1 = new Switch("switch1", 100, 400, this);
		switch1.addEventListener(this, Events.SWITCH);

		// buttons
		Button button1 = new Button("button1", 550, 700, this);
		button1.addEventListener(this, Events.BUTTON_HOLD);
		Button button2 = new Button("button2", 950, 700, this);
		button2.addEventListener(this, Events.BUTTON_HOLD);

		// players
		Player player1 = new Player(false, 250, 700, this);
		Player player2 = new Player(true, 750, 700, this);
		player1.setOtherPlayer(player2);
		player2.setOtherPlayer(player1);

		player1.addEventListener(this, Events.DEATH);
		player2.addEventListener(this, Events.DEATH);

		// enemies
		Enemy enemy1 = new Enemy("enemy1", "ghostSheet.png", EnemyType.homing, player1, this);
		enemy1.setPosition(250, 400);
		Enemy enemy2 = new Enemy("enemy2", "ghostSheet.png", EnemyType.staticX, player1, this);
		enemy2.setPosition(480, 300);
		Enemy enemy3 = new Enemy("enemy3", "ghostSheet.png", EnemyType.staticX, player1, this);
		enemy3.setPosition(20, 300);
		Enemy enemy4 = new Enemy("enemy4", "ghostSheet.png", EnemyType.homing, player2, this);
		enemy4.setPosition(550, 400);

		// doors
		door1 = new Door(400, 100, this);
		door1.addEventListener(this, Events.DOOR);
		door2 = new Door(600, 100, this);
//		door2.addEventListener(this, Events.DOOR);

		// enemies
		/*
		 * Enemy enemy1 = new Enemy("enemy1", "ghostSheet.png",
		 * EnemyType.homing, this, player2); enemy1.setPosition(500,500);
		 * 
		 * Enemy enemy2 = new Enemy("enemy2", "ghostSheet.png",
		 * EnemyType.staticX, this, player1); enemy2.setPosition(480,200);
		 */
	}

	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		physicsManager.update();
	}

	@Override
	public void handleEvent(Event event) {
		if (event.getType().equals(Events.SWITCH)) {
			new Ground(670, 250, 150, 100, this);
		}

		if (event.getType().equals(Events.BUTTON_HOLD)) {
			DisplayObject obj = ((DisplayObject) event.getSource());
			if (obj.getId().equals("button1")) {
				tower1.activate();
				// tower2.attack();
			}

			if (obj.getId().equals("button2")) {
				// tower1.attack();
				tower2.activate();
				// new Arrow(100, gameHeight-300, 0, -4, this);
				// new Arrow(400, gameHeight-300, 0, -4, this);
			}
		}

		if (event.getType().equals(Events.DEATH)) {
			Main.getInstance().resetLevel();
		}
		if (event.getType().equals(Events.DOOR) && !winScreenShown) {
			winScreenShown = true;
			Sprite win = new Sprite("win", "win.png");
			win.setPosition(500, 400);
			win.setzOrder(1);
			this.addChild(win);
		}
	}
}
