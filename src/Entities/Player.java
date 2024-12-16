package Entities;

import static Utilz.Sprites.LoadingSprites.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static Utilz.HelpMethods.CanMoveHere;

import Utilz.LoadSave;
import gamestates.Gamestate;
import main.Game;

public class Player extends Entity {
	private BufferedImage[][] ani;
	private int aniTick = 0, aniIndex = 0, aniSpeed = 10;
	public static int playerAction = MOVE;
	private boolean left, right, up, down;
	private boolean moving = true, attacking =false;
	public static boolean damaged=false,destroyed=false;
	private float playerSpeed = 2.5f;
	public static int xOffset=0;
	public static int Player_Health=10;
	private int Offset_border=590;
	public boolean leftDel = true, rightDel = true, upDel = true, downDel = true;
	public int random,Y1;
    public int random1,Y2;
    public static boolean alive=true;

	public Player(float x, float y) {
		super(x, y);
		LoadImage();

	} 

	public void update() {
		updatePos();
		updateHitbox();
		UpdateAnimationTick();
		setAnimation();
	}

	public void render(Graphics g) {
		g.drawImage(ani[playerAction][aniIndex], (int) x, (int) y, 110, 110, null);
		drawHitbox(g);
	}

	public void UpdateAnimationTick() {

		aniTick++;
		if (aniTick >= aniSpeed) {
			aniIndex++;
			aniTick = 0;
			if (aniIndex >= GetSprite(playerAction)) {
				aniIndex = 0;
			}
		}
		
		if(playerAction==DAMAGED && aniIndex==9) {
			damaged=false;
			moving=true;
		}
		if(playerAction==DESTROYED && aniIndex==20) {
		    Gamestate.state=Gamestate.GAME_OVER;
			destroyed=false;
		}
		
	}

	private void updatePos() {
		moving = false;

		CanMoveHere(hitbox.x, hitbox.y, hitbox.width, hitbox.height, this);
		
		if (left && !right && alive && leftDel) {
			x -= playerSpeed; 
			moving = true;
		} else if (right && !left && rightDel && alive && x<Offset_border) {
			x += playerSpeed;
			moving = true;
		}
					

		if (up && !down && upDel && alive) {
			y -= playerSpeed;
			moving = true;
		} else if (down && !up && downDel && alive) {
			y += playerSpeed;
			moving = true;
		}
		
		xOffset+=2;

		resetOffset();
		resetDirDel();
	}
	
	public void resetOffset() {
		if(xOffset==Game.GAME_WIDTH) {
			xOffset=0;
			Y1=(int)(Math.random()*Game.GAME_HEIGHT/2);
			Y2=(int)(Math.random()*(Game.GAME_HEIGHT+128-Game.GAME_HEIGHT/2+128)+Game.GAME_HEIGHT/2+128);
			random=(int)Math.round((Math.random()*7.0));
		    random1=(int)Math.round((Math.random()*7.0));
		    
		}
	}

	private void resetDirDel() {
		leftDel = true;
		rightDel = true;
		upDel = true;
		downDel = true;
	}

	private void setAnimation() {
		int startAni = playerAction;

		if (moving)
			playerAction = MOVE;
		if (damaged) {
			playerAction = DAMAGED;
		}
		if(destroyed) {
			playerAction=DESTROYED;
		}
		
		if (startAni != playerAction) {
			resetAniTick();
		}
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	public void LoadImage() {
		BufferedImage image = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
		ani = new BufferedImage[10][21];
		for (int i = 0; i < ani.length; i++) {
			for (int j = 0; j < ani[i].length; j++) {
				ani[i][j] = image.getSubimage(j * 192, i * 194, 192, 194);
			}
		}

	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;

	}

	public void resetDirBoolean() {
		up = false;
		down = false;
		right = false;
		left = false;

	}

}
