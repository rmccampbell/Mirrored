package mirrored;

import java.util.ArrayList;

import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.tweening.Tween;
import edu.virginia.engine.tweening.TweenManager;
import edu.virginia.engine.tweening.TweenTransitions;
import edu.virginia.engine.tweening.TweenableParams;

// Start Screen

public class Level0 extends Level implements IEventListener {

	//static TweenManager myTweenManager = new TweenManager();
	double time;
	
	public Level0() {
		super("startScreen", "StartScreen.png");
		
		Sprite begin = new Sprite("begin", "begin.png");
		this.addChild(begin);
		begin.setPivotPoint(0,0);
		begin.setPosition(500,380);
		/*
		Tween beginTween = new Tween(begin, 100, TweenTransitions.FLASH);
		beginTween.animate(TweenableParams.ALPHA, 1.0, 0.0);
		myTweenManager.add(beginTween);
		*/
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		if(!pressedKeys.isEmpty()) Main.getInstance().nextLevel();
		//myTweenManager.update(5);
	}

	@Override
	public void handleEvent(Event event) {
	}
}
