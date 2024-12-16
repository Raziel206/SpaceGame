package main;

import java.awt.Graphics;

import gamestates.DeathScreen;
import gamestates.Gamestate;
import gamestates.Menue;
import gamestates.Playing;

public class Game implements Runnable {
	private GameWindow window;
	private GamePanel panel;
	private Thread GameThread;
	private final int FPS_Set = 120;
	private final int UPS_Set = 200;
	
	private Playing playing;
	private Menue menu;
	private DeathScreen deathScreen;

	public static final int TILES_DEFAULT_SIZE = 32;
	public static final float SCALE = 1.5f;
	public static final int TILES_IN_WIDTH = 26;
	public static final int TILES_IN_HEIGHT = 14;
	public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public static final int GAME_WIDTH = 1280;
	public static final int GAME_HEIGHT = 720;

	public Game() {
		initClasses();
		panel = new GamePanel(this);
		window = new GameWindow(panel);
		panel.requestFocus();
		GameStart();
	}

	private void initClasses() {
		menu=new Menue(this);
		playing=new Playing (this);
		deathScreen=new DeathScreen(playing);
	}

	public void GameStart() {
		GameThread = new Thread(this);
		GameThread.start();
	}

	public void update() {
		switch (Gamestate.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:
			playing.update();
			break;
		case GAME_OVER:
			deathScreen.update();
			break;
		case OPTIONS:
		case QUIT:
		default:
			System.exit(0);
			break;

		}

	}

	public void render(Graphics g) {
		switch (Gamestate.state) {
		case MENU:
			menu.draw(g);
			break;
		case PLAYING:
			playing.draw(g);
			break;
		case GAME_OVER:
			deathScreen.draw(g);
			break;
		default:
			break;
		}
	}

	@Override
	public void run() {
		long last_check = System.currentTimeMillis();
		final double TimePerFrame = 1000000000.0 / FPS_Set;
		final double TimePerUpdate = 1000000000.0 / UPS_Set;
		long previousTime = System.nanoTime();
		int frames = 0;
		int updates = 0;
		double deltaU = 0;
		double deltaF = 0;
		while (true) {
			long currentTime = System.nanoTime();
			deltaU += (currentTime - previousTime) / TimePerUpdate;
			deltaF += (currentTime - previousTime) / TimePerFrame;
			previousTime = currentTime;
			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;

			}
			if (deltaF >= 1) {
				panel.repaint();
				frames++;
				deltaF--;
			}
			if (System.currentTimeMillis() - last_check >= 1000) {
				last_check = System.currentTimeMillis();
				System.out.println("FPS:" + frames + "|UPS:" + updates);
				frames = 0;
				updates = 0;
			}
			
		}

	}

	public void WindowFocusLost() {
		if(Gamestate.state==Gamestate.PLAYING) {
			playing.getPlayer().resetDirBoolean();
		}
	}
	
	public Menue getMenu() {
		return menu;
	}
	
	public Playing getPlaying() {
		return playing;
	}

	public DeathScreen getDeathScreen() {
		return deathScreen;
	}
}
