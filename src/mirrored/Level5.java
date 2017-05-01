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

public class Level5 extends Level implements IEventListener {

	Ground bridge;
	SplashTower tower1;
	Enemy enemy1, enemy2, enemy3, enemy4;
	private boolean winScreenShown = false;
	static TweenManager myTweenManager = new TweenManager();
	
	public Level5() {
		super("Level 5", "lava_tiles.png");
		physicsManager = new PhysicsManager();
		
		Sprite level5Intro = new Sprite("level5Intro", "level05.png");
		this.addChild(level5Intro);
		level5Intro.setPivotPoint(0,0);
		level5Intro.setPosition(300,200);
		level5Intro.setzOrder(1);
		
		Tween level5Tween = new Tween(level5Intro, 200, TweenTransitions.EASE_OUT);
		level5Tween.animate(TweenableParams.ALPHA, 1.0, 0.0);
		Main.getInstance().getTweenManager().add(level5Tween);
		
		new Ground(0, 0, gameWidth, 475, this);
		new Ground(375, 475, gameWidth-375, 125, this);
		new Ground(0, 600, gameWidth, 200, this);
		
		// boundaries 
		new Wall(gameWidth/2-10, 0, 20, gameHeight, this);
		new Wall(0, 0, 20, gameHeight, this);
		new Wall(gameWidth-20, 0, 20, gameHeight, this);
		new Wall(0, 0, gameWidth, 20, this);
		new Wall(0, gameHeight-20, gameWidth, 20, this);
		
		// left side walls
		new Wall(150, 600, 25, 180, this);
		new Wall(350, 300, 25, 175, this);
		// right side walls
		new Wall(625, 475, 125, 125, this);
		new Wall(500, 105, 250, 25, this);
		
		// doors
		Door door1 = new Door(400, 60, this);
		Door door2 = new Door(600, 60, this);
		door2.addEventListener(this, Events.DOOR);
		
		// spash towers
		tower1 = new SplashTower("tower1", 125, 300, this);
		
		//buttons
		Button tower1Button = new Button("tower1Button", 900, 75, this);
		tower1Button.addEventListener(this, Events.BUTTON_HOLD);
		tower1Button.addEventListener(this, Events.BUTTON_ON);
		tower1Button.addEventListener(this, Events.BUTTON_OFF);
		Button arrow1Button = new Button("arrow1Button", 80, 700, this);
		arrow1Button.addEventListener(this, Events.BUTTON_ON);
		
		// switches
		Switch switch1 = new Switch("switch1", 900, 425, this);
		switch1.addEventListener(this, Events.SWITCH);
		
		// players
		Player player1 = new Player(false, (0.20) * gameWidth, 720, this);
		Player player2 = new Player(true, (0.80) * gameWidth, 720, this);
		player1.setOtherPlayer(player2);
		player2.setOtherPlayer(player1);

		player1.addEventListener(this, Events.DEATH);
		player2.addEventListener(this, Events.DEATH);

		// left side enemies
		enemy1 = new Enemy("enemy1", "ghostSheet.png", EnemyType.still, player1, this);
		enemy1.setPosition(50,650);
		enemy2 = new Enemy("enemy2", "ghostSheet.png", EnemyType.still, player1, this);
		enemy2.setPosition(125, 650);
		enemy3 = new Enemy("enemy3", "ghostSheet.png", EnemyType.still, player1, this);
		enemy3.setPosition(50,725);
		enemy4 = new Enemy("enemy4", "ghostSheet.png", EnemyType.still, player1, this);
		enemy4.setPosition(125, 725);
		// right side enemies
		Enemy enemy5 = new Enemy("enemy5", "ghostSheet.png", EnemyType.staticY, player2, this);
		enemy5.setPosition(700, 100);
		Enemy enemy6 = new Enemy("enemy6", "ghostSheet.png", EnemyType.staticX, player2, this);
		enemy6.setPosition(600, 200);
		
		// sound effects
		Main.getInstance().getSoundManager().loadSound("arrowSound", "arrowSound.wav");
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
		if(event.getType().equals(Events.DEATH)){
			Main.getInstance().resetLevel();
		}
		if(event.getType().equals(Events.DOOR) && !winScreenShown) {
			winScreenShown = true;
			Sprite win = new Sprite("win", "win.png");
			win.setPosition(500, 400);
			win.setzOrder(1);
			this.addChild(win);
		}
		if(event.getType().equals(Events.BUTTON_HOLD)){
			DisplayObject obj = ((DisplayObject) event.getSource());
			if (obj.getId().equals("tower1Button")) {
				tower1.activate();
			} 
		}
		if(event.getType().equals(Events.BUTTON_ON)){
			DisplayObject obj = ((DisplayObject)event.getSource());
			if (obj.getId().equals("arrow1Button")) {
				new Arrow(gameWidth-10, 60, -4, 0, this);
				Main.getInstance().getSoundManager().playSound("arrowSound");
			} 
			else if(obj.getId().equals("tower1Button")) {
				Main.getInstance().getSoundManager().playSound("splashTowerSound", true);
			}
		}
		if (event.getType().equals(Events.BUTTON_OFF)) {
			DisplayObject obj = ((DisplayObject)event.getSource());
			if(obj.getId().equals("tower1Button")) {
				Main.getInstance().getSoundManager().stopSound("splashTowerSound");
			}
		}
		if(event.getType().equals(Events.SWITCH)){
			DisplayObject obj = ((DisplayObject)event.getSource());
			if (obj.getId().equals("switch1")) {
				new Ground(0, 475, 150, 125, this);
				enemy1.setType(EnemyType.homing);
				enemy2.setType(EnemyType.homing);
				enemy3.setType(EnemyType.homing);
				enemy4.setType(EnemyType.homing);
			}
			Main.getInstance().getSoundManager().playSound("switchSound");
		}
	}

}
