package mirrored;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Laith on 4/23/17.
 */
public class SplashTower extends Character {
	
	private boolean active = false;

    public SplashTower(String id, double x, double y, Level level) {
        super(id, "splash_tower2.png", 1, 2, level);
        setPosition(x, y);
        setBBox(0, 0, 50, 50);
        setAttackRadius(60);

        addAnimation("inactive", 0, 1);
        addAnimation("active", 1, 1);
        setAnimation("inactive");

        getPhysics().setStatic(true);
    }

    @Override
    public void update(ArrayList<Integer> pressedKeys) {
        super.update(pressedKeys);
        if (active)
        	setAnimation("active");
        else
        	setAnimation("inactive");
        active = false;
    }

//    @Override
//    public void draw(Graphics g){
//        super.draw(g);
//        Rectangle2D rect = getAttackBox();
//        g.drawRect((int)rect.getX(), (int)rect.getY(), (int)rect.getHeight(), (int)rect.getWidth());
//    }
    
    public void activate() {
    	attack();
    	active = true;
	}

    //call this when the tower is triggered and pass the result to the getcollision method in physicsmanager so
    //all the necessary characters can get killed
    @Override
    protected Rectangle2D getAttackBox() {
        Rectangle2D attack = new Rectangle2D.Double();
        attack = new Rectangle2D.Double(this.getX()-attackRadius, this.getY()-attackRadius, 2 * attackRadius, 2 * attackRadius);
        return attack;
    }

//    @Override
//    public void die() {
//        throw new RuntimeException();
//    }

}
