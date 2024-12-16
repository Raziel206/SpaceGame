package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Entities.*;
import levels.Level;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;

public class Playing extends State implements Statemethods{
	private Player player;	
	private Enemies enemy1,enemy2;
	private PlayerCharge playerCharge;
	private LevelManager levelManager;
	public static boolean track=false;
	private PauseOverlay pauseOverlay;
	private boolean paused=false,p_charge=false,flag=true;

	public Playing(Game game) {
		super(game);
        initClasses();
        resetAll();
	}

	private void initClasses() {
		player = new Player(70, 300);
		enemy1=new Enemies(250,-1);
		levelManager = new LevelManager(game,player);
		pauseOverlay=new PauseOverlay(this);
	}

	@Override
	public void update() {
		if (!paused) {
			levelManager.update();
			player.update();
			enemy1.update();
			
		} else {
			pauseOverlay.update();
		}
	}	

	@Override
	public void draw(Graphics g) {
		levelManager.draw(g);
		if(!paused){
		player.render(g);
		enemy1.render(g);
		resetCharge(g);
		}	
		if (paused)
			pauseOverlay.draw(g);
	}
	
	public void resetCharge(Graphics g) {
		if(p_charge) { 
			if(playerCharge.Killme) {
				playerCharge=null;
			    flag=true;
			    p_charge=false;
			}
			else {
			    playerCharge.render(g);
			}	
		}	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(paused) 
			pauseOverlay.mousePressed(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(paused) 
			pauseOverlay.mouseReleased(e);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(paused) 
			pauseOverlay.mouseMoved(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseDragged(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			p_charge=true;
			if(flag) {
				playerCharge=new PlayerCharge(enemy1);
				track=true;
				flag=false;
			} 
			break;
		case KeyEvent.VK_W:
			player.setUp(true);
			break;
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_S:
			player.setDown(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_ESCAPE:
			paused = !paused;
			break;
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			player.setUp(false);
			break;
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_S:
			player.setDown(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setAttacking(false);
			break;	
		}
		
	}
	
	public Player getPlayer() {
		return player;
	}

	public void WindowFocusLost() {
		player.resetDirBoolean();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	public void unpauseGame() {
		paused=false;
		
	}

	public void resetAll() {
		Gamestate.state=Gamestate.MENU;
		Player.xOffset=0;
		Player.Player_Health=10;
		Player.alive=true;
		Player.playerAction=6;
		Player.destroyed=false;
		Enemies.SCORE=0;
		player.x=70;
		player.y=200;
		
	}

}
