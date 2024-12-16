package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import static main.Game.*;
import javax.swing.JPanel;

import inputs.*;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private MouseInputs mouseinputs;
	private Game game;

	public GamePanel(Game game) {
		mouseinputs = new MouseInputs(this);
		this.game = game;
		PanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseinputs);
		addMouseMotionListener(mouseinputs);
	} 

	public void PanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
	}

	public void updateGame() {
		
	}

	public Game getGame() {
		return game;
	}

}
