package Entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Utilz.LoadSave;
import gamestates.Playing;
import main.Game;

public class PlayerCharge {
	
	private BufferedImage charge;
	private Rectangle2D.Float hitbox;
	private static Rectangle2D.Float hitbox2;
	private int xPos,yPos1,yPos2,Constant;
	public static int Cons;
	public boolean Killme=false;
	private Enemies enemy1;
	public PlayerCharge(Enemies enemy1) {
		this.hitbox=Entity.getHitbox();
		this.enemy1=enemy1;
		Constant=(int)(Game.GAME_WIDTH-hitbox.width);
		xPos=(int)(hitbox.x+hitbox.width);
		yPos1=(int)hitbox.y-10;
		yPos2=(int)(hitbox.y+hitbox.width-46)-40;
		loadCharge();
		initHitbox();
	}
	
	private void loadCharge() {
		charge=LoadSave.GetSpriteAtlas(LoadSave.PLAYER_CHARGE);
	}
	
    private void initHitbox() {
    	hitbox2=new Rectangle2D.Float(xPos,yPos1+19,46,42);
    }
	
	public void render(Graphics g) {
		g.drawImage(charge,xPos,yPos1,46,46, null);
		g.drawImage(charge,xPos,yPos2,46,46, null);
//		g.drawRect((int) hitbox2.x, (int) hitbox2.y, (int) hitbox2.width, (int) hitbox2.height);
		hitbox2.x=xPos;
		xPos+=20;
		Cons=(int)(hitbox.y+hitbox.width+Constant);
		if(xPos>=Cons) {
			Playing.track=false;
			Killme=true;
		}
		if(enemy1.getHitbox().intersects(hitbox2)) {
		 Enemies.damaged=true;
		 Enemies.Enemy_health-=1;
		 Playing.track=false;
		 Killme=true;
		} 
		if(Enemies.Enemy_health==0) {
			Enemies.destroyed=true;
			Enemies.Enemy_health=5;
			Enemies.SCORE+=5;
		}
	}
	
	public static Rectangle2D.Float getHitbox(){
		return hitbox2;
	}
	
    private void update() {
    }

}
