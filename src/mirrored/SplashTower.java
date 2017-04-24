package mirrored;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Laith on 4/23/17.
 */
public class SplashTower extends Character {

    public SplashTower(String id) {
        super(id);
    }

    public SplashTower(String id, String fileName, int rows, int cols) {
        super(id, fileName, rows, cols);
    }

    @Override
    public void update(ArrayList<Integer> pressedKeys) {
        super.update(pressedKeys);
    }

    //call this when the tower is triggered and pass the result to the getcollision method in physicsmanager so
    //all the necessary characters can get killed
    protected Rectangle2D getAttackBox(double attackRadius){
        Rectangle2D attack = new Rectangle2D.Double();
        attack = new Rectangle2D.Double(this.getX(), this.getY(), 2*attackRadius, 2*attackRadius );
        return attack;
    }

}
