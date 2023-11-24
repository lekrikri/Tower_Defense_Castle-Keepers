package enemies;

import static helpz.Constants.Enemies.*;

import managers.EnemyManager;

public class Blop extends Enemy {

	public Blop(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, BLOP,em);
	}

}
