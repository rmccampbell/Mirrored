package mirrored;

import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.physics.PhysicsManager;
import edu.virginia.engine.tweening.Tween;
import edu.virginia.engine.tweening.TweenManager;
import edu.virginia.engine.tweening.TweenTransitions;
import edu.virginia.engine.tweening.TweenableParams;

/**
 * Created by Laith on 4/24/17.
 */
public class Level3 extends Level implements IEventListener {
	SplashTower tower1;
	SplashTower tower2;
	Door door1, door2;

	public Level3() {
		super("Level 3", "lava_tiles.png");
		physicsManager = new PhysicsManager();

		Sprite level3Intro = new Sprite("level3Intro", "level03.png");
		this.addChild(level3Intro);
		level3Intro.setPivotPoint(0,0);
		level3Intro.setPosition(300,200);
		level3Intro.setzOrder(1);
		
		Tween level3Tween = new Tween(level3Intro, 200, TweenTransitions.EASE_OUT);
		level3Tween.animate(TweenableParams.ALPHA, 1.0, 0.0);
		Main.getInstance().getTweenManager().add(level3Tween);
		
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
		button1.addEventListener(this, Events.BUTTON_ON);
		button1.addEventListener(this, Events.BUTTON_OFF);
		Button button2 = new Button("button2", 950, 700, this);
		button2.addEventListener(this, Events.BUTTON_HOLD);
		button2.addEventListener(this, Events.BUTTON_ON);
		button2.addEventListener(this, Events.BUTTON_OFF);

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
		enemy3.setPosition(230, 300);
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
		
		// sound effects
		Main.getInstance().getSoundManager().loadSound("switchSound", "switchSound.wav");
		Main.getInstance().getSoundManager().loadSound("splashTowerSound", "splashTowerSound.wav");
	}

	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		physicsManager.update();
		Main.getInstance().getTweenManager().update(1);
	}

	@Override
	public void handleEvent(Event event) {
		if (event.getType().equals(Events.SWITCH)) {
			new Ground(650, 250, 200, 100, this);
			Main.getInstance().getSoundManager().playSound("switchSound");
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
		if (event.getType().equals(Events.BUTTON_ON)) {
			Main.getInstance().getSoundManager().playSound("splashTowerSound", true);
		}
		if (event.getType().equals(Events.BUTTON_OFF)) {
			Main.getInstance().getSoundManager().stopSound("splashTowerSound");
		}
		if (event.getType().equals(Events.DEATH)) {
			Main.getInstance().resetLevel();
		}
		if (event.getType().equals(Events.DOOR)) {
			Main.getInstance().nextLevel();

		}
	}
}
