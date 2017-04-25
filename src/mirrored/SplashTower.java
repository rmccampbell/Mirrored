package mirrored;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.physics.PhysicsObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Laith on 4/23/17.
 */
public class SplashTower extends Character {


    public SplashTower(String id, double x, double y, DisplayObjectContainer parent) {
        super(id, "splash_tower.png", 1 , 1);
        parent.addChild(this);
        setPosition(x, y);
        setAttackRadius(60);

        addAnimation("tower", 0, 1);
        setAnimation("tower");

        addPhysics(PhysicsObject.STATIC, 0);
        Main.getInstance().getPhysicsManager().addObject(this);
    }

    @Override
    public void update(ArrayList<Integer> pressedKeys) {
        super.update(pressedKeys);
    }

    @Override
    public void draw(Graphics g){
        super.draw(g);
        Rectangle2D rect = getAttackBox();
        g.drawRect((int)rect.getX(), (int)rect.getY(), (int)rect.getHeight(), (int)rect.getWidth());

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
