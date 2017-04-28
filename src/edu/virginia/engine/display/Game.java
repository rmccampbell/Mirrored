package edu.virginia.engine.display;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;


/**
 * Highest level class for creating a game in Java.
 * 
 * */
public class Game extends DisplayObjectContainer implements ActionListener, KeyListener, MouseListener {

	/* Frames per second this game runs at */
	private int FRAMES_PER_SEC = 45;

	/* The main JFrame that holds this game */
	private JFrame mainFrame;

	/* Timer that this game runs on */
	private Timer gameTimer;
	
	/* The JPanel for this game */
	private GameScenePanel scenePanel;
	
	private double deltaTime;
	private double lastTime;

	public Game(String gameId, int width, int height) {
		super(gameId);
		
		setUpMainFrame(gameId, width, height);
		
		setScenePanel(new GameScenePanel(this));
		
		/* Use an absolute layout */
		scenePanel.setLayout(null);
	}
	
	public double getDeltaTime() {
		return deltaTime;
	}
	
	public void setFramesPerSecond(int fps){
		if(fps > 0) this.FRAMES_PER_SEC = fps;
	}

	public void setUpMainFrame(String gameId, int width, int height) {
		this.mainFrame = new JFrame();
		getMainFrame().setTitle(gameId);
		getMainFrame().setResizable(false);
		getMainFrame().setVisible(true);
		getMainFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getMainFrame().setBounds(0, 0, width, height);
		getMainFrame().addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		getMainFrame().addKeyListener(this);
		getMainFrame().getContentPane().addMouseListener(this);
	}

	/**
	 * Starts the game
	 */
	public void start() {
		lastTime = System.currentTimeMillis() / 1000.0;
		if (gameTimer == null) {
			gameTimer = new Timer(1000 / FRAMES_PER_SEC, this);
		}
		gameTimer.start();
	}

	/**
	 * Stops the animation.
	 */
	public void stop() {
		pause();
		gameTimer = null;
	}

	public void pause() {
		if (gameTimer != null) {
			gameTimer.stop();
		}
	}
	
	/**
	 * Added to ensure game is not updated before started
	 */
	public boolean isRunning() {
		return gameTimer != null && gameTimer.isRunning();
	}
	
	/**
	 * Close the window
	 * */
	public void closeGame(){
		this.stop();
		if(this.getMainFrame() != null){
			this.getMainFrame().setVisible(false);
			this.getMainFrame().dispose();
		}
	}


	/**
	 * Called once per frame. updates the game, redraws the screen, etc. May
	 * need to optimize this if games get too slow.
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		repaintGame();
	}
	
	/**
	 * Forces a repaint
	 * */
	public void repaint(){repaintGame();}
	public void repaintGame(){
		if(getScenePanel() != null){
			getScenePanel().validate();
			getScenePanel().repaint();
		}
	}

	protected void nextFrame(Graphics g) {
		if (!isRunning())
			return;
		try {
			/* Update all objects on the stage */
			this.update(pressedKeys);

			/* Draw everything on the screen */
			this.draw(g);
		} catch (Exception e) {
			System.out.println("Exception in nextFrame of game. Stopping game (no frames will be drawn anymore)");
			stop();
			e.printStackTrace();
		}
	}

	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		double currTime = System.currentTimeMillis() / 1000.0;
		deltaTime = currTime - lastTime;
		lastTime = currTime;
		super.update(pressedKeys);
	}

	@Override
	public void draw(Graphics g){
		/* Start with no transparency */
		((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				1.0f));
		
		super.draw(g);
	}

	public JFrame getMainFrame() {
		return this.mainFrame;
	}
	
	public void setScenePanel(GameScenePanel scenePanel) {
		this.scenePanel = scenePanel;
		this.getMainFrame().add(this.scenePanel);
		getMainFrame().setFocusable(true);
		getMainFrame().requestFocusInWindow();
	}

	public GameScenePanel getScenePanel() {
		return scenePanel;
	}
	
	public void click(int x, int y) {
		
	}

	ArrayList<Integer> pressedKeys = new ArrayList<>();
	@Override
	public void keyPressed(KeyEvent e) {
		if(!pressedKeys.contains(e.getKeyCode()))
			pressedKeys.add(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressedKeys.remove(Integer.valueOf(e.getKeyCode()));
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		click(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
