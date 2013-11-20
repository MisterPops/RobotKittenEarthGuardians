package robotkittenearthguardians;
/*
 * A game where you take the place of a Robotic Kitten Earth Guardian
 * and your job is to protect earth from the (insert evil character).
 * Most of earth has been destroyed and it is up to you to uphold what
 * remains. Get cat treats to purchase new upgrades that will help you
 * face the hoards of (insert evil character).
 */

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import robotkittenearthguardians.input.Mouse;
import robotkittenearthguardians.entity.Entity;
import robotkittenearthguardians.entity.mob.Player;
import robotkittenearthguardians.graphics.Camera;
import robotkittenearthguardians.graphics.Screen;
import robotkittenearthguardians.input.Keyboard;
import robotkittenearthguardians.level.GameMaster;
import robotkittenearthguardians.level.Level;

public class MainGame extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	//JFrame variables
	private static int screenWidth = 960;
	private static int screenHeight = 540;
	private static int screenScale = 1;
	private static String title = "Robot Kitten Guardians";
	private boolean running = false;	//True: Game loops is running; False: Game loop is not running

	private Thread thread;
	private JFrame frame;
	private Screen screen;
	private Keyboard key;
	private Mouse mouse;
	private Level level;
	private Player player;
	private Camera camera;
	
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
		screen = new Screen(screenWidth, screenHeight);
		frame = new JFrame();
		key = new Keyboard();
		//Revieve data from the mouse, movement and buttons
		mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addKeyListener(key);
		level = new Level();
		player = new Player(850, 430, key);
		camera = new Camera(screenWidth, screenHeight);
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
	 * Returns the String title of the game.
	 * @return String title
	 */
	public String getTitle() {
		return title;
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
		level.update();
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
		
		Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();
		screen.clear();
		
		//(- screenWidth/Height / 2; sets player in center of screen)
		//Changed location of the map to center player
		//int xScroll = player.getXCoord() - screenWidth / 2; //To change the camera posistion -/+ from player coordinates
		//int yScroll = player.getYCoord() - screenHeight / 2;
		camera.render(screen, player);
		level.render(screen);
		
		//Sets Screen's pixel array to the main pixels array to be drawn
		//by drawImage
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.getPixels(i);
		}
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, screenWidth, screenHeight);
		//g2.setComposite(AlphaComposite.Clear);
		//g2.setComposite(AlphaComposite.Src);
		//g2.setComposite(AlphaComposite.SrcOver.derive(1f));
		g2.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Verdana", 0, 20));
		g2.drawString("Player Coords: X: " + player.getXCoord() + ", Y: " + player.getYCoord(), 30, 30);
		g2.drawString("Score: " + GameMaster.getScore(), 800, 30);
		g2.drawString("Mouse Coords: X: " + Mouse.getMouseX() + ", Y: " + Mouse.getMouseY(), 30, 55);
		g2.drawString("Mouse Angle: " + Mouse.mouseRadToDeg(), 30, 80);
		g2.drawString("Mouse Button: " + Mouse.getMouseB(), 30, 105);
		g2.drawString("Projectile ArrayList: " + Entity.getProjectilesSize(), 30, 130);
		g2.drawString("Particle ArrayList: " + Entity.getParticleSize(), 30, 155);
		g2.drawString("Mob ArrayList: " + Entity.getMobSize(), 30, 180);
		g2.drawString("Player ArrayList: " + Entity.getPlayerSize(), 30, 205);
		g2.dispose();
		/*Graphics graphics = bs.getDrawGraphics();
		/*graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, screenWidth, screenHeight);
		graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Verdana", 0, 20));
		graphics.drawString("Player Coords: X: " + player.getXCoord() + ", Y: " + player.getYCoord(), 30, 30);
		graphics.drawString("Score: " + GameMaster.getScore(), 800, 30);
		graphics.drawString("Mouse Coords: X: " + Mouse.getMouseX() + ", Y: " + Mouse.getMouseY(), 30, 55);
		graphics.drawString("Mouse Angle: " + Mouse.mouseRadToDeg(), 30, 80);
		graphics.drawString("Mouse Button: " + Mouse.getMouseB(), 30, 105);
		graphics.drawString("Projectile ArrayList: " + Entity.getProjectilesSize(), 30, 130);
		graphics.drawString("Particle ArrayList: " + Entity.getParticleSize(), 30, 155);
		graphics.drawString("Mob ArrayList: " + Entity.getMobSize(), 30, 180);
		graphics.drawString("Player ArrayList: " + Entity.getPlayerSize(), 30, 205);
		graphics.dispose();*/
		bs.show();
	}
	
	public static void main(String[] args) {
		MainGame game = new MainGame();
		
		//JFrame boiler plate
		game.frame.setResizable(false);
		game.frame.setTitle(game.getTitle());
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
}
