package prototype;

import java.util.ArrayList;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.physics.PhysicsManager;

public class Main extends Game {
	
	PhysicsManager pm;

	public Main() {
		super("Prototype", 600, 400);
		pm = new PhysicsManager();
		Player player1 = new Player(false, 150, 200, this);
		Player player2 = new Player(true, 450, 200, this);
		player1.setOtherPlayer(player2);
		player2.setOtherPlayer(player1);
		pm.addObject(player1);
		pm.addObject(player2);
		addPlatform(100, 100, 100, 100);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		pm.update();
	}

	private Platform addPlatform(double x, double y, double width, double height) {
		Platform p = new Platform(x, y, width, height, this);
		pm.addObject(p);
		return p;
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

}
