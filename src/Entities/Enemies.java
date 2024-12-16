package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import Utilz.LoadSave;
import main.Game;
import static Utilz.Sprites.EnemySprite.*;

public class Enemies {

	private BufferedImage Enemy[][];
	private int enemyAction = MOVE;
	public static int yPos, xPos, dir;
	private EnemyCharge enemyCharge;
	public static int Enemy_health = 5, SCORE = 0;
	private float Speed = 4f;

	private int aniTick = 0, aniSpeed = 10, aniIndex = 0;
	private boolean moving = true, attacking = false, still = true, tracker = false;
	public static boolean damaged = false, destroyed = false;
	private static Rectangle2D.Float hitbox;

	public Enemies(int yPos, int dir) {
		Enemies.dir = dir;
		Enemies.yPos = yPos;
		Enemies.xPos = (int) (Game.GAME_WIDTH * 1.5);
		loadEnemy();
		initHitbox();
	}

	private void activateEnemy() {
		yPos = 250;
		xPos = (int) (Game.GAME_WIDTH * 1.5);
		loadEnemy();
		initHitbox();
		still = true;
		tracker = true;
	}

	public void loadEnemy() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.ENEMY);
		Enemy = new BufferedImage[4][10];
		for (int i = 0; i < Enemy.length; i++)
			for (int j = 0; j < Enemy[i].length; j++)
				Enemy[i][j] = img.getSubimage(j * 192, i * 192, 192, 192);
	}

	public void render(Graphics g) {
		if ((xPos > 1150 && still) || tracker) {
			updatePos(g);
			drawHitbox(g);
			tracker = false;
			enemyCharge = new EnemyCharge();
		} else {
			enemyCharge.render(g);
			if (Enemy_health > 0)
				yPos += (int) (Speed * dir);
			g.drawImage(Enemy[enemyAction][aniIndex], 1150, yPos, 150, 150, null);
			hitbox.x = 1225;
			hitbox.y = yPos + 45;
			if (hitbox.y <= 0 || hitbox.y + 60 >= Game.GAME_HEIGHT)
				dir = hitbox.y <= 0 ? 1 : -1;
			drawHitbox(g);
			still = false;
			if (EnemyCharge.hitbox2.intersects(Entity.getHitbox())) {
				enemyCharge = null;
				enemyCharge = new EnemyCharge();
				Player.Player_Health--;
				Player.damaged=true;

			}
			if (EnemyCharge.hitbox2.x + 18 <= 0) {
				enemyCharge = null;
				enemyCharge = new EnemyCharge();
			}
			if(Player.Player_Health<=0) {
				Player.alive=false;
				Player.destroyed=true;
			}
				

		}
	}

	public void update() {
		UpdateAnimationTick();
		setAnimation();
	}

	public void UpdateAnimationTick() {

		aniTick++;
		if (aniTick >= aniSpeed) {
			aniIndex++;
			aniTick = 0;
			if (aniIndex >= GetEnemySprite(enemyAction)) {
				aniIndex = 0;
			}
			if (aniIndex == 9 && enemyAction == DAMAGED)
				damaged = false;
			if (aniIndex == 9 && enemyAction == DESTROYED) {
				destroyed = false;
				activateEnemy();
			}
		}
	}

	private void setAnimation() {
		int startAni = enemyAction;
		if (moving)
			enemyAction = MOVE;
		if (attacking) {
			enemyAction = ATTACK_1;
		}
		if (destroyed) {
			enemyAction = DESTROYED;
		}
		if (damaged) {
			enemyAction = DAMAGED;
		}
		if (startAni != enemyAction) {
			resetAniTick();
		}
	}

	private void initHitbox() {
		hitbox = new Rectangle2D.Float(xPos+10, yPos, 110, 60);
	}

	protected void drawHitbox(Graphics g) {
		g.setColor(Color.RED);
//		g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	public void updatePos(Graphics g) {
		g.drawImage(Enemy[enemyAction][aniIndex], (int) xPos, yPos, 150, 150, null);
		hitbox.x = xPos + 20;
		hitbox.y = yPos + 45;
		xPos -= 5;
		moving = true;
	}

	public static Rectangle2D.Float getHitbox() {
		return hitbox;
	}
}
