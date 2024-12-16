package levels;

import static Utilz.Sprites.LoadingSprites.GetSprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import Entities.Player;
import Utilz.LoadSave;
import main.Game;

public class LevelManager {
	private Game game;
	private BufferedImage levelSprite,obstacles[][];
    private Player player;
    public static Rectangle2D.Float hitbox1;
	public static Rectangle2D.Float hitbox2;
    private int aniTick,aniSpeed=30,aniIndex;
    protected float x1,y1,x2,y2;	
	public LevelManager(Game game,Player player) {
		this.player=player;
		this.game = game;
		levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		createObstacles();
	}

	public void draw(Graphics g) {
		g.drawImage(levelSprite, 0-Player.xOffset, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		g.drawImage(levelSprite, Game.GAME_WIDTH-Player.xOffset, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
	    g.drawImage(obstacles[player.random][aniIndex],(int)(Game.GAME_WIDTH-Player.xOffset*2),player.Y1,128,128,null);
	    g.drawImage(obstacles[player.random1][aniIndex],(int)(Game.GAME_WIDTH-Player.xOffset*2),player.Y2,128,128,null);		
	}

	public void update() {
		UpdateAnimationTick();
	}
	
	public void UpdateAnimationTick() {

	    aniTick++;
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniIndex++;
			aniTick = 0;
			if (aniIndex >=8) {
				aniIndex = 0;
			}
		}
	}
	
	
	public void createObstacles() {
		BufferedImage rockSprite=LoadSave.GetSpriteAtlas(LoadSave.OBSTACLES);
		obstacles=new BufferedImage[8][8];
		for(int i=0;i<obstacles.length;i++) {
			for(int j=0;j<obstacles[i].length;j++) {
				obstacles[i][j]=rockSprite.getSubimage(j*128, i*128, 128, 128);
			}
		}
	}

}
