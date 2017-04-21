package mirrored;

import java.util.Arrays;
import java.util.List;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.physics.PhysicsManager;

public class Main extends Game {
	
	public static final int gameWidth = 1000;
	public static final int gameHeight = 800;
	
	private List<Class<? extends Level>> levels;
	private Level level;
	private PhysicsManager physicsMan;

	private static Main instance;

	public Main() {
		super("Prototype", gameWidth, gameHeight);
		instance = this;

		levels = Arrays.asList(Level1.class, Level1.class);
		level = new Level1();
		
		addChild(level);
	}
	
	public void setLevel(Level level) {
		removeChild(this.level);
		this.level = level;
		addChild(level);
	}
	
	private void setLevel(Class<? extends Level> levelClass) {
		try {
			setLevel(levelClass.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void setLevel(int level) {
		setLevel(levels.get(level));
	}
	
	public void nextLevel() {
		int ind = levels.indexOf(level.getClass());
		setLevel(ind + 1);
	}
	
	public void resetLevel() {
		setLevel(level.getClass());
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
}
