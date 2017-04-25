package mirrored;

import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class Level1 extends Level implements IEventListener {

	public Level1() {
		super("Level 1");
		physicsManager = Main.getInstance().getPhysicsManager();
		physicsManager.clear();
		
		// level 1
		new Ground(0, -25, gameWidth, 350, this);
		new Ground(0, 425, gameWidth, 400, this);
		new Ground(700, 325, 100, 100, this);
		
		// switches
		Switch switch1 = new Switch("switch1", 100, 600, this);
		switch1.addEventListener(this, Events.SWITCH);
		switch1.setScale(0.7);
		Switch switch2 = new Switch("switch2", 600, 700, this);
		switch2.addEventListener(this, Events.SWITCH);		
		switch2.setScale(0.7);
		
		// boundaries 
		new Wall(gameWidth/2-10, 0, 20, gameHeight, this);
		new Wall(0, 0, 20, gameHeight, this);
		new Wall(gameWidth-20, 0, 20, gameHeight, this);
		new Wall(0, 0, gameWidth, 20, this);
		new Wall(0, gameHeight-40, gameWidth, 20, this);

		// players
		Player player1 = new Player(false, (0.05) * gameWidth, 700, this);
		Player player2 = new Player(true, (0.95) * gameWidth, 700, this);
		player1.setOtherPlayer(player2);
		player2.setOtherPlayer(player1);

		player1.addEventListener(this, Events.DEATH);
		player2.addEventListener(this, Events.DEATH);

		player1.setzOrder(1);
		player2.setzOrder(1);

		// doors
		Door door1 = new Door(200, 100, this);
		door1.addEventListener(this, Events.DOOR);
		Door door2 = new Door(800, 100, this);
		door2.addEventListener(this, Events.DOOR);
		
		// enemies
		Enemy enemy1 = new Enemy("enemy1", "ghostSheet.png", EnemyType.homing, this, player2);
		enemy1.setPosition(500,500);

		Enemy enemy2 = new Enemy("enemy2", "ghostSheet.png", EnemyType.staticX, this, player1);
		enemy2.setPosition(480,200);

	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		physicsManager.update();
//		particleMan.update();
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
//			this.addChildConcurrent(gameover);
		}
		if(event.getType().equals(Events.DOOR)) {
			Main.getInstance().nextLevel();
//			Sprite win = new Sprite("win", "win.png");
//			win.setPosition(500, 400);
//			win.setzOrder(1);
//			this.addChildConcurrent(win);
		}
	}

}
