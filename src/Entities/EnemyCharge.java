package Entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Utilz.LoadSave;

public class EnemyCharge {
	private Rectangle2D.Float hitbox;
	private int xPosi,yPosi;
	public static Rectangle2D.Float hitbox2;
	private BufferedImage charge;

	public EnemyCharge() {
		xPosi=(int)Enemies.getHitbox().x-46;
		yPosi=(int)Enemies.getHitbox().y-46;
		loadCharge();
		initHitbox();
	}
	
    private void loadCharge() {
    	charge=LoadSave.GetSpriteAtlas(LoadSave.ENEMY_CHARGE);
    }
    
    public void render(Graphics g) {
    	g.drawImage(charge,xPosi,yPosi,46,46, null);
    	g.drawImage(charge,xPosi,yPosi+20,46,46, null);
//    	g.drawRect((int) hitbox2.x, (int) hitbox2.y, (int) hitbox2.width, (int) hitbox2.height);
    	xPosi-=20;
    	hitbox2.x=xPosi;
    }
    
    private void initHitbox() {
    	hitbox2=new Rectangle2D.Float(xPosi,yPosi+20,46,27);
    }
    
    
}
