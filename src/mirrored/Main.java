package mirrored;

import java.util.Arrays;
import java.util.List;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.physics.PhysicsManager;

public class Main extends Game {
	
	static final int gameWidth = 1200;
	static final int gameHeight = 800;
	
	List<Class<? extends Level>> levels;
	Level level;

	PhysicsManager physicsMan;
	public Main() {
		super("Prototype", gameWidth, gameHeight);

		levels = Arrays.asList(Level1.class);
		level = new Level1();
		
		addChild(level);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
}
