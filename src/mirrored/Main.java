package mirrored;

import java.util.Arrays;
import java.util.List;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.physics.PhysicsManager;

public class Main extends Game {
	
	static final int gameWidth = 1000;
	static final int gameHeight = 800;
	
	List<Class<? extends Level>> levels;
	Level level;

	PhysicsManager physicsMan;
	public Main() {
		super("Prototype", gameWidth, gameHeight);

		levels = Arrays.asList(Level1.class, Level1.class);
		level = new Level1();
		
		addChild(level);
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void nextLevel() {
		int ind = levels.indexOf(level.getClass());
		Class<? extends Level> levelClass = levels.get(ind);
		try {
			setLevel(levelClass.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
}
