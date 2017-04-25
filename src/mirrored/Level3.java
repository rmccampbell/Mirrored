package mirrored;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

import java.util.ArrayList;

/**
 * Created by Laith on 4/24/17.
 */
public class Level3 extends Level implements IEventListener {

    public Level3() {
        super("Level 3");
        physicsManager = Main.getInstance().getPhysicsManager();
        physicsManager.clear();

        // level 3
        new Ground(0, 0, gameWidth, 300, this);
        new Ground(0, 300, gameWidth/2, gameHeight-300, this);
        new Ground(gameWidth/2, 400, gameWidth/2, gameHeight-400, this);

        // boundaries
        new Wall(gameWidth/2-10, 0, 20, gameHeight, this);
        new Wall(0, 0, 20, gameHeight, this);
        new Wall(gameWidth-20, 0, 20, gameHeight, this);
        new Wall(0, 0, gameWidth, 20, this);
        new Wall(0, gameHeight-20, gameWidth, 20, this);

        //game walls
        new Wall(100, gameHeight-200, 300, 20, this);

        // buttons
        Button button1 = new Button("button1", 50, 700, this);
        button1.addEventListener(this, Events.BUTTON_ON);
        button1.addEventListener(this, Events.BUTTON_OFF);
        Button button2 = new Button("button2", 450, 700, this);
        button2.addEventListener(this, Events.BUTTON_ON);
        button2.addEventListener(this, Events.BUTTON_OFF);

        // players
        Player player1 = new Player(false, 250, 700, this);
        Player player2 = new Player(true, 750, 700, this);
        player1.setOtherPlayer(player2);
        player2.setOtherPlayer(player1);

        player1.addEventListener(this, Events.DEATH);
        player2.addEventListener(this, Events.DEATH);

        player1.setzOrder(1);
        player2.setzOrder(1);

        // doors
        Door door1 = new Door(400, 100, this);
        door1.addEventListener(this, Events.DOOR);
        Door door2 = new Door(600, 100, this);
        door2.addEventListener(this, Events.DOOR);

        // enemies
		/*
		Enemy enemy1 = new Enemy("enemy1", "ghostSheet.png", EnemyType.homing, this, player2);
		enemy1.setPosition(500,500);

		Enemy enemy2 = new Enemy("enemy2", "ghostSheet.png", EnemyType.staticX, this, player1);
		enemy2.setPosition(480,200);
		 */
    }

    @Override
    public void update(ArrayList<Integer> pressedKeys) {
        super.update(pressedKeys);
        physicsManager.update();
    }

    @Override
    public void handleEvent(Event event) {
        if (event.getType().equals(Events.DEATH)) {
            Main.getInstance().resetLevel();
        }
        if (event.getType().equals(Events.DOOR)) {
            Main.getInstance().nextLevel();
        }
    }
}
