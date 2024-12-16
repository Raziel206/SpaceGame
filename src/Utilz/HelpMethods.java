package Utilz;

import Entities.Player;

import main.Game;

public class HelpMethods {
	private static Player player;

	public static void CanMoveHere(float x, float y, float width, float height, Player player) {
		HelpMethods.player = player;
		if (x <= 0)
			HelpMethods.player.leftDel = false;
		if (y == 0)
			HelpMethods.player.upDel = false;
		if (x + width == Game.GAME_WIDTH)
			HelpMethods.player.rightDel = false;
		if (y + height >= Game.GAME_HEIGHT)
			HelpMethods.player.downDel = false;
	}
}
