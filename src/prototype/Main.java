package prototype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.physics.PhysicsManager;

public class Main extends Game implements IEventListener{
	
	List<Class<? extends Level>> levels;
	Level level;

	PhysicsManager physicsMan;
	static int gameWidth = 1200;
	static int gameHeight = 800;

	public Main() {
		super("Prototype", gameWidth, gameHeight);

		levels = Arrays.asList(Level1.class);
		level = new Level1();
		
		physicsMan = new PhysicsManager();
		
		// level 1
		addGround(gameWidth/2, gameHeight/4-50, gameWidth, 350);
		addGround(gameWidth/2, (3*gameHeight/4), gameWidth, 350);
		addGround(750, 375, 100, 100);
		Switch switch1 = new Switch("switch1", 100, 600, this);
		physicsMan.addTrigger(switch1);
		switch1.addEventListener(this, SwitchEvent.EVENT_TYPE);
		Switch switch2 = new Switch("switch2", 600, 700, this);
		physicsMan.addTrigger(switch2);
		switch2.addEventListener(this, SwitchEvent.EVENT_TYPE);		
		
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

		player1.addEventListener(this, GameOverEvent.EVENT_TYPE);
		player2.addEventListener(this, GameOverEvent.EVENT_TYPE);

		player1.setzOrder(1);
		player2.setzOrder(1);

		physicsMan.addObject(player1);
		physicsMan.addObject(player2); 
		
		// doors
		Door door1 = new Door(200, 100, this);
		physicsMan.addTrigger(door1);
		door1.addEventListener(this, WinEvent.EVENT_TYPE);
		Door door2 = new Door(800, 100, this);
		physicsMan.addTrigger(door2);
		door2.addEventListener(this, WinEvent.EVENT_TYPE);
		
//		Doors door1 = new Doors("door1", 50, 80, this);
//		door1.setScaleX(0.5);
//		door1.setScaleY(0.5);
//		physicsMan.addObject(door1);
//		door1.addEventListener(this, WinEvent.EVENT_TYPE);
//		Doors door2 = new Doors("door2", gameWidth-50, 80, this);
//		door2.setScaleX(0.5);
//		door2.setScaleY(0.5);
//		physicsMan.addObject(door2);
//		door2.addEventListener(this, WinEvent.EVENT_TYPE);
		
		// enemies
		Enemy enemy1 = addEnemy("enemy1", "ghost.png", "staticX", player2);
		enemy1.setPosition(500,500);
		enemy1.addEventListener(this, GameOverEvent.EVENT_TYPE);
		
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		physicsMan.update();
//		particleMan.update();
	}

	private Wall addWall(double x, double y, double width, double height) {
		Wall p = new Wall(x, y, width, height, this);
		physicsMan.addObject(p);
		return p;
	}
	
	private Ground addGround(double x, double y, double width, double height) {
		Ground g = new Ground(x, y, width, height, this);
		physicsMan.addTrigger(g);
		return g;
	}
	
	private Enemy addEnemy(String id, String fileName, String type, Player player){
		Enemy e = new Enemy(id, fileName, type, this, player);
		physicsMan.addObject(e);
		return e;
	}
	
	@Override
	public void handleEvent(Event event) {
		if(event.getType().equals(SwitchEvent.EVENT_TYPE)){
			DisplayObject obj = ((DisplayObject)event.getSource());
			if (obj.getId().equals("switch1")) {
				TrapDoor trap = new TrapDoor(900, 500, this);
				physicsMan.addTrigger(trap);
			}
			else if (obj.getId().equals("switch2")) {
				addGround(250, 375, 100, 100);
			}
		}
		if(event.getType().equals(GameOverEvent.EVENT_TYPE)){
			Sprite gameover = new Sprite("gameover", "gameover.png");
			gameover.setPosition(500, 400);
			gameover.setzOrder(1);
			this.addChildConcurrent(gameover);
		}
		if(event.getType().equals(WinEvent.EVENT_TYPE)){
			Sprite win = new Sprite("win", "win.png");
			win.setPosition(500, 400);
			win.setzOrder(1);
			this.addChildConcurrent(win);
		}
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
	
}
