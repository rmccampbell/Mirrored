package prototype;

import java.util.ArrayList;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.particles.ParticleManager;
import edu.virginia.engine.physics.PhysicsManager;

public class Main extends Game {
	
	PhysicsManager physicsMan;
	ParticleManager particleMan;
	static int gameWidth = 1000;
	static int gameHeight = 800;

	public Main() {
		super("Prototype", gameWidth, gameHeight);
		physicsMan = new PhysicsManager();
		
		// level
		
		addGround(gameWidth/2, gameHeight/2, 800, 600);
		
		addWall(gameWidth/2, gameHeight/2, 20, gameHeight);
		addWall(0, gameHeight/2, 20, gameHeight);
		addWall(gameWidth-5, gameHeight/2, 20, gameHeight);
		addWall(gameWidth/2, 0, gameWidth, 20);
		addWall(gameWidth/2, gameHeight-35, gameWidth, 20);
		
		addWall(100, 100, 100, 100);
		
		// players
		Player player1 = new Player(false, (0.25) * gameWidth, 200, this);
		Player player2 = new Player(true, (0.75) * gameWidth, 200, this);
		player1.setOtherPlayer(player2);
		player2.setOtherPlayer(player1);
		physicsMan.addObject(player1);
		physicsMan.addObject(player2); 

		// particles
//		particleMan = new ParticleManager();
//		ParticleSystem test = new ParticleSystem("test");
//		test.create(10);
//		this.addChild(test);
//		particleMan.add(test);
		
		// enemies
		Enemy e = addEnemy("e", "coin.png", "", player1);
		e.setPosition(100,300);
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
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

}
