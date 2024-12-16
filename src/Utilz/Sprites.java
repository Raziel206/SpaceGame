package Utilz;

import main.Game;

public class Sprites {

	public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 59;
			public static final int B_HEIGHT_DEFAULT = 25;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
		}

		public static class PauseButtons {
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
		}

		public static class URMButtons {
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);

		}

		public static class VolumeButtons {
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;

			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
		}

	}

	public static class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class LoadingSprites {
		public static final int DESTROYED = 0;
		public static final int ATTACK_1 = 1;
		public static final int ATTACK_2 = 2;
		public static final int BOOST = 3;
		public static final int DAMAGED = 4;
		public static final int MOVE = 6;
		public static final int ROCKETS = 7;
		public static final int SHIELD = 8;
		public static final int TURRET = 9;

		public static int GetSprite(int player_action) {

			switch (player_action) {
			case BOOST:
				return 5;
			case ATTACK_1:
			case ATTACK_2:
				return 4;
			case DESTROYED:
				return 21;
			case DAMAGED:
				return 10;
			case MOVE:
				return 6;
			case ROCKETS:
				return 7;
			case SHIELD:
				return 6;
			case TURRET:
				return 8;
			default:
				return 5;
			}
		}
	}

	public static class EnemySprite {
		public static final int ATTACK_1 = 3;
		public static final int DAMAGED = 0;
		public static final int DESTROYED= 1;
		public static final int MOVE = 2;

		public static int GetEnemySprite(int enemyAction) {
			switch (enemyAction) {
			case ATTACK_1:
				return 3;
			case DESTROYED:
				return 10;
			case DAMAGED:
				return 10;
			case MOVE:
				return 6;
			default:
				return 6;
			}
		}

	}

}
