package edu.virginia.engine.tweening;

import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.events.EventDispatcher;

public class Tween extends EventDispatcher {
	private DisplayObject object;
	private TweenTransitions transition;
	private double duration;
	private double time = 0.0;
	
	private ArrayList<TweenParam> tweenParams = new ArrayList<>();

	public Tween(DisplayObject object, double duration) {
		this.object = object;
		this.duration = duration;
		this.transition = TweenTransitions.LINEAR;
	}

	public Tween(DisplayObject object, double duration, TweenTransitions transition) {
		this.object = object;
		this.duration = duration;
		this.transition = transition;
	}
	
	public Tween animate(TweenableParams param, double startVal, double endVal) {
		tweenParams.add(new TweenParam(param, startVal, endVal));
		return this;
	}

	public Tween animate(TweenableParams param, double endVal) {
		double startVal = getStartVal(param);
		return animate(param, startVal, endVal);
	}

	public void update(double deltaTime) {
		time += deltaTime;
		double percent = Math.min(time / duration, 1.0);
		double value = transition.applyTransition(percent);
		for (TweenParam tweenParam : tweenParams) {
			setValue(tweenParam.getParam(), tweenParam.lerp(value));
		}
		if (isComplete())
			dispatchEvent(new TweenEvent(TweenEvent.TWEEN_COMPLETE, this));
	}
	
	public double getDuration() {
		return duration;
	}
	
	public double getTime() {
		return time;
	}
	
	public DisplayObject getObject() {
		return object;
	}
	
	public TweenTransitions getTransition() {
		return transition;
	}
	
	public boolean isComplete() {
		return time >= duration;
	}
	
	private void setValue(TweenableParams param, double value) {
		switch (param) {
		case X:
			object.setX(value);
			break;
		case Y:
			object.setY(value);
			break;
		case SCALE:
			object.setScale(value);
			break;
		case SCALE_X:
			object.setScaleX(value);
			break;
		case SCALE_Y:
			object.setScaleY(value);
			break;
		case ROTATION:
			object.setRotation(value);
			break;
		case ALPHA:
			object.setAlpha((float) value);
			break;
		}
	}
	
	private double getStartVal(TweenableParams param) {
		switch (param) {
		case X:
			return object.getX();
		case Y:
			return object.getY();
		case SCALE:
			return object.getScaleX();
		case SCALE_X:
			return object.getScaleX();
		case SCALE_Y:
			return object.getScaleY();
		case ROTATION:
			return object.getRotation();
		case ALPHA:
			return object.getAlpha();
		default: // Unreachable
			return 0.0;
		}
	}
}
