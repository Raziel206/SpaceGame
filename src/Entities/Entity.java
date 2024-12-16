package Entities;

import java.awt.geom.Rectangle2D;

import gamestates.Playing;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public abstract class Entity {

	public float x;
	public float y;
	protected static Rectangle2D.Float hitbox;

	public Entity(float x, float y) {
		this.x = x;
		this.y = y;
		initHitbox();
	}

	private void initHitbox() {
		hitbox = new Rectangle2D.Float(x, y + 25, 110, 60);

	}

	protected void updateHitbox() {
		hitbox.x = (int) x;
		hitbox.y = (int) y + 25;
	} 

	protected void drawHitbox(Graphics g) {
		g.setColor(Color.GRAY);
		g.setFont(new Font("Arial",Font.BOLD,28));
		g.drawString("Health: "+Player.Player_Health, 10, 30);
		g.drawString("Score: "+Enemies.SCORE, 10, 60);
		g.setFont(new Font("Arial",Font.PLAIN,14));
		g.setColor(Color.RED);
//		g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
		if (PlayerCharge.getHitbox() != null) {
			if (((PlayerCharge.Cons - PlayerCharge.getHitbox().x) / 100 > 1) && Playing.track) {
				g.drawString((int)((PlayerCharge.Cons - PlayerCharge.getHitbox().x) / 100 )+ "",
						(int) (hitbox.x + (hitbox.width / 2)) - 15, (int) hitbox.y - 5);
			}
		}
	}

	public static Rectangle2D.Float getHitbox() {
		return hitbox;
	}

}