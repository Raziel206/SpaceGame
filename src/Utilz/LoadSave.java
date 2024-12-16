package Utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {
	public static final String PLAYER_ATLAS = "spritesheet.png";
	public static final String LEVEL_ATLAS = "Outside_image.jpg";
	public static final String MENU_BUTTONS = "Menu.png";
	public static final String MENU_BACKGROUND = "Menu_background.png";
	public static final String PAUSE_BACKGROUND = "pause_menu.png";
	public static final String SOUND_BUTTONS = "sound_button.png";
	public static final String URM_BUTTONS = "urm_buttons.png";
	public static final String VOLUME_BUTTONS = "volume_buttons.png";
	public static final String OBSTACLES = "Rocks.png";
	public static final String ENEMY = "Enemy.png";
	public static final String PLAYER_CHARGE = "Charge_1.png";
	public static final String ENEMY_CHARGE = "Enemy_Attack.png";
	public static final String DEATH_BACKGROUND = "DeathScreen.png";
	public static final String ICON_IMAGE = "Icon.png";

	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage image = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		try {
			image = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
			   if(is!=null) 
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return image;
	}

}
