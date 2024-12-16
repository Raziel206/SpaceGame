package inputs;

import java.awt.event.MouseEvent;
import static Utilz.Sprites.LoadingSprites.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gamestates.Gamestate;
import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {
	private GamePanel gamepanel;

	public MouseInputs(GamePanel gamepanel) {
		this.gamepanel = gamepanel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		switch (Gamestate.state) {
		case PLAYING:
			gamepanel.getGame().getPlaying().mouseDragged(e);
			break;
		default:
			break;

		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		switch (Gamestate.state) {
		case PLAYING:
			gamepanel.getGame().getPlaying().mouseClicked(e);
			break;
		default:
			break;

		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (Gamestate.state) {
		case MENU:
			gamepanel.getGame().getMenu().mouseMoved(e);
			break;
		case PLAYING:
			gamepanel.getGame().getPlaying().mouseMoved(e);
			break;
		case GAME_OVER:
			gamepanel.getGame().getDeathScreen().mouseMoved(e);
			break;	
		default:
			break;
			
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		switch (Gamestate.state) {
		case MENU:
			gamepanel.getGame().getMenu().mousePressed(e);
			break;
		case PLAYING:
			gamepanel.getGame().getPlaying().mousePressed(e);
			break;
		case GAME_OVER:
			gamepanel.getGame().getDeathScreen().mousePressed(e);
			break;	
		default:
			break;

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (Gamestate.state) {
		case MENU:
			gamepanel.getGame().getMenu().mouseReleased(e);
			break;
		case PLAYING:
			gamepanel.getGame().getPlaying().mouseReleased(e);
			break;
		case GAME_OVER:
			gamepanel.getGame().getDeathScreen().mouseReleased(e);
			break;	
		default:
			break;

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
