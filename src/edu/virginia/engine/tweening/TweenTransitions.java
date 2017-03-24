package edu.virginia.engine.tweening;

public enum TweenTransitions {
	
	LINEAR, EASE_IN, EASE_OUT, EASE_IN_OUT, EASE_IN_BACK, EASE_OUT_BACK, EASE_IN_ELASTIC, EASE_OUT_ELASTIC;

	public double applyTransition(double percent) {
		switch (this) {
		case LINEAR:
			return percent;
		case EASE_IN:
			return easeIn(percent);
		case EASE_OUT:
			return easeOut(percent);
		case EASE_IN_OUT:
			return easeInOut(percent);
		case EASE_IN_BACK:
			return easeInBack(percent);
		case EASE_OUT_BACK:
			return easeOutBack(percent);
		case EASE_IN_ELASTIC:
			return easeInElastic(percent);
		case EASE_OUT_ELASTIC:
			return easeOutElastic(percent);
		default:
			return 0;
		}
	}

	private static double easeIn(double t) {
		return t*t*t;
	}

	private static double easeOut(double t) {
		return t*t*t - 3*t*t + 3*t;
	}

	private static double easeInOut(double t) {
		double t2 = t*t;
		double t3 = t2*t;
		return 6*t3*t2 - 15*t2*t2 + 10*t3;
	}

	private static double easeInBack(double t) {
		return 4*t*t*t - 3*t*t;
	}

	private static double easeOutBack(double t) {
		return 4*t*t*t - 9*t*t + 6*t;
	}
	
	private static double easeInElastic(double t) {
		double t2 = t*t;
		double t3 = t2*t;
		return 33*t3*t2 - 59*t2*t2 + 32*t3 - 5*t2;
	}
	
	private static double easeOutElastic(double t) {
		double t2 = t*t;
		double t3 = t2*t;
		return 33*t3*t2 - 106*t2*t2 + 126*t3 - 67*t2 + 15*t;
	}
}
