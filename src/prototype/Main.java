package prototype;

import java.util.ArrayList;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.particles.ParticleManager;
import edu.virginia.engine.particles.ParticleSystem;
import edu.virginia.engine.physics.PhysicsManager;

public class Main extends Game {
	
	PhysicsManager physicsMan;
	ParticleManager particleMan;
	static int gameWidth = 800;
	static int gameHeight = 600;

	public Main() {
		super("Prototype", gameWidth, gameHeight);
		physicsMan = new PhysicsManager();
		Player player1 = new Player(false, (0.25) * gameWidth, 200, this);
		Player player2 = new Player(true, (0.75) * gameWidth, 200, this);
		player1.setOtherPlayer(player2);
		player2.setOtherPlayer(player1);
		physicsMan.addObject(player1);
		physicsMan.addObject(player2); 
		
		// level
		addPlatform(gameWidth/2, gameHeight/2, 20, gameHeight);
		addPlatform(0, gameHeight/2, 20, gameHeight);
		//addPlatform(gameWidth, gameHeight)
		
		addPlatform(100, 100, 100, 100);
		
		// particles
//		particleMan = new ParticleManager();
//		ParticleSystem test = new ParticleSystem("test");
//		test.create(10);
//		this.addChild(test);
//		particleMan.add(test);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		physicsMan.update();
//		particleMan.update();
	}

	private Platform addPlatform(double x, double y, double width, double height) {
		Platform p = new Platform(x, y, width, height, this);
		physicsMan.addObject(p);
		return p;
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

}
