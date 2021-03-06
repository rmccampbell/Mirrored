package mirrored;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.physics.PhysicsManager;
import edu.virginia.engine.tweening.Tween;
import edu.virginia.engine.tweening.TweenTransitions;
import edu.virginia.engine.tweening.TweenableParams;

import java.util.ArrayList;

/**
 * Created by Laith on 4/28/17.
 */
public class Level4 extends Level implements IEventListener {

    Door door1, door2;

    public Level4() {
        super("Level 4", "lava_tiles.png");
        physicsManager = new PhysicsManager();

        Sprite level4Intro = new Sprite("level4Intro", "level04.png");
        this.addChild(level4Intro);
        level4Intro.setPivotPoint(0,0);
        level4Intro.setPosition(300,200);
        level4Intro.setzOrder(1);

        Tween level4Tween = new Tween(level4Intro, 200, TweenTransitions.EASE_OUT);
        level4Tween.animate(TweenableParams.ALPHA, 1.0, 0.0);
        Main.getInstance().getTweenManager().add(level4Tween);

        door1 = new Door(250, 100, this);
        door1.addEventListener(this, Events.DOOR);
        door2 = new Door(750, 100, this);

        //upper bridge
        new Ground(650, 0, 200, 200, this);
        new Ground(150, 0, 200, 200, this);

        //lower bridge
        new Ground(650, gameHeight-200, 200, 200, this);
        new Ground(150, gameHeight-200, 200, 200, this);

        //bottom horizontal
        new Ground(550, gameHeight-275, 400, 75, this);
        new Ground(50, gameHeight-275, 400, 75, this);

        //left bridge
        new Ground(550, gameHeight-600, 100, 325, this);
        new Ground(50, gameHeight-600, 100, 325, this);

        //right bridge
        new Ground(850, gameHeight-600, 100, 325, this);
        new Ground(350, gameHeight-600, 100, 325, this);

        //top horizontal
        new Ground(650, gameHeight-600, 200, 75, this);
        new Ground(150, gameHeight-600, 200, 75, this);

        //middle
        new Ground(650, gameHeight-440, 200, 75, this);
        new Ground(150, gameHeight-440, 200, 75, this);

        // boundaries
        new Wall(gameWidth / 2 - 10, 0, 20, gameHeight, this);
        new Wall(0, 0, 20, gameHeight, this);
        new Wall(gameWidth - 20, 0, 20, gameHeight, this);
        new Wall(0, 0, gameWidth, 20, this);
        new Wall(0, gameHeight - 20, gameWidth, 20, this);

        //players
        Player player1 = new Player(false, 250, 700, this);
        Player player2 = new Player(true, 750, 700, this);
        player1.setOtherPlayer(player2);
        player2.setOtherPlayer(player1);
        player1.addEventListener(this, Events.DEATH);
        player2.addEventListener(this, Events.DEATH);

        //game walls
        new Wall(50, gameHeight-600, 100, 20, this);
        new Wall(50, gameHeight-200, 100, 20, this);
        new Wall(550, gameHeight-600, 100, 20, this);
        new Wall(550, gameHeight-200, 100, 20, this);
        new Wall(50, gameHeight-580, 20, 380, this);
        new Wall(550, gameHeight-580, 20, 380, this);

        new Wall(350, gameHeight-600, 100, 20, this);
        new Wall(350, gameHeight-200, 100, 20, this);
        new Wall(850, gameHeight-600, 100, 20, this);
        new Wall(850, gameHeight-200, 100, 20, this);
        new Wall(430, gameHeight-580, 20, 380, this);
        new Wall(930, gameHeight-580, 20, 380, this);


        //enemies
        Enemy enemy1 = new Enemy("enemy1", "ghostSheet.png", EnemyType.staticY, player1, this);
        enemy1.setPosition(100, gameHeight-580);

        Enemy enemy2 = new Enemy("enemy2", "ghostSheet.png", EnemyType.staticY, player2, this);
        enemy2.setPosition(900, gameHeight-200);

        Enemy enemy3 = new Enemy("enemy3", "ghostSheet.png", EnemyType.staticY, player1, this);
        enemy3.setPosition(400, gameHeight-200);

        Enemy enemy4 = new Enemy("enemy4", "ghostSheet.png", EnemyType.staticY, player2, this);
        enemy4.setPosition(600, gameHeight-580);

        Enemy enemy5 = new Enemy("enemy5", "ghostSheet.png", EnemyType.staticX, player1, this);
        enemy5.setPosition(130, gameHeight-580);

        Enemy enemy6 = new Enemy("enemy6", "ghostSheet.png", EnemyType.staticX, player2, this);
        enemy6.setPosition(630, gameHeight-225);

        //buttons
        Button button1 = new Button("button1", 250, gameHeight-410, this);
        button1.addEventListener(this, Events.BUTTON_ON);
        Button button2 = new Button("button2", 750, gameHeight-230, this);
        button2.addEventListener(this, Events.BUTTON_ON);



//        Enemy enemy4 = new Enemy("enemy4", "ghostSheet.png", EnemyType.homing, player2, this);
//        enemy4.setPosition(550, 400);
//        Enemy enemy1 = new Enemy("enemy1", "ghostSheet.png", EnemyType.homing, player1, this);
//        enemy1.setPosition(250, 400);

        // sound effects
        Main.getInstance().getSoundManager().loadSound("arrowSound", "arrowSound.wav");

    }

    @Override
    public void update(ArrayList<Integer> pressedKeys) {
        super.update(pressedKeys);
        physicsManager.update();
        Main.getInstance().getTweenManager().update(1);
    }

    public void handleEvent(Event event) {
        if (event.getType().equals(Events.DEATH)) {
            Main.getInstance().resetLevel();
        }
        if (event.getType().equals(Events.DOOR)) {
            Main.getInstance().nextLevel();
        }

        if (event.getType().equals(Events.BUTTON_ON)) {
            DisplayObject obj = ((DisplayObject) event.getSource());
            if (obj.getId().equals("button1")) {
                new Arrow(930, gameHeight-570, -8, 0, this);
                new Arrow(430, gameHeight-570, -8, 0, this);
                Main.getInstance().getSoundManager().playSound("arrowSound");
            }
            if (obj.getId().equals("button2")) {
                new Arrow(100, gameHeight-410, 8, 0, this);
                new Arrow(600, gameHeight-410, 8, 0, this);
                Main.getInstance().getSoundManager().playSound("arrowSound");
            }
        }

    }

}
