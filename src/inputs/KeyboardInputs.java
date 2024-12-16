package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;

import static Utilz.Sprites.Directions.*;
import main.GamePanel;

public class KeyboardInputs implements KeyListener {
	private GamePanel gamepanel;
	public KeyboardInputs(GamePanel gamepanel) {
		this.gamepanel=gamepanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	} 

	@Override
	public void keyPressed(KeyEvent e) {
		switch(Gamestate.state) {
		case MENU:
			gamepanel.getGame().getMenu().keyPressed(e);
			break;
		case PLAYING:
			gamepanel.getGame().getPlaying().keyPressed(e);
			break;
		case GAME_OVER:
			gamepanel.getGame().getDeathScreen().keyPressed(e);
			break;
		default:
			break;
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
			
		}
}


