package mirrored;

import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.physics.PhysicsManager;

public class Level1 extends Level implements IEventListener {

	public Level1() {
		super("Level 1");
		physicsManager = new PhysicsManager();
		
		// level 1
		addGround(gameWidth/2, gameHeight/4-50, gameWidth, 350);
		addGround(gameWidth/2, (3*gameHeight/4), gameWidth, 350);
		addGround(750, 375, 100, 100);
		Switch switch1 = new Switch("switch1", 100, 600, this);
		physicsManager.addTrigger(switch1);
		switch1.addEventListener(this, Events.SWITCH);
		Switch switch2 = new Switch("switch2", 600, 700, this);
		physicsManager.addTrigger(switch2);
		switch2.addEventListener(this, Events.SWITCH);		
		
		// boundaries 
		addWall(gameWidth/2, gameHeight/2, 20, gameHeight);
		addWall(0, gameHeight/2, 20, gameHeight);
		addWall(gameWidth-5, gameHeight/2, 20, gameHeight);
		addWall(gameWidth/2, 0, gameWidth, 20);
		addWall(gameWidth/2, gameHeight-35, gameWidth, 20);

		// players
		Player player1 = new Player(false, (0.05) * gameWidth, 700, this);
		Player player2 = new Player(true, (0.95) * gameWidth, 700, this);
		player1.setOtherPlayer(player2);
		player2.setOtherPlayer(player1);

		player1.addEventListener(this, Events.DEATH);
		player2.addEventListener(this, Events.DEATH);

		player1.setzOrder(1);
		player2.setzOrder(1);

		physicsManager.addObject(player1);
		physicsManager.addObject(player2); 
		
		// doors
		Door door1 = new Door(200, 100, this);
		physicsManager.addTrigger(door1);
		door1.addEventListener(this, Events.DOOR);
		Door door2 = new Door(800, 100, this);
		physicsManager.addTrigger(door2);
		door2.addEventListener(this, Events.DOOR);
		
//		Doors door1 = new Doors("door1", 50, 80, this);
//		door1.setScaleX(0.5);
//		door1.setScaleY(0.5);
//		physicsManager.addObject(door1);
//		door1.addEventListener(this, WinEvent.EVENT_TYPE);
//		Doors door2 = new Doors("door2", gameWidth-50, 80, this);
//		door2.setScaleX(0.5);
//		door2.setScaleY(0.5);
//		physicsManager.addObject(door2);
//		door2.addEventListener(this, WinEvent.EVENT_TYPE);
		
		// enemies
		Enemy enemy1 = addEnemy("enemy1", "ghost.png", EnemyType.homing, player2);
		enemy1.setPosition(500,500);

		Enemy enemy2 = addEnemy("enemy2", "ghost.png", EnemyType.staticX, player1);
		enemy2.setPosition(480,200);

	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		physicsManager.update();
//		particleMan.update();
	}

	private Wall addWall(double x, double y, double width, double height) {
		Wall p = new Wall(x, y, width, height, this);
		physicsManager.addObject(p);
		return p;
	}
	
	private Ground addGround(double x, double y, double width, double height) {
		Ground g = new Ground(x, y, width, height, this);
		physicsManager.addTrigger(g);
		return g;
	}
	
	private Enemy addEnemy(String id, String fileName, EnemyType type, Player player){
		Enemy e = new Enemy(id, fileName, type, this, player);
		physicsManager.addObject(e);
		return e;
	}
	
	@Override
	public void handleEvent(Event event) {
		if(event.getType().equals(Events.SWITCH)){
			DisplayObject obj = ((DisplayObject)event.getSource());
			if (obj.getId().equals("switch1")) {
				TrapDoor trap = new TrapDoor(900, 500, this);
				physicsManager.addTrigger(trap);
			}
			else if (obj.getId().equals("switch2")) {
				addGround(250, 375, 100, 100);
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
