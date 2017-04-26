package mirrored;

public class Ground extends TexturedSprite {

	public Ground(double x, double y, double width, double height, Level level) {
		super("ground", "floor_tiles.png", x, y, width, height);
		level.addChild(this);
		level.getPhysicsManager().addTrigger(this);
		setzOrder(-1);
	}
}
