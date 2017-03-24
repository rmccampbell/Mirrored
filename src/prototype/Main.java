package prototype;

import java.util.ArrayList;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.physics.SimplePhysicsManager;

public class Main extends Game {
	
	SimplePhysicsManager pm;

	public Main() {
		super("Prototype", 600, 400);
		Player player1 = new Player(false, 150, 200, this);
		Player player2 = new Player(true, 450, 200, this);
		pm = new SimplePhysicsManager();
		pm.addObject(player1);
		pm.addObject(player2);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		pm.update();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

}
