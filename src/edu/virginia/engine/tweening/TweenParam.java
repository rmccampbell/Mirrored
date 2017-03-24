package edu.virginia.engine.tweening;

public class TweenParam {
	
	private TweenableParams param;
	private double startVal, endVal;
	
	public TweenParam(TweenableParams param, double startVal, double endVal) {
		this.param = param;
		this.startVal = startVal;
		this.endVal = endVal;
	}
	
	public TweenableParams getParam() {
		return param;
	}
	
	public double getStartVal() {
		return startVal;
	}
	
	public double getEndVal() {
		return endVal;
	}

	public double lerp(double value) {
		return startVal + value * (endVal - startVal);
	}

}
