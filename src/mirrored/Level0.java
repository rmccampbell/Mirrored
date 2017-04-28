package mirrored;

import java.util.ArrayList;

import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

// Start Screen

public class Level0 extends Level implements IEventListener {

	public Level0() {
		super("startScreen", "StartScreen.png");
		this.setPivotPoint(0,0);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		if(!pressedKeys.isEmpty()) Main.getInstance().nextLevel();
	}

	@Override
	public void handleEvent(Event event) {
	}
}
