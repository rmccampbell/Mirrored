package edu.virginia.engine.tweening;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

import edu.virginia.engine.display.DisplayObject;

public class TweenManager {
	
	private HashSet<Tween> tweens = new HashSet<>();
	private ArrayDeque<Tween> queuedTweens = new ArrayDeque<>();
	
	public TweenManager() {
	}
	
	public void update(double deltaTime) {
		if (tweens.isEmpty() && !queuedTweens.isEmpty())
			tweens.add(queuedTweens.removeFirst());
		for (Tween tween : new ArrayList<>(tweens)) {
			tween.update(deltaTime);
			if (tween.isComplete())
				tweens.remove(tween);
		}
	}
	
	public void add(Tween tween) {
		tweens.add(tween);
	}
	
	public Tween addTween(DisplayObject object, double duration) {
		Tween tween = new Tween(object, duration);
		add(tween);
		return tween;
	}
	
	public Tween addTween(DisplayObject object, double duration, TweenTransitions transition) {
		Tween tween = new Tween(object, duration, transition);
		add(tween);
		return tween;
	}
	
	public void queue(Tween tween) {
		queuedTweens.addLast(tween);
	}

	public Tween queueTween(DisplayObject object, double duration) {
		Tween tween = new Tween(object, duration);
		queue(tween);
		return tween;
	}	

	public Tween queueTween(DisplayObject object, double duration, TweenTransitions transition) {
		Tween tween = new Tween(object, duration, transition);
		queue(tween);
		return tween;
	}	
}
