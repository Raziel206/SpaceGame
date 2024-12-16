package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Entities.Enemies;

import static Utilz.Sprites.UI.URMButtons.URM_SIZE;
import Utilz.LoadSave;
import main.Game;
import ui.UrmButton;

public class DeathScreen {
	private Playing playing;
	private BufferedImage img;
	private int imgX, imgY, imgW, imgH;
	private UrmButton menu, play;

	public DeathScreen(Playing playing) {
		this.playing = playing;
		createImg();
		createButtons();
		System.out.println("Your score: "+Enemies.SCORE);
	}

	private void createImg() {
		img = LoadSave.GetSpriteAtlas(LoadSave.DEATH_BACKGROUND);
		imgW = (int) (img.getWidth() * Game.SCALE);
		imgH = (int) (img.getHeight() * Game.SCALE);
		imgX = Game.GAME_WIDTH / 2 - imgW / 2;
		imgY = (int) (100 * Game.SCALE);
	}

	private void createButtons() {
		int menuX = (int) (335 * Game.SCALE);
		int playX = (int) (440 * Game.SCALE);
		int y = (int) (195 * Game.SCALE);
		play = new UrmButton(playX+30, y, URM_SIZE, URM_SIZE, 0);
		menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);

	}

	public void draw(Graphics g) { 
		g.setColor(new Color(12, 22, 79));
		g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        g.setFont(new Font("Arial",Font.BOLD,19));
		g.drawImage(img, imgX, imgY, imgW, imgH, null);

		menu.draw(g); 
		play.draw(g);
		g.setColor(Color.BLACK);
		g.drawString("SCORE :"+Enemies.SCORE, imgX+122, imgY+220);
	}

	public void update() {
		menu.update();
		play.update();
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			playing.resetAll();
			Gamestate.state = Gamestate.MENU;
		}
	}

	private boolean isIn(UrmButton b, MouseEvent e) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

	public void mouseMoved(MouseEvent e) {
		play.setMouseOver(false);
		menu.setMouseOver(false);

		if (isIn(menu, e))
			menu.setMouseOver(true);
		else if (isIn(play, e))
			play.setMouseOver(true);
	}

	public void mouseReleased(MouseEvent e) {
		if (isIn(menu, e)) {
			if (menu.isMousePressed()) {
				playing.resetAll();
				Gamestate.state = Gamestate.MENU;
			}
		} else if (isIn(play, e))
			if (play.isMousePressed()) {
				playing.resetAll();
				Gamestate.state = Gamestate.PLAYING;
			}
		menu.resetBools();
		play.resetBools();
	}

	public void mousePressed(MouseEvent e) {
		if (isIn(menu, e))
			menu.setMousePressed(true);
		else if (isIn(play, e))
			play.setMousePressed(true);
	}

}
