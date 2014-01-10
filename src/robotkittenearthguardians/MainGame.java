package robotkittenearthguardians;
/*
 * A game where you take the place of a Robotic Kitten Earth Guardian
 * and your job is to protect earth from the (insert evil character).
 * Most of earth has been destroyed and it is up to you to uphold what
 * remains. Get cat treats to purchase new upgrades that will help you
 * face the hoards of (insert evil character).
 */

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import robotkittenearthguardians.input.Mouse;
import robotkittenearthguardians.gameState.GameState;
import robotkittenearthguardians.gameState.StartScreenState;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.input.Keyboard;

public class MainGame extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	//JFrame variables
	private static int screenWidth = 1152;
	private static int screenHeight = 648;
	private static int screenScale = 1;
	private static String title = "Robot Kitten Guardians";
	private boolean running = false;	//True: Game loops is running; False: Game loop is not running
	private Thread thread;
	private JFrame frame;
	private Screen screen;
	private Keyboard key;
	private Mouse mouse;
	public static GameState currentState;
	
	//Each pixel of the screen is put into the int[] pixels. They are transfered to BufferedImage image
	//To be drawn to the screen.
	private BufferedImage image = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	/**
	 * Sets the dimensions and prefferedSize of the JFrame according to getScreenWidth()
	 * and getScreenHeight().
	 */
	public MainGame() {
		Dimension screenSize = new Dimension(screenWidth * screenScale, screenHeight * screenScale);
		setPreferredSize(screenSize);
		screen = new Screen(screenWidth, screenHeight, pixels, image);
		frame = new JFrame();
		key = new Keyboard();
		//Revieve data from the mouse, movement and buttons
		mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addKeyListener(key);
		currentState = new StartScreenState(key);
	}
	
	/**
	 * Returns the int width of the screen.
	 * @return int screenWidth
	 */
	public static int getScreenWidth() {
		return screenWidth;
	}
	
	/**
	 * Returns the int height of the screen.
	 * @return int screenHeight
	 */
	public static int getScreenHeight() {
		return screenHeight;
	}
	
	/**
	 * Returns the int scale of the screen.
	 * @return int screenScale
	 */
	public static int getScreenScale() {
		return screenScale;
	}
	
	/**
	 * Ran in main method after JFrame setup. Sets running boolean to true
	 * starting the loop to update and render the game in the run method.
	 * Creates a new MainGame thread and starts it thus executing run method.
	 */
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	/**
	 * Stops the game when executed. Sets running boolean to false stopping
	 * the update render loop and joins the thread to end it.
	 */
	public synchronized void stop() {
		System.exit(0);
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ran when MainGame thread is started. Loops the update and render
	 * methods while running boolean is equal to true. Update is ran at
	 * 60 ups, while render is ran at max fps.
	 */
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double NS = 60.0 / 1000000000.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		requestFocus();	
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) * NS;
			lastTime = now;
			
			while(delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}
	
	/**
	 * In charge of updating the game's logic.
	 */
	public void update() {
		key.update();
		currentState.update();
		if(key.escape) {
			stop();
		}
	}
	
	/**
	 * In charge of rendering and drawing to the JFrame.
	 */
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		currentState.render(screen, bs);
	}
	
	public static void main(String[] args) {
		MainGame game = new MainGame();
		
		//JFrame boiler plate
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
}
