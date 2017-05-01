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

public class Level2 extends Level implements IEventListener {

	Ground bridge;
	
	public Level2() {
		super("Level 2", "lava_tiles.png");
		physicsManager = new PhysicsManager();
		
		Sprite level2Intro = new Sprite("level2Intro", "level02.png");
		this.addChild(level2Intro);
		level2Intro.setPivotPoint(0,0);
		level2Intro.setPosition(300,200);
		level2Intro.setzOrder(1);
		
		Tween level2Tween = new Tween(level2Intro, 200, TweenTransitions.EASE_OUT);
		level2Tween.animate(TweenableParams.ALPHA, 1.0, 0.0);
		Main.getInstance().getTweenManager().add(level2Tween);
		
		new Ground(0, 0, gameWidth, gameHeight, this);
		
		// boundaries 
		new Wall(gameWidth/2-10, 0, 20, gameHeight, this);
		new Wall(0, 0, 20, gameHeight, this);
		new Wall(gameWidth-20, 0, 20, gameHeight, this);
		new Wall(0, 0, gameWidth, 20, this);
		new Wall(0, gameHeight-20, gameWidth, 20, this);
		
		// level 2
		new Wall(0, 600, 400, 25, this);
		new Wall(100, 400, 80, 80, this);
		new Wall(300, 400, 80, 80, this);
		
		new Wall(gameWidth/2+100, 600, 400, 25, this);
		new Wall(gameWidth/2, 450, 400, 25, this);
		new Wall(gameWidth/2+100, 300, 400, 25, this);
		new Wall(gameWidth/2, 150, 400, 25, this);
		
		// doors
		Door door1 = new Door(400, 100, this);
		door1.addEventListener(this, Events.DOOR);
		Door door2 = new Door(600, 100, this);
//		door2.addEventListener(this, Events.DOOR);
		
		// players
		Player player1 = new Player(false, (0.05) * gameWidth, 720, this);
		Player player2 = new Player(true, (0.95) * gameWidth, 720, this);
		player1.setOtherPlayer(player2);
		player2.setOtherPlayer(player1);

		player1.addEventListener(this, Events.DEATH);
		player2.addEventListener(this, Events.DEATH);

		// left side enemies
		Enemy enemy1 = new Enemy("enemy1", "ghostSheet.png", EnemyType.homing, player1, this);
		enemy1.setPosition(480,20);
		Enemy enemy2 = new Enemy("enemy2", "ghostSheet.png", EnemyType.staticY, player1, this);
		enemy2.setPosition(60, 500);
		// right side enemies
		Enemy enemy3 = new Enemy("enemy3", "ghostSheet.png", EnemyType.staticX, player2, this);
		enemy3.setPosition(500,500);
		Enemy enemy4 = new Enemy("enemy4", "ghostSheet.png", EnemyType.staticX, player2, this);
		enemy4.setPosition(800,420);

		//buttons
		Button button1 = new Button("button1", 250, 550, this);
		button1.addEventListener(this, Events.BUTTON_ON);
		button1.setzOrder(-1);
	
		// sound effects
		Main.getInstance().getSoundManager().loadSound("arrowSound", "arrowSound.wav");
		
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		physicsManager.update();
//		particleMan.update();
		Main.getInstance().getTweenManager().update(1);
	}

	@Override
	public void handleEvent(Event event) {
		if(event.getType().equals(Events.SWITCH)){
			DisplayObject obj = ((DisplayObject)event.getSource());
			if (obj.getId().equals("switch1")) {
				new TrapDoor(900, 500, this);
			}
			else if (obj.getId().equals("switch2")) {
				new Ground(200, 325, 100, 100, this);
			}
		}
		if(event.getType().equals(Events.DEATH)) {
			Main.getInstance().resetLevel();
//			Sprite gameover = new Sprite("gameover", "gameover.png");
//			gameover.setPosition(500, 400);
//			gameover.setzOrder(1);
//			this.addChild(gameover);
		}
		if(event.getType().equals(Events.DOOR)) {
			Main.getInstance().nextLevel();
//			Sprite win = new Sprite("win", "win.png");
//			win.setPosition(500, 400);
//			win.setzOrder(1);
//			this.addChild(win);
		}
		if(event.getType().equals(Events.BUTTON_ON)){
			 new Arrow(480, gameHeight-500, -8, 0, this);
			 Main.getInstance().getSoundManager().playSound("arrowSound");
		}
		if(event.getType().equals(Events.BUTTON_OFF)){
			
		}
	}
}
