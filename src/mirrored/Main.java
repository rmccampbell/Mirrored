package mirrored;

import java.util.Arrays;
import java.util.List;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.sound.SoundManager;

public class Main extends Game {
	
	public static final int gameWidth = 1000;
	public static final int gameHeight = 800;
	
//	private PhysicsManager physicsMan = new PhysicsManager();
	private List<Class<? extends Level>> levels;
	private Level level;
	static SoundManager mySoundManager = new SoundManager();

	private static Main instance;

	public Main() {
		// Added window border padding
		super("Prototype", gameWidth + 6, gameHeight + 29);
		instance = this;

		levels = Arrays.asList(Level1.class, Level2.class, Level3.class);
		level = new Level1();
		
		addChild(level);

		mySoundManager.loadSound("BGmusic", "gameMusic.wav");
		mySoundManager.playSound("BGmusic", true);
	}
	
	public Level getLevel() {
		return level;
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
		System.out.println("next level: " + ind);
		setLevel(ind + 1);
	}
	
	public void resetLevel() {
		setLevel(level.getClass());
	}
	
//	public PhysicsManager getPhysicsManager() {
//		return level.getPhysicsManager();
//	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
}
