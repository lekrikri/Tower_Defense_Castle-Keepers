package helpz;

public class Constants {

	public static class Direction {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class Enemies {

		public static final int BLOP = 2;
		public static final int CAT = 3;
		public static final int KNIGHT = 0;
		public static final int WOLF = 1;

		public static int GetReward(int enemyType) {
			switch (enemyType) {
			case BLOP:
				return 10;
			case CAT:
				return 15;
			case KNIGHT:
				return 25;
			case WOLF:
				return 100;
			}
			return 0;
		}

		public static float GetSpeed(int enemyType) {
			switch (enemyType) {
			case BLOP:
				return 0.8f;
			case CAT:
				return 1.5f;
			case KNIGHT:
				return 0.5f;
			case WOLF:
				return 0.8f;
			}
			return 0;
		}

		public static int GetStartHealth(int enemyType) {
			switch (enemyType) {
			case BLOP:
				return 200;
			case CAT:
				return 100;
			case KNIGHT:
				return 1000;
			case WOLF:
				return 3000;

			}
			return 0;
		}
	}

	public static class Tiles {
		public static final int WATER_TILE = 0;
		public static final int GRASS_TILE = 1;
		public static final int ROAD_TILE = 2;
	}

}
