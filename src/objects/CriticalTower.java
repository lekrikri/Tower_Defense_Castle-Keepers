package objects;

import java.util.Random;

import enemies.Enemy;

public class CriticalTower extends Tower {

    public CriticalTower() {
        super(1, "Critical", 1, 8, 100, 40, 100, 1);
    }

    @Override
    public void attack(Enemy enemy) {
        Random rand = new Random();
		if (rand.nextInt(10) == 0) {
		int crit = (getDamage() * 2);
		enemy.hurt(crit);
		}
        
    }

}
